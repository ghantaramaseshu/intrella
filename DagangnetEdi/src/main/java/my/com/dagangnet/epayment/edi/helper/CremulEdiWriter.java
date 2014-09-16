package my.com.dagangnet.epayment.edi.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.milyn.edi.unedifact.d05b.CREMUL.Cremul;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup1;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup2;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup3;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup4;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup5;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup6;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup10;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup11;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup13;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup14;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup20;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup21;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup28;
import org.milyn.edi.unedifact.d05b.common.AUTAuthenticationResult;
import org.milyn.edi.unedifact.d05b.common.BGMBeginningOfMessage;
import org.milyn.edi.unedifact.d05b.common.BUSBusinessFunction;
import org.milyn.edi.unedifact.d05b.common.CNTControlTotal;
import org.milyn.edi.unedifact.d05b.common.COMCommunicationContact;
import org.milyn.edi.unedifact.d05b.common.CTAContactInformation;
import org.milyn.edi.unedifact.d05b.common.DOCDocumentMessageDetails;
import org.milyn.edi.unedifact.d05b.common.DTMDateTimePeriod;
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

public class CremulEdiWriter {

	private static int uniqNumber = 0;

	private static final int maxUniqNumber = 9999;
	private static final int maxUniqNumber_unh = 999999;

	public static void main(String... strings) {
		CremulEdiWriter cremulEdiWriter = new CremulEdiWriter();

		UNEdifactInterchange41 edi = new UNEdifactInterchange41();

		List<UNEdifactMessage41> lst = new ArrayList<UNEdifactMessage41>();

		UNB41 unb = cremulEdiWriter.getUnb();

		UNEdifactMessage41 message = new UNEdifactMessage41();

		UNH41 unh = cremulEdiWriter.getUnh();
		Cremul cremul = cremulEdiWriter.getCremul();
		
		UNT41 unt = cremulEdiWriter.getUnt();
		UNZ41 unz = cremulEdiWriter.getUnz();
		
		message.setMessageHeader(unh);
		message.setMessage(cremul);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private UNB41 getUnb() {

		UNB41 unb = new UNB41();

		Party senderParty = new Party();
		senderParty.setId("9556448012345");
		unb.setSender(senderParty);

		Party recipientParty = new Party();
		recipientParty.setId("9556448012346");
		unb.setRecipient(recipientParty);

		DateTime messageDate = new DateTime();
		messageDate.setDate(getDate());
		messageDate.setTime(getTime());
		unb.setDate(messageDate);

		Ref pwd = new Ref();
		pwd.setRef("PASSWORD");
		unb.setRecipientRef(pwd);

		unb.setApplicationRef("CREMUL97");
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
		mi.setId("CREMUL");
		mi.setVersionNum("1");
		mi.setReleaseNum("971");
		mi.setControllingAgencyCode("UN");
		mi.setAssociationAssignedCode("MYF010");
		unh41.setMessageIdentifier(mi);
		return unh41;
	}

	private Cremul getCremul() {

		Cremul cremul = new Cremul();
		BGMBeginningOfMessage bgm = new BGMBeginningOfMessage();

		C002DocumentMessageName bgmMsgName = new C002DocumentMessageName();
		bgmMsgName.setE1001DocumentNameCode("454");

		C106DocumentMessageIdentification bgmMsgIde = new C106DocumentMessageIdentification();
		bgmMsgIde.setE1004DocumentIdentifier("CREB101401221603460460");

		bgm.setC002DocumentMessageName(bgmMsgName);
		bgm.setC106DocumentMessageIdentification(bgmMsgIde);
		bgm.setE1225MessageFunctionCode("9");
		bgm.setE4343ResponseTypeCode("AB");

		DTMDateTimePeriod dtm = new DTMDateTimePeriod();
		C507DateTimePeriod cdtm = new C507DateTimePeriod();
		cdtm.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("137");
		cdtm.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
		cdtm.setE2379DateOrTimeOrPeriodFormatCode("203");
		dtm.setC507DateTimePeriod(cdtm);

		BUSBusinessFunction bus = new BUSBusinessFunction();
		C521BusinessFunction cbus = new C521BusinessFunction();
		cbus.setE4025BusinessFunctionCode("1");
		cbus.setE4027BusinessFunctionTypeCodeQualifier("DUT");
		bus.setE4487FinancialTransactionTypeCode("4");
		bus.setC521BusinessFunction(cbus);
		
		RFFReference ref = new RFFReference();
		C506Reference cref = new C506Reference();
		cref.setE1153ReferenceCodeQualifier("PQ");
		cref.setE1154ReferenceIdentifier("1401221552440438");
		ref.setC506Reference(cref);

		SegmentGroup1 sg1 = new SegmentGroup1();
		sg1.setRFFReference(ref);
		
		
		
		List<SegmentGroup1> sg1Lst = new ArrayList<SegmentGroup1>();
		sg1Lst.add(sg1);
		
		CTAContactInformation cta = new CTAContactInformation();
		cta.setE3139ContactFunctionCode("NA");		
		
		C056DepartmentOrEmployeeDetails depEmpDetails= new C056DepartmentOrEmployeeDetails();
		depEmpDetails.setE3413DepartmentOrEmployeeNameCode("ADMIN");
		depEmpDetails.setE3412DepartmentOrEmployeeName("LINA");
		
		cta.setC056DepartmentOrEmployeeDetails(depEmpDetails);
		SegmentGroup2 sg2 = new SegmentGroup2();
		
		sg2.setCTAContactInformation(cta);
		List<SegmentGroup2> sg2Lst = new ArrayList<SegmentGroup2>();
		sg2Lst.add(sg2);
		
		NADNameAndAddress nad = new NADNameAndAddress();
		nad.setE3035PartyFunctionCodeQualifier("OY");
		
		C082PartyIdentificationDetails cpd = new C082PartyIdentificationDetails();
		cpd.setE3039PartyIdentifier("ROC");
		
		C058NameAndAddress cnad = new C058NameAndAddress();
		cnad.setE31241NameAndAddressDescription("TRANSOCEAN AGENCIES S/B");
		cnad.setE31242NameAndAddressDescription("51-1 JALAN BATU NILAM 4");
		cnad.setE31243NameAndAddressDescription("BANDAR BUKIT TINGGI");
		cnad.setE31244NameAndAddressDescription("KLANG SELANGOR");
		
		nad.setC058NameAndAddress(cnad);
		nad.setC082PartyIdentificationDetails(cpd);
		
		COMCommunicationContact com=new COMCommunicationContact();
        C076CommunicationContact ccn = new C076CommunicationContact();
        ccn.setE3148CommunicationAddressIdentifier("9556448015072");
        ccn.setE3155CommunicationAddressCodeQualifier("EI");
        com.setC076CommunicationContact(ccn);
        List<COMCommunicationContact> comLst = new ArrayList<COMCommunicationContact>();
        comLst.add(com);
        
		SegmentGroup3 sg3 = new SegmentGroup3();
		sg3.setNADNameAndAddress(nad);
		sg3.setCOMCommunicationContact(comLst);
		
		List<SegmentGroup3> sg3Lst = new ArrayList<SegmentGroup3>();
		sg3Lst.add(sg3);
		
		LINLineItem lin= new LINLineItem();
		lin.setE1082LineItemIdentifier("1");
		
		DTMDateTimePeriod dtmSg4 = new DTMDateTimePeriod();
		C507DateTimePeriod cdtmSg41 = new C507DateTimePeriod();
		cdtmSg41.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("137");
		cdtmSg41.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
		cdtmSg41.setE2379DateOrTimeOrPeriodFormatCode("203");
		dtmSg4.setC507DateTimePeriod(cdtmSg41);

		List<DTMDateTimePeriod> dtmLst = new ArrayList<DTMDateTimePeriod>();
		dtmLst.add(dtmSg4);
         
		MOAMonetaryAmount moa=new MOAMonetaryAmount();
        C516MonetaryAmount cma = new C516MonetaryAmount();
        cma.setE5025MonetaryAmountTypeCodeQualifier("60");
        cma.setE5004MonetaryAmount(new BigDecimal("289842"));
        cma.setE6345CurrencyIdentificationCode("MYR");
        moa.setC516MonetaryAmount(cma);
        
        List<MOAMonetaryAmount> moaLst=new ArrayList<MOAMonetaryAmount>();
        moaLst.add(moa);
		
        SegmentGroup4 sg4 = new SegmentGroup4();
		sg4.setLINLineItem(lin);
		sg4.setDTMDateTimePeriod(dtmLst);
		sg4.setMOAMonetaryAmount(moaLst);
		
		List<SegmentGroup4> sg4Lst = new ArrayList<SegmentGroup4>();
		sg4Lst.add(sg4);
		
		RFFReference ref1=new RFFReference();
        C506Reference cref1 = new C506Reference();
        cref1.setE1153ReferenceCodeQualifier("ACK");
        cref1.setE1154ReferenceIdentifier("1401221552440438");
        ref1.setC506Reference(cref1);
        
        RFFReference ref2=new RFFReference();
        C506Reference cref2 = new C506Reference();
        cref2.setE1153ReferenceCodeQualifier("AEK");
        cref2.setE1154ReferenceIdentifier("PBBBF031814012215481EFT1360    0001");
        ref2.setC506Reference(cref2);
		
        SegmentGroup5 sg5 =new SegmentGroup5();
        sg5.setRFFReference(ref1);
        SegmentGroup5 sg5ref2 =new SegmentGroup5();
        sg5ref2.setRFFReference(ref2);
        List<SegmentGroup5> sg5refLst=new ArrayList<SegmentGroup5>();
        sg5refLst.add(sg5);
        sg5refLst.add(sg5ref2);
        
        sg4.setSegmentGroup5(sg5refLst);
		
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
		
		CTAContactInformation ctaSg6 = new CTAContactInformation();
		ctaSg6.setE3139ContactFunctionCode("BC");
		
		C056DepartmentOrEmployeeDetails ctaEmpDetails = new C056DepartmentOrEmployeeDetails();
		ctaEmpDetails.setE3413DepartmentOrEmployeeNameCode("123456");
		ctaEmpDetails.setE3412DepartmentOrEmployeeName("ALI BAKAR");
		ctaSg6.setC056DepartmentOrEmployeeDetails(ctaEmpDetails);
		
		COMCommunicationContact comSg6 = new COMCommunicationContact();
		
		C076CommunicationContact dupComSg6= new C076CommunicationContact();
		dupComSg6.setE3148CommunicationAddressIdentifier("dupComSg6");
		dupComSg6.setE3155CommunicationAddressCodeQualifier("EI");
		comSg6.setC076CommunicationContact(dupComSg6);
		List<COMCommunicationContact> comSg6Lst = new ArrayList<COMCommunicationContact>();
		comSg6Lst.add(comSg6);
		
		SegmentGroup6 sg6 = new SegmentGroup6();
		sg6.setFIIFinancialInstitutionInformation(fii);
		sg6.setCTAContactInformation(ctaSg6);
		sg6.setCOMCommunicationContact(comSg6Lst);
		
		sg4.setSegmentGroup6(sg6);
		
		SEQSequenceDetails seq=new  SEQSequenceDetails();
        C286SequenceInformation cseq=new C286SequenceInformation();
        cseq.setE1159SequenceIdentifierSourceCode("1");
        seq.setC286SequenceInformation(cseq);
        
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
		
		RFFReference rffSg10 = new RFFReference();
		C506Reference crefSg10 = new C506Reference();
		crefSg10.setE1153ReferenceCodeQualifier("SMK");
		crefSg10.setE1154ReferenceIdentifier("B10101026798");
		rffSg10.setC506Reference(crefSg10);
		
		DTMDateTimePeriod dtmSg11 = new DTMDateTimePeriod();
		C507DateTimePeriod cdtSg11 = new C507DateTimePeriod();
		cdtSg11.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("102");
		cdtSg11.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyy").format(new Date()));
		cdtSg11.setE2379DateOrTimeOrPeriodFormatCode("602");
		dtmSg11.setC507DateTimePeriod(cdtSg11);
		
		
		SegmentGroup11 sg11 = new SegmentGroup11();
		sg11.setRFFReference(rffSg10);
		sg11.setDTMDateTimePeriod(dtmSg11);
		
		List<SegmentGroup11> sg11Lst = new ArrayList<SegmentGroup11>();
		sg11Lst.add(sg11);
		
		MOAMonetaryAmount moaSg13 = new MOAMonetaryAmount();
		C516MonetaryAmount cmaSg13 = new C516MonetaryAmount();
		cmaSg13.setE5025MonetaryAmountTypeCodeQualifier("161");
		cmaSg13.setE5004MonetaryAmount(new BigDecimal("289842"));
		cmaSg13.setE6345CurrencyIdentificationCode("MYR");
		moaSg13.setC516MonetaryAmount(cmaSg13);
		
		SegmentGroup13 sg13=new SegmentGroup13();
		sg13.setMOAMonetaryAmount(moaSg13);
		List<SegmentGroup13> sg13Lst = new ArrayList<SegmentGroup13>();
		sg13Lst.add(sg13);
		
		NADNameAndAddress nadSg14 = new NADNameAndAddress();
		nadSg14.setE3035PartyFunctionCodeQualifier("BE");
		C082PartyIdentificationDetails cpdSg14 = new C082PartyIdentificationDetails();
		cpdSg14.setE3039PartyIdentifier("KDRM");
		C058NameAndAddress cnadSg14 = new C058NameAndAddress();
		cnadSg14.setE31241NameAndAddressDescription("KASTAM DIRAJA MALAYSIA");
		cnadSg14.setE31242NameAndAddressDescription("KOMPLEKS KEMENTERIAN KEWANGAN,");
		cnadSg14.setE31243NameAndAddressDescription("3 PERSIARAN PERDANA,PRESINT 2,");
		cnadSg14.setE31244NameAndAddressDescription("62596 PUTRAJAYA");
		nadSg14.setC058NameAndAddress(cnadSg14);
		nadSg14.setC082PartyIdentificationDetails(cpdSg14);
		
		CTAContactInformation ctaSg14 = new CTAContactInformation();
		ctaSg14.setE3139ContactFunctionCode("NT");
		
		COMCommunicationContact comSg14 = new COMCommunicationContact();
		C076CommunicationContact ccnSg14 = new C076CommunicationContact();
		ccnSg14.setE3148CommunicationAddressIdentifier("9556448000719");
		ccnSg14.setE3155CommunicationAddressCodeQualifier("EI");
		comSg14.setC076CommunicationContact(ccnSg14);
		List<COMCommunicationContact> comSg14Lst = new ArrayList<COMCommunicationContact>();
		comSg14Lst.add(comSg14);
		
		SegmentGroup14 sg14 = new SegmentGroup14();
		sg14.setNADNameAndAddress(nadSg14);
		sg14.setCTAContactInformation(ctaSg14);
		sg14.setCOMCommunicationContact(comSg14Lst);
		
		List<SegmentGroup14> sg14Lst = new ArrayList<SegmentGroup14>();
		sg14Lst.add(sg14);
		
		PRCProcessIdentification prc = new PRCProcessIdentification();
		C242ProcessTypeAndDescription cptd = new C242ProcessTypeAndDescription();
		cptd.setE7187ProcessTypeDescriptionCode("8");
		prc.setC242ProcessTypeAndDescription(cptd);
		
		SegmentGroup20 sg20=new SegmentGroup20();
		sg20.setPRCProcessIdentification(prc);
		
		DOCDocumentMessageDetails doc = new DOCDocumentMessageDetails();
		C002DocumentMessageName cdm = new C002DocumentMessageName();
		cdm.setE1001DocumentNameCode("961");
		cdm.setE1000DocumentName("CUSTOMS ACKNOWLEDGEMENT");
		C503DocumentMessageDetails docMsg = new C503DocumentMessageDetails();
		docMsg.setE1004DocumentIdentifier("B10101026798");
		
		doc.setC002DocumentMessageName(cdm);
		doc.setC503DocumentMessageDetails(docMsg);
		doc.setE3153CommunicationMediumTypeCode("EI");
		
		DTMDateTimePeriod dtmSg21 = new DTMDateTimePeriod();
		C507DateTimePeriod cdtSg21 = new C507DateTimePeriod();
		cdtSg21.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("171");
		cdtSg21.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		cdtSg21.setE2379DateOrTimeOrPeriodFormatCode("102");		
		dtmSg21.setC507DateTimePeriod(cdtSg21);
		
		List<DTMDateTimePeriod> dtmSg11Lst = new ArrayList<DTMDateTimePeriod>();
		dtmSg11Lst.add(dtmSg21);		
		
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
        
        CNTControlTotal cntt = new CNTControlTotal();
		C270Control cc = new C270Control();
		cc.setE6069ControlTotalTypeCodeQualifier("150");
		cc.setE6066ControlTotalQuantity(new BigDecimal("289842"));
		cntt.setC270Control(cc);
		
		CNTControlTotal cntt1 = new CNTControlTotal();
		C270Control cc1 = new C270Control();
		cc1.setE6069ControlTotalTypeCodeQualifier("160");
		cc1.setE6066ControlTotalQuantity(new BigDecimal("1"));
		cntt1.setC270Control(cc1);
		
		List<CNTControlTotal>  cntLst = new ArrayList<CNTControlTotal>();
		cntLst.add(cntt);
		cntLst.add(cntt1);
		
		AUTAuthenticationResult aut = new AUTAuthenticationResult();
		aut.setE9280ValidationResultText("9FA9239260C0CB47");
		
		DTMDateTimePeriod dtmSg28 = new DTMDateTimePeriod();
        C507DateTimePeriod cdtSg28 = new C507DateTimePeriod();
        cdtSg28.setE2005DateOrTimeOrPeriodFunctionCodeQualifier("209");
        cdtSg28.setE2380DateOrTimeOrPeriodText(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
        cdtSg28.setE2379DateOrTimeOrPeriodFormatCode("203");
        dtmSg28.setC507DateTimePeriod(cdtSg28);
       
		SegmentGroup28 seg28 = new SegmentGroup28();
		seg28.setAUTAuthenticationResult(aut);
		seg28.setDTMDateTimePeriod(dtmSg28);
		
		List<SegmentGroup28> sg28Lst = new ArrayList<SegmentGroup28>();
		sg28Lst.add(seg28);
		
        cremul.setBGMBeginningOfMessage(bgm);
		cremul.setDTMDateTimePeriod(dtm);
		cremul.setBUSBusinessFunction(bus);
		cremul.setSegmentGroup1(sg1Lst);
		cremul.setSegmentGroup2(sg2Lst);
		cremul.setSegmentGroup3(sg3Lst);
		cremul.setSegmentGroup4(sg4Lst);
		cremul.setCNTControlTotal(cntLst);
		cremul.setSegmentGroup28(sg28Lst);
		
		return cremul;
	}

	private UNT41 getUnt() {
		UNT41 unt = new UNT41();
		unt.setMessageRefNum("32");
		unt.setSegmentCount(1);
		return unt;
	}
	
	private UNZ41 getUnz(){
		UNZ41 unz =new UNZ41();
		unz.setControlCount(1);
		unz.setControlRef(getDynamicNumber());
        return unz;
	}
	private static String getDate() {
		String dtStr;
		Date now = new Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyMMdd");
		dtStr = formate.format(now);
		return dtStr;
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
