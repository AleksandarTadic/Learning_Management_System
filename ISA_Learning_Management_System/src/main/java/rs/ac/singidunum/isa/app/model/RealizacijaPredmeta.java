package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RealizacijaPredmeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Predmet predmet;

    @OneToMany(mappedBy = "realizacijaPredmeta")
    private Set<Obavestenje> obavestenja = new HashSet<Obavestenje>();

    @ManyToMany
    private Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji = new HashSet<NastavnikNaRealizaciji>();

    public RealizacijaPredmeta() {
    }

    public RealizacijaPredmeta(Long id, Predmet predmet) {
        this.id = id;
        this.predmet = predmet;
    }

    public RealizacijaPredmeta(Long id, Predmet predmet, Set<Obavestenje> obavestenja, Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji) {
        this.id = id;
        this.predmet = predmet;
        this.obavestenja = obavestenja;
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Set<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(Set<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public Set<NastavnikNaRealizaciji> getNastavniciNaRealizaciji() {
        return nastavniciNaRealizaciji;
    }

    public void setNastavniciNaRealizaciji(Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji) {
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }
}
