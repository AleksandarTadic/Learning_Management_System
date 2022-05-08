package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PohadjanjePredmeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private int konacnaOcena;

    @ManyToOne(optional = false)
    private RealizacijaPredmeta realizacijaPredmeta;

    @ManyToOne(optional = false)
    private Student student;

    public PohadjanjePredmeta() {
    }

    public PohadjanjePredmeta(Long id, int konacnaOcena, RealizacijaPredmeta realizacijaPredmeta) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public PohadjanjePredmeta(Long id, int konacnaOcena, RealizacijaPredmeta realizacijaPredmeta, Student student) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(int konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
