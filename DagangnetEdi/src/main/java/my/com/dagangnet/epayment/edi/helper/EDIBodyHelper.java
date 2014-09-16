package my.com.dagangnet.epayment.edi.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import my.com.dagangnet.epayment.edi.pojo.AUT;
import my.com.dagangnet.epayment.edi.pojo.BGM;
import my.com.dagangnet.epayment.edi.pojo.BUS;
import my.com.dagangnet.epayment.edi.pojo.CNT;
import my.com.dagangnet.epayment.edi.pojo.COM;
import my.com.dagangnet.epayment.edi.pojo.CTA;
import my.com.dagangnet.epayment.edi.pojo.DOC;
import my.com.dagangnet.epayment.edi.pojo.DTM;
import my.com.dagangnet.epayment.edi.pojo.FCA;
import my.com.dagangnet.epayment.edi.pojo.FII;
import my.com.dagangnet.epayment.edi.pojo.LIN;
import my.com.dagangnet.epayment.edi.pojo.MOA;
import my.com.dagangnet.epayment.edi.pojo.NAD;
import my.com.dagangnet.epayment.edi.pojo.PRC;
import my.com.dagangnet.epayment.edi.pojo.RFF;
import my.com.dagangnet.epayment.edi.pojo.SEQ;
import org.milyn.edi.unedifact.d05b.common.AUTAuthenticationResult;
import org.milyn.edi.unedifact.d05b.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d05b.common.BUSBusinessFunction;
import org.milyn.edi.unedifact.d05b.common.CNTControlTotal;
import org.milyn.edi.unedifact.d05b.common.COMCommunicationContact;
import org.milyn.edi.unedifact.d05b.common.CTAContactInformation;
import org.milyn.edi.unedifact.d05b.common.DOCDocumentMessageDetails;
import org.milyn.edi.unedifact.d05b.common.DTMDateTimePeriod;
import org.milyn.edi.unedifact.d05b.common.FCAFinancialChargesAllocation;
import org.milyn.edi.unedifact.d05b.common.FIIFinancialInstitutionInformation;
import org.milyn.edi.unedifact.d05b.common.LINLineItem;
import org.milyn.edi.unedifact.d05b.common.MOAMonetaryAmount;
import org.milyn.edi.unedifact.d05b.common.NADNameAndAddress;
import org.milyn.edi.unedifact.d05b.common.PRCProcessIdentification;
import org.milyn.edi.unedifact.d05b.common.RFFReference;
import org.milyn.edi.unedifact.d05b.common.SEQSequenceDetails;
import org.milyn.edi.unedifact.d05b.common.field.C002DocumentMessageName;
import org.milyn.edi.unedifact.d05b.common.field.C056DepartmentOrEmployeeDetails;
import org.milyn.edi.unedifact.d05b.common.field.C058NameAndAddress;
import org.milyn.edi.unedifact.d05b.common.field.C076CommunicationContact;
import org.milyn.edi.unedifact.d05b.common.field.C078AccountHolderIdentification;
import org.milyn.edi.unedifact.d05b.common.field.C082PartyIdentificationDetails;
import org.milyn.edi.unedifact.d05b.common.field.C088InstitutionIdentification;
import org.milyn.edi.unedifact.d05b.common.field.C106DocumentMessageIdentification;
import org.milyn.edi.unedifact.d05b.common.field.C242ProcessTypeAndDescription;
import org.milyn.edi.unedifact.d05b.common.field.C270Control;
import org.milyn.edi.unedifact.d05b.common.field.C286SequenceInformation;
import org.milyn.edi.unedifact.d05b.common.field.C503DocumentMessageDetails;
import org.milyn.edi.unedifact.d05b.common.field.C506Reference;
import org.milyn.edi.unedifact.d05b.common.field.C507DateTimePeriod;
import org.milyn.edi.unedifact.d05b.common.field.C516MonetaryAmount;
import org.milyn.edi.unedifact.d05b.common.field.C521BusinessFunction;
import org.milyn.edi.unedifact.d05b.common.field.C878ChargeAllowanceAccount;



public class EDIBodyHelper extends EDICoreHelper{
	
public BGMBeginningOfMessage getBgm(BGM b){
		
		BGMBeginningOfMessage bgm = new BGMBeginningOfMessage();
		C002DocumentMessageName bgmMsgName = new C002DocumentMessageName();
		bgmMsgName.setE1001DocumentNameCode(b.getMsgCode());   // set Document Name code
		C106DocumentMessageIdentification bgmMsgIde = new C106DocumentMessageIdentification();
		bgmMsgIde.setE1004DocumentIdentifier(b.getMsgNumber()); //set Document number
		bgm.setC002DocumentMessageName(bgmMsgName);  
		bgm.setC106DocumentMessageIdentification(bgmMsgIde);
		bgm.setE1225MessageFunctionCode(b.getMsgFunCode()); // set Message function code
		bgm.setE4343ResponseTypeCode(b.getMsgName()); //set Response code
	
		return bgm;
	}
	
	public BUSBusinessFunction getBus(BUS b){
		
		BUSBusinessFunction bus = new BUSBusinessFunction();
		C521BusinessFunction cbus = new C521BusinessFunction();
		cbus.setE4025BusinessFunctionCode(b.getFunCode());   // set Business function Qualifier
		cbus.setE4027BusinessFunctionTypeCodeQualifier(b.getFunQualifier());  //  set Business function code
		bus.setE4487FinancialTransactionTypeCode(b.getTxnCode());  // set Type of Financial traansactional code 
		bus.setC521BusinessFunction(cbus);
		return bus;
	}
	
	
	public RFFReference getRff(RFF r){
		RFFReference rff = new RFFReference();
		C506Reference cref = new C506Reference();
		cref.setE1153ReferenceCodeQualifier(r.getRefQualifier());  // Set Reference Qualifier
		cref.setE1154ReferenceIdentifier(r.getRefNumber());  // Set reference number
		rff.setC506Reference(cref);
		return rff;
	}
	
	public NADNameAndAddress getNad(NAD n){
		
		NADNameAndAddress nad = new NADNameAndAddress();
		nad.setE3035PartyFunctionCodeQualifier(n.getPartyQualifier());   // set Party Qualifier
		C082PartyIdentificationDetails cpd = new C082PartyIdentificationDetails();
		cpd.setE3039PartyIdentifier(n.getPartyIdentification());  // set Party ID identification
		C058NameAndAddress cnad = new C058NameAndAddress();
		cnad.setE31241NameAndAddressDescription(n.getAddressObj().getAddressLane1()); // set name address lane 1
		cnad.setE31242NameAndAddressDescription(n.getAddressObj().getAddressLane2()); // set name address lane 2
		cnad.setE31243NameAndAddressDescription(n.getAddressObj().getAddressLane3()); // set name address lane 3
		cnad.setE31244NameAndAddressDescription(n.getAddressObj().getAddressLane4()); // set name address lane 4
		nad.setC058NameAndAddress(cnad);
		nad.setC082PartyIdentificationDetails(cpd);
		return nad;
	}
	
	public LINLineItem getLin(LIN l){
		LINLineItem lin= new LINLineItem();
		lin.setE1082LineItemIdentifier(l.getItemNumber());
		return lin;
	}
	
	public MOAMonetaryAmount getMoa(MOA m){
		MOAMonetaryAmount moa=new MOAMonetaryAmount();
        C516MonetaryAmount cma = new C516MonetaryAmount();
        cma.setE5025MonetaryAmountTypeCodeQualifier(m.getAmtTypQualifier());  // set amount type qualifier
        cma.setE5004MonetaryAmount(new BigDecimal(m.getAmt()));  // set monetary amount
        cma.setE6345CurrencyIdentificationCode(m.getCurrencyCode());  // set Currency Code
        moa.setC516MonetaryAmount(cma);
        return moa;
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
	
	
	public FCAFinancialChargesAllocation getFca(FCA f){		
		FCAFinancialChargesAllocation fca=new FCAFinancialChargesAllocation();
		fca.setE4471SettlementMeansCode(f.getCode());  // set settlement code
		C878ChargeAllowanceAccount cact = new C878ChargeAllowanceAccount();
		cact.setE3434InstitutionBranchIdentifier(f.getBranchNumber()); // set branch number
		cact.setE1131CodeListIdentificationCode(f.getCodeLstQualifier());  // set code list qualifier
		cact.setE3055CodeListResponsibleAgencyCode(f.getAgencyCode()); // set code list responsibly agency
		cact.setE3194AccountHolderIdentifier(f.getAcctHolderNumber()); // set account holder number
		cact.setE6345CurrencyIdentificationCode(f.getCurrencyCode()); // set currency 
		fca.setC878ChargeAllowanceAccount(cact);
		
		return fca;
	}
	
	public SEQSequenceDetails getSeq(SEQ s){
		SEQSequenceDetails seq=new  SEQSequenceDetails();
        C286SequenceInformation cseq=new C286SequenceInformation();
        cseq.setE1159SequenceIdentifierSourceCode(s.getNumber());  // set Sequency number
        seq.setC286SequenceInformation(cseq);
        return seq;
	}
	
	public DTMDateTimePeriod getDtm(DTM dtm) {
		DTMDateTimePeriod ediDtm = new DTMDateTimePeriod();
		C507DateTimePeriod cdt = new C507DateTimePeriod();
		cdt.setE2005DateOrTimeOrPeriodFunctionCodeQualifier(dtm.getDtQualifier());
		cdt.setE2380DateOrTimeOrPeriodText(dtm.getDtPeriod());
		cdt.setE2379DateOrTimeOrPeriodFormatCode(dtm.getDtFrmtQualifier());
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

	public AUTAuthenticationResult getAut(AUT aut){
		AUTAuthenticationResult ediAut = new AUTAuthenticationResult();
		ediAut.setE9280ValidationResultText(aut.getResult());
		ediAut.setE9282ValidationKeyIdentifier(aut.getIdentification());
		return ediAut;
	}
	
	public CNTControlTotal getCnt(CNT cnt){
		CNTControlTotal ediCntt = new CNTControlTotal();
		C270Control cc = new C270Control();
		cc.setE6069ControlTotalTypeCodeQualifier(cnt.getQualifier());
		cc.setE6066ControlTotalQuantity(new BigDecimal(cnt.getValue()));
		ediCntt.setC270Control(cc);
		return ediCntt;
	}
	
	public List<CNTControlTotal> getCnt(List<CNT> cntLst){
		List<CNTControlTotal> ediCntLst = new ArrayList<CNTControlTotal>();
		for(CNT cnt:cntLst){
			ediCntLst.add(getCnt(cnt));
		}
		return ediCntLst;
	}
	
	public DOCDocumentMessageDetails getDoc(DOC doc){
		DOCDocumentMessageDetails ediDoc = new DOCDocumentMessageDetails();
		C002DocumentMessageName cdm = new C002DocumentMessageName();
		cdm.setE1001DocumentNameCode(doc.getMsgCode());
		cdm.setE1000DocumentName(doc.getMsgName());
		C503DocumentMessageDetails docMsg = new C503DocumentMessageDetails();
		docMsg.setE1004DocumentIdentifier(doc.getMsgNumber());
		
		ediDoc.setC002DocumentMessageName(cdm);
		ediDoc.setC503DocumentMessageDetails(docMsg);
		ediDoc.setE3153CommunicationMediumTypeCode(doc.getChannelCode());
		return ediDoc;
	}
	
	public PRCProcessIdentification getPrc(PRC prc){
		PRCProcessIdentification ediPrc = new PRCProcessIdentification();
		
		C242ProcessTypeAndDescription cptd = new C242ProcessTypeAndDescription();
		cptd.setE7187ProcessTypeDescriptionCode(prc.getTypeIdentification());
		
		ediPrc.setC242ProcessTypeAndDescription(cptd);
		return ediPrc;
	}
	
	public COMCommunicationContact getCom(COM com){
		COMCommunicationContact ediCom = new COMCommunicationContact();
		C076CommunicationContact ccnSg14 = new C076CommunicationContact();
		ccnSg14.setE3148CommunicationAddressIdentifier(com.getNumber());
		ccnSg14.setE3155CommunicationAddressCodeQualifier(com.getChannelQualifier());
		ediCom.setC076CommunicationContact(ccnSg14);
		return ediCom;
	}
	
	public List<COMCommunicationContact> getCom(List<COM> comLst){
		List<COMCommunicationContact> ediComLst = new ArrayList<COMCommunicationContact>();
		for(COM com:comLst){
			ediComLst.add(getCom(com));
		}
		return ediComLst;
	}
	
	public List<COMCommunicationContact> getComLst(COM com) {
        List<COM> moaLst = new ArrayList<COM>();
        moaLst.add(com);
        return getCom(moaLst);
	}
	
	public CTAContactInformation getCta(CTA cta){
		CTAContactInformation ediCta = new CTAContactInformation();
		ediCta.setE3139ContactFunctionCode(cta.getCode());
		C056DepartmentOrEmployeeDetails empDtls = new C056DepartmentOrEmployeeDetails();
		empDtls.setE3413DepartmentOrEmployeeNameCode(cta.getEmpIdentification());
		empDtls.setE3412DepartmentOrEmployeeName(cta.getEmpName());
		ediCta.setC056DepartmentOrEmployeeDetails(empDtls);
		return ediCta;
	}
	
	
	public FIIFinancialInstitutionInformation getFii(FII fii){
		FIIFinancialInstitutionInformation ediFii = new FIIFinancialInstitutionInformation();
		C078AccountHolderIdentification acctHolderSg10 = new C078AccountHolderIdentification();
		acctHolderSg10.setE3194AccountHolderIdentifier(fii.getAcctHolderNumber());
		acctHolderSg10.setE31921AccountHolderName(fii.getAcctHolderName());
		acctHolderSg10.setE31922AccountHolderName(fii.getAcctHolderName2());
		acctHolderSg10.setE6345CurrencyIdentificationCode(fii.getCurrencyCode());

		C088InstitutionIdentification instIdentSg10 = new C088InstitutionIdentification();
		instIdentSg10.setE3433InstitutionNameCode(fii.getInstitutionIdentification());
		instIdentSg10.setE11311CodeListIdentificationCode(fii.getCodeLstQualifier());
		instIdentSg10.setE30551CodeListResponsibleAgencyCode(fii.getAgnecyCode());
		instIdentSg10.setE3434InstitutionBranchIdentifier(fii.getBranchNumber());
		instIdentSg10.setE11312CodeListIdentificationCode(fii.getCodeLstQualifier2());
		instIdentSg10.setE30552CodeListResponsibleAgencyCode(fii.getAgnecyCode2());
		instIdentSg10.setE3432InstitutionName(fii.getInstitutionName());
		instIdentSg10.setE3436InstitutionBranchLocationName(fii.getInstitutionPlace());

		ediFii.setE3035PartyFunctionCodeQualifier(fii.getPartyQualifier());
		ediFii.setC078AccountHolderIdentification(acctHolderSg10);
		ediFii.setC088InstitutionIdentification(instIdentSg10);
		return ediFii;
	}
	
	public List<FIIFinancialInstitutionInformation> getFii(List<FII> fiiLst){
		List<FIIFinancialInstitutionInformation> ediFiiLst = new ArrayList<FIIFinancialInstitutionInformation>();
		for(FII fii:fiiLst){
			ediFiiLst.add(getFii(fii));
		}
		return ediFiiLst;
	}
	
	public List<FIIFinancialInstitutionInformation> getFiiLst(FII fii){
		List<FII> fiiLst = new ArrayList<FII>();
		fiiLst.add(fii);
		return getFii(fiiLst);
	}
}
