package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Nastavnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Lob
    @Column(nullable = false)
    private String biografija;

    @Column(nullable = false, unique = true)
    private String jmbg;

    @ManyToOne(optional = false)
    private Adresa adresa;

    @ManyToOne(optional = false)
    private Korisnik korisnik;

    @OneToMany(mappedBy = "nastavnik")
    private Set<Zvanje> zvanja = new HashSet<Zvanje>();

    public Nastavnik() {
    }

    public Nastavnik(Long id, String ime, String biografija, String jmbg, Adresa adresa, Korisnik korisnik, Set<Zvanje> zvanja) {
        this.id = id;
        this.ime = ime;
        this.biografija = biografija;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.korisnik = korisnik;
        this.zvanja = zvanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Set<Zvanje> getZvanja() {
        return zvanja;
    }

    public void setZvanja(Set<Zvanje> zvanja) {
        this.zvanja = zvanja;
    }
}
