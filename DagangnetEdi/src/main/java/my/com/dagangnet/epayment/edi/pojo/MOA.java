package my.com.dagangnet.epayment.edi.pojo;

public class MOA {

	private String amtTypQualifier;
	private String amt;
	private String currencyCode;

	public String getAmtTypQualifier() {
		return amtTypQualifier;
	}

	public void setAmtTypQualifier(String amtTypQualifier) {
		this.amtTypQualifier = amtTypQualifier;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
