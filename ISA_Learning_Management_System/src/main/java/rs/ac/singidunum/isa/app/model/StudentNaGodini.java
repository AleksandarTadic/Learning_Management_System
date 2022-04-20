package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudentNaGodini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date datumUpisa;

    @Column(nullable = false, unique = true)
    private String brojIndeksa;

    @ManyToOne(optional = false)
    private GodinaStudija godinaStudija;

    @ManyToOne(optional = false)
    private Student student;


    public StudentNaGodini() {
    }

    public StudentNaGodini(Long id, Date datumUpisa, String brojIndeksa, GodinaStudija godinaStudija, Student student) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
        this.godinaStudija = godinaStudija;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public rs.ac.singidunum.isa.app.model.Student getStudent() {
        return student;
    }

    public void setStudent(rs.ac.singidunum.isa.app.model.Student student) {
        this.student = student;
    }
}
