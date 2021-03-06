
package user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "cmp_attr_label",
    "cmp_attr_value",
    "cmp_attr_code"
})
public class CmpData implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("cmp_attr_label")
    private String cmpAttrLabel;
    @JsonProperty("cmp_attr_value")
    private String cmpAttrValue;
    @JsonProperty("cmp_attr_code")
    private String cmpAttrCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8983469382560591164L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CmpData() {
    }

    /**
     * 
     * @param id
     * @param cmpAttrValue
     * @param cmpAttrCode
     * @param cmpAttrLabel
     */
    public CmpData(Long id, String cmpAttrLabel, String cmpAttrValue, String cmpAttrCode) {
        super();
        this.id = id;
        this.cmpAttrLabel = cmpAttrLabel;
        this.cmpAttrValue = cmpAttrValue;
        this.cmpAttrCode = cmpAttrCode;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public CmpData withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("cmp_attr_label")
    public String getCmpAttrLabel() {
        return cmpAttrLabel;
    }

    @JsonProperty("cmp_attr_label")
    public void setCmpAttrLabel(String cmpAttrLabel) {
        this.cmpAttrLabel = cmpAttrLabel;
    }

    public CmpData withCmpAttrLabel(String cmpAttrLabel) {
        this.cmpAttrLabel = cmpAttrLabel;
        return this;
    }

    @JsonProperty("cmp_attr_value")
    public String getCmpAttrValue() {
        return cmpAttrValue;
    }

    @JsonProperty("cmp_attr_value")
    public void setCmpAttrValue(String cmpAttrValue) {
        this.cmpAttrValue = cmpAttrValue;
    }

    public CmpData withCmpAttrValue(String cmpAttrValue) {
        this.cmpAttrValue = cmpAttrValue;
        return this;
    }

    @JsonProperty("cmp_attr_code")
    public String getCmpAttrCode() {
        return cmpAttrCode;
    }

    @JsonProperty("cmp_attr_code")
    public void setCmpAttrCode(String cmpAttrCode) {
        this.cmpAttrCode = cmpAttrCode;
    }

    public CmpData withCmpAttrCode(String cmpAttrCode) {
        this.cmpAttrCode = cmpAttrCode;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public CmpData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("cmpAttrLabel", cmpAttrLabel).append("cmpAttrValue", cmpAttrValue).append("cmpAttrCode", cmpAttrCode).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(cmpAttrValue).append(additionalProperties).append(cmpAttrCode).append(cmpAttrLabel).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CmpData) == false) {
            return false;
        }
        CmpData rhs = ((CmpData) other);
        return new EqualsBuilder().append(id, rhs.id).append(cmpAttrValue, rhs.cmpAttrValue).append(additionalProperties, rhs.additionalProperties).append(cmpAttrCode, rhs.cmpAttrCode).append(cmpAttrLabel, rhs.cmpAttrLabel).isEquals();
    }

}
