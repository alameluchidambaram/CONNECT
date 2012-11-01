/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
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
package gov.hhs.fha.nhinc.docretrieve.aspect;

import gov.hhs.fha.nhinc.event.BaseEventDescriptionBuilder;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType.DocumentResponse;

import java.util.List;

import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

public class RetrieveDocumentSetResponseTypeDescriptionBuilder extends BaseEventDescriptionBuilder {

    private static final HCIDExtractor HCID_EXTRACTOR = new HCIDExtractor();
    private static final ErrorCodeExtractor ERROR_CODE_EXTRACTOR = new ErrorCodeExtractor();
    private RetrieveDocumentSetResponseType response;

    public RetrieveDocumentSetResponseTypeDescriptionBuilder(RetrieveDocumentSetResponseType response) {
        this.response = response;
    }

    @Override
    public void buildTimeStamp() {
    }

    @Override
    public void buildStatuses() {
        if (response != null) {
            setStatuses(ImmutableList.of(response.getRegistryResponse().getStatus()));
        }
    }

    @Override
    public void buildRespondingHCIDs() {
        if (response != null) {
            List<String> listWithDups = Lists.transform(response.getDocumentResponse(), HCID_EXTRACTOR);
            setRespondingHCIDs(ImmutableSet.copyOf(listWithDups).asList());
        }
    }

    @Override
    public void buildPayloadTypes() {
    }

    @Override
    public void buildPayloadSize() {
    }

    @Override
    public void buildNPI() {
    }

    @Override
    public void buildInitiatingHCID() {
    }

    @Override
    public void buildErrorCodes() {
        if (response != null && response.getRegistryResponse().getRegistryErrorList() != null) {
            List<String> listWithDups = Lists.transform(response.getRegistryResponse().getRegistryErrorList()
                    .getRegistryError(), ERROR_CODE_EXTRACTOR);
            setErrorCodes(ImmutableSet.copyOf(listWithDups).asList());
        }
    }

    private static class HCIDExtractor implements Function<DocumentResponse, String> {

        @Override
        public String apply(DocumentResponse documentResponse) {
            return documentResponse.getHomeCommunityId();
        }
    }

    private static class ErrorCodeExtractor implements Function<RegistryError, String> {

        @Override
        public String apply(RegistryError registryError) {
            return registryError.getErrorCode();
        }
    }

    @Override
    public void setArguments(Object... aguements) {
        // TODO Auto-generated method stub
        
    }
}