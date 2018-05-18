/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.state;

/**
 *
 * @author taleb
 */
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "message",
    "content",
    "sequence"
})
public class ServerRolesResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("content")
    private List<String> roles = null;
    @JsonProperty("sequence")
    private String sequence;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ServerRolesResponse() {
    }

    /**
     *
     * @param content
     * @param message
     * @param sequence
     */
    public ServerRolesResponse(String message, List<String> content, String sequence) {
        super();
        this.message = message;
        this.roles = content;
        this.sequence = sequence;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public ServerRolesResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("content")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("content")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public ServerRolesResponse withContent(List<String> content) {
        this.roles = content;
        return this;
    }

    @JsonProperty("sequence")
    public String getSequence() {
        return sequence;
    }

    @JsonProperty("sequence")
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public ServerRolesResponse withSequence(String sequence) {
        this.sequence = sequence;
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

    public ServerRolesResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("message", message).append("content", roles).append("sequence", sequence).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(roles).append(message).append(additionalProperties).append(sequence).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServerRolesResponse) == false) {
            return false;
        }
        ServerRolesResponse rhs = ((ServerRolesResponse) other);
        return new EqualsBuilder().append(roles, rhs.roles).append(message, rhs.message).append(additionalProperties, rhs.additionalProperties).append(sequence, rhs.sequence).isEquals();
    }

}
