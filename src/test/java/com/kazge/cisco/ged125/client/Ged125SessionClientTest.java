package com.kazge.cisco.ged125.client;

import java.io.IOException;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.request.HeartBeatReqMessage;
import com.kazge.cisco.ged125.message.request.servicecontrol.ConnectMessage;
import com.kazge.cisco.ged125.message.request.servicecontrol.InitServiceCtrlReqMessage;
import com.kazge.cisco.ged125.message.request.servicecontrol.RunScriptReqMessage;
import com.kazge.cisco.ged125.message.response.OpenConfMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.NewCallMessage;
import com.com.kazge.common.midware.common.JacksonUtils;
import com.com.kazge.common.midware.common.Log;
import com.com.kazge.common.midware.common.SimpleBaseTest;

public class Ged125SessionClientTest extends SimpleBaseTest {
	private Ged125SessionClient createClient() {
		Ged125SessionClient client = null;
		 client = new Ged125SessionClient("localhost", 5001, 16, 120);
		// client = new Ged125SessionClient("54.201.116.21", 5000, 16, 120);
//		client = new Ged125SessionClient("69.85.93.32", 5100, 16, 120);
//		client = new Ged125SessionClient(" 10.2.74.115", 5000, 16, 120);

		return client;
	}

	public void testRunClient() {
		int count = 1;
		for (int i = 0; i < count; i++) {
			oneRun();
		}

		try {
			Log.info("press any key to end.");
			System.in.read();
		} catch (IOException e) {
		}
	}

	private void oneRun() {
		// note, the thread should wait the socket to sent the data, otherwise,
		// the server will get all data with value 0.
		Thread th = new Thread(new Runnable() {
			public void run() {
				try {
					final Ged125SessionClient client = createClient();
					client.open(new Ged125ClientMessageListener() {

						@Override
						public void onMessage(Ged125Message message) {
							Log.debug("resp message:" + JacksonUtils.toJsonString(message));
							if (message instanceof NewCallMessage){
								ConnectMessage cmsg = new ConnectMessage();
								cmsg.setLabelType(MessageEnum.LABEL_TYPE_NORMAL);
								cmsg.setLabel("testclient:" + System.currentTimeMillis());
								cmsg.getServiceControlHeader().setDialogueID(((NewCallMessage) message).getServiceControlHeader().getDialogueID());
								client.sendMessage(cmsg);
							}
						}

						@Override
						public void onOpen(OpenConfMessage message) {
							Log.debug("on open message:" + JacksonUtils.toJsonString(message));

							Ged125Message reqMsg = null;

							reqMsg = new HeartBeatReqMessage();
							client.sendMessage(reqMsg);

							InitServiceCtrlReqMessage smsg = new InitServiceCtrlReqMessage();
							smsg.getServiceControlHeader().setDialogueID(1);
							smsg.getServiceControlHeader().setSendSeqNo(2);
							client.sendMessage(smsg);

							RunScriptReqMessage cmsg = new RunScriptReqMessage();
							cmsg.getServiceControlHeader().setDialogueID(2);
							cmsg.setCallVariable1("EWTV");
							cmsg.setCallVariable5("5");
							cmsg.setCallVariable6("6");
							cmsg.setCallVariable8("8");
							cmsg.setCallVariable10("10");
							client.sendMessage(cmsg);
						}

						@Override
						public void onClose() {
							Log.debug("on close message.");
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					fail();
				}
			}
		});

		th.start();
	}
}
