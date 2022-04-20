package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @ManyToOne(optional = false)
    private Univerzitet univerzitet;

    @ManyToOne(optional = false)
    private Nastavnik dekan;

    @ManyToOne(optional = false)
    private Adresa adresa;

    @OneToMany(mappedBy = "fakultet")
    private Set<StudijskiProgram> studijskiProgrami = new HashSet<StudijskiProgram>();

    public Fakultet() {
    }

    public Fakultet(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Fakultet(Long id, String naziv, Univerzitet univerzitet, Nastavnik dekan, Adresa adresa) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.adresa = adresa;
    }

    public Fakultet(Long id, String naziv, Univerzitet univerzitet, Nastavnik dekan, Adresa adresa, Set<StudijskiProgram> studijskiProgrami) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.adresa = adresa;
        this.studijskiProgrami = studijskiProgrami;
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

    public Univerzitet getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(Univerzitet univerzitet) {
        this.univerzitet = univerzitet;
    }

    public Nastavnik getDekan() {
        return dekan;
    }

    public void setDekan(Nastavnik dekan) {
        this.dekan = dekan;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Set<StudijskiProgram> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }
}
