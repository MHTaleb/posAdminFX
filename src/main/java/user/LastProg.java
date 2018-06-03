
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
    "programName"
})
public class LastProg implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("programName")
    private String programName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5557428780281423798L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastProg() {
    }

    /**
     * 
     * @param id
     * @param programName
     */
    public LastProg(Long id, String programName) {
        super();
        this.id = id;
        this.programName = programName;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public LastProg withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("programName")
    public String getProgramName() {
        return programName;
    }

    @JsonProperty("programName")
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public LastProg withProgramName(String programName) {
        this.programName = programName;
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

    public LastProg withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("programName", programName).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(additionalProperties).append(programName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LastProg) == false) {
            return false;
        }
        LastProg rhs = ((LastProg) other);
        return new EqualsBuilder().append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(programName, rhs.programName).isEquals();
    }

}
