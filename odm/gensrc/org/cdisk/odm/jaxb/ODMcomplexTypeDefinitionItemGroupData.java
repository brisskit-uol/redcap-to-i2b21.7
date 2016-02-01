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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ODMcomplexTypeDefinition-ItemGroupData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ODMcomplexTypeDefinition-ItemGroupData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}AuditRecord" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}Signature" minOccurs="0"/>
 *         &lt;element ref="{http://www.cdisc.org/ns/odm/v1.3}Annotation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;group ref="{http://www.cdisc.org/ns/odm/v1.3}ItemDataGroup" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;group ref="{http://www.cdisc.org/ns/odm/v1.3}ItemDataStarGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.cdisc.org/ns/odm/v1.3}ItemGroupDataElementExtension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.cdisc.org/ns/odm/v1.3}ItemGroupDataAttributeExtension"/>
 *       &lt;attGroup ref="{http://www.cdisc.org/ns/odm/v1.3}ItemGroupDataAttributeDefinition"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ODMcomplexTypeDefinition-ItemGroupData", propOrder = {
    "auditRecord",
    "signature",
    "annotation",
    "itemDataGroup",
    "itemDataStarGroup"
})
public class ODMcomplexTypeDefinitionItemGroupData {

    @XmlElement(name = "AuditRecord")
    protected ODMcomplexTypeDefinitionAuditRecord auditRecord;
    @XmlElement(name = "Signature")
    protected ODMcomplexTypeDefinitionSignature signature;
    @XmlElement(name = "Annotation")
    protected List<ODMcomplexTypeDefinitionAnnotation> annotation;
    @XmlElement(name = "ItemData")
    protected List<ODMcomplexTypeDefinitionItemData> itemDataGroup;
    @XmlElements({
        @XmlElement(name = "ItemDataURI", type = ODMcomplexTypeDefinitionItemDataURI.class),
        @XmlElement(name = "ItemDataAny", type = ODMcomplexTypeDefinitionItemDataAny.class),
        @XmlElement(name = "ItemDataBoolean", type = ODMcomplexTypeDefinitionItemDataBoolean.class),
        @XmlElement(name = "ItemDataString", type = ODMcomplexTypeDefinitionItemDataString.class),
        @XmlElement(name = "ItemDataInteger", type = ODMcomplexTypeDefinitionItemDataInteger.class),
        @XmlElement(name = "ItemDataFloat", type = ODMcomplexTypeDefinitionItemDataFloat.class),
        @XmlElement(name = "ItemDataDouble", type = ODMcomplexTypeDefinitionItemDataDouble.class),
        @XmlElement(name = "ItemDataDate", type = ODMcomplexTypeDefinitionItemDataDate.class),
        @XmlElement(name = "ItemDataTime", type = ODMcomplexTypeDefinitionItemDataTime.class),
        @XmlElement(name = "ItemDataDatetime", type = ODMcomplexTypeDefinitionItemDataDatetime.class),
        @XmlElement(name = "ItemDataHexBinary", type = ODMcomplexTypeDefinitionItemDataHexBinary.class),
        @XmlElement(name = "ItemDataBase64Binary", type = ODMcomplexTypeDefinitionItemDataBase64Binary.class),
        @XmlElement(name = "ItemDataHexFloat", type = ODMcomplexTypeDefinitionItemDataHexFloat.class),
        @XmlElement(name = "ItemDataBase64Float", type = ODMcomplexTypeDefinitionItemDataBase64Float.class),
        @XmlElement(name = "ItemDataPartialDate", type = ODMcomplexTypeDefinitionItemDataPartialDate.class),
        @XmlElement(name = "ItemDataPartialTime", type = ODMcomplexTypeDefinitionItemDataPartialTime.class),
        @XmlElement(name = "ItemDataPartialDatetime", type = ODMcomplexTypeDefinitionItemDataPartialDatetime.class),
        @XmlElement(name = "ItemDataDurationDatetime", type = ODMcomplexTypeDefinitionItemDataDurationDatetime.class),
        @XmlElement(name = "ItemDataIntervalDatetime", type = ODMcomplexTypeDefinitionItemDataIntervalDatetime.class),
        @XmlElement(name = "ItemDataIncompleteDatetime", type = ODMcomplexTypeDefinitionItemDataIncompleteDatetime.class),
        @XmlElement(name = "ItemDataIncompleteDate", type = ODMcomplexTypeDefinitionItemDataIncompleteDate.class),
        @XmlElement(name = "ItemDataIncompleteTime", type = ODMcomplexTypeDefinitionItemDataIncompleteTime.class)
    })
    protected List<Object> itemDataStarGroup;
    @XmlAttribute(name = "ItemGroupOID", required = true)
    protected String itemGroupOID;
    @XmlAttribute(name = "ItemGroupRepeatKey")
    protected String itemGroupRepeatKey;
    @XmlAttribute(name = "TransactionType")
    protected TransactionType transactionType;

    /**
     * Gets the value of the auditRecord property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionAuditRecord }
     *     
     */
    public ODMcomplexTypeDefinitionAuditRecord getAuditRecord() {
        return auditRecord;
    }

    /**
     * Sets the value of the auditRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionAuditRecord }
     *     
     */
    public void setAuditRecord(ODMcomplexTypeDefinitionAuditRecord value) {
        this.auditRecord = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link ODMcomplexTypeDefinitionSignature }
     *     
     */
    public ODMcomplexTypeDefinitionSignature getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link ODMcomplexTypeDefinitionSignature }
     *     
     */
    public void setSignature(ODMcomplexTypeDefinitionSignature value) {
        this.signature = value;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the annotation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ODMcomplexTypeDefinitionAnnotation }
     * 
     * 
     */
    public List<ODMcomplexTypeDefinitionAnnotation> getAnnotation() {
        if (annotation == null) {
            annotation = new ArrayList<ODMcomplexTypeDefinitionAnnotation>();
        }
        return this.annotation;
    }

    /**
     * Gets the value of the itemDataGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemDataGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemDataGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ODMcomplexTypeDefinitionItemData }
     * 
     * 
     */
    public List<ODMcomplexTypeDefinitionItemData> getItemDataGroup() {
        if (itemDataGroup == null) {
            itemDataGroup = new ArrayList<ODMcomplexTypeDefinitionItemData>();
        }
        return this.itemDataGroup;
    }

    /**
     * Gets the value of the itemDataStarGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemDataStarGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemDataStarGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ODMcomplexTypeDefinitionItemDataURI }
     * {@link ODMcomplexTypeDefinitionItemDataAny }
     * {@link ODMcomplexTypeDefinitionItemDataBoolean }
     * {@link ODMcomplexTypeDefinitionItemDataString }
     * {@link ODMcomplexTypeDefinitionItemDataInteger }
     * {@link ODMcomplexTypeDefinitionItemDataFloat }
     * {@link ODMcomplexTypeDefinitionItemDataDouble }
     * {@link ODMcomplexTypeDefinitionItemDataDate }
     * {@link ODMcomplexTypeDefinitionItemDataTime }
     * {@link ODMcomplexTypeDefinitionItemDataDatetime }
     * {@link ODMcomplexTypeDefinitionItemDataHexBinary }
     * {@link ODMcomplexTypeDefinitionItemDataBase64Binary }
     * {@link ODMcomplexTypeDefinitionItemDataHexFloat }
     * {@link ODMcomplexTypeDefinitionItemDataBase64Float }
     * {@link ODMcomplexTypeDefinitionItemDataPartialDate }
     * {@link ODMcomplexTypeDefinitionItemDataPartialTime }
     * {@link ODMcomplexTypeDefinitionItemDataPartialDatetime }
     * {@link ODMcomplexTypeDefinitionItemDataDurationDatetime }
     * {@link ODMcomplexTypeDefinitionItemDataIntervalDatetime }
     * {@link ODMcomplexTypeDefinitionItemDataIncompleteDatetime }
     * {@link ODMcomplexTypeDefinitionItemDataIncompleteDate }
     * {@link ODMcomplexTypeDefinitionItemDataIncompleteTime }
     * 
     * 
     */
    public List<Object> getItemDataStarGroup() {
        if (itemDataStarGroup == null) {
            itemDataStarGroup = new ArrayList<Object>();
        }
        return this.itemDataStarGroup;
    }

    /**
     * Gets the value of the itemGroupOID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemGroupOID() {
        return itemGroupOID;
    }

    /**
     * Sets the value of the itemGroupOID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemGroupOID(String value) {
        this.itemGroupOID = value;
    }

    /**
     * Gets the value of the itemGroupRepeatKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemGroupRepeatKey() {
        return itemGroupRepeatKey;
    }

    /**
     * Sets the value of the itemGroupRepeatKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemGroupRepeatKey(String value) {
        this.itemGroupRepeatKey = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionType }
     *     
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionType }
     *     
     */
    public void setTransactionType(TransactionType value) {
        this.transactionType = value;
    }

}
