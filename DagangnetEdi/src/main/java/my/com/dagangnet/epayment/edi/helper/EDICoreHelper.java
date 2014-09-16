package my.com.dagangnet.epayment.edi.helper;

import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;
import org.milyn.smooks.edi.unedifact.model.r41.types.DateTime;
import org.milyn.smooks.edi.unedifact.model.r41.types.MessageIdentifier;
import org.milyn.smooks.edi.unedifact.model.r41.types.Party;
import org.milyn.smooks.edi.unedifact.model.r41.types.Ref;
import org.milyn.smooks.edi.unedifact.model.r41.types.SyntaxIdentifier;

import my.com.dagangnet.epayment.edi.pojo.UNB;
import my.com.dagangnet.epayment.edi.pojo.UNH;
import my.com.dagangnet.epayment.edi.pojo.UNT;
import my.com.dagangnet.epayment.edi.pojo.UNZ;

public class EDICoreHelper {

	public UNB41 getUnb(UNB unb) {
		UNB41 ediUnb = new UNB41();

		Party senderParty = new Party();
		senderParty.setId(unb.getInterChangeSender());
		ediUnb.setSender(senderParty);

		Party recipientParty = new Party();
		recipientParty.setId(unb.getInterChangeRecipient());
		ediUnb.setRecipient(recipientParty);

		DateTime messageDate = new DateTime();
		messageDate.setDate(unb.getDateOfPreparation());
		messageDate.setTime(unb.getTimeOfPreparation());
		ediUnb.setDate(messageDate);

		Ref pwd = new Ref();
		pwd.setRef(unb.getRecipientPassword());
		ediUnb.setRecipientRef(pwd);

		ediUnb.setApplicationRef(unb.getApplicationReferance());
		// unb41.setProcessingPriorityCode("Password");
		ediUnb.setTestIndicator(unb.getTestIndicator());

		ediUnb.setControlRef(unb.getInterChangeControl());

		SyntaxIdentifier syntaxIdentifier = new SyntaxIdentifier();
		syntaxIdentifier.setId(unb.getSyntaxIdentifier());
		syntaxIdentifier.setVersionNum(unb.getSyntaxVersionNumber());
		ediUnb.setSyntaxIdentifier(syntaxIdentifier);

		return ediUnb;
	}
	
	public UNH41 getUnh(UNH unh){
		UNH41 ediUnh = new UNH41();
		ediUnh.setMessageRefNum(unh.getMsgRefNumber());
		MessageIdentifier mi = new MessageIdentifier();
		mi.setId(unh.getMsgIdentifier().getTypeIdentifier());
		mi.setVersionNum(unh.getMsgIdentifier().getVersionNumebr());
		mi.setReleaseNum(unh.getMsgIdentifier().getReleaseNumber());
		mi.setControllingAgencyCode(unh.getMsgIdentifier().getAgencey());
		mi.setAssociationAssignedCode(unh.getMsgIdentifier().getAssignCode());
		ediUnh.setMessageIdentifier(mi);
		return ediUnh;
	}
	
	public UNT41 getUnt(UNT unt) {
		UNT41 ediUnt = new UNT41();
		ediUnt.setMessageRefNum(unt.getMsgRefNumber());
		ediUnt.setSegmentCount(unt.getMsgSgmtCount());
		return ediUnt;
	}
	
	public UNZ41 getUnz(UNZ unz){
		UNZ41 ediUnz =new UNZ41();
		ediUnz.setControlCount(unz.getControlCount());
		ediUnz.setControlRef(unz.getControlRef());
        return ediUnz;
	}
}
