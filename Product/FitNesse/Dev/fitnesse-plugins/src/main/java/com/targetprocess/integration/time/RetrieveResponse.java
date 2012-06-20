
package com.targetprocess.integration.time;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RetrieveResult" type="{http://targetprocess.com}ArrayOfTimeDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "retrieveResult"
})
@XmlRootElement(name = "RetrieveResponse")
public class RetrieveResponse {

    @XmlElement(name = "RetrieveResult")
    protected ArrayOfTimeDTO retrieveResult;

    /**
     * Gets the value of the retrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimeDTO }
     *     
     */
    public ArrayOfTimeDTO getRetrieveResult() {
        return retrieveResult;
    }

    /**
     * Sets the value of the retrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimeDTO }
     *     
     */
    public void setRetrieveResult(ArrayOfTimeDTO value) {
        this.retrieveResult = value;
    }

}