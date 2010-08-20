package gov.hhs.fha.nhinc.patientdiscovery.entity.deferred.request.proxy;

import org.hl7.v3.MCCIIN000002UV01;
import org.hl7.v3.RespondingGatewayPRPAIN201305UV02RequestType;

public class EntityPatientDiscoveryDeferredRequestProxyNoOpImpl implements EntityPatientDiscoveryDeferredRequestProxy {

    public MCCIIN000002UV01 processPatientDiscoveryAsyncReq(RespondingGatewayPRPAIN201305UV02RequestType processPatientDiscoveryAsyncReqAsyncRequest) {
        return new MCCIIN000002UV01();
    }

}
