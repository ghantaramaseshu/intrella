package my.com.dagangnet.epayment.edi.test;

import my.com.dagangnet.epayment.edi.pojo.BGM;
import my.com.dagangnet.epayment.edi.pojo.DTM;
import my.com.dagangnet.epayment.edi.pojo.ERC;
import my.com.dagangnet.epayment.edi.pojo.ERP;
import my.com.dagangnet.epayment.edi.pojo.FTX;
import my.com.dagangnet.epayment.edi.pojo.FspgRes;
import my.com.dagangnet.epayment.edi.pojo.GIS;
import my.com.dagangnet.epayment.edi.pojo.MOA;
import my.com.dagangnet.epayment.edi.pojo.MsgIdentifier;
import my.com.dagangnet.epayment.edi.pojo.RFF;
import my.com.dagangnet.epayment.edi.pojo.TAX;
import my.com.dagangnet.epayment.edi.pojo.UNB;
import my.com.dagangnet.epayment.edi.pojo.UNH;
import my.com.dagangnet.epayment.edi.pojo.UNT;
import my.com.dagangnet.epayment.edi.pojo.UNZ;
import my.com.dagangnet.epayment.edi.services.impl.AckEDIServiceImpl;

public class AckTest {

	public static void main(String[] strings) {
		AckTest test = new AckTest();
		AckEDIServiceImpl service = new AckEDIServiceImpl();
		//service.doSuccessEdi(test.getFspgRes());
		//service.doCancelPaymentEdi(test.getFspgRes());
		service.doErrorEdi(test.getFspgRes());
		
	}

	private FspgRes getFspgRes() {
		FspgRes fspgRes = new FspgRes();
		fspgRes.setUnb(getUnb());
		fspgRes.setUnh(getUnh());
		fspgRes.setBgm(getBgm());
		fspgRes.setDtm(getDtm());
		fspgRes.setGis(getGis());
		fspgRes.setFtx(getFtx());
		fspgRes.setRef(getRff());
		fspgRes.setTax(getTax());
		fspgRes.setMoa(getMoa());
		fspgRes.setErp(getErp());
		fspgRes.setErc(getErc());
		fspgRes.setFtx2(getFtx2());
		fspgRes.setUnt(getUnt());
		fspgRes.setUnz(getUnz());
		return fspgRes;
	}

	private UNB getUnb() {
		UNB unb = new UNB();
		unb.setSyntaxIdentifier("UNOA");
		unb.setSyntaxVersionNumber("2");
		unb.setInterChangeSender("9556448012345");
		unb.setInterChangeRecipient("9556448012346");
		unb.setDateOfPreparation("140913");
		unb.setTimeOfPreparation("0615");
		unb.setInterChangeControl("14091306150001");
		unb.setRecipientPassword("RECEIPIENT_PWD");
		unb.setApplicationReferance("FSPGACK");
		unb.setTestIndicator("9");
		return unb;
	}

	private UNH getUnh() {
		UNH unh = new UNH();
		unh.setMsgRefNumber("000002");

		MsgIdentifier msgIdentifier = new MsgIdentifier();
		msgIdentifier.setTypeIdentifier("CUSRES");
		msgIdentifier.setVersionNumebr("1");
		msgIdentifier.setReleaseNumber("971");
		msgIdentifier.setAgencey("UN");
		msgIdentifier.setAssignCode("MYC010");

		unh.setMsgIdentifier(msgIdentifier);
		return unh;
	}

	private BGM getBgm() {
		BGM bgm = new BGM();
		bgm.setMsgCode("312");
		bgm.setMsgName("ACKNOWLEDGE RESPONSE");
		bgm.setMsgNumber("PBBBF031814012215481EFT1360");
		bgm.setMsgFunCode("9");
		return bgm;
	}

	private DTM getDtm() {
		DTM dtm = new DTM();
		dtm.setDtQualifier("137");
		dtm.setDtPeriod("201401221554");
		dtm.setDtFrmtQualifier("203");
		return dtm;
	}

	private GIS getGis() {
		GIS gis = new GIS();
		gis.setIndicatorCode("00");
		gis.setLstQualifier("AAD");
		return gis;
	}

	private FTX getFtx() {
		FTX ftx = new FTX();
		ftx.setSubjQualifier("AAP");
		ftx.setTextLiteral("RECEIVED WITH NO ERROR");
		return ftx;
	}
	
	private FTX getFtx2() {
		FTX ftx = new FTX();
		ftx.setSubjQualifier("AAO");
		ftx.setTextLiteral("TRANSACTION LIMIT EXCEDED");
		return ftx;
	}
	
	private RFF getRff() {
		RFF rff = new RFF();
		rff.setRefQualifier("ABE");
		rff.setRefNumber("PBBBF031814012215481EFT1360 0001");
		return rff;
	}
	
	private TAX getTax(){
		TAX tax = new TAX();
		tax.setFeeFunQualifier("4");
		return tax;
	}
	private MOA getMoa(){
		MOA moa = new MOA();
		moa.setAmtTypQualifier("9");
		moa.setAmt("10000");
		moa.setCurrencyCode("MYR");
		return moa;
	}
	
	private ERP getErp() {
		ERP erp =new ERP();
		erp.setMsgCode("1");
		return erp;
	}
	
	
	private ERC getErc() {
		ERC erc=new ERC();
		erc.setAppErrCode("48");
		erc.setCodeLstQualifier("FSP");
		return erc;
	}
	
	private UNT getUnt() {
		UNT unt = new UNT();
		unt.setMsgSgmtCount(7);
		unt.setMsgRefNumber("1");
		return unt;
	}

	private UNZ getUnz() {
		UNZ unz = new UNZ();
		unz.setControlCount(1);
		unz.setControlRef("14012215540731");
		return unz;
	}
}
