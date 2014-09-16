package my.com.dagangnet.epayment.edi.helper;

import java.util.ArrayList;
import java.util.List;

import my.com.dagangnet.epayment.edi.pojo.DebmulRequest;

import org.milyn.edi.unedifact.d05b.DEBMUL.Debmul;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup1;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup10;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup11;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup13;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup14;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup2;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup20;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup21;
import org.milyn.edi.unedifact.d05b.DEBMUL.SegmentGroup28;
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

public class DebmulResHelper {

	private EDIBodyHelper helper = new EDIBodyHelper();
	
	public Debmul getDebmulMsg(DebmulRequest req){
		
		Debmul debmul = new Debmul();
		
		// set BGM
		BGMBeginningOfMessage bgm = helper.getBgm(req.getbGM());
		
		// set DTM (1)
		DTMDateTimePeriod dtm1 = helper.getDtm(req.getDtm1());
		
		// set BUS
		BUSBusinessFunction bus = helper.getBus(req.getBus());
		
		
		// Set RFF(1)
		RFFReference rff1 = helper.getRff(req.getRff1()); 
		SegmentGroup1 sg1 = new SegmentGroup1();
		sg1.setRFFReference(rff1);
		List<SegmentGroup1> sg1Lst = new ArrayList<SegmentGroup1>();
		sg1Lst.add(sg1);

		// Set CTA(1)
		CTAContactInformation cta1=helper.getCta(req.getCta1());
		SegmentGroup2 sg2 = new SegmentGroup2();
		sg2.setCTAContactInformation(cta1);
		List<SegmentGroup2> sg2Lst = new ArrayList<SegmentGroup2>();
		sg2Lst.add(sg2);
		
		// Set NAD(1)
		NADNameAndAddress nad1=helper.getNad(req.getNad1());
		
		// Set COM(1)
		List<COMCommunicationContact> comLst1=helper.getComLst(req.getCom1());
		
		SegmentGroup3 sg3 = new SegmentGroup3();
		sg3.setNADNameAndAddress(nad1);
		sg3.setCOMCommunicationContact(comLst1);
		List<SegmentGroup3> sg3Lst = new ArrayList<SegmentGroup3>();
		sg3Lst.add(sg3);
		
		// Set LIN
		LINLineItem lin = helper.getLin(req.getLin());
		
		// Set DTM(2)
		List<DTMDateTimePeriod> dtm2Lst=helper.getDtmLst(req.getDtm2());
		
		// Set MOA(1)
		List<MOAMonetaryAmount> moa1Lst=helper.getMoaLst(req.getMoa1());
		
		SegmentGroup4 sg4 = new SegmentGroup4();
		sg4.setLINLineItem(lin);
		sg4.setDTMDateTimePeriod(dtm2Lst);
		sg4.setMOAMonetaryAmount(moa1Lst);
		List<SegmentGroup4> sg4Lst = new ArrayList<SegmentGroup4>();
		sg4Lst.add(sg4);
		
		// Set RFF(2)
		RFFReference rff2=helper.getRff(req.getRff2());
		
		// Set RFF(3)
		RFFReference rff3=helper.getRff(req.getRff3());
		
		SegmentGroup5 sg5 =new SegmentGroup5();
        sg5.setRFFReference(rff2);
        SegmentGroup5 sg5ref2 =new SegmentGroup5();
        sg5ref2.setRFFReference(rff3);
        List<SegmentGroup5> sg5refLst=new ArrayList<SegmentGroup5>();
        sg5refLst.add(sg5);
        sg5refLst.add(sg5ref2);
        
        sg4.setSegmentGroup5(sg5refLst);
		
        // Set FII(1)
        FIIFinancialInstitutionInformation fii1=helper.getFii(req.getFii1());
        
        // Set CTA(2)
        CTAContactInformation cta2 = helper.getCta(req.getCta2());  
        
        // Set COM(2)
        List<COMCommunicationContact> comLst2=helper.getComLst(req.getCom2());
        
        // Set FCA
        FCAFinancialChargesAllocation fca=helper.getFca(req.getFca());
        
        SegmentGroup7 sg7=new SegmentGroup7();
		sg7.setFCAFinancialChargesAllocation(fca);
		List<SegmentGroup7> fcaSg7Lst = new ArrayList<SegmentGroup7>();
		fcaSg7Lst.add(sg7);
		
		// Set MOA(2)
		List<MOAMonetaryAmount> moa2Lst=helper.getMoaLst(req.getMoa2());
		
		sg7.setMOAMonetaryAmount(moa2Lst);
		
		SegmentGroup6 sg6 = new SegmentGroup6();
		sg6.setFIIFinancialInstitutionInformation(fii1);
		sg6.setCTAContactInformation(cta2);
		sg6.setCOMCommunicationContact(comLst2);
		sg4.setSegmentGroup6(sg6);
		sg4.setSegmentGroup7(fcaSg7Lst);
		
		// Set SEQ
		SEQSequenceDetails seq=helper.getSeq(req.getSeq());
		
		 // Set FII(2)
		List<FIIFinancialInstitutionInformation> fii2Lst =helper.getFiiLst(req.getFii2());
		
		// Set RFF(4)
		RFFReference rff4 = helper.getRff(req.getRff4());
		
		 // Set DTM(3)
		DTMDateTimePeriod dtm3 = helper.getDtm(req.getDtm3());
		
		SegmentGroup11 sg11 = new SegmentGroup11();
		sg11.setRFFReference(rff4);
		sg11.setDTMDateTimePeriod(dtm3);
		List<SegmentGroup11> sg11Lst = new ArrayList<SegmentGroup11>();
		sg11Lst.add(sg11);
		
		// Set MOA(3)
		MOAMonetaryAmount moa3 = helper.getMoa(req.getMoa3());
		
		SegmentGroup13 sg13=new SegmentGroup13();
		sg13.setMOAMonetaryAmount(moa3);
		List<SegmentGroup13> sg13Lst = new ArrayList<SegmentGroup13>();
		sg13Lst.add(sg13);
		
		// Set NAD(2)
		NADNameAndAddress nad2 = helper.getNad(req.getNad2());
		
		// Set CTA(3)
		CTAContactInformation cta3 = helper.getCta(req.getCta3());
		
		SegmentGroup14 sg14 = new SegmentGroup14();
		sg14.setNADNameAndAddress(nad2);
		sg14.setCTAContactInformation(cta3);
		List<SegmentGroup14> sg14Lst = new ArrayList<SegmentGroup14>();
		sg14Lst.add(sg14);
		
		// Set PRC(1)
		PRCProcessIdentification prc = helper.getPrc(req.getPrc());
		
		SegmentGroup20 sg20=new SegmentGroup20();
		sg20.setPRCProcessIdentification(prc);
		
		// Set DOC(1)
		DOCDocumentMessageDetails doc = helper.getDoc(req.getDoc());
		
		// Set DTM(4)
		List<DTMDateTimePeriod> dtm4Lst = helper.getDtmLst(req.getDtm4());
		
		SegmentGroup21 sg21=new SegmentGroup21();
		sg21.setDOCDocumentMessageDetails(doc);
		sg21.setDTMDateTimePeriod(dtm4Lst);
		List<SegmentGroup21> sg21Lst = new ArrayList<SegmentGroup21>();
		sg21Lst.add(sg21);
		
		sg20.setSegmentGroup21(sg21Lst);
		
		SegmentGroup10 sg10 = new SegmentGroup10();
        sg10.setSEQSequenceDetails(seq);
        sg10.setFIIFinancialInstitutionInformation(fii2Lst);
        sg10.setSegmentGroup11(sg11Lst);
        sg10.setSegmentGroup13(sg13Lst);
        sg10.setSegmentGroup14(sg14Lst);
        sg10.setSegmentGroup20(sg20);
        
        List<SegmentGroup10> sg10Lst = new ArrayList<SegmentGroup10>();
        sg10Lst.add(sg10);
        
        sg4.setSegmentGroup10(sg10Lst);
		
        // Set CNT(1)
        CNTControlTotal cnt1 = helper.getCnt(req.getCnt1());
        
        // Set CNT(2)
        CNTControlTotal cnt2 = helper.getCnt(req.getCnt2());
        
        List<CNTControlTotal>  cntLst = new ArrayList<CNTControlTotal>();
		cntLst.add(cnt1);
		cntLst.add(cnt2);
		
		 // Set AUT(1)
		AUTAuthenticationResult aut = helper.getAut(req.getAut());
		
		 // Set DTM(5)
		DTMDateTimePeriod dtm5 = helper.getDtm(req.getDtm5());
		
		SegmentGroup28 seg28 = new SegmentGroup28();
		seg28.setAUTAuthenticationResult(aut);
		seg28.setDTMDateTimePeriod(dtm5);
		List<SegmentGroup28> sg28Lst = new ArrayList<SegmentGroup28>();
		sg28Lst.add(seg28);
		
		// Set the segment's to debmul object
		
		debmul.setBGMBeginningOfMessage(bgm);
		debmul.setDTMDateTimePeriod(dtm1);		
		debmul.setBUSBusinessFunction(bus);		
		debmul.setSegmentGroup1(sg1Lst);
		debmul.setSegmentGroup2(sg2Lst);
		debmul.setSegmentGroup3(sg3Lst);
		debmul.setSegmentGroup4(sg4Lst);
		debmul.setCNTControlTotal(cntLst);
		debmul.setSegmentGroup28(sg28Lst);
		
		return debmul;
	}
	
}
