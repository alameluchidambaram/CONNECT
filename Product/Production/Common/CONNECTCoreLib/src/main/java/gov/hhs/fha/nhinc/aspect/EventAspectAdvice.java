/**
 * Copyright (c) 2012, United States Government, as represented by the Secretary of Health and Human Services.
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
package gov.hhs.fha.nhinc.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zmelnick
 * 
 */
@Aspect
public class EventAspectAdvice {

    private static final Log log = LogFactory.getLog(EventAspectAdvice.class);

    private EventAdviceDelegate inboundMessageAdviceDelegate;

    private EventAdviceDelegate inboundProcessingAdviceDelegate;

    private EventAdviceDelegate adapterDelegationAdviceDelegate;

    private EventAdviceDelegate outboundMessageAdviceDelegate;

    private EventAdviceDelegate outboundProcessingAdviceDelegate;

    private EventAdviceDelegate nwhinInvocationAdviceDelegate;
    
    private FailureAdviceDelegate failureAdviceDelegate;

    @Before("@annotation(annotation)")
    public void beginInboundMessageEvent(JoinPoint joinPoint, InboundMessageEvent annotation) {
        inboundMessageAdviceDelegate.begin(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @After("@annotation(annotation)")
    public void endInboundMessageEvent(JoinPoint joinPoint, InboundMessageEvent annotation) {
        inboundMessageAdviceDelegate.end(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @Before("@annotation(annotation)")
    public void beginInboundProcessingEvent(JoinPoint joinPoint, InboundProcessingEvent annotation) {
        inboundProcessingAdviceDelegate.begin(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @After("@annotation(annotation)")
    public void endInboundProcessingEvent(JoinPoint joinPoint, InboundProcessingEvent annotation) {
        inboundProcessingAdviceDelegate.end(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @Before("@annotation(annotation)")
    public void beginAdapterDelegationEvent(JoinPoint joinPoint, AdapterDelegationEvent annotation) {
        adapterDelegationAdviceDelegate.begin(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @After("@annotation(annotation)")
    public void endAdapterDelegationEvent(JoinPoint joinPoint, AdapterDelegationEvent annotation) {
        adapterDelegationAdviceDelegate.end(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @Before("@annotation(annotation)")
    public void beginOutboundMessageEvent(JoinPoint joinPoint, OutboundMessageEvent annotation) {
        outboundMessageAdviceDelegate.begin(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @After("@annotation(annotation)")
    public void endOutboundMessageEvent(JoinPoint joinPoint, OutboundMessageEvent annotation) {
        outboundMessageAdviceDelegate.end(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @Before("@annotation(annotation)")
    public void beginOutboundProcessingEvent(JoinPoint joinPoint, OutboundProcessingEvent annotation) {
        outboundProcessingAdviceDelegate.begin(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @After("@annotation(annotation)")
    public void endOutboundProcessingEvent(JoinPoint joinPoint, OutboundProcessingEvent annotation) {
        outboundProcessingAdviceDelegate.end(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @Before("@annotation(annotation)")
    public void beginNwhinInvocationEvent(JoinPoint joinPoint, NwhinInvocationEvent annotation) {
        nwhinInvocationAdviceDelegate.begin(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @After("@annotation(annotation)")
    public void endNwhinInvocationEvent(JoinPoint joinPoint, NwhinInvocationEvent annotation) {
        nwhinInvocationAdviceDelegate.end(joinPoint.getArgs(), annotation.serviceType(), annotation.version(), annotation.descriptionBuilder());
    }

    @AfterThrowing("@annotation(gov.hhs.fha.nhinc.aspect.InboundMessageEvent) &&"
            + "@annotation(gov.hhs.fha.nhinc.aspect.InboundProcessingEvent) &&"
            + "@annotation(gov.hhs.fha.nhinc.aspect.AdapterDelegationEvent) && "
            + "@annotation(gov.hhs.fha.nhinc.aspect.OutboundMessageEvent) && "
            + "@annotation(gov.hhs.fha.nhinc.aspect.OutboundProcessingEvent) && "
            + "@annotation(gov.hhs.fha.nhinc.aspect.NwhinInvocationEvent)")
    public void failEvent(JoinPoint joinPoint) {
        failureAdviceDelegate.fail(joinPoint.getArgs());
    }

    @Autowired
    public void setInboundMessageAdviceDelegate(EventAdviceDelegate inboundMessageAdviceDelegate) {
        this.inboundMessageAdviceDelegate = inboundMessageAdviceDelegate;
    }

    @Autowired
    public void setInboundProcessingAdviceDelegate(EventAdviceDelegate inboundProcessingAdviceDelegate) {
        this.inboundProcessingAdviceDelegate = inboundProcessingAdviceDelegate;
    }

    @Autowired
    public void setAdapterDelegationAdviceDelegate(EventAdviceDelegate adapterDelegationAdviceDelegate) {
        this.adapterDelegationAdviceDelegate = adapterDelegationAdviceDelegate;
    }

    @Autowired
    public void setOutboundMessageAdviceDelegate(EventAdviceDelegate outboundMessageAdviceDelegate) {
        this.outboundMessageAdviceDelegate = outboundMessageAdviceDelegate;
    }

    @Autowired
    public void setOutboundProcessingAdviceDelegate(EventAdviceDelegate outboundProcessingAdviceDelegate) {
        this.outboundProcessingAdviceDelegate = outboundProcessingAdviceDelegate;
    }

    @Autowired
    public void setNwhinInvocationAdviceDelegate(EventAdviceDelegate nwhinInvocationAdviceDelegate) {
        this.nwhinInvocationAdviceDelegate = nwhinInvocationAdviceDelegate;
    }

    public void setFailureAdviceDelegate(FailureAdviceDelegate failureAdviceDelegate) {
        this.failureAdviceDelegate = failureAdviceDelegate;
    }
    
    

}