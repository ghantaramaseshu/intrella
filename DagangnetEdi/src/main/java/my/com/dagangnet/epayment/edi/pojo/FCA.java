package my.com.dagangnet.epayment.edi.pojo;

public class FCA {

	private String code;
	private String branchNumber;
	private String codeLstQualifier;
	private String agencyCode;
	private String acctHolderNumber;
	private String currencyCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAcctHolderNumber() {
		return acctHolderNumber;
	}

	public void setAcctHolderNumber(String acctHolderNumber) {
		this.acctHolderNumber = acctHolderNumber;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCodeLstQualifier() {
		return codeLstQualifier;
	}

	public void setCodeLstQualifier(String codeLstQualifier) {
		this.codeLstQualifier = codeLstQualifier;
	}

	
}
