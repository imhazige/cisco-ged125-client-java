package com.kazge.cisco.ged125.server;

import java.util.Map;

import com.kazge.cisco.ged125.message.socket.Ged125MessageChannel;

public interface Ged125ServerSessionManager {
	void remove(String id);

	Ged125ServerSession create(Ged125MessageChannel channel);

	Map<String, Ged125ServerSession> getAll();

	Ged125ServerSession getById(String id);
}
