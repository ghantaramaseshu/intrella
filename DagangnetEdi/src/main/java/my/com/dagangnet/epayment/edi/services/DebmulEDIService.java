package my.com.dagangnet.epayment.edi.services;

import my.com.dagangnet.epayment.edi.pojo.DebmulRequest;

public interface DebmulEDIService {

	public String generateDeubmulMsg(final DebmulRequest debmulRes);
	
}
