package my.com.dagangnet.epayment.edi.pojo;

public class UNH {

	private String msgRefNumber;
	private MsgIdentifier msgIdentifier;

	public String getMsgRefNumber() {
		return msgRefNumber;
	}

	public void setMsgRefNumber(String msgRefNumber) {
		this.msgRefNumber = msgRefNumber;
	}

	public MsgIdentifier getMsgIdentifier() {
		return msgIdentifier;
	}

	public void setMsgIdentifier(MsgIdentifier msgIdentifier) {
		this.msgIdentifier = msgIdentifier;
	}

}
