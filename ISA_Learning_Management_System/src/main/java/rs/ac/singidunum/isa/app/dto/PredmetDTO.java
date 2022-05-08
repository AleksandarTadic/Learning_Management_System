package rs.ac.singidunum.isa.app.dto;



import rs.ac.singidunum.isa.app.model.Predmet;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Set;

public class PredmetDTO {
    private Long id;
    private String naziv;
    private int espb;
    private boolean obavezan;
    private int brojPredavanja;
    private int brojVezbi;
    private int drugiObliciNastave;
    private int istrazivackiRad;
    private int ostaliCasovi;
    //    opciono
    private int brojSemestara;

    private PredmetDTO preduslov;

    private ArrayList<IshodDTO> silabus = new ArrayList<IshodDTO>();
    private ArrayList<GodinaStudijaDTO> godineStudija = new ArrayList<GodinaStudijaDTO>();

    public PredmetDTO() {
    }

    public PredmetDTO(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara) {
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
    }

    public PredmetDTO(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, PredmetDTO preduslov) {
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
        this.preduslov = preduslov;
    }

    public PredmetDTO(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, ArrayList<IshodDTO> silabus, ArrayList<GodinaStudijaDTO> godineStudija) {
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
        this.silabus = silabus;
        this.godineStudija = godineStudija;
    }

    public PredmetDTO(Long id, String naziv, int espb, boolean obavezan, int brojPredavanja, int brojVezbi, int drugiObliciNastave, int istrazivackiRad, int ostaliCasovi, int brojSemestara, PredmetDTO preduslov, ArrayList<IshodDTO> silabus, ArrayList<GodinaStudijaDTO> godineStudija) {
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
        this.preduslov = preduslov;
        this.silabus = silabus;
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

    public PredmetDTO getPreduslov() {
        return preduslov;
    }

    public void setPreduslov(PredmetDTO preduslov) {
        this.preduslov = preduslov;
    }

    public ArrayList<IshodDTO> getSilabus() {
        return silabus;
    }

    public void setSilabus(ArrayList<IshodDTO> silabus) {
        this.silabus = silabus;
    }

    public ArrayList<GodinaStudijaDTO> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(ArrayList<GodinaStudijaDTO> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public static ArrayList<PredmetDTO> toDTOArrayList(Iterable<Predmet> predmeti, Boolean depth) {
        ArrayList<PredmetDTO> predmetiDTO = new ArrayList<>();
        for(Predmet predmet : predmeti) {
            predmetiDTO.add(PredmetDTO.toDTO(predmet, depth));
        }
        return predmetiDTO;
    }

    public static ArrayList<PredmetDTO> toDTOArrayList(Set<Predmet> predmeti, Boolean depth) {
        ArrayList<PredmetDTO> predmetiDTO = new ArrayList<>();
        for(Predmet predmet : predmeti) {
            predmetiDTO.add(PredmetDTO.toDTO(predmet, depth));
        }
        return predmetiDTO;
    }

    public static PredmetDTO toDTO(Predmet predmet, Boolean depth) {
        PredmetDTO predmetDTO = new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEspb(), predmet.isObavezan(), predmet.getBrojPredavanja(), predmet.getBrojVezbi(), predmet.getDrugiObliciNastave(), predmet.getIstrazivackiRad(), predmet.getOstaliCasovi(), predmet.getBrojSemestara());
        if(depth) {
            if(predmet.getPreduslov() != null) {
                predmetDTO.setPreduslov(PredmetDTO.toDTO(predmet.getPreduslov(), false));
            }
            predmetDTO.setGodineStudija(GodinaStudijaDTO.toDTOArrayList(predmet.getGodineStudija(), false));
            predmetDTO.setSilabus(IshodDTO.toDTOArrayList(predmet.getSilabus(), false));
        }
        return predmetDTO;
    }
}
