/*
 * Copyright (c) 2009-2015, United States Government, as represented by the Secretary of Health and Human Services.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above
 *       copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of the United States Government nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gov.hhs.fha.nhinc.patientdiscovery.audit.LoggerImpl;

import gov.hhs.fha.nhinc.patientdiscovery.audit.Logger.PatientDiscoveryAuditEJBLogger;
import gov.hhs.fha.nhinc.auditrepository.nhinc.proxy.AuditRepositoryProxyObjectFactory;
import gov.hhs.fha.nhinc.common.auditlog.LogEventRequestType;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

/**
 *
 * @author achidamb
 */
@Stateless
public class PatientDiscoveryEJBLoggerImpl implements PatientDiscoveryAuditEJBLogger {
    
    private static final Logger LOG = Logger.getLogger(PatientDiscoveryAuditEJBLogger.class);
   
    @Asynchronous
    @Override
    public void auditMessage(LogEventRequestType request, AssertionType assertion) {
        LOG.trace("--- Before auditing of  message ---");
        auditLogMessages(request, assertion);
        LOG.trace("--- After auditing of message ---");
    }
    
    
    private void auditLogMessages(LogEventRequestType auditLogMsg, AssertionType assertion) {
        if (auditLogMsg != null && auditLogMsg.getAuditMessage() != null) {
            new AuditRepositoryProxyObjectFactory().getAuditRepositoryProxy().auditLog(auditLogMsg, assertion);
        } else {
            LOG.error("auditLogMsg is null, no auditing will take place.");
        }
    }
       
}
