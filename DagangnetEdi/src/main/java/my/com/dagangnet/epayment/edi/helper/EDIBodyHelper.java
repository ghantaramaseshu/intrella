package my.com.dagangnet.epayment.edi.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import my.com.dagangnet.epayment.edi.pojo.BGM;
import my.com.dagangnet.epayment.edi.pojo.DTM;
import my.com.dagangnet.epayment.edi.pojo.ERC;
import my.com.dagangnet.epayment.edi.pojo.ERP;
import my.com.dagangnet.epayment.edi.pojo.FTX;
import my.com.dagangnet.epayment.edi.pojo.GIS;
import my.com.dagangnet.epayment.edi.pojo.MOA;
import my.com.dagangnet.epayment.edi.pojo.RFF;
import my.com.dagangnet.epayment.edi.pojo.TAX;

import org.milyn.edi.unedifact.d93a.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d93a.common.DTMDateTimePeriod;
import org.milyn.edi.unedifact.d93a.common.ERCApplicationErrorInformation;
import org.milyn.edi.unedifact.d93a.common.ERPErrorPointDetails;
import org.milyn.edi.unedifact.d93a.common.FTXFreeText;
import org.milyn.edi.unedifact.d93a.common.GISGeneralIndicator;
import org.milyn.edi.unedifact.d93a.common.MOAMonetaryAmount;
import org.milyn.edi.unedifact.d93a.common.RFFReference;
import org.milyn.edi.unedifact.d93a.common.TAXDutyTaxFeeDetails;
import org.milyn.edi.unedifact.d93a.common.field.C002DocumentMessageName;
import org.milyn.edi.unedifact.d93a.common.field.C108TextLiteral;
import org.milyn.edi.unedifact.d93a.common.field.C506Reference;
import org.milyn.edi.unedifact.d93a.common.field.C507DateTimePeriod;
import org.milyn.edi.unedifact.d93a.common.field.C516MonetaryAmount;
import org.milyn.edi.unedifact.d93a.common.field.C529ProcessingIndicator;
import org.milyn.edi.unedifact.d93a.common.field.C701ErrorPointDetails;
import org.milyn.edi.unedifact.d93a.common.field.C901ApplicationErrorDetail;

public class EDIBodyHelper extends EDICoreHelper {

	public BGMBeginningOfMessage getBgm(BGM bgm) {
		BGMBeginningOfMessage ediBgm = new BGMBeginningOfMessage();

		C002DocumentMessageName cc = new C002DocumentMessageName();
		cc.setE1000DocumentMessageName(bgm.getMsgName());
		cc.setE1001DocumentMessageNameCoded(bgm.getMsgCode());

		ediBgm.setC002DocumentMessageName(cc);
		ediBgm.setE1004DocumentMessageNumber(bgm.getMsgNumber());
		ediBgm.setE1225MessageFunctionCoded(bgm.getMsgFunCode());
		return ediBgm;
	}

	public DTMDateTimePeriod getDtm(DTM dtm) {
		DTMDateTimePeriod ediDtm = new DTMDateTimePeriod();
		C507DateTimePeriod cdt = new C507DateTimePeriod();
		cdt.setE2005DateTimePeriodQualifier(dtm.getDtQualifier());
		cdt.setE2380DateTimePeriod(dtm.getDtPeriod());
		cdt.setE2379DateTimePeriodFormatQualifier(dtm.getDtFrmtQualifier());
		ediDtm.setC507DateTimePeriod(cdt);
		return ediDtm;
	}

	public List<DTMDateTimePeriod> getDtm(List<DTM> dtmLst) {
		List<DTMDateTimePeriod> ediDtmLst = new ArrayList<DTMDateTimePeriod>();
		for (DTM dtm : dtmLst) {
			ediDtmLst.add(getDtm(dtm));
		}
		return ediDtmLst;
	}

	public List<DTMDateTimePeriod> getDtmLst(DTM dtm) {
		List<DTM> dtmLst = new ArrayList<DTM>();
		dtmLst.add(dtm);
		return getDtm(dtmLst);
	}

	public FTXFreeText getFtx(FTX ftx) {
		FTXFreeText ediFtx = new FTXFreeText();

		C108TextLiteral ctl = new C108TextLiteral();
		ctl.setE44401FreeText(ftx.getTextLiteral());

		ediFtx.setE4451TextSubjectQualifier(ftx.getSubjQualifier());
		ediFtx.setC108TextLiteral(ctl);
		return ediFtx;
	}

	public List<FTXFreeText> getFtx(List<FTX> ftxLst) {
		List<FTXFreeText> ediFtxLst = new ArrayList<FTXFreeText>();
		for (FTX ftx : ftxLst) {
			ediFtxLst.add(getFtx(ftx));
		}
		return ediFtxLst;
	}

	public List<FTXFreeText> getFtxLst(FTX ftx) {
		List<FTX> ftxLst = new ArrayList<FTX>();
		ftxLst.add(ftx);
		return getFtx(ftxLst);
	}

	public GISGeneralIndicator getGis(GIS gis) {
		GISGeneralIndicator ediGis = new GISGeneralIndicator();
		C529ProcessingIndicator cpi = new C529ProcessingIndicator();
		cpi.setE7365ProcessingIndicatorCoded(gis.getIndicatorCode());
		cpi.setE7187ProcessTypeIdentification(gis.getLstQualifier());
		ediGis.setC529ProcessingIndicator(cpi);
		return ediGis;
	}

	public List<GISGeneralIndicator> getGis(List<GIS> gisLst) {
		List<GISGeneralIndicator> ediGisLst = new ArrayList<GISGeneralIndicator>();
		for (GIS gis : gisLst) {
			ediGisLst.add(getGis(gis));
		}
		return ediGisLst;
	}

	public List<GISGeneralIndicator> getGisLst(GIS gis) {
		List<GIS> gisLst = new ArrayList<GIS>();
		gisLst.add(gis);
		return getGis(gisLst);
	}

	public RFFReference getRff(RFF rff) {
		RFFReference ediRff = new RFFReference();
		C506Reference crf = new C506Reference();
		crf.setE1153ReferenceQualifier(rff.getRefQualifier());
		crf.setE1154ReferenceNumber(rff.getRefNumber());
		ediRff.setC506Reference(crf);
		return ediRff;

	}

	public TAXDutyTaxFeeDetails getTax(TAX tax) {
		TAXDutyTaxFeeDetails ediTax = new TAXDutyTaxFeeDetails();
		ediTax.setE5283DutyTaxFeeFunctionQualifier(tax.getFeeFunQualifier());
		return ediTax;
	}

	public MOAMonetaryAmount getMoa(MOA moa) {
		MOAMonetaryAmount ediMoa = new MOAMonetaryAmount();
		C516MonetaryAmount cma = new C516MonetaryAmount();
		cma.setE5025MonetaryAmountTypeQualifier(moa.getAmtTypQualifier());
		cma.setE5004MonetaryAmount(new BigDecimal(moa.getAmt()));
		cma.setE6345CurrencyCoded(moa.getCurrencyCode());
		ediMoa.setC516MonetaryAmount(cma);
		return ediMoa;
	}

	public List<MOAMonetaryAmount> getMoa(List<MOA> moaLst) {
		List<MOAMonetaryAmount> ediMoaLst = new ArrayList<MOAMonetaryAmount>();
		for (MOA moa : moaLst) {
			ediMoaLst.add(getMoa(moa));
		}
		return ediMoaLst;
	}

	public List<MOAMonetaryAmount> getMoaLst(MOA moa) {
		List<MOA> moaLst = new ArrayList<MOA>();
		moaLst.add(moa);
		return getMoa(moaLst);
	}

	public ERPErrorPointDetails getERP(ERP erp) {
		ERPErrorPointDetails ediErp = new ERPErrorPointDetails();
		C701ErrorPointDetails cerrd = new C701ErrorPointDetails();
		cerrd.setE1049MessageSectionCoded(erp.getMsgCode());
		ediErp.setC701ErrorPointDetails(cerrd);
		return ediErp;
	}

	public ERCApplicationErrorInformation getErc(ERC erc) {
		ERCApplicationErrorInformation ediErc = new ERCApplicationErrorInformation();
		C901ApplicationErrorDetail caed = new C901ApplicationErrorDetail();
		caed.setE9321ApplicationErrorCoded(erc.getAppErrCode());
		caed.setE1131CodeListQualifier(erc.getCodeLstQualifier());
		ediErc.setC901ApplicationErrorDetail(caed);
		return ediErc;
	}
	
	public List<ERCApplicationErrorInformation> getErc(List<ERC> ercLst) {
		List<ERCApplicationErrorInformation> ediErcLst = new ArrayList<ERCApplicationErrorInformation>();
		for (ERC erc : ercLst) {
			ediErcLst.add(getErc(erc));
		}
		return ediErcLst;
	}

	public List<ERCApplicationErrorInformation> getErcLst(ERC erc) {
		List<ERC> moaLst = new ArrayList<ERC>();
		moaLst.add(erc);
		return getErc(moaLst);
	}
}
