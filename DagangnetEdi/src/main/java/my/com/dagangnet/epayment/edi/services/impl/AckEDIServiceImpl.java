package my.com.dagangnet.epayment.edi.services.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import my.com.dagangnet.epayment.edi.helper.CusResHelper;
import my.com.dagangnet.epayment.edi.helper.AckEDIBodyHelper;
import my.com.dagangnet.epayment.edi.pojo.FspgRes;
import my.com.dagangnet.epayment.edi.services.AckEDIService;

import org.milyn.edi.unedifact.d93a.CUSRES.Cusres;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;

public class AckEDIServiceImpl implements AckEDIService {

	private AckEDIBodyHelper ediHelper = new AckEDIBodyHelper();
	private CusResHelper cusResHelper = new CusResHelper();

	public String doSuccessEdi(final FspgRes fspgRes) {
		String ediStr = "";
		UNEdifactInterchange41 edi = new UNEdifactInterchange41();

		List<UNEdifactMessage41> msgLst = new ArrayList<UNEdifactMessage41>();
		UNEdifactMessage41 message = new UNEdifactMessage41();

		UNB41 unb = ediHelper.getUnb(fspgRes.getUnb());
		UNH41 unh = ediHelper.getUnh(fspgRes.getUnh());
		Cusres cusres = cusResHelper.getSuccessCusres(fspgRes);
		UNT41 unt = ediHelper.getUnt(fspgRes.getUnt());
		UNZ41 unz = ediHelper.getUnz(fspgRes.getUnz());

		message.setMessageHeader(unh);
		message.setMessage(cusres);
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

	public String doErrorEdi(final FspgRes fspgRes) {
		String ediStr = "";
		UNEdifactInterchange41 edi = new UNEdifactInterchange41();

		List<UNEdifactMessage41> msgLst = new ArrayList<UNEdifactMessage41>();
		UNEdifactMessage41 message = new UNEdifactMessage41();

		UNB41 unb = ediHelper.getUnb(fspgRes.getUnb());
		UNH41 unh = ediHelper.getUnh(fspgRes.getUnh());
		Cusres cusres = cusResHelper.getErrorCusres(fspgRes);
		UNT41 unt = ediHelper.getUnt(fspgRes.getUnt());
		UNZ41 unz = ediHelper.getUnz(fspgRes.getUnz());

		message.setMessageHeader(unh);
		message.setMessage(cusres);
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

	public String doCancelPaymentEdi(final FspgRes fspgRes) {
		String ediStr = "";
		UNEdifactInterchange41 edi = new UNEdifactInterchange41();

		List<UNEdifactMessage41> msgLst = new ArrayList<UNEdifactMessage41>();
		UNEdifactMessage41 message = new UNEdifactMessage41();

		UNB41 unb = ediHelper.getUnb(fspgRes.getUnb());
		UNH41 unh = ediHelper.getUnh(fspgRes.getUnh());
		Cusres cusres = cusResHelper.getCancelPaymentCusres(fspgRes);
		UNT41 unt = ediHelper.getUnt(fspgRes.getUnt());
		UNZ41 unz = ediHelper.getUnz(fspgRes.getUnz());

		message.setMessageHeader(unh);
		message.setMessage(cusres);
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
