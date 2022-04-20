package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GodinaStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date godina;

    @ManyToOne(optional = false)
    private Predmet predmet;

    @ManyToMany
    private Set<StudijskiProgram> studijskiProgrami = new HashSet<StudijskiProgram>();

    public GodinaStudija() {
    }

    public GodinaStudija(Long id, Date godina, Predmet predmet) {
        this.id = id;
        this.godina = godina;
        this.predmet = predmet;
    }

    public GodinaStudija(Long id, Date godina, Predmet predmet, Set<StudijskiProgram> studijskiProgrami) {
        this.id = id;
        this.godina = godina;
        this.predmet = predmet;
        this.studijskiProgrami = studijskiProgrami;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGodina() {
        return godina;
    }

    public void setGodina(Date godina) {
        this.godina = godina;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Set<StudijskiProgram> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }
}
