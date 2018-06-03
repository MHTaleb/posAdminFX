
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
    "packName",
    "mng_composants"
})
public class MngPACK implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("packName")
    private String packName;
    @JsonProperty("mng_composants")
    private List<MngComposant> mngComposants = new ArrayList<MngComposant>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2792944127451162637L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MngPACK() {
    }

    /**
     * 
     * @param id
     * @param mngComposants
     * @param packName
     */
    public MngPACK(Long id, String packName, List<MngComposant> mngComposants) {
        super();
        this.id = id;
        this.packName = packName;
        this.mngComposants = mngComposants;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public MngPACK withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("packName")
    public String getPackName() {
        return packName;
    }

    @JsonProperty("packName")
    public void setPackName(String packName) {
        this.packName = packName;
    }

    public MngPACK withPackName(String packName) {
        this.packName = packName;
        return this;
    }

    @JsonProperty("mng_composants")
    public List<MngComposant> getMngComposants() {
        return mngComposants;
    }

    @JsonProperty("mng_composants")
    public void setMngComposants(List<MngComposant> mngComposants) {
        this.mngComposants = mngComposants;
    }

    public MngPACK withMngComposants(List<MngComposant> mngComposants) {
        this.mngComposants = mngComposants;
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

    public MngPACK withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("packName", packName).append("mngComposants", mngComposants).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(additionalProperties).append(mngComposants).append(packName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MngPACK) == false) {
            return false;
        }
        MngPACK rhs = ((MngPACK) other);
        return new EqualsBuilder().append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(mngComposants, rhs.mngComposants).append(packName, rhs.packName).isEquals();
    }

}
