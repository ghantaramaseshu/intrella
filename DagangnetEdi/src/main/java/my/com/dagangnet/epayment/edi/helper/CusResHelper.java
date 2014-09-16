package my.com.dagangnet.epayment.edi.helper;

import java.util.ArrayList;
import java.util.List;

import my.com.dagangnet.epayment.edi.pojo.FspgRes;

import org.milyn.edi.unedifact.d93a.CUSRES.Cusres;
import org.milyn.edi.unedifact.d93a.CUSRES.SegmentGroup1;
import org.milyn.edi.unedifact.d93a.CUSRES.SegmentGroup2;
import org.milyn.edi.unedifact.d93a.CUSRES.SegmentGroup3;
import org.milyn.edi.unedifact.d93a.CUSRES.SegmentGroup5;
import org.milyn.edi.unedifact.d93a.CUSRES.SegmentGroup7;

public class CusResHelper {

	private EDIBodyHelper ediHelper = new EDIBodyHelper();

	public Cusres getSuccessCusres(FspgRes fspgRes) {

		Cusres cs = new Cusres();
		cs.setBGMBeginningOfMessage(ediHelper.getBgm(fspgRes.getBgm()));
		cs.setDTMDateTimePeriod(ediHelper.getDtmLst(fspgRes.getDtm()));
		cs.setFTXFreeText(ediHelper.getFtxLst(fspgRes.getFtx()));
		cs.setGISGeneralIndicator(ediHelper.getGisLst(fspgRes.getGis()));

		return cs;

	}

	public Cusres getCancelPaymentCusres(FspgRes fspgRes) {

		Cusres cs = new Cusres();
		cs.setBGMBeginningOfMessage(ediHelper.getBgm(fspgRes.getBgm()));
		cs.setDTMDateTimePeriod(ediHelper.getDtmLst(fspgRes.getDtm()));
		cs.setFTXFreeText(ediHelper.getFtxLst(fspgRes.getFtx()));
		cs.setGISGeneralIndicator(ediHelper.getGisLst(fspgRes.getGis()));

		SegmentGroup7 s7 = new SegmentGroup7();
		s7.setTAXDutyTaxFeeDetails(ediHelper.getTax(fspgRes.getTax()));
		s7.setMOAMonetaryAmount(ediHelper.getMoaLst(fspgRes.getMoa()));

		List<SegmentGroup7> sg7Lst = new ArrayList<SegmentGroup7>();
		sg7Lst.add(s7);

		SegmentGroup5 sg5 = new SegmentGroup5();
		sg5.setRFFReference(ediHelper.getRff(fspgRes.getRef()));
		List<SegmentGroup5> sg5lst = new ArrayList<SegmentGroup5>();
		sg5lst.add(sg5);
		sg5.setSegmentGroup7(sg7Lst);
		
		cs.setSegmentGroup5(sg5lst);

		return cs;

	}
	
	public Cusres getErrorCusres(FspgRes fspgRes) {
		Cusres cs = new Cusres();
		cs.setBGMBeginningOfMessage(ediHelper.getBgm(fspgRes.getBgm()));
		cs.setDTMDateTimePeriod(ediHelper.getDtmLst(fspgRes.getDtm()));
		cs.setFTXFreeText(ediHelper.getFtxLst(fspgRes.getFtx()));
		cs.setGISGeneralIndicator(ediHelper.getGisLst(fspgRes.getGis()));
		
		SegmentGroup3 sg3=new SegmentGroup3();
		sg3.setFTXFreeText(ediHelper.getFtx(fspgRes.getFtx2()));
		
		SegmentGroup2 sg2=new SegmentGroup2();
		sg2.setSegmentGroup3(sg3);
		List<SegmentGroup2> sg2Lst=new ArrayList<SegmentGroup2>();
		sg2Lst.add(sg2);		

		SegmentGroup5 sg5 = new SegmentGroup5();
		sg5.setRFFReference(ediHelper.getRff(fspgRes.getRef()));
		List<SegmentGroup5> sg5lst = new ArrayList<SegmentGroup5>();
		sg5lst.add(sg5);
		
		
		cs.setSegmentGroup5(sg5lst);
		cs.setSegmentGroup2(sg2Lst);
		return cs;
		
	}

}
