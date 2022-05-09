package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private int espb;

    @Column(nullable = false)
    private boolean obavezan;

    @Column(nullable = false)
    private int brojPredavanja;

    @Column(nullable = false)
    private int brojVezbi;

    @Column(nullable = false)
    private int drugiObliciNastave;

    @Column(nullable = false)
    private int istrazivackiRad;

    @Column(nullable = false)
    private int ostaliCasovi;

//    opciono
    @Column(nullable = false)
    private int brojSemestara;

    @ManyToOne(optional = false)
    private GodinaStudija godinaStudija;

    @ManyToOne(optional = true)
    private Predmet preduslov;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private Set<Ishod> silabus = new HashSet<Ishod>();


    public Predmet() {
    }

    public Predmet(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, GodinaStudija godinaStudija) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
        this.brojSemestara = brojSemestara;
        this.godinaStudija = godinaStudija;
    }

    public Predmet(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, GodinaStudija godinaStudija, Predmet preduslov) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
        this.brojSemestara = brojSemestara;
        this.godinaStudija = godinaStudija;
        this.preduslov = preduslov;
    }

    public Predmet(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, GodinaStudija godinaStudija, Set<Ishod> silabus) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
        this.brojSemestara = brojSemestara;
        this.godinaStudija = godinaStudija;
        this.silabus = silabus;
    }

    public Predmet(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, GodinaStudija godinaStudija, Predmet preduslov, Set<Ishod> silabus) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
        this.brojSemestara = brojSemestara;
        this.godinaStudija = godinaStudija;
        this.preduslov = preduslov;
        this.silabus = silabus;
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

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public boolean isObavezan() {
        return obavezan;
    }

    public void setObavezan(boolean obavezan) {
        this.obavezan = obavezan;
    }

    public int getBrojPredavanja() {
        return brojPredavanja;
    }

    public void setBrojPredavanja(int brojPredavanja) {
        this.brojPredavanja = brojPredavanja;
    }

    public int getBrojVezbi() {
        return brojVezbi;
    }

    public void setBrojVezbi(int brojVezbi) {
        this.brojVezbi = brojVezbi;
    }

    public int getDrugiObliciNastave() {
        return drugiObliciNastave;
    }

    public void setDrugiObliciNastave(int drugiObliciNastave) {
        this.drugiObliciNastave = drugiObliciNastave;
    }

    public int getIstrazivackiRad() {
        return istrazivackiRad;
    }

    public void setIstrazivackiRad(int istrazivackiRad) {
        this.istrazivackiRad = istrazivackiRad;
    }

    public int getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(int ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public int getBrojSemestara() {
        return brojSemestara;
    }

    public void setBrojSemestara(int brojSemestara) {
        this.brojSemestara = brojSemestara;
    }

    public Predmet getPreduslov() {
        return preduslov;
    }

    public void setPreduslov(Predmet preduslov) {
        this.preduslov = preduslov;
    }

    public Set<Ishod> getSilabus() {
        return silabus;
    }

    public void setSilabus(Set<Ishod> silabus) {
        this.silabus = silabus;
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }
}
