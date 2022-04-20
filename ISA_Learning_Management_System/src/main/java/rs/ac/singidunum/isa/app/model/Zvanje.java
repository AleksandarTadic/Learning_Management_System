package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zvanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date datumIzbora;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date datumPrestanka;

    @ManyToOne(optional = false)
    private TipZvanja tipZvanja;

    @ManyToOne(optional = false)
    private NaucnaOblast naucnaOblast;

    @ManyToOne(optional = false)
    private Nastavnik nastavnik;

    public Zvanje() {
    }

    public Zvanje(Long id, Date datumIzbora, Date datumPrestanka) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
    }

    public Zvanje(Long id, Date datumIzbora, Date datumPrestanka, TipZvanja tipZvanja, NaucnaOblast naucnaOblast, Nastavnik nastavnik) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.tipZvanja = tipZvanja;
        this.naucnaOblast = naucnaOblast;
        this.nastavnik = nastavnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public TipZvanja getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanja tipZvanja) {
        this.tipZvanja = tipZvanja;
    }

    public NaucnaOblast getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }
}
