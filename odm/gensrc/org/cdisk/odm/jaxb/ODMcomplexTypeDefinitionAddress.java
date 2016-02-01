//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.05 at 02:26:17 PM GMT 
//


package org.cdisk.odm.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ODMcomplexTypeDefinition-Address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ODMcomplexTypeDefinition-Address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}StreetName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}City" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}StateProv" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}Country" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}PostalCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}OtherText" minOccurs="0"/>
 *         &lt;group ref="{http://www.cdisc.org/ns/odm/v1.3}AddressElementExtension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.cdisc.org/ns/odm/v1.3}AddressAttributeExtension"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ODMcomplexTypeDefinition-Address", propOrder = {
    "streetName",
    "city",
    "stateProv",
    "country",
    "postalCode",
    "otherText"
})
public class ODMcomplexTypeDefinitionAddress {

    @XmlElement(name = "StreetName")
    protected List<ODMcomplexTypeDefinitionStreetName> streetName;
    @XmlElement(name = "City")
    protected ODMcomplexTypeDefinitionCity city;
    @XmlElement(name = "StateProv")
    protected ODMcomplexTypeDefinitionStateProv stateProv;
    @XmlElement(name = "Country")
    protected ODMcomplexTypeDefinitionCountry country;
    @XmlElement(name = "PostalCode")
    protected ODMcomplexTypeDefinitionPostalCode postalCode;
    @XmlElement(name = "OtherText")
    protected ODMcomplexTypeDefinitionOtherText otherText;

    /**
     * Gets the value of the streetName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the streetName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStreetName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ODMcomplexTypeDefinitionStreetName }
     * 
     * 
     */
    public List<ODMcomplexTypeDefinitionStreetName> getStreetName() {
        if (streetName == null) {
            streetName = new ArrayList<ODMcomplexTypeDefinitionStreetName>();
        }
        return this.streetName;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionCity }
     *     
     */
    public ODMcomplexTypeDefinitionCity getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionCity }
     *     
     */
    public void setCity(ODMcomplexTypeDefinitionCity value) {
        this.city = value;
    }

    /**
     * Gets the value of the stateProv property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionStateProv }
     *     
     */
    public ODMcomplexTypeDefinitionStateProv getStateProv() {
        return stateProv;
    }

    /**
     * Sets the value of the stateProv property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionStateProv }
     *     
     */
    public void setStateProv(ODMcomplexTypeDefinitionStateProv value) {
        this.stateProv = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionCountry }
     *     
     */
    public ODMcomplexTypeDefinitionCountry getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionCountry }
     *     
     */
    public void setCountry(ODMcomplexTypeDefinitionCountry value) {
        this.country = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionPostalCode }
     *     
     */
    public ODMcomplexTypeDefinitionPostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionPostalCode }
     *     
     */
    public void setPostalCode(ODMcomplexTypeDefinitionPostalCode value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the otherText property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionOtherText }
     *     
     */
    public ODMcomplexTypeDefinitionOtherText getOtherText() {
        return otherText;
    }

    /**
     * Sets the value of the otherText property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionOtherText }
     *     
     */
    public void setOtherText(ODMcomplexTypeDefinitionOtherText value) {
        this.otherText = value;
    }

}
