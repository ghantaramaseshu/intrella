package my.com.dagangnet.epayment.edi.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup10;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup11;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup13;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup14;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup20;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup21;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup28;
import org.milyn.edi.unedifact.d05b.DEBMUL.Debmul;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup1;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup2;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup3;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup4;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup5;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup6;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup7;
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
//import org.milyn.edi.unedifact.d93a.DEBADV.SegmentGroup7;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNT41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;
import org.milyn.smooks.edi.unedifact.model.r41.types.DateTime;
import org.milyn.smooks.edi.unedifact.model.r41.types.MessageIdentifier;
import org.milyn.smooks.edi.unedifact.model.r41.types.Party;
import org.milyn.smooks.edi.unedifact.model.r41.types.Ref;
import org.milyn.smooks.edi.unedifact.model.r41.types.SyntaxIdentifier;

public class DebmulEdiWriter {

	private static int uniqNumber = 0;
	private static final int maxUniqNumber = 9999;
	private static final int maxUniqNumber_unh = 999999;
	
	public static void main(String[] args) {
		new DebmulEdiWriter();
	}
	
	public DebmulEdiWriter() {
		
		UNEdifactInterchange41 edi = new UNEdifactInterchange41();
		List<UNEdifactMessage41> lst = new ArrayList<UNEdifactMessage41>();
		
		UNB41 unb = getUnb();
		UNEdifactMessage41 message = new UNEdifactMessage41();
		UNH41 unh = getUnh();
		
		Debmul debmul = getDebmul();
		
		UNT41 unt = getUnt();
		UNZ41 unz = getUnz();
		
		message.setMessageHeader(unh);
		message.setMessage(debmul);
		message.setMessageTrailer(unt);
		lst.add(message);

		edi.setInterchangeHeader(unb);
		edi.setMessages(lst);
		edi.setInterchangeTrailer(unz);
		
		Writer writer = new StringWriter();
		try {
			edi.write(writer, null);
			System.out.println(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Debmul getDebmul() {
		
		Debmul debmul = new Debmul();

		// Set BGM
		BGMBeginningOfMessage bgm = new BGMBeginningOfMessage();
		C002DocumentMessageName bgmMsgName = new C002DocumentMessageName();
		bgmMsgName.setE1001DocumentNameCode("456");
		C106DocumentMessageIdentification bgmMsgIde = new C106DocumentMessageIdentification();
		bgmMsgIde.setE1004DocumentIdentifier("DEBB101401221603500463");
		bgm.setC002DocumentMessageName(bgmMsgName);
		bgm.setC106DocumentMessageIdentification(bgmMsgIde);
		bgm.setE1225MessageFunctionCode("9");
		//bgm.setE4343ResponseTypeCode("AB");
		// Set BGM
		
		// Set DTM
		DTMDateTimePeriod dtm = new DTMDateTimePeriod();
		C507DateTimePeriod cdtm = new C507DateTimePeriod();
		cdtm.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("137");
		cdtm.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
		cdtm.setE2379DateOrTimeOrPeriodFormatCode("203");
		dtm.setC507DateTimePeriod(cdtm);
		// Set DTM
		
		// Set BUS
		BUSBusinessFunction bus = new BUSBusinessFunction();
		C521BusinessFunction cbus = new C521BusinessFunction();
		cbus.setE4025BusinessFunctionCode("1");
		cbus.setE4027BusinessFunctionTypeCodeQualifier("DUT");
		bus.setE4487FinancialTransactionTypeCode("4");
		bus.setC521BusinessFunction(cbus);
		// Set BUS
		
		// Set REF(1)
		RFFReference ref = new RFFReference();
		C506Reference cref = new C506Reference();
		cref.setE1153ReferenceCodeQualifier("PQ");
		cref.setE1154ReferenceIdentifier("1401221552440438");
		ref.setC506Reference(cref);
		SegmentGroup1 sg1 = new SegmentGroup1();
		sg1.setRFFReference(ref);
		// Set REF(1)
		
		
		List<SegmentGroup1> sg1Lst = new ArrayList<SegmentGroup1>();
		sg1Lst.add(sg1);
		
		// Set CTA(1)
		CTAContactInformation cta = new CTAContactInformation();
		cta.setE3139ContactFunctionCode("NT");		
		
		C056DepartmentOrEmployeeDetails depEmpDetails= new C056DepartmentOrEmployeeDetails();
		depEmpDetails.setE3413DepartmentOrEmployeeNameCode("ADMIN20");
		depEmpDetails.setE3412DepartmentOrEmployeeName("LINA");
		
		cta.setC056DepartmentOrEmployeeDetails(depEmpDetails);
		SegmentGroup2 sg2 = new SegmentGroup2();
		sg2.setCTAContactInformation(cta);
		List<SegmentGroup2> sg2Lst = new ArrayList<SegmentGroup2>();
		sg2Lst.add(sg2);
		// Set CTA(1)
		
		// Set NAD(1)
		NADNameAndAddress nad = new NADNameAndAddress();
		nad.setE3035PartyFunctionCodeQualifier("OY");
		
		/*C082PartyIdentificationDetails cpd = new C082PartyIdentificationDetails();
		cpd.setE3039PartyIdentifier("ROC");*/
		C058NameAndAddress cnad = new C058NameAndAddress();
		cnad.setE31241NameAndAddressDescription("TRANSOCEAN AGENCIES S/B");
		cnad.setE31242NameAndAddressDescription("51-1 JALAN BATU NILAM 4");
		cnad.setE31243NameAndAddressDescription("BANDAR BUKIT TINGGI");
		cnad.setE31244NameAndAddressDescription("KLANG SELANGOR");
		
		nad.setC058NameAndAddress(cnad);
		//nad.setC082PartyIdentificationDetails(cpd);
		// Set NAD(1)
		
		// Set COM(1)
		COMCommunicationContact com=new COMCommunicationContact();
        C076CommunicationContact ccn = new C076CommunicationContact();
        ccn.setE3148CommunicationAddressIdentifier("9556448015072");
        ccn.setE3155CommunicationAddressCodeQualifier("EI");
        com.setC076CommunicationContact(ccn);
        List<COMCommunicationContact> comLst = new ArrayList<COMCommunicationContact>();
        comLst.add(com);
        // Set COM(1)
		
        SegmentGroup3 sg3 = new SegmentGroup3();
		sg3.setNADNameAndAddress(nad);
		sg3.setCOMCommunicationContact(comLst);
		List<SegmentGroup3> sg3Lst = new ArrayList<SegmentGroup3>();
		sg3Lst.add(sg3);
		
		 // Set LIN
		LINLineItem lin= new LINLineItem();
		lin.setE1082LineItemIdentifier("1");
		 // Set LIN
		
		// Set DTM(2)
		DTMDateTimePeriod dtmSg4 = new DTMDateTimePeriod();
		C507DateTimePeriod cdtmSg41 = new C507DateTimePeriod();
		cdtmSg41.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("209");
		cdtmSg41.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
		cdtmSg41.setE2379DateOrTimeOrPeriodFormatCode("102");
		dtmSg4.setC507DateTimePeriod(cdtmSg41);
		List<DTMDateTimePeriod> dtmLst = new ArrayList<DTMDateTimePeriod>();
		dtmLst.add(dtmSg4);
		// Set DTM(2)
		
		// Set MOA(1)
		MOAMonetaryAmount moa=new MOAMonetaryAmount();
        C516MonetaryAmount cma = new C516MonetaryAmount();
        cma.setE5025MonetaryAmountTypeCodeQualifier("60");
        cma.setE5004MonetaryAmount(new BigDecimal("289842"));
        cma.setE6345CurrencyIdentificationCode("MYR");
        moa.setC516MonetaryAmount(cma);
        List<MOAMonetaryAmount> moaLst=new ArrayList<MOAMonetaryAmount>();
        moaLst.add(moa);
        // Set MOA(1)
		
        SegmentGroup4 sg4 = new SegmentGroup4();
		sg4.setLINLineItem(lin);
		sg4.setDTMDateTimePeriod(dtmLst);
		sg4.setMOAMonetaryAmount(moaLst);
		List<SegmentGroup4> sg4Lst = new ArrayList<SegmentGroup4>();
		sg4Lst.add(sg4);
		
		 // Set REF(2)
		RFFReference ref1=new RFFReference();
        C506Reference cref1 = new C506Reference();
        cref1.setE1153ReferenceCodeQualifier("ACK");
        cref1.setE1154ReferenceIdentifier("1401221553410749");
        ref1.setC506Reference(cref1);
        // Set REF(2)
        
        // Set REF(3)
        RFFReference ref2=new RFFReference();
        C506Reference cref2 = new C506Reference();
        cref2.setE1153ReferenceCodeQualifier("AEK");
        cref2.setE1154ReferenceIdentifier("PBBBF031814012215481EFT1360    0001");
        ref2.setC506Reference(cref2);
        // Set REF(3)
		
        SegmentGroup5 sg5 =new SegmentGroup5();
        sg5.setRFFReference(ref1);
        SegmentGroup5 sg5ref2 =new SegmentGroup5();
        sg5ref2.setRFFReference(ref2);
        List<SegmentGroup5> sg5refLst=new ArrayList<SegmentGroup5>();
        sg5refLst.add(sg5);
        sg5refLst.add(sg5ref2);
        
        sg4.setSegmentGroup5(sg5refLst);
		
        // Set FII(1)
        FIIFinancialInstitutionInformation fii = new FIIFinancialInstitutionInformation();
		
		C078AccountHolderIdentification acctHolder = new C078AccountHolderIdentification();
		acctHolder.setE3194AccountHolderIdentifier("3103460919");
		acctHolder.setE31921AccountHolderName("TRANSOCEAN AGENCIES S/B");
		acctHolder.setE31922AccountHolderName("");
		acctHolder.setE6345CurrencyIdentificationCode("MYR");
		
		C088InstitutionIdentification instIdent = new C088InstitutionIdentification();
		instIdent.setE3433InstitutionNameCode("PBB");
		instIdent.setE11311CodeListIdentificationCode("");
		instIdent.setE30552CodeListResponsibleAgencyCode("");
		instIdent.setE3434InstitutionBranchIdentifier("PBBEMYKL");
		instIdent.setE11312CodeListIdentificationCode("154");
		instIdent.setE30552CodeListResponsibleAgencyCode("BNM");
		instIdent.setE3432InstitutionName("PUBLIC BANK BERHAD");
		instIdent.setE3436InstitutionBranchLocationName("33-12254");
		
		fii.setE3035PartyFunctionCodeQualifier("OR");
		fii.setC078AccountHolderIdentification(acctHolder);
		fii.setC088InstitutionIdentification(instIdent);
        // Set FII(1)
        
		 // Set CTA(2)
		CTAContactInformation ctaSg6 = new CTAContactInformation();
		ctaSg6.setE3139ContactFunctionCode("BC");
		C056DepartmentOrEmployeeDetails ctaEmpDetails = new C056DepartmentOrEmployeeDetails();
		ctaEmpDetails.setE3413DepartmentOrEmployeeNameCode("123456");
		ctaEmpDetails.setE3412DepartmentOrEmployeeName("ALI BAKAR");
		ctaSg6.setC056DepartmentOrEmployeeDetails(ctaEmpDetails);
		 // Set CTA(2)
        
		// Set COM(2)
		COMCommunicationContact comSg6 = new COMCommunicationContact();
		C076CommunicationContact dupComSg6= new C076CommunicationContact();
		dupComSg6.setE3148CommunicationAddressIdentifier("9556448015072");
		dupComSg6.setE3155CommunicationAddressCodeQualifier("EI");
		comSg6.setC076CommunicationContact(dupComSg6);
		List<COMCommunicationContact> comSg6Lst = new ArrayList<COMCommunicationContact>();
		comSg6Lst.add(comSg6);
		// Set COM(2)
        
		// Set FCA
		FCAFinancialChargesAllocation fca=new FCAFinancialChargesAllocation();
		fca.setE4471SettlementMeansCode("15");
		C878ChargeAllowanceAccount cact = new C878ChargeAllowanceAccount();
		cact.setE3434InstitutionBranchIdentifier("33-12254");
		cact.setE1131CodeListIdentificationCode("154");
		cact.setE3055CodeListResponsibleAgencyCode("BNM");
		cact.setE3194AccountHolderIdentifier("3103460919");
		cact.setE6345CurrencyIdentificationCode("MYR");
		fca.setC878ChargeAllowanceAccount(cact);
		// Set FCA
		
		SegmentGroup7 sg7=new SegmentGroup7();
		sg7.setFCAFinancialChargesAllocation(fca);
		List<SegmentGroup7> fcaSg7Lst = new ArrayList<SegmentGroup7>();
		fcaSg7Lst.add(sg7);
		
		// Set MOA(2)
		MOAMonetaryAmount moaAmt=new MOAMonetaryAmount();
		C516MonetaryAmount cmamt=new C516MonetaryAmount();
		cmamt.setE5025MonetaryAmountTypeCodeQualifier("131");
		cmamt.setE5004MonetaryAmount(new BigDecimal("000"));
		cmamt.setE6345CurrencyIdentificationCode("MYR");
		moaAmt.setC516MonetaryAmount(cmamt);
		List<MOAMonetaryAmount> moaAmtLst = new ArrayList<MOAMonetaryAmount>();
		moaAmtLst.add(moaAmt);
		sg7.setMOAMonetaryAmount(moaAmtLst);
		// Set MOA(2)
		
		SegmentGroup6 sg6 = new SegmentGroup6();
		sg6.setFIIFinancialInstitutionInformation(fii);
		sg6.setCTAContactInformation(ctaSg6);
		sg6.setCOMCommunicationContact(comSg6Lst);
		sg4.setSegmentGroup6(sg6);
		sg4.setSegmentGroup7(fcaSg7Lst);
        
		// Set SEQ
		SEQSequenceDetails seq=new  SEQSequenceDetails();
        C286SequenceInformation cseq=new C286SequenceInformation();
        cseq.setE1159SequenceIdentifierSourceCode("1");
        seq.setC286SequenceInformation(cseq);
        // Set SEQ
		
        // Set FII(2)
        FIIFinancialInstitutionInformation fiiSg10 = new FIIFinancialInstitutionInformation();

		C078AccountHolderIdentification acctHolderSg10 = new C078AccountHolderIdentification();
		acctHolderSg10.setE3194AccountHolderIdentifier("1601-0001116-05-6");
		acctHolderSg10.setE31921AccountHolderName("KASTAM DIRAJA MALAYSIA");
		acctHolderSg10.setE31922AccountHolderName("");
		acctHolderSg10.setE6345CurrencyIdentificationCode("MYR");

		C088InstitutionIdentification instIdentSg10 = new C088InstitutionIdentification();
		instIdentSg10.setE3433InstitutionNameCode("CIB");
		instIdentSg10.setE11311CodeListIdentificationCode("");
		instIdentSg10.setE30552CodeListResponsibleAgencyCode("");
		instIdentSg10.setE3434InstitutionBranchIdentifier("FSP");
		instIdentSg10.setE11312CodeListIdentificationCode("154");
		instIdentSg10.setE30552CodeListResponsibleAgencyCode("BNM");
		instIdentSg10.setE3432InstitutionName("KASTAM DIRAJA MALAYSIA");
		instIdentSg10.setE3436InstitutionBranchLocationName("NO DATA");

		fiiSg10.setE3035PartyFunctionCodeQualifier("BF");
		fiiSg10.setC078AccountHolderIdentification(acctHolderSg10);
		fiiSg10.setC088InstitutionIdentification(instIdentSg10);
		
		List<FIIFinancialInstitutionInformation> fiiLst = new ArrayList<FIIFinancialInstitutionInformation>();
		fiiLst.add(fiiSg10);
        // Set FII(2)
		
		 // Set RFF(4)
		RFFReference rffSg10 = new RFFReference();
		C506Reference crefSg10 = new C506Reference();
		crefSg10.setE1153ReferenceCodeQualifier("SMK");
		crefSg10.setE1154ReferenceIdentifier("B10101026798");
		rffSg10.setC506Reference(crefSg10);
		// Set RFF(4)
		
		 // Set DTM(3)
		DTMDateTimePeriod dtmSg11 = new DTMDateTimePeriod();
		C507DateTimePeriod cdtSg11 = new C507DateTimePeriod();
		cdtSg11.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("102");
		cdtSg11.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyy").format(new Date()));
		cdtSg11.setE2379DateOrTimeOrPeriodFormatCode("602");
		dtmSg11.setC507DateTimePeriod(cdtSg11);
		// Set DTM(3)
		
		SegmentGroup11 sg11 = new SegmentGroup11();
		sg11.setRFFReference(rffSg10);
		sg11.setDTMDateTimePeriod(dtmSg11);
		
		List<SegmentGroup11> sg11Lst = new ArrayList<SegmentGroup11>();
		sg11Lst.add(sg11);
		
		// Set MOA(3)
		MOAMonetaryAmount moaSg13 = new MOAMonetaryAmount();
		C516MonetaryAmount cmaSg13 = new C516MonetaryAmount();
		cmaSg13.setE5025MonetaryAmountTypeCodeQualifier("161");
		cmaSg13.setE5004MonetaryAmount(new BigDecimal("289842"));
		cmaSg13.setE6345CurrencyIdentificationCode("MYR");
		moaSg13.setC516MonetaryAmount(cmaSg13);
		// Set MOA(3)
		
		SegmentGroup13 sg13=new SegmentGroup13();
		sg13.setMOAMonetaryAmount(moaSg13);
		List<SegmentGroup13> sg13Lst = new ArrayList<SegmentGroup13>();
		sg13Lst.add(sg13);
		
		// Set NAD(2)
		NADNameAndAddress nadSg14 = new NADNameAndAddress();
		nadSg14.setE3035PartyFunctionCodeQualifier("BE");
		C082PartyIdentificationDetails cpdSg14 = new C082PartyIdentificationDetails();
		cpdSg14.setE3039PartyIdentifier("KDRM");
		C058NameAndAddress cnadSg14 = new C058NameAndAddress();
		cnadSg14.setE31241NameAndAddressDescription("KASTAM DIRAJA MALAYSIA");
		cnadSg14.setE31242NameAndAddressDescription("KOMPLEKS KEMENTERIAN KEWANGAN,");
		cnadSg14.setE31243NameAndAddressDescription("3 PERSIARAN PERDANA,PRESINT 2,");
		cnadSg14.setE31244NameAndAddressDescription("62596 PUTRAJAYA");
		cnadSg14.setE31245NameAndAddressDescription("9556448015072");
		nadSg14.setC058NameAndAddress(cnadSg14);
		nadSg14.setC082PartyIdentificationDetails(cpdSg14);
		// Set NAD(2)
		
		// Set CTA(3)
		CTAContactInformation ctaSg14 = new CTAContactInformation();
		ctaSg14.setE3139ContactFunctionCode("NT");
		// Set CTA(3)
		
		SegmentGroup14 sg14 = new SegmentGroup14();
		sg14.setNADNameAndAddress(nadSg14);
		sg14.setCTAContactInformation(ctaSg14);
		
		List<SegmentGroup14> sg14Lst = new ArrayList<SegmentGroup14>();
		sg14Lst.add(sg14);
		
		// Set PRC(1)
		PRCProcessIdentification prc = new PRCProcessIdentification();
		C242ProcessTypeAndDescription cptd = new C242ProcessTypeAndDescription();
		cptd.setE7187ProcessTypeDescriptionCode("8");
		prc.setC242ProcessTypeAndDescription(cptd);
		// Set PRC(1)
		
		SegmentGroup20 sg20=new SegmentGroup20();
		sg20.setPRCProcessIdentification(prc);
		
		// Set DOC
		DOCDocumentMessageDetails doc = new DOCDocumentMessageDetails();
		C002DocumentMessageName cdm = new C002DocumentMessageName();
		cdm.setE1001DocumentNameCode("961");
		cdm.setE1000DocumentName("CUSTOMS ACKNOWLEDGEMENT");
		C503DocumentMessageDetails docMsg = new C503DocumentMessageDetails();
		docMsg.setE1004DocumentIdentifier("B10101026798");
		
		doc.setC002DocumentMessageName(cdm);
		doc.setC503DocumentMessageDetails(docMsg);
		doc.setE3153CommunicationMediumTypeCode("EI");
		// Set DOC
		
		// Set DTM(4)
		DTMDateTimePeriod dtmSg21 = new DTMDateTimePeriod();
		C507DateTimePeriod cdtSg21 = new C507DateTimePeriod();
		cdtSg21.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("171");
		cdtSg21.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		cdtSg21.setE2379DateOrTimeOrPeriodFormatCode("102");		
		dtmSg21.setC507DateTimePeriod(cdtSg21);
		
		List<DTMDateTimePeriod> dtmSg11Lst = new ArrayList<DTMDateTimePeriod>();
		dtmSg11Lst.add(dtmSg21);
		// Set DTM(4)
		
		SegmentGroup21 sg21=new SegmentGroup21();
		sg21.setDOCDocumentMessageDetails(doc);
		sg21.setDTMDateTimePeriod(dtmSg11Lst);
		
		List<SegmentGroup21> sg21Lst = new ArrayList<SegmentGroup21>();
		sg21Lst.add(sg21);
		
		sg20.setSegmentGroup21(sg21Lst);
		
		SegmentGroup10 sg10 = new SegmentGroup10();
        sg10.setSEQSequenceDetails(seq);
        sg10.setFIIFinancialInstitutionInformation(fiiLst);
        sg10.setSegmentGroup11(sg11Lst);
        sg10.setSegmentGroup13(sg13Lst);
        sg10.setSegmentGroup14(sg14Lst);
        sg10.setSegmentGroup20(sg20);
        
        List<SegmentGroup10> sg10Lst = new ArrayList<SegmentGroup10>();
        sg10Lst.add(sg10);
        
        sg4.setSegmentGroup10(sg10Lst);
		
        // Set CNT(1)
        CNTControlTotal cntt = new CNTControlTotal();
		C270Control cc = new C270Control();
		cc.setE6069ControlTotalTypeCodeQualifier("140");
		cc.setE6066ControlTotalQuantity(new BigDecimal("289842"));
		cntt.setC270Control(cc);
        // Set CNT(1)
		
        // Set CNT(2)
		CNTControlTotal cntt1 = new CNTControlTotal();
		C270Control cc1 = new C270Control();
		cc1.setE6069ControlTotalTypeCodeQualifier("160");
		cc1.setE6066ControlTotalQuantity(new BigDecimal("1"));
		cntt1.setC270Control(cc1);
        // Set CNT(2)
        
		List<CNTControlTotal>  cntLst = new ArrayList<CNTControlTotal>();
		cntLst.add(cntt);
		cntLst.add(cntt1);
		
		 // Set AUT
		AUTAuthenticationResult aut = new AUTAuthenticationResult();
		aut.setE9280ValidationResultText("9EA5252022149095");
		// Set AUT
		
		 // Set DTM(5)
		DTMDateTimePeriod dtmSg28 = new DTMDateTimePeriod();
        C507DateTimePeriod cdtSg28 = new C507DateTimePeriod();
        cdtSg28.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("209");
        cdtSg28.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
        cdtSg28.setE2379DateOrTimeOrPeriodFormatCode("203");
        dtmSg28.setC507DateTimePeriod(cdtSg28);
		 // Set DTM(5)
        
        SegmentGroup28 seg28 = new SegmentGroup28();
		seg28.setAUTAuthenticationResult(aut);
		seg28.setDTMDateTimePeriod(dtmSg28);
		
		List<SegmentGroup28> sg28Lst = new ArrayList<SegmentGroup28>();
		sg28Lst.add(seg28);
        
		
		debmul.setBGMBeginningOfMessage(bgm);
		debmul.setDTMDateTimePeriod(dtm);
		debmul.setBUSBusinessFunction(bus);
		debmul.setSegmentGroup1(sg1Lst);
		debmul.setSegmentGroup2(sg2Lst);
		debmul.setSegmentGroup3(sg3Lst);
		debmul.setSegmentGroup4(sg4Lst);
		debmul.setCNTControlTotal(cntLst);
		debmul.setSegmentGroup28(sg28Lst);
		
		return debmul;
	}

	private UNB41 getUnb() {

		UNB41 unb = new UNB41();

		Party senderParty = new Party();
		senderParty.setId("9556448065961");
		unb.setSender(senderParty);

		Party recipientParty = new Party();
		recipientParty.setId("9556448015072");
		unb.setRecipient(recipientParty);

		DateTime messageDate = new DateTime();
		messageDate.setDate(getDate());
		messageDate.setTime(getTime());
		unb.setDate(messageDate);

		Ref pwd = new Ref();
		pwd.setRef("PASSWORD");
		unb.setRecipientRef(pwd);

		unb.setApplicationRef("DEBMUL97");
		// unb41.setProcessingPriorityCode("Password");
		unb.setTestIndicator("1");

		unb.setControlRef(getDynamicNumber());

		SyntaxIdentifier syntaxIdentifier = new SyntaxIdentifier();
		syntaxIdentifier.setId("UNOA");
		syntaxIdentifier.setVersionNum("2");
		unb.setSyntaxIdentifier(syntaxIdentifier);

		return unb;
	}

	private UNH41 getUnh() {
		UNH41 unh41 = new UNH41();
		unh41.setMessageRefNum(this.getDynamicNumberUNH());
		MessageIdentifier mi = new MessageIdentifier();
		mi.setId("DEBMUL");
		mi.setVersionNum("1");
		mi.setReleaseNum("971");
		mi.setControllingAgencyCode("UN");
		mi.setAssociationAssignedCode("MYF010");
		unh41.setMessageIdentifier(mi);
		return unh41;
	}
	
	
	private static String getDate() {
		String dtStr;
		Date now = new Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyMMdd");
		dtStr = formate.format(now);
		return dtStr;
	}

	private UNT41 getUnt() {
		UNT41 unt = new UNT41();
		unt.setMessageRefNum("33");
		unt.setSegmentCount(1);
		return unt;
	}
	
	private UNZ41 getUnz(){
		UNZ41 unz =new UNZ41();
		unz.setControlCount(1);
		unz.setControlRef(getDynamicNumber());
        return unz;
	}
	
	private static String getTime() {
		String dtStr;
		Date now = new Date();
		SimpleDateFormat formate = new SimpleDateFormat("hhmm");
		dtStr = formate.format(now);
		return dtStr;
	}

	private static String getDynamicNumber() {
		String randomString;
		randomString = getDate() + getTime();

		uniqNumber += 1;

		if (uniqNumber > maxUniqNumber) {
			uniqNumber = 0;
		}
		return randomString + String.format("%04d", uniqNumber);
	}

	private String getDynamicNumberUNH() {
		String randomString = "";
		uniqNumber += 1;
		if (uniqNumber > maxUniqNumber_unh) {
			uniqNumber = 0;
		}
		return randomString + String.format("%06d", uniqNumber);
	}
	
}
