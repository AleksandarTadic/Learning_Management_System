package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date datumOsnivanja;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Adresa adresa;

    @ManyToOne(optional = false)
    private Nastavnik rektor;

    @OneToMany(mappedBy = "univerzitet")
    private Set<Fakultet> fakulteti = new HashSet<Fakultet>();

    public Univerzitet() {
    }

    public Univerzitet(Long id, String naziv, Date datumOsnivanja) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
    }

    public Univerzitet(Long id, String naziv, Date datumOsnivanja, Adresa adresa, Nastavnik rektor) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
        this.rektor = rektor;
    }

    public Univerzitet(Long id, String naziv, Date datumOsnivanja, Adresa adresa, Nastavnik rektor, Set<Fakultet> fakulteti) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
        this.rektor = rektor;
        this.fakulteti = fakulteti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Nastavnik getRektor() {
        return rektor;
    }

    public void setRektor(Nastavnik rektor) {
        this.rektor = rektor;
    }

    public Set<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(Set<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
    }
}
