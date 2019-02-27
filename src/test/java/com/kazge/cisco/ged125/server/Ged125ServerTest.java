package com.kazge.cisco.ged125.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.kazge.cisco.ged125.message.EccName;
import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.request.CloseReqMessage;
import com.kazge.cisco.ged125.message.request.HeartBeatReqMessage;
import com.kazge.cisco.ged125.message.request.OpenReqMessage;
import com.kazge.cisco.ged125.message.request.servicecontrol.InitServiceCtrlReqMessage;
import com.kazge.cisco.ged125.message.response.CloseConfMessage;
import com.kazge.cisco.ged125.message.response.HeartBeatConfMessage;
import com.kazge.cisco.ged125.message.response.OpenConfMessage;
import com.kazge.cisco.ged125.message.response.RegisterVariablesMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.InitServiceCtrlConfMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.InitServiceCtrlDataMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.InitServiceCtrlEndMessage;
import com.kazge.cisco.ged125.message.socket.Ged125MessageChannel;
import com.com.kazge.common.midware.common.BitUtils;
import com.com.kazge.common.midware.common.ExceptionUtils;
import com.com.kazge.common.midware.common.JacksonUtils;
import com.com.kazge.common.midware.common.Log;
import com.com.kazge.common.midware.common.SimpleBaseTest;

public class Ged125ServerTest extends SimpleBaseTest {
	public void testRunServer() {
		Ged125Server server = new Ged125Server(new Ged125ServerSessionManager() {

			@Override
			public void remove(String id) {

			}

			@Override
			public Ged125ServerSession getById(String id) {
				return null;
			}

			@Override
			public Map<String, Ged125ServerSession> getAll() {
				return null;
			}

			@Override
			public Ged125ServerSession create(final Ged125MessageChannel channel) {
				return new Ged125ServerSession() {

					private Map<Long, String> eccTagMap;
					private long heartBeat;

					@Override
					public void sendMessage(Ged125Message msg) {
						if (null == channel) {
							return;
						}
						synchronized (channel) {
							if (channel.isClosed()) {
								return;
							}
							channel.sendMessage(msg);
							if (!(msg instanceof HeartBeatConfMessage)) {
								Log.info("VRU sent msg %s", JacksonUtils.toJsonString(msg));
							}
						}

					}

					private void updateHeartBeat() {
						heartBeat = System.currentTimeMillis();
					}

					@Override
					public void onMessage(Ged125Message inmsg) {
						updateHeartBeat();
						if (!(inmsg instanceof HeartBeatReqMessage)) {
							Log.info("PG got message:%s", JacksonUtils.toJsonString(inmsg));
						}
						if (inmsg instanceof OpenReqMessage) {
							OpenConfMessage outmsg = new OpenConfMessage();
							outmsg.setInvokeId(inmsg.getInvokeId());
							outmsg.setUseServiceControl(true);
							sendMessage(outmsg);
						} else if (inmsg instanceof InitServiceCtrlReqMessage) {
							InitServiceCtrlReqMessage serviceCtrlReqMessage = (InitServiceCtrlReqMessage) inmsg;
							long servicereqInvokeId = serviceCtrlReqMessage.getInvokeId();

							InitServiceCtrlConfMessage serviceCtrlConfmsg = new InitServiceCtrlConfMessage();
							serviceCtrlConfmsg.setInvokeId(servicereqInvokeId);
							serviceCtrlConfmsg.getServiceControlHeader().setDialogueID(MessageEnum.NULL_DATA_ELEMENT);
							serviceCtrlConfmsg.getServiceControlHeader().setSendSeqNo(MessageEnum.NULL_DATA_ELEMENT);

							sendMessage(serviceCtrlConfmsg);

							InitServiceCtrlDataMessage serviceCtrlDataMessage = new InitServiceCtrlDataMessage();
							serviceCtrlDataMessage.setInvokeId(servicereqInvokeId);
							serviceCtrlDataMessage.getServiceControlHeader()
									.setDialogueID(MessageEnum.NULL_DATA_ELEMENT);
							serviceCtrlDataMessage.getServiceControlHeader()
									.setSendSeqNo(MessageEnum.NULL_DATA_ELEMENT);

							// TODO features
							serviceCtrlDataMessage.setServiceFeatures(BitUtils.set(MessageEnum.FEATURE_RUN_SCRIPT,
									MessageEnum.FEATURE_CONNECT, MessageEnum.FEATURE_RELEASE));
							sendMessage(serviceCtrlDataMessage);

							InitServiceCtrlEndMessage initEndMsg = new InitServiceCtrlEndMessage();

							initEndMsg.getServiceControlHeader().setDialogueID(MessageEnum.NULL_DATA_ELEMENT);
							initEndMsg.getServiceControlHeader().setSendSeqNo(MessageEnum.NULL_DATA_ELEMENT);
							initEndMsg.setInvokeId(servicereqInvokeId);

							sendMessage(initEndMsg);

							sendSettingEccVarsMessage();
						} else if (inmsg instanceof HeartBeatReqMessage) {
							HeartBeatConfMessage outmsg = new HeartBeatConfMessage();
							outmsg.setInvokeId(inmsg.getInvokeId());
							sendMessage(outmsg);
						} else if (inmsg instanceof CloseReqMessage) {
							CloseConfMessage outmsg = new CloseConfMessage();
							outmsg.setInvokeId(inmsg.getInvokeId());
							try {
								sendMessage(outmsg);
							} catch (Exception e) {
								// do nothing
								Log.info("send CloseReqMessage fail, maybe the client have been closed. "
										+ e.getMessage());
							}
							Log.info("get125 server session close as got CloseReqMessage.");
							channel.close();
						}
					}

					private void sendSettingEccVarsMessage() {
						Map<String, Object> sets = new HashMap<String, Object>();
						
						Map<String, Object> objm = new HashMap<String, Object>();
						
						objm.put("tag", 200);
						objm.put("enable", true);
						sets.put("user.name", objm);
						
						objm = new HashMap<String, Object>();
						objm.put("tag", 201);
						objm.put("enable", false);
						sets.put("user.fd", objm);

						// ECC vars
						RegisterVariablesMessage vmsg = new RegisterVariablesMessage();

						Set<Entry<String, Object>> en = sets.entrySet();

						for (Entry<String, Object> entry : en) {
							String name = entry.getKey();
							if (!name.startsWith("user")) {
								Log.warn("ignore ecc without prefix 'user' " + name);
								continue;
							}

							@SuppressWarnings("unchecked")
							Map<String, Object> map = (Map<String, Object>) entry.getValue();

							if (null == map) {
								continue;
							}

							Boolean enable = (Boolean) map.get("enable");

							if (null != enable && !enable) {
								continue;
							}

							EccName scc = new EccName();
							scc.setName(name);
							scc.setTag((Integer) map.get("tag"));

							if (scc.isEmpty()) {
								continue;
							}

							vmsg.getEccTags().put(scc.getName(), scc);
							getEccTagMap().put(scc.getTag(), scc.getName());
						}

						sendMessage(vmsg);
					}
					
					private Map<Long, String> getEccTagMap() {
						if (null == eccTagMap) {
							eccTagMap = new HashMap<Long, String>();
						}
						return eccTagMap;
					}

					@Override
					public String getId() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void onChannelException(Throwable cause) {
						Log.error(ExceptionUtils.silence(cause));
						
					}
				};
			}
		}, "test", 5000, 1000, 1000, 500);

		server.start();
	}
}
