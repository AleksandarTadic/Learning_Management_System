package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Obavestenje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date vremePostavljanja;
    @Column(nullable = true)
    private String sadrzaj;
    @Column(nullable = true)
    private String naslov;

    @ManyToOne(optional = false)
    private NastavnikNaRealizaciji nastavnikNaRealizaciji;
    @ManyToOne(optional = false)
    private RealizacijaPredmeta realizacijaPredmeta;

    @OneToMany(mappedBy = "obavestenje")
    private Set<Fajl> prilozi = new HashSet<Fajl>();

    public Obavestenje() {
    }

    public Obavestenje(Long id, Date vremePostavljanja, String sadrzaj, String naslov, NastavnikNaRealizaciji nastavnikNaRealizaciji, RealizacijaPredmeta realizacijaPredmeta) {
        this.id = id;
        this.vremePostavljanja = vremePostavljanja;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Obavestenje(Long id, Date vremePostavljanja, String sadrzaj, String naslov, NastavnikNaRealizaciji nastavnikNaRealizaciji, RealizacijaPredmeta realizacijaPredmeta, Set<Fajl> prilozi) {
        this.id = id;
        this.vremePostavljanja = vremePostavljanja;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.prilozi = prilozi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVremePostavljanja() {
        return vremePostavljanja;
    }

    public void setVremePostavljanja(Date vremePostavljanja) {
        this.vremePostavljanja = vremePostavljanja;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public NastavnikNaRealizaciji getNastavnikNaRealizaciji() {
        return nastavnikNaRealizaciji;
    }

    public void setNastavnikNaRealizaciji(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
    }

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Set<Fajl> getPrilozi() {
        return prilozi;
    }

    public void setPrilozi(Set<Fajl> prilozi) {
        this.prilozi = prilozi;
    }
}
