
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
    "packName",
    "packID",
    "apps"
})
public class Pack implements Serializable
{

    @JsonProperty("packName")
    private String packName;
    @JsonProperty("packID")
    private Long packID;
    @JsonProperty("apps")
    private List<App> apps = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1129770280059912005L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pack() {
    }

    /**
     * 
     * @param apps
     * @param packID
     * @param packName
     */
    public Pack(String packName, Long packID, List<App> apps) {
        super();
        this.packName = packName;
        this.packID = packID;
        this.apps = apps;
    }

    @JsonProperty("packName")
    public String getPackName() {
        return packName;
    }

    @JsonProperty("packName")
    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Pack withPackName(String packName) {
        this.packName = packName;
        return this;
    }

    @JsonProperty("packID")
    public Long getPackID() {
        return packID;
    }

    @JsonProperty("packID")
    public void setPackID(Long packID) {
        this.packID = packID;
    }

    public Pack withPackID(Long packID) {
        this.packID = packID;
        return this;
    }

    @JsonProperty("apps")
    public List<App> getApps() {
        return apps;
    }

    @JsonProperty("apps")
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public Pack withApps(List<App> apps) {
        this.apps = apps;
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

    public Pack withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("packName", packName).append("packID", packID).append("apps", apps).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(apps).append(packID).append(packName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pack) == false) {
            return false;
        }
        Pack rhs = ((Pack) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(apps, rhs.apps).append(packID, rhs.packID).append(packName, rhs.packName).isEquals();
    }

}
