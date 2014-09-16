package my.com.dagangnet.epayment.edi.pojo;

public class UNB {
	private String syntaxIdentifier;
	private String syntaxVersionNumber;
	private String interChangeSender;
	private String interChangeRecipient;
	private String dateOfPreparation;
	private String timeOfPreparation;
	private String interChangeControl;
	private String recipientPassword;
	private String applicationReferance;
	private String testIndicator;

	public String getSyntaxIdentifier() {
		return syntaxIdentifier;
	}

	public void setSyntaxIdentifier(String syntaxIdentifier) {
		this.syntaxIdentifier = syntaxIdentifier;
	}

	public String getSyntaxVersionNumber() {
		return syntaxVersionNumber;
	}

	public void setSyntaxVersionNumber(String syntaxVersionNumber) {
		this.syntaxVersionNumber = syntaxVersionNumber;
	}

	public String getInterChangeSender() {
		return interChangeSender;
	}

	public void setInterChangeSender(String interChangeSender) {
		this.interChangeSender = interChangeSender;
	}

	public String getInterChangeRecipient() {
		return interChangeRecipient;
	}

	public void setInterChangeRecipient(String interChangeRecipient) {
		this.interChangeRecipient = interChangeRecipient;
	}

	public String getDateOfPreparation() {
		return dateOfPreparation;
	}

	public void setDateOfPreparation(String dateOfPreparation) {
		this.dateOfPreparation = dateOfPreparation;
	}

	public String getTimeOfPreparation() {
		return timeOfPreparation;
	}

	public void setTimeOfPreparation(String timeOfPreparation) {
		this.timeOfPreparation = timeOfPreparation;
	}

	public String getInterChangeControl() {
		return interChangeControl;
	}

	public void setInterChangeControl(String interChangeControl) {
		this.interChangeControl = interChangeControl;
	}

	public String getRecipientPassword() {
		return recipientPassword;
	}

	public void setRecipientPassword(String recipientPassword) {
		this.recipientPassword = recipientPassword;
	}

	public String getApplicationReferance() {
		return applicationReferance;
	}

	public void setApplicationReferance(String applicationReferance) {
		this.applicationReferance = applicationReferance;
	}

	public String getTestIndicator() {
		return testIndicator;
	}

	public void setTestIndicator(String testIndicator) {
		this.testIndicator = testIndicator;
	}

}
