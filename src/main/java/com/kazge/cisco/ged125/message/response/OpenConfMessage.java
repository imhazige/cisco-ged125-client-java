package com.kazge.cisco.ged125.message.response;

public class OpenConfMessage extends ResponseMessage {
	private boolean useEventFeed;
	private boolean usePolledFeed;
	private boolean useCallRouting;
	private boolean useTimeSynch;
	private boolean useServiceControl;

	public void setUseEventFeed(boolean useEventFeed) {
		this.useEventFeed = useEventFeed;
	}

	public void setUsePolledFeed(boolean usePolledFeed) {
		this.usePolledFeed = usePolledFeed;
	}

	public void setUseCallRouting(boolean useCallRouting) {
		this.useCallRouting = useCallRouting;
	}

	public void setUseTimeSynch(boolean useTimeSynch) {
		this.useTimeSynch = useTimeSynch;
	}

	public void setUseServiceControl(boolean useServiceControl) {
		this.useServiceControl = useServiceControl;
	}

	public boolean isUseEventFeed() {
		return useEventFeed;
	}

	public boolean isUsePolledFeed() {
		return usePolledFeed;
	}

	public boolean isUseCallRouting() {
		return useCallRouting;
	}

	public boolean isUseTimeSynch() {
		return useTimeSynch;
	}

	public boolean isUseServiceControl() {
		return useServiceControl;
	}

	@Override
	public long getMessageType() {
		return 4;
	}
}
