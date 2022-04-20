package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String jmbg;

    @Column(nullable = false)
    private String ime;

    @ManyToOne(optional = false)
    private Adresa adresa;

    @ManyToOne(optional = false)
    private Korisnik korisnik;

    @OneToMany(mappedBy = "student")
    private Set<StudentNaGodini> studentNaGodini = new HashSet<StudentNaGodini>();

    @ManyToMany
    private Set<PohadjanjePredmeta> pohadjanjaPredmeta = new HashSet<PohadjanjePredmeta>();

    public Student() {
    }

    public Student(Long id, String jmbg, String ime, Adresa adresa, Korisnik korisnik) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.korisnik = korisnik;
    }

    public Student(Long id, String jmbg, String ime, Adresa adresa, Korisnik korisnik, Set<StudentNaGodini> studentNaGodini, Set<PohadjanjePredmeta> pohadjanjaPredmeta) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.korisnik = korisnik;
        this.studentNaGodini = studentNaGodini;
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
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

    public Set<StudentNaGodini> getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(Set<StudentNaGodini> studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }

    public Set<PohadjanjePredmeta> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(Set<PohadjanjePredmeta> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }
}
