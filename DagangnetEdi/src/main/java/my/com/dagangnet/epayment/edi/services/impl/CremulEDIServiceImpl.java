package my.com.dagangnet.epayment.edi.services.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.milyn.edi.unedifact.d05b.CREMUL.Cremul;
import org.milyn.edi.unedifact.d93a.CUSRES.Cusres;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;

import my.com.dagangnet.epayment.edi.helper.CremulHelper;
import my.com.dagangnet.epayment.edi.pojo.CremulRequest;
import my.com.dagangnet.epayment.edi.services.CremulEDIService;

public class CremulEDIServiceImpl implements CremulEDIService{
	private CremulHelper ediHelper = new CremulHelper();
	
	public String doEDI(final CremulRequest cremulReq) {
		String ediStr = "";

		UNB41 unb = ediHelper.getUnb(cremulReq.getUnb());
		UNH41 unh = ediHelper.getUnh(cremulReq.getUnh());	
		Cremul cremul = ediHelper.getCremul(cremulReq);
		UNT41 unt = ediHelper.getUnt(cremulReq.getUnt());
		UNZ41 unz = ediHelper.getUnz(cremulReq.getUnz());
		
		
		List<UNEdifactMessage41> msgLst = new ArrayList<UNEdifactMessage41>();
		UNEdifactMessage41 message = new UNEdifactMessage41();
		
		message.setMessageHeader(unh);
		message.setMessage(cremul);
		message.setMessageTrailer(unt);
		msgLst.add(message);
		
		UNEdifactInterchange41 edi = new UNEdifactInterchange41();
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
