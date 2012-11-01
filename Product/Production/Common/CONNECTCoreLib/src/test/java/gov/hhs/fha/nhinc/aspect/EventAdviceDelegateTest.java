/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012, United States Government, as represented by the Secretary of Health and Human Services.
 * All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above
 *     copyright notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the United States Government nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 *THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
 *DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gov.hhs.fha.nhinc.aspect;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.event.BaseEventDescriptionBuilder;
import gov.hhs.fha.nhinc.event.DefaultEventDescriptionBuilder;
import gov.hhs.fha.nhinc.event.EventFactory;
import gov.hhs.fha.nhinc.event.EventRecorder;
import gov.hhs.fha.nhinc.event.MessageRoutingAccessor;
import gov.hhs.fha.nhinc.event.initiator.BeginNwhinInvocationEvent;
import gov.hhs.fha.nhinc.event.initiator.BeginOutboundMessageEvent;
import gov.hhs.fha.nhinc.event.initiator.BeginOutboundProcessingEvent;
import gov.hhs.fha.nhinc.event.initiator.EndNwhinInvocationEvent;
import gov.hhs.fha.nhinc.event.initiator.EndOutboundMessageEvent;
import gov.hhs.fha.nhinc.event.initiator.EndOutboundProcessingEvent;
import gov.hhs.fha.nhinc.event.responder.BeginAdapterDelegationEvent;
import gov.hhs.fha.nhinc.event.responder.BeginInboundMessageEvent;
import gov.hhs.fha.nhinc.event.responder.BeginInboundProcessingEvent;
import gov.hhs.fha.nhinc.event.responder.EndAdapterDelegationEvent;
import gov.hhs.fha.nhinc.event.responder.EndInboundMessageEvent;
import gov.hhs.fha.nhinc.event.responder.EndInboundProcessingEvent;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;

import org.junit.Test;

public class EventAdviceDelegateTest {
    private MessageRoutingAccessor messageRoutingAccessor = mock(MessageRoutingAccessor.class);
    private EventRecorder eventRecorder = mock(EventRecorder.class);
    private static final String DS_SERVICE_TYPE = "Document Submission";
    private static final String DS_VERISON = "2.0";
    private EventFactory eventFactory = new EventFactory();

    @Test
    public void inboundMessageAdviceDelegate() {
        InboundMessageAdviceDelegate inboundMessageAdviceDelegate = new InboundMessageAdviceDelegate();

        ProvideAndRegisterDocumentSetRequestType requestType = new ProvideAndRegisterDocumentSetRequestType();
        Object[] args = { requestType };

        inboundMessageAdviceDelegate.setEventRecorder(eventRecorder);
        inboundMessageAdviceDelegate.setMessageRoutingAccessor(messageRoutingAccessor);
        inboundMessageAdviceDelegate.setEventFactory(eventFactory);

        inboundMessageAdviceDelegate.begin(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(BeginInboundMessageEvent.class));

        inboundMessageAdviceDelegate.end(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(EndInboundMessageEvent.class));
    }

    @Test
    public void inboundProcessingAdviceDelegate() {
        InboundProcessingAdviceDelegate inboundProcessingAdviceDelegate = new InboundProcessingAdviceDelegate();
        ProvideAndRegisterDocumentSetRequestType body = new ProvideAndRegisterDocumentSetRequestType();
        AssertionType assertion = new AssertionType();
        Object[] args = { body, assertion };
        
        
        inboundProcessingAdviceDelegate.setEventRecorder(eventRecorder);
        inboundProcessingAdviceDelegate.setEventFactory(eventFactory);
        inboundProcessingAdviceDelegate.setMessageRoutingAccessor(messageRoutingAccessor);
        
        inboundProcessingAdviceDelegate.begin(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(BeginInboundProcessingEvent.class));
        
        inboundProcessingAdviceDelegate.end(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(EndInboundProcessingEvent.class));
        
    }
    
    @Test
    public void adapterDelegationAdviceDelegate() {
        AdapterDelegationAdviceDelegate adapterDelegationAdviceDelegate = new AdapterDelegationAdviceDelegate();
    
        ProvideAndRegisterDocumentSetRequestType body = new ProvideAndRegisterDocumentSetRequestType();
        AssertionType assertion = new AssertionType();
        Object[] args = { body, assertion };
        
        adapterDelegationAdviceDelegate.setEventFactory(eventFactory);
        adapterDelegationAdviceDelegate.setEventRecorder(eventRecorder);
        adapterDelegationAdviceDelegate.setMessageRoutingAccessor(messageRoutingAccessor);
        
        adapterDelegationAdviceDelegate.begin(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(BeginAdapterDelegationEvent.class));
        
        adapterDelegationAdviceDelegate.end(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(EndAdapterDelegationEvent.class));
    }
    
    @Test
    public void outboundMessageAdviceDelegate() {
        OutboundMessageAdviceDelegate outboundMessageAdviceDelegate = new OutboundMessageAdviceDelegate();
        
        Object[] args = {};
        
        outboundMessageAdviceDelegate.setEventRecorder(eventRecorder);
        outboundMessageAdviceDelegate.setMessageRoutingAccessor(messageRoutingAccessor);
        outboundMessageAdviceDelegate.setEventFactory(eventFactory);
        
        assertNotNull(outboundMessageAdviceDelegate.createBeginEvent());
        assertNotNull(outboundMessageAdviceDelegate.createEndEvent());
        
        outboundMessageAdviceDelegate.begin(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(BeginOutboundMessageEvent.class));
        
        outboundMessageAdviceDelegate.end(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(EndOutboundMessageEvent.class));
    }
    
    @Test
    public void outboundProcessingAdviceDelegate() {
        OutboundProcessingAdviceDelegate outboundProcessingAdviceDelegate = new OutboundProcessingAdviceDelegate();
        
        Object[] args = {};
        
        outboundProcessingAdviceDelegate.setEventRecorder(eventRecorder);
        outboundProcessingAdviceDelegate.setMessageRoutingAccessor(messageRoutingAccessor);
        outboundProcessingAdviceDelegate.setEventFactory(eventFactory);
        
        assertNotNull(outboundProcessingAdviceDelegate.createBeginEvent());
        assertNotNull(outboundProcessingAdviceDelegate.createEndEvent());
        
        outboundProcessingAdviceDelegate.begin(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(BeginOutboundProcessingEvent.class));
        
        outboundProcessingAdviceDelegate.end(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(EndOutboundProcessingEvent.class));
    }
    
    
    @Test
    public void nwhinInvocationAdviceDelegate() {
        NwhinInvocationAdviceDelegate nwhinInvocationAdviceDelegate = new NwhinInvocationAdviceDelegate();
        
        Object[] args = {};
        
        nwhinInvocationAdviceDelegate.setEventRecorder(eventRecorder);
        nwhinInvocationAdviceDelegate.setMessageRoutingAccessor(messageRoutingAccessor);
        nwhinInvocationAdviceDelegate.setEventFactory(eventFactory);
        
        assertNotNull(nwhinInvocationAdviceDelegate.createBeginEvent());
        assertNotNull(nwhinInvocationAdviceDelegate.createEndEvent());
        
        nwhinInvocationAdviceDelegate.begin(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(BeginNwhinInvocationEvent.class));
        
        nwhinInvocationAdviceDelegate.end(args, DS_SERVICE_TYPE, DS_VERISON, DefaultEventDescriptionBuilder.class);
        verify(eventRecorder).recordEvent(isA(EndNwhinInvocationEvent.class));
    }
    
}