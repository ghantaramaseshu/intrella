package my.com.dagangnet.epayment.edi.helper;

import java.util.ArrayList;
import java.util.List;

import org.milyn.edi.unedifact.d05b.CREMUL.Cremul;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup1;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup10;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup11;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup13;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup14;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup2;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup20;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup21;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup28;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup3;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup4;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup5;
import org.milyn.edi.unedifact.d05b.CREMUL.SegmentGroup6;
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

import my.com.dagangnet.epayment.edi.pojo.CremulRequest;

public class CremulHelper extends EDIBodyHelper{
	
	
	public Cremul getCremul(CremulRequest cremulReq){
	
		BGMBeginningOfMessage bgm_line1 = getBgm(cremulReq.getBgm());
		DTMDateTimePeriod dtm_line2 = getDtm(cremulReq.getDtm1());
		BUSBusinessFunction bus_line3 = getBus(cremulReq.getBus());
		RFFReference rff_line4 = getRff(cremulReq.getRff1());
		NADNameAndAddress nad_line5 = getNad(cremulReq.getNad1());
		CTAContactInformation cta_line6 = getCta(cremulReq.getCta1());
		List<COMCommunicationContact> comLst_line7 = getComLst(cremulReq.getCom1());
		LINLineItem lin_line8 =  getLin(cremulReq.getLin());
		List<DTMDateTimePeriod> dtmLst_line9 = getDtmLst(cremulReq.getDtm2());
		List<MOAMonetaryAmount> moaLst_line10 = getMoaLst(cremulReq.getMoa1());
		RFFReference rff_line11 = getRff(cremulReq.getRff2());
		RFFReference rff_line12 = getRff(cremulReq.getRff3());
		FIIFinancialInstitutionInformation fii_line13 = getFii(cremulReq.getFii1());
		CTAContactInformation cta_line14 = getCta(cremulReq.getCta2());
		List<COMCommunicationContact> comLst_line15 = getComLst(cremulReq.getCom2());
		SEQSequenceDetails seq_line16 = getSeq(cremulReq.getSeq());
		List<FIIFinancialInstitutionInformation> fiiLst_line17 = getFiiLst(cremulReq.getFii2());
		RFFReference rff_line18 = getRff(cremulReq.getRff4());
		DTMDateTimePeriod dtm_line19 = getDtm(cremulReq.getDtm3());
		MOAMonetaryAmount moa_line20 = getMoa(cremulReq.getMoa2());
		NADNameAndAddress nad_line21 = getNad(cremulReq.getNad2());
		CTAContactInformation cta_line22 = getCta(cremulReq.getCta3());
		List<COMCommunicationContact> comLst_line23 = getComLst(cremulReq.getCom3());
		PRCProcessIdentification prc_line24 = getPrc(cremulReq.getPrc());
		DOCDocumentMessageDetails doc_line25 = getDoc(cremulReq.getDoc());
		List<DTMDateTimePeriod> dtmLst_line26 = getDtmLst(cremulReq.getDtm4());
		CNTControlTotal cnt_line27 = getCnt(cremulReq.getCnt1());
		CNTControlTotal cnt_line28 = getCnt(cremulReq.getCnt2());
		AUTAuthenticationResult aut_line29 = getAut(cremulReq.getAut());
		DTMDateTimePeriod dtm_line30 = getDtm(cremulReq.getDtm5());
		
		SegmentGroup1 sg1 = new SegmentGroup1();
		sg1.setRFFReference(rff_line4);		
		List<SegmentGroup1> sg1Lst = new ArrayList<SegmentGroup1>();
		sg1Lst.add(sg1);
		
		SegmentGroup2 sg2 = new SegmentGroup2();		
		sg2.setCTAContactInformation(cta_line6);
		List<SegmentGroup2> sg2Lst = new ArrayList<SegmentGroup2>();
		sg2Lst.add(sg2);
		
		SegmentGroup3 sg3 = new SegmentGroup3();
		sg3.setNADNameAndAddress(nad_line5);
		sg3.setCOMCommunicationContact(comLst_line7);		
		List<SegmentGroup3> sg3Lst = new ArrayList<SegmentGroup3>();
		sg3Lst.add(sg3);		
		
		SegmentGroup5 sg5_1 =new SegmentGroup5();
		sg5_1.setRFFReference(rff_line11);
        SegmentGroup5 sg5_2 =new SegmentGroup5();
        sg5_2.setRFFReference(rff_line12);
        List<SegmentGroup5> sg5Lst=new ArrayList<SegmentGroup5>();
        sg5Lst.add(sg5_1);
        sg5Lst.add(sg5_2);
        
        SegmentGroup6 sg6 = new SegmentGroup6();
		sg6.setFIIFinancialInstitutionInformation(fii_line13);
		sg6.setCTAContactInformation(cta_line14);
		sg6.setCOMCommunicationContact(comLst_line15);
		
		SegmentGroup11 sg11 = new SegmentGroup11();
		sg11.setRFFReference(rff_line18);
		sg11.setDTMDateTimePeriod(dtm_line19);
		List<SegmentGroup11> sg11Lst = new ArrayList<SegmentGroup11>();
		sg11Lst.add(sg11);
		
		SegmentGroup13 sg13=new SegmentGroup13();
		sg13.setMOAMonetaryAmount(moa_line20);
		List<SegmentGroup13> sg13Lst = new ArrayList<SegmentGroup13>();
		sg13Lst.add(sg13);
		
		SegmentGroup14 sg14 = new SegmentGroup14();
		sg14.setNADNameAndAddress(nad_line21);
		sg14.setCTAContactInformation(cta_line22);
		sg14.setCOMCommunicationContact(comLst_line23);		
		List<SegmentGroup14> sg14Lst = new ArrayList<SegmentGroup14>();
		sg14Lst.add(sg14);
		
		SegmentGroup21 sg21=new SegmentGroup21();
		sg21.setDOCDocumentMessageDetails(doc_line25);
		sg21.setDTMDateTimePeriod(dtmLst_line26);		
		List<SegmentGroup21> sg21Lst = new ArrayList<SegmentGroup21>();
		sg21Lst.add(sg21);
		
		SegmentGroup20 sg20=new SegmentGroup20();
		sg20.setPRCProcessIdentification(prc_line24);
		sg20.setSegmentGroup21(sg21Lst);
		
		SegmentGroup10 sg10 = new SegmentGroup10();
		sg10.setSEQSequenceDetails(seq_line16);
        sg10.setFIIFinancialInstitutionInformation(fiiLst_line17);
        sg10.setSegmentGroup11(sg11Lst);
        sg10.setSegmentGroup13(sg13Lst);
        sg10.setSegmentGroup14(sg14Lst);
        sg10.setSegmentGroup20(sg20);
        List<SegmentGroup10> sg10Lst = new ArrayList<SegmentGroup10>();
        sg10Lst.add(sg10);
        
        SegmentGroup4 sg4 = new SegmentGroup4();
		sg4.setLINLineItem(lin_line8);
		sg4.setDTMDateTimePeriod(dtmLst_line9);
		sg4.setMOAMonetaryAmount(moaLst_line10);		
		List<SegmentGroup4> sg4Lst = new ArrayList<SegmentGroup4>();
		sg4Lst.add(sg4);
		sg4.setSegmentGroup5(sg5Lst);
		sg4.setSegmentGroup6(sg6);
		sg4.setSegmentGroup10(sg10Lst);
		
		SegmentGroup28 seg28 = new SegmentGroup28();
		seg28.setAUTAuthenticationResult(aut_line29);
		seg28.setDTMDateTimePeriod(dtm_line30);
		List<SegmentGroup28> sg28Lst = new ArrayList<SegmentGroup28>();
		sg28Lst.add(seg28);
		
		List<CNTControlTotal>  cntLst = new ArrayList<CNTControlTotal>();
		cntLst.add(cnt_line27);
		cntLst.add(cnt_line28);
		
		Cremul cremul = new Cremul();
		cremul.setBGMBeginningOfMessage(bgm_line1);
		cremul.setDTMDateTimePeriod(dtm_line2);
		cremul.setBUSBusinessFunction(bus_line3);
		cremul.setSegmentGroup1(sg1Lst);
		cremul.setSegmentGroup2(sg2Lst);
		cremul.setSegmentGroup3(sg3Lst);
		cremul.setSegmentGroup4(sg4Lst);
		cremul.setCNTControlTotal(cntLst);
		cremul.setSegmentGroup28(sg28Lst);
		return cremul;
	}
}
