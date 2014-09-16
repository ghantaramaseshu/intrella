package my.com.dagangnet.epayment.edi.services;

import my.com.dagangnet.epayment.edi.pojo.CremulRequest;

public interface CremulEDIService {
	
	public String doEDI(final CremulRequest cremulReq);
	
}
