
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
    "code_externe",
    "nom",
    "prenom",
    "password",
    "dateDebut",
    "dateFin",
    "nombreErreur",
    "dateCreation",
    "dateMiseAJour",
    "utilisateur",
    "lastConnexion",
    "etatUtilisateur",
    "langueUtilisateur",
    "typeUtilisateur",
    "lastProg",
    "niveauAcces",
    "handler",
    "mng_PACKs",
    "hibernateLazyInitializer"
})
public class OneUserDTO implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("code_externe")
    private String codeExterne;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("password")
    private String password;
    @JsonProperty("dateDebut")
    private String dateDebut;
    @JsonProperty("dateFin")
    private String dateFin;
    @JsonProperty("nombreErreur")
    private Long nombreErreur;
    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("utilisateur")
    private String utilisateur;
    @JsonProperty("lastConnexion")
    private String lastConnexion;
    @JsonProperty("etatUtilisateur")
    private Boolean etatUtilisateur;
    @JsonProperty("langueUtilisateur")
    private LangueUtilisateur langueUtilisateur;
    @JsonProperty("typeUtilisateur")
    private TypeUtilisateur typeUtilisateur;
    @JsonProperty("lastProg")
    private LastProg lastProg;
    @JsonProperty("niveauAcces")
    private NiveauAcces niveauAcces;
    @JsonProperty("handler")
    private Handler handler;
    @JsonProperty("mng_PACKs")
    private List<MngPACK> mngPACKs = new ArrayList<MngPACK>();
    @JsonProperty("hibernateLazyInitializer")
    private HibernateLazyInitializer hibernateLazyInitializer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6629788594206399643L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OneUserDTO() {
    }

    /**
     * 
     * @param dateCreation
     * @param hibernateLazyInitializer
     * @param codeExterne
     * @param lastProg
     * @param dateFin
     * @param niveauAcces
     * @param password
     * @param nom
     * @param dateDebut
     * @param id
     * @param prenom
     * @param nombreErreur
     * @param mngPACKs
     * @param typeUtilisateur
     * @param langueUtilisateur
     * @param etatUtilisateur
     * @param dateMiseAJour
     * @param lastConnexion
     * @param utilisateur
     * @param handler
     */
    public OneUserDTO(Long id, String codeExterne, String nom, String prenom, String password, String dateDebut, String dateFin, Long nombreErreur, String dateCreation, String dateMiseAJour, String utilisateur, String lastConnexion, Boolean etatUtilisateur, LangueUtilisateur langueUtilisateur, TypeUtilisateur typeUtilisateur, LastProg lastProg, NiveauAcces niveauAcces, Handler handler, List<MngPACK> mngPACKs, HibernateLazyInitializer hibernateLazyInitializer) {
        super();
        this.id = id;
        this.codeExterne = codeExterne;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreErreur = nombreErreur;
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.utilisateur = utilisateur;
        this.lastConnexion = lastConnexion;
        this.etatUtilisateur = etatUtilisateur;
        this.langueUtilisateur = langueUtilisateur;
        this.typeUtilisateur = typeUtilisateur;
        this.lastProg = lastProg;
        this.niveauAcces = niveauAcces;
        this.handler = handler;
        this.mngPACKs = mngPACKs;
        this.hibernateLazyInitializer = hibernateLazyInitializer;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public OneUserDTO withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("code_externe")
    public String getCodeExterne() {
        return codeExterne;
    }

    @JsonProperty("code_externe")
    public void setCodeExterne(String codeExterne) {
        this.codeExterne = codeExterne;
    }

    public OneUserDTO withCodeExterne(String codeExterne) {
        this.codeExterne = codeExterne;
        return this;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    public OneUserDTO withNom(String nom) {
        this.nom = nom;
        return this;
    }

    @JsonProperty("prenom")
    public String getPrenom() {
        return prenom;
    }

    @JsonProperty("prenom")
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public OneUserDTO withPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public OneUserDTO withPassword(String password) {
        this.password = password;
        return this;
    }

    @JsonProperty("dateDebut")
    public String getDateDebut() {
        return dateDebut;
    }

    @JsonProperty("dateDebut")
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public OneUserDTO withDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    @JsonProperty("dateFin")
    public String getDateFin() {
        return dateFin;
    }

    @JsonProperty("dateFin")
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public OneUserDTO withDateFin(String dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    @JsonProperty("nombreErreur")
    public Long getNombreErreur() {
        return nombreErreur;
    }

    @JsonProperty("nombreErreur")
    public void setNombreErreur(Long nombreErreur) {
        this.nombreErreur = nombreErreur;
    }

    public OneUserDTO withNombreErreur(Long nombreErreur) {
        this.nombreErreur = nombreErreur;
        return this;
    }

    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public OneUserDTO withDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public OneUserDTO withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("utilisateur")
    public String getUtilisateur() {
        return utilisateur;
    }

    @JsonProperty("utilisateur")
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public OneUserDTO withUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    @JsonProperty("lastConnexion")
    public String getLastConnexion() {
        return lastConnexion;
    }

    @JsonProperty("lastConnexion")
    public void setLastConnexion(String lastConnexion) {
        this.lastConnexion = lastConnexion;
    }

    public OneUserDTO withLastConnexion(String lastConnexion) {
        this.lastConnexion = lastConnexion;
        return this;
    }

    @JsonProperty("etatUtilisateur")
    public Boolean getEtatUtilisateur() {
        return etatUtilisateur;
    }

    @JsonProperty("etatUtilisateur")
    public void setEtatUtilisateur(Boolean etatUtilisateur) {
        this.etatUtilisateur = etatUtilisateur;
    }

    public OneUserDTO withEtatUtilisateur(Boolean etatUtilisateur) {
        this.etatUtilisateur = etatUtilisateur;
        return this;
    }

    @JsonProperty("langueUtilisateur")
    public LangueUtilisateur getLangueUtilisateur() {
        return langueUtilisateur;
    }

    @JsonProperty("langueUtilisateur")
    public void setLangueUtilisateur(LangueUtilisateur langueUtilisateur) {
        this.langueUtilisateur = langueUtilisateur;
    }

    public OneUserDTO withLangueUtilisateur(LangueUtilisateur langueUtilisateur) {
        this.langueUtilisateur = langueUtilisateur;
        return this;
    }

    @JsonProperty("typeUtilisateur")
    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    @JsonProperty("typeUtilisateur")
    public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public OneUserDTO withTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
        return this;
    }

    @JsonProperty("lastProg")
    public LastProg getLastProg() {
        return lastProg;
    }

    @JsonProperty("lastProg")
    public void setLastProg(LastProg lastProg) {
        this.lastProg = lastProg;
    }

    public OneUserDTO withLastProg(LastProg lastProg) {
        this.lastProg = lastProg;
        return this;
    }

    @JsonProperty("niveauAcces")
    public NiveauAcces getNiveauAcces() {
        return niveauAcces;
    }

    @JsonProperty("niveauAcces")
    public void setNiveauAcces(NiveauAcces niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    public OneUserDTO withNiveauAcces(NiveauAcces niveauAcces) {
        this.niveauAcces = niveauAcces;
        return this;
    }

    @JsonProperty("handler")
    public Handler getHandler() {
        return handler;
    }

    @JsonProperty("handler")
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public OneUserDTO withHandler(Handler handler) {
        this.handler = handler;
        return this;
    }

    @JsonProperty("mng_PACKs")
    public List<MngPACK> getMngPACKs() {
        return mngPACKs;
    }

    @JsonProperty("mng_PACKs")
    public void setMngPACKs(List<MngPACK> mngPACKs) {
        this.mngPACKs = mngPACKs;
    }

    public OneUserDTO withMngPACKs(List<MngPACK> mngPACKs) {
        this.mngPACKs = mngPACKs;
        return this;
    }

    @JsonProperty("hibernateLazyInitializer")
    public HibernateLazyInitializer getHibernateLazyInitializer() {
        return hibernateLazyInitializer;
    }

    @JsonProperty("hibernateLazyInitializer")
    public void setHibernateLazyInitializer(HibernateLazyInitializer hibernateLazyInitializer) {
        this.hibernateLazyInitializer = hibernateLazyInitializer;
    }

    public OneUserDTO withHibernateLazyInitializer(HibernateLazyInitializer hibernateLazyInitializer) {
        this.hibernateLazyInitializer = hibernateLazyInitializer;
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

    public OneUserDTO withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("codeExterne", codeExterne).append("nom", nom).append("prenom", prenom).append("password", password).append("dateDebut", dateDebut).append("dateFin", dateFin).append("nombreErreur", nombreErreur).append("dateCreation", dateCreation).append("dateMiseAJour", dateMiseAJour).append("utilisateur", utilisateur).append("lastConnexion", lastConnexion).append("etatUtilisateur", etatUtilisateur).append("langueUtilisateur", langueUtilisateur).append("typeUtilisateur", typeUtilisateur).append("lastProg", lastProg).append("niveauAcces", niveauAcces).append("handler", handler).append("mngPACKs", mngPACKs).append("hibernateLazyInitializer", hibernateLazyInitializer).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dateCreation).append(hibernateLazyInitializer).append(codeExterne).append(lastProg).append(dateFin).append(niveauAcces).append(password).append(nom).append(dateDebut).append(prenom).append(id).append(nombreErreur).append(mngPACKs).append(additionalProperties).append(typeUtilisateur).append(langueUtilisateur).append(etatUtilisateur).append(dateMiseAJour).append(lastConnexion).append(utilisateur).append(handler).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OneUserDTO) == false) {
            return false;
        }
        OneUserDTO rhs = ((OneUserDTO) other);
        return new EqualsBuilder().append(dateCreation, rhs.dateCreation).append(hibernateLazyInitializer, rhs.hibernateLazyInitializer).append(codeExterne, rhs.codeExterne).append(lastProg, rhs.lastProg).append(dateFin, rhs.dateFin).append(niveauAcces, rhs.niveauAcces).append(password, rhs.password).append(nom, rhs.nom).append(dateDebut, rhs.dateDebut).append(prenom, rhs.prenom).append(id, rhs.id).append(nombreErreur, rhs.nombreErreur).append(mngPACKs, rhs.mngPACKs).append(additionalProperties, rhs.additionalProperties).append(typeUtilisateur, rhs.typeUtilisateur).append(langueUtilisateur, rhs.langueUtilisateur).append(etatUtilisateur, rhs.etatUtilisateur).append(dateMiseAJour, rhs.dateMiseAJour).append(lastConnexion, rhs.lastConnexion).append(utilisateur, rhs.utilisateur).append(handler, rhs.handler).isEquals();
    }

}
