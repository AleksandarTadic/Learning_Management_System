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

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date godina;

    @OneToMany(mappedBy = "godinaStudija")
    private Set<Predmet> predmeti = new HashSet<Predmet>();

    @ManyToMany
    private Set<StudijskiProgram> studijskiProgrami = new HashSet<StudijskiProgram>();

    public GodinaStudija() {
    }

    public GodinaStudija(Long id, Date godina, Set<Predmet> predmeti, Set<StudijskiProgram> studijskiProgrami) {
        this.id = id;
        this.godina = godina;
        this.predmeti = predmeti;
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

    public Set<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(Set<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public Set<StudijskiProgram> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }
}
