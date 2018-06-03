
package language;

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
    "message",
    "langs",
    "sequence"
})
public class LanguagesServerResponseList implements Serializable
{

    @JsonProperty("message")
    private String message;
    @JsonProperty("content")
    private List<Lang> langs = null;
    @JsonProperty("sequence")
    private Object sequence;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7352774712728539546L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LanguagesServerResponseList() {
    }

    /**
     * 
     * @param message
     * @param sequence
     * @param langs
     */
    public LanguagesServerResponseList(String message, List<Lang> langs, Object sequence) {
        super();
        this.message = message;
        this.langs = langs;
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

    public LanguagesServerResponseList withMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("langs")
    public List<Lang> getLangs() {
        return langs;
    }

    @JsonProperty("langs")
    public void setLangs(List<Lang> langs) {
        this.langs = langs;
    }

    public LanguagesServerResponseList withLangs(List<Lang> langs) {
        this.langs = langs;
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

    public LanguagesServerResponseList withSequence(Object sequence) {
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

    public LanguagesServerResponseList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("message", message).append("langs", langs).append("sequence", sequence).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(additionalProperties).append(sequence).append(langs).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LanguagesServerResponseList) == false) {
            return false;
        }
        LanguagesServerResponseList rhs = ((LanguagesServerResponseList) other);
        return new EqualsBuilder().append(message, rhs.message).append(additionalProperties, rhs.additionalProperties).append(sequence, rhs.sequence).append(langs, rhs.langs).isEquals();
    }

}
