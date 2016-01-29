/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.patientdiscovery.nhin.proxy;

import gov.hhs.fha.nhinc.responsewrapper.NwhinResponseWrapper;
import org.hl7.v3.PRPAIN201305UV02;
import org.hl7.v3.PRPAIN201306UV02;

/**
 *
 * @author achidamb
 * @param <T>
 * @param <K>
 */
public class NwhinPDResponseWrapper<T extends PRPAIN201305UV02, K extends PRPAIN201306UV02> extends
    NwhinResponseWrapper<T, K> {

    @Override
    protected NwhinResponseWrapper getRespnseWrapper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
