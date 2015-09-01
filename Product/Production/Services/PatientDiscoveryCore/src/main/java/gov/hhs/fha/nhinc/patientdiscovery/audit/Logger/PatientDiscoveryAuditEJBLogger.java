/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.patientdiscovery.audit.Logger;

import gov.hhs.fha.nhinc.common.auditlog.LogEventRequestType;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;

/**
 *
 * @author achidamb
 */
public interface PatientDiscoveryAuditEJBLogger {
    
    public abstract  void auditMessage(LogEventRequestType request, AssertionType assertion);
      
}
