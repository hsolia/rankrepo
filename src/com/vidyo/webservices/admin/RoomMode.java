
package com.vidyo.webservices.admin;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="roomURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isLocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="hasPin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="roomPIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "roomURL",
    "isLocked",
    "hasPin",
    "roomPIN"
})
@XmlRootElement(name = "RoomMode")
public class RoomMode {

    @XmlElementRef(name = "roomURL", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class)
    protected JAXBElement<String> roomURL;
    protected boolean isLocked;
    protected boolean hasPin;
    @XmlElementRef(name = "roomPIN", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class)
    protected JAXBElement<String> roomPIN;

    /**
     * Gets the value of the roomURL property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoomURL() {
        return roomURL;
    }

    /**
     * Sets the value of the roomURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoomURL(JAXBElement<String> value) {
        this.roomURL = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the isLocked property.
     * 
     */
    public boolean isIsLocked() {
        return isLocked;
    }

    /**
     * Sets the value of the isLocked property.
     * 
     */
    public void setIsLocked(boolean value) {
        this.isLocked = value;
    }

    /**
     * Gets the value of the hasPin property.
     * 
     */
    public boolean isHasPin() {
        return hasPin;
    }

    /**
     * Sets the value of the hasPin property.
     * 
     */
    public void setHasPin(boolean value) {
        this.hasPin = value;
    }

    /**
     * Gets the value of the roomPIN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoomPIN() {
        return roomPIN;
    }

    /**
     * Sets the value of the roomPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoomPIN(JAXBElement<String> value) {
        this.roomPIN = ((JAXBElement<String> ) value);
    }

}
