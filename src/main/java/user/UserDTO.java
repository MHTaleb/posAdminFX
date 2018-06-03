
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
    "username",
    "userID",
    "packs",
    "aclName"
})
public class UserDTO implements Serializable
{

    @JsonProperty("username")
    private String username;
    @JsonProperty("userID")
    private Long userID;
    @JsonProperty("packs")
    private List<Pack> packs = null;
    @JsonProperty("aclName")
    private String aclName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2265727826174015333L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserDTO() {
    }

    /**
     * 
     * @param aclName
     * @param userID
     * @param username
     * @param packs
     */
    public UserDTO(String username, Long userID, List<Pack> packs, String aclName) {
        super();
        this.username = username;
        this.userID = userID;
        this.packs = packs;
        this.aclName = aclName;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public UserDTO withUsername(String username) {
        this.username = username;
        return this;
    }

    @JsonProperty("userID")
    public Long getUserID() {
        return userID;
    }

    @JsonProperty("userID")
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public UserDTO withUserID(Long userID) {
        this.userID = userID;
        return this;
    }

    @JsonProperty("packs")
    public List<Pack> getPacks() {
        return packs;
    }

    @JsonProperty("packs")
    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public UserDTO withPacks(List<Pack> packs) {
        this.packs = packs;
        return this;
    }

    @JsonProperty("aclName")
    public String getAclName() {
        return aclName;
    }

    @JsonProperty("aclName")
    public void setAclName(String aclName) {
        this.aclName = aclName;
    }

    public UserDTO withAclName(String aclName) {
        this.aclName = aclName;
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

    public UserDTO withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", username).append("userID", userID).append("packs", packs).append("aclName", aclName).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(aclName).append(userID).append(username).append(additionalProperties).append(packs).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserDTO) == false) {
            return false;
        }
        UserDTO rhs = ((UserDTO) other);
        return new EqualsBuilder().append(aclName, rhs.aclName).append(userID, rhs.userID).append(username, rhs.username).append(additionalProperties, rhs.additionalProperties).append(packs, rhs.packs).isEquals();
    }

}
