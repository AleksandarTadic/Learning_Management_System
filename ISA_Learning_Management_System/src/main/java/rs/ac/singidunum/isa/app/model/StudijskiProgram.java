package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudijskiProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false, unique = true)
    private String naziv;

    @ManyToOne(optional = false)
    private Nastavnik rukovodilac;

    @ManyToOne(optional = false)
    private Fakultet fakultet;

    @ManyToMany
    private Set<GodinaStudija> godineStudija = new HashSet<GodinaStudija>();

    public StudijskiProgram() {
    }

    public StudijskiProgram(Long id, String naziv, Nastavnik rukovodilac, Fakultet fakultet) {
        this.id = id;
        this.naziv = naziv;
        this.rukovodilac = rukovodilac;
        this.fakultet = fakultet;
    }

    public StudijskiProgram(Long id, String naziv, Nastavnik rukovodilac, Fakultet fakultet, Set<GodinaStudija> godineStudija) {
        this.id = id;
        this.naziv = naziv;
        this.rukovodilac = rukovodilac;
        this.fakultet = fakultet;
        this.godineStudija = godineStudija;
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

    public Nastavnik getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(Nastavnik rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public Set<GodinaStudija> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(Set<GodinaStudija> godineStudija) {
        this.godineStudija = godineStudija;
    }
}
