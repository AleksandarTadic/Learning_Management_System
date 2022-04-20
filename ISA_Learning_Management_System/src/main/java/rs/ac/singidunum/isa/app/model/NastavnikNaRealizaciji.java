package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NastavnikNaRealizaciji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int brojCasova;

    @ManyToOne(optional = false)
    private TipNastave tipNastave;

    @ManyToOne(optional = false)
    private Nastavnik nastavnik;

    @OneToMany(mappedBy = "nastavnikNaRealizaciji")
    private Set<Obavestenje> obavestenja = new HashSet<Obavestenje>();

    @ManyToMany
    private Set<RealizacijaPredmeta> realizacijePredmeta = new HashSet<RealizacijaPredmeta>();

    public NastavnikNaRealizaciji() {
    }

    public NastavnikNaRealizaciji(Long id, int brojCasova) {
        this.id = id;
        this.brojCasova = brojCasova;
    }

    public NastavnikNaRealizaciji(Long id, int brojCasova, TipNastave tipNastave, Nastavnik nastavnik, Set<Obavestenje> obavestenja, Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.id = id;
        this.brojCasova = brojCasova;
        this.tipNastave = tipNastave;
        this.nastavnik = nastavnik;
        this.obavestenja = obavestenja;
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Set<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(Set<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }
}
