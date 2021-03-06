
package user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "cmp_type",
    "cmp_parents",
    "cmp_fils",
    "cmp_datas"
})
public class MngComposant implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("cmp_type")
    private String cmpType;
    @JsonProperty("cmp_parents")
    private List<Object> cmpParents = new ArrayList<Object>();
    @JsonProperty("cmp_fils")
    private List<Object> cmpFils = new ArrayList<Object>();
    @JsonProperty("cmp_datas")
    private List<CmpData> cmpDatas = new ArrayList<CmpData>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 282126448645462056L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MngComposant() {
    }

    /**
     * 
     * @param id
     * @param cmpFils
     * @param cmpType
     * @param cmpDatas
     * @param cmpParents
     */
    public MngComposant(Long id, String cmpType, List<Object> cmpParents, List<Object> cmpFils, List<CmpData> cmpDatas) {
        super();
        this.id = id;
        this.cmpType = cmpType;
        this.cmpParents = cmpParents;
        this.cmpFils = cmpFils;
        this.cmpDatas = cmpDatas;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public MngComposant withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("cmp_type")
    public String getCmpType() {
        return cmpType;
    }

    @JsonProperty("cmp_type")
    public void setCmpType(String cmpType) {
        this.cmpType = cmpType;
    }

    public MngComposant withCmpType(String cmpType) {
        this.cmpType = cmpType;
        return this;
    }

    @JsonProperty("cmp_parents")
    public List<Object> getCmpParents() {
        return cmpParents;
    }

    @JsonProperty("cmp_parents")
    public void setCmpParents(List<Object> cmpParents) {
        this.cmpParents = cmpParents;
    }

    public MngComposant withCmpParents(List<Object> cmpParents) {
        this.cmpParents = cmpParents;
        return this;
    }

    @JsonProperty("cmp_fils")
    public List<Object> getCmpFils() {
        return cmpFils;
    }

    @JsonProperty("cmp_fils")
    public void setCmpFils(List<Object> cmpFils) {
        this.cmpFils = cmpFils;
    }

    public MngComposant withCmpFils(List<Object> cmpFils) {
        this.cmpFils = cmpFils;
        return this;
    }

    @JsonProperty("cmp_datas")
    public List<CmpData> getCmpDatas() {
        return cmpDatas;
    }

    @JsonProperty("cmp_datas")
    public void setCmpDatas(List<CmpData> cmpDatas) {
        this.cmpDatas = cmpDatas;
    }

    public MngComposant withCmpDatas(List<CmpData> cmpDatas) {
        this.cmpDatas = cmpDatas;
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

    public MngComposant withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("cmpType", cmpType).append("cmpParents", cmpParents).append("cmpFils", cmpFils).append("cmpDatas", cmpDatas).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(cmpFils).append(additionalProperties).append(cmpType).append(cmpDatas).append(cmpParents).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MngComposant) == false) {
            return false;
        }
        MngComposant rhs = ((MngComposant) other);
        return new EqualsBuilder().append(id, rhs.id).append(cmpFils, rhs.cmpFils).append(additionalProperties, rhs.additionalProperties).append(cmpType, rhs.cmpType).append(cmpDatas, rhs.cmpDatas).append(cmpParents, rhs.cmpParents).isEquals();
    }

}
