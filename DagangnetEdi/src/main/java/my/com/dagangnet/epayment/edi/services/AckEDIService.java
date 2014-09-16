package my.com.dagangnet.epayment.edi.services;

import my.com.dagangnet.epayment.edi.pojo.FspgRes;

public interface AckEDIService {

	public String doSuccessEdi(final FspgRes fspgRes);

	public String doErrorEdi(final FspgRes fspgRes);

	public String doCancelPaymentEdi(final FspgRes fspgRes);

}
