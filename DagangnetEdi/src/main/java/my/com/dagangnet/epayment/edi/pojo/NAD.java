package my.com.dagangnet.epayment.edi.pojo;

public class NAD {

	private String partyQualifier;
	private String partyIdentification;
	private AddressObj addressObj;

	public String getPartyQualifier() {
		return partyQualifier;
	}

	public void setPartyQualifier(String partyQualifier) {
		this.partyQualifier = partyQualifier;
	}

	public String getPartyIdentification() {
		return partyIdentification;
	}

	public void setPartyIdentification(String partyIdentification) {
		this.partyIdentification = partyIdentification;
	}

	public AddressObj getAddressObj() {
		return addressObj;
	}

	public void setAddressObj(AddressObj addressObj) {
		this.addressObj = addressObj;
	}

}
