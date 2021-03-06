
package ch.iec.tc57._2011.schema.message;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDKindType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IDKindType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="name"/&gt;
 *     &lt;enumeration value="uuid"/&gt;
 *     &lt;enumeration value="transaction"/&gt;
 *     &lt;enumeration value="other"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "IDKindType")
@XmlEnum
public enum IDKindType {

    @XmlEnumValue("name")
    NAME("name"),
    @XmlEnumValue("uuid")
    UUID("uuid"),
    @XmlEnumValue("transaction")
    TRANSACTION("transaction"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    IDKindType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IDKindType fromValue(String v) {
        for (IDKindType c: IDKindType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
