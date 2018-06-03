
package user;

import java.io.Serializable;
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
    "cmp_datas",
    "cmp_type"
})
public class App implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("cmp_datas")
    private List<CmpData> cmpDatas = null;
    @JsonProperty("cmp_type")
    private String cmpType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8495302129377138804L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public App() {
    }

    /**
     * 
     * @param id
     * @param cmpType
     * @param cmpDatas
     */
    public App(Long id, List<CmpData> cmpDatas, String cmpType) {
        super();
        this.id = id;
        this.cmpDatas = cmpDatas;
        this.cmpType = cmpType;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public App withId(Long id) {
        this.id = id;
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

    public App withCmpDatas(List<CmpData> cmpDatas) {
        this.cmpDatas = cmpDatas;
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

    public App withCmpType(String cmpType) {
        this.cmpType = cmpType;
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

    public App withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("cmpDatas", cmpDatas).append("cmpType", cmpType).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(additionalProperties).append(cmpType).append(cmpDatas).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof App) == false) {
            return false;
        }
        App rhs = ((App) other);
        return new EqualsBuilder().append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(cmpType, rhs.cmpType).append(cmpDatas, rhs.cmpDatas).isEquals();
    }

}
