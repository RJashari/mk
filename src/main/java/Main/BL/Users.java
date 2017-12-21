/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.BL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rinor Jashari
 */
@Entity
@Table(name = "Users", catalog = "Menaxhimi_celesave", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUsersID", query = "SELECT u FROM Users u WHERE u.usersID = :usersID")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled")
    , @NamedQuery(name = "Users.findByRoli", query = "SELECT u FROM Users u WHERE u.roli = :roli")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "usersID", nullable = false)
    private Integer usersID;
    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;
    @Size(max = 50)
    @Column(name = "password", length = 50)
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "enabled")
    private Boolean enabled;
    @Size(max = 50)
    @Column(name = "roli", length = 50)
    private String roli;

    public Users() {
    }

    public Users(Integer usersID) {
        this.usersID = usersID;
    }

    public Integer getUsersID() {
        return usersID;
    }

    public void setUsersID(Integer usersID) {
        this.usersID = usersID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersID != null ? usersID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usersID == null && other.usersID != null) || (this.usersID != null && !this.usersID.equals(other.usersID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.BL.Users[ usersID=" + usersID + " ]";
    }
    
}
