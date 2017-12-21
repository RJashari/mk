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
@Table(name = "Dega", catalog = "Menaxhimi_celesave", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dega.findAll", query = "SELECT d FROM Dega d")
    , @NamedQuery(name = "Dega.findByDegaID", query = "SELECT d FROM Dega d WHERE d.degaID = :degaID")
    , @NamedQuery(name = "Dega.findByEmri", query = "SELECT d FROM Dega d WHERE d.emri = :emri")
    , @NamedQuery(name = "Dega.findByPergjegjesi", query = "SELECT d FROM Dega d WHERE d.pergjegjesi = :pergjegjesi")
    , @NamedQuery(name = "Dega.findByCelesiHyrjes", query = "SELECT d FROM Dega d WHERE d.celesiHyrjes = :celesiHyrjes")
    , @NamedQuery(name = "Dega.findByCelesiServerRoom", query = "SELECT d FROM Dega d WHERE d.celesiServerRoom = :celesiServerRoom")
    , @NamedQuery(name = "Dega.findByCelesiDeresAtm", query = "SELECT d FROM Dega d WHERE d.celesiDeresAtm = :celesiDeresAtm")
    , @NamedQuery(name = "Dega.findByKodiAlarmitDege", query = "SELECT d FROM Dega d WHERE d.kodiAlarmitDege = :kodiAlarmitDege")
    , @NamedQuery(name = "Dega.findByCelesiTrezor", query = "SELECT d FROM Dega d WHERE d.celesiTrezor = :celesiTrezor")
    , @NamedQuery(name = "Dega.findByCelesiKasaforte1", query = "SELECT d FROM Dega d WHERE d.celesiKasaforte1 = :celesiKasaforte1")
    , @NamedQuery(name = "Dega.findByCelesiKasaforte2", query = "SELECT d FROM Dega d WHERE d.celesiKasaforte2 = :celesiKasaforte2")
    , @NamedQuery(name = "Dega.findByKodiAlarmitTrezor", query = "SELECT d FROM Dega d WHERE d.kodiAlarmitTrezor = :kodiAlarmitTrezor")
    , @NamedQuery(name = "Dega.findByKodiShiferKasaforte", query = "SELECT d FROM Dega d WHERE d.kodiShiferKasaforte = :kodiShiferKasaforte")
    , @NamedQuery(name = "Dega.findByKodetDigjitaleKasaforte1", query = "SELECT d FROM Dega d WHERE d.kodetDigjitaleKasaforte1 = :kodetDigjitaleKasaforte1")
    , @NamedQuery(name = "Dega.findByKodetDigjitaleKasaforte2", query = "SELECT d FROM Dega d WHERE d.kodetDigjitaleKasaforte2 = :kodetDigjitaleKasaforte2")
    , @NamedQuery(name = "Dega.findByDataKrijimit", query = "SELECT d FROM Dega d WHERE d.dataKrijimit = :dataKrijimit")
    , @NamedQuery(name = "Dega.findByDataModifikimit", query = "SELECT d FROM Dega d WHERE d.dataModifikimit = :dataModifikimit")})
public class Dega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "degaID", nullable = false)
    private Integer degaID;
    @Size(max = 50)
    @Column(name = "emri", length = 50)
    private String emri;
    @Size(max = 50)
    @Column(name = "pergjegjesi", length = 50)
    private String pergjegjesi;
    @Size(max = 250)
    @Column(name = "celesi_hyrjes", length = 250)
    private String celesiHyrjes;
    @Size(max = 250)
    @Column(name = "celesi_server_room", length = 250)
    private String celesiServerRoom;
    @Size(max = 250)
    @Column(name = "celesi__deres_atm", length = 250)
    private String celesiDeresAtm;
    @Size(max = 250)
    @Column(name = "kodi_alarmit_dege", length = 250)
    private String kodiAlarmitDege;
    @Size(max = 250)
    @Column(name = "celesi_trezor", length = 250)
    private String celesiTrezor;
    @Size(max = 250)
    @Column(name = "celesi_kasaforte_1", length = 250)
    private String celesiKasaforte1;
    @Size(max = 250)
    @Column(name = "celesi_kasaforte_2", length = 250)
    private String celesiKasaforte2;
    @Size(max = 250)
    @Column(name = "kodi_alarmit_trezor", length = 250)
    private String kodiAlarmitTrezor;
    @Size(max = 250)
    @Column(name = "kodi_shifer_kasaforte", length = 250)
    private String kodiShiferKasaforte;
    @Size(max = 250)
    @Column(name = "kodet_digjitale_kasaforte_1", length = 250)
    private String kodetDigjitaleKasaforte1;
    @Size(max = 250)
    @Column(name = "kodet_digjitale_kasaforte_2", length = 250)
    private String kodetDigjitaleKasaforte2;
    @Size(max = 50)
    @Column(name = "data_krijimit", length = 50)
    private String dataKrijimit;
    @Size(max = 50)
    @Column(name = "data_modifikimit", length = 50)
    private String dataModifikimit;

    public Dega() {
    }

    public Dega(Integer degaID) {
        this.degaID = degaID;
    }

    public Integer getDegaID() {
        return degaID;
    }

    public void setDegaID(Integer degaID) {
        this.degaID = degaID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getPergjegjesi() {
        return pergjegjesi;
    }

    public void setPergjegjesi(String pergjegjesi) {
        this.pergjegjesi = pergjegjesi;
    }

    public String getCelesiHyrjes() {
        return celesiHyrjes;
    }

    public void setCelesiHyrjes(String celesiHyrjes) {
        this.celesiHyrjes = celesiHyrjes;
    }

    public String getCelesiServerRoom() {
        return celesiServerRoom;
    }

    public void setCelesiServerRoom(String celesiServerRoom) {
        this.celesiServerRoom = celesiServerRoom;
    }

    public String getCelesiDeresAtm() {
        return celesiDeresAtm;
    }

    public void setCelesiDeresAtm(String celesiDeresAtm) {
        this.celesiDeresAtm = celesiDeresAtm;
    }

    public String getKodiAlarmitDege() {
        return kodiAlarmitDege;
    }

    public void setKodiAlarmitDege(String kodiAlarmitDege) {
        this.kodiAlarmitDege = kodiAlarmitDege;
    }

    public String getCelesiTrezor() {
        return celesiTrezor;
    }

    public void setCelesiTrezor(String celesiTrezor) {
        this.celesiTrezor = celesiTrezor;
    }

    public String getCelesiKasaforte1() {
        return celesiKasaforte1;
    }

    public void setCelesiKasaforte1(String celesiKasaforte1) {
        this.celesiKasaforte1 = celesiKasaforte1;
    }

    public String getCelesiKasaforte2() {
        return celesiKasaforte2;
    }

    public void setCelesiKasaforte2(String celesiKasaforte2) {
        this.celesiKasaforte2 = celesiKasaforte2;
    }

    public String getKodiAlarmitTrezor() {
        return kodiAlarmitTrezor;
    }

    public void setKodiAlarmitTrezor(String kodiAlarmitTrezor) {
        this.kodiAlarmitTrezor = kodiAlarmitTrezor;
    }

    public String getKodiShiferKasaforte() {
        return kodiShiferKasaforte;
    }

    public void setKodiShiferKasaforte(String kodiShiferKasaforte) {
        this.kodiShiferKasaforte = kodiShiferKasaforte;
    }

    public String getKodetDigjitaleKasaforte1() {
        return kodetDigjitaleKasaforte1;
    }

    public void setKodetDigjitaleKasaforte1(String kodetDigjitaleKasaforte1) {
        this.kodetDigjitaleKasaforte1 = kodetDigjitaleKasaforte1;
    }

    public String getKodetDigjitaleKasaforte2() {
        return kodetDigjitaleKasaforte2;
    }

    public void setKodetDigjitaleKasaforte2(String kodetDigjitaleKasaforte2) {
        this.kodetDigjitaleKasaforte2 = kodetDigjitaleKasaforte2;
    }

    public String getDataKrijimit() {
        return dataKrijimit;
    }

    public void setDataKrijimit(String dataKrijimit) {
        this.dataKrijimit = dataKrijimit;
    }

    public String getDataModifikimit() {
        return dataModifikimit;
    }

    public void setDataModifikimit(String dataModifikimit) {
        this.dataModifikimit = dataModifikimit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (degaID != null ? degaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dega)) {
            return false;
        }
        Dega other = (Dega) object;
        if ((this.degaID == null && other.degaID != null) || (this.degaID != null && !this.degaID.equals(other.degaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.BL.Dega[ degaID=" + degaID + " ]";
    }
    
}
