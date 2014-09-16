package my.com.dagangnet.epayment.edi.pojo;

public class BUS {

	private String funQualifier;
	private String funCode;
	private String txnCode;

	public String getFunQualifier() {
		return funQualifier;
	}

	public void setFunQualifier(String funQualifier) {
		this.funQualifier = funQualifier;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

}
