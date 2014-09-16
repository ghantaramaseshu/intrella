package my.com.dagangnet.epayment.edi.services.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import my.com.dagangnet.epayment.edi.helper.DebmulResHelper;
import my.com.dagangnet.epayment.edi.helper.AckEDIBodyHelper;
import my.com.dagangnet.epayment.edi.pojo.DebmulRequest;
import my.com.dagangnet.epayment.edi.services.DebmulEDIService;

import org.milyn.edi.unedifact.d05b.DEBMUL.Debmul;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;

public class DebmulEDIServiceImpl implements DebmulEDIService{

	private AckEDIBodyHelper ediHelper = new AckEDIBodyHelper();
	private DebmulResHelper debmulResHelper = new DebmulResHelper();
	
	public String generateDeubmulMsg(final DebmulRequest debmulRes) {
		
		String ediStr = "";
		UNEdifactInterchange41 edi = new UNEdifactInterchange41();

		List<UNEdifactMessage41> msgLst = new ArrayList<UNEdifactMessage41>();
		UNEdifactMessage41 message = new UNEdifactMessage41();
		
		UNB41 unb = ediHelper.getUnb(debmulRes.getUnb());
		UNH41 unh = ediHelper.getUnh(debmulRes.getUnh());
		
		Debmul debmul =debmulResHelper.getDebmulMsg(debmulRes);
		
		UNT41 unt = ediHelper.getUnt(debmulRes.getUnt());
		UNZ41 unz = ediHelper.getUnz(debmulRes.getUnz());
		
		message.setMessageHeader(unh);
		message.setMessage(debmul);
		message.setMessageTrailer(unt);
		msgLst.add(message);

		edi.setInterchangeHeader(unb);
		edi.setMessages(msgLst);
		edi.setInterchangeTrailer(unz);

		Writer writer = new StringWriter();
		
		try {
			edi.write(writer, null);
			ediStr = writer.toString();
			System.out.println(ediStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ediStr;
	}

}
