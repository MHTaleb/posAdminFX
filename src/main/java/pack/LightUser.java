
package pack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "username",
    "userID"
})
public class LightUser implements Serializable
{

    @JsonProperty("username")
    private String username;
    @JsonProperty("userID")
    private Long userID;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4141013347727463182L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LightUser() {
    }

    /**
     * 
     * @param aclName
     * @param userID
     * @param username
     * @param packs
     */
    public LightUser(String username, Long userID) {
        super();
        this.username = username;
        this.userID = userID;

    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public LightUser withUsername(String username) {
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

    public LightUser withUserID(Long userID) {
        this.userID = userID;
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

    public LightUser withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.userID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LightUser other = (LightUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.userID, other.userID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LightUser{" + "username=" + username + ", userID=" + userID + ", additionalProperties=" + additionalProperties + '}';
    }

   
    

}
