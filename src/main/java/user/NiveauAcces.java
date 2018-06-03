
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
    "aclTitle",
    "roles"
})
public class NiveauAcces implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("aclTitle")
    private String aclTitle;
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1662307612004309239L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NiveauAcces() {
    }

    /**
     * 
     * @param id
     * @param roles
     * @param aclTitle
     */
    public NiveauAcces(Long id, String aclTitle, List<String> roles) {
        super();
        this.id = id;
        this.aclTitle = aclTitle;
        this.roles = roles;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public NiveauAcces withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("aclTitle")
    public String getAclTitle() {
        return aclTitle;
    }

    @JsonProperty("aclTitle")
    public void setAclTitle(String aclTitle) {
        this.aclTitle = aclTitle;
    }

    public NiveauAcces withAclTitle(String aclTitle) {
        this.aclTitle = aclTitle;
        return this;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public NiveauAcces withRoles(List<String> roles) {
        this.roles = roles;
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

    public NiveauAcces withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("aclTitle", aclTitle).append("roles", roles).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(additionalProperties).append(roles).append(aclTitle).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NiveauAcces) == false) {
            return false;
        }
        NiveauAcces rhs = ((NiveauAcces) other);
        return new EqualsBuilder().append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(roles, rhs.roles).append(aclTitle, rhs.aclTitle).isEquals();
    }

}
