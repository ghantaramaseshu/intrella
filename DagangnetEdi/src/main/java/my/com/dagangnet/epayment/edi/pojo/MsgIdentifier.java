package my.com.dagangnet.epayment.edi.pojo;

public class MsgIdentifier {
	private String typeIdentifier;
	private String versionNumebr;
	private String releaseNumber;
	private String agencey;
	private String assignCode;

	public String getTypeIdentifier() {
		return typeIdentifier;
	}

	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier = typeIdentifier;
	}

	public String getVersionNumebr() {
		return versionNumebr;
	}

	public void setVersionNumebr(String versionNumebr) {
		this.versionNumebr = versionNumebr;
	}

	public String getReleaseNumber() {
		return releaseNumber;
	}

	public void setReleaseNumber(String releaseNumber) {
		this.releaseNumber = releaseNumber;
	}

	public String getAgencey() {
		return agencey;
	}

	public void setAgencey(String agencey) {
		this.agencey = agencey;
	}

	public String getAssignCode() {
		return assignCode;
	}

	public void setAssignCode(String assignCode) {
		this.assignCode = assignCode;
	}

}
