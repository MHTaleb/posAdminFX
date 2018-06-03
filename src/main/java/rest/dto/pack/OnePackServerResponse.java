/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.dto.pack;



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
import rest.dto.pack.PackContent;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"message",
"content",
"sequence"
})
public class OnePackServerResponse implements Serializable
{

@JsonProperty("message")
private String message;
@JsonProperty("content")
private PackContent content = null;
@JsonProperty("sequence")
private Object sequence;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = -4881058647776737333L;

/**
* No args constructor for use in serialization
* 
*/
public OnePackServerResponse() {
}

/**
* 
* @param content
* @param message
* @param sequence
*/
public OnePackServerResponse(String message, PackContent content, Object sequence) {
super();
this.message = message;
this.content = content;
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

public OnePackServerResponse withMessage(String message) {
this.message = message;
return this;
}

@JsonProperty("content")
public PackContent getPackContent() {
return content;
}

@JsonProperty("content")
public void setPackContent(PackContent content) {
this.content = content;
}

public OnePackServerResponse withPackContent(PackContent content) {
this.content = content;
return this;
}

@JsonProperty("sequence")
public Object getSequence() {
return sequence;
}

@JsonProperty("sequence")
public void setSequence(Object sequence) {
this.sequence = sequence;
}

public OnePackServerResponse withSequence(Object sequence) {
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

public OnePackServerResponse withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("message", message).append("content", content).append("sequence", sequence).append("additionalProperties", additionalProperties).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(content).append(message).append(additionalProperties).append(sequence).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof OnePackServerResponse) == false) {
return false;
}
OnePackServerResponse rhs = ((OnePackServerResponse) other);
return new EqualsBuilder().append(content, rhs.content).append(message, rhs.message).append(additionalProperties, rhs.additionalProperties).append(sequence, rhs.sequence).isEquals();
}

}