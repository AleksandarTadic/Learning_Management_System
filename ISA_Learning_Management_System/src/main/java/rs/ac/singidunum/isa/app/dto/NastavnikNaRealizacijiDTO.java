package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.NastavnikNaRealizaciji;

import java.util.ArrayList;
import java.util.Set;

public class NastavnikNaRealizacijiDTO {
    private Long id;
    private int brojCasova;

    private TipNastaveDTO tipNastave;
    private NastavnikDTO nastavnik;

    private ArrayList<ObavestenjeDTO> obavestenja = new ArrayList<>();

    private ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta = new ArrayList<RealizacijaPredmetaDTO>();

    public NastavnikNaRealizacijiDTO() {
    }

    public NastavnikNaRealizacijiDTO(Long id, int brojCasova) {
        this.id = id;
        this.brojCasova = brojCasova;
    }

    public NastavnikNaRealizacijiDTO(Long id, int brojCasova, TipNastaveDTO tipNastave, NastavnikDTO nastavnik) {
        this.id = id;
        this.brojCasova = brojCasova;
        this.tipNastave = tipNastave;
        this.nastavnik = nastavnik;
    }

    public NastavnikNaRealizacijiDTO(Long id, int brojCasova, TipNastaveDTO tipNastave, NastavnikDTO nastavnik, ArrayList<ObavestenjeDTO> obavestenja, ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta) {
        this.id = id;
        this.brojCasova = brojCasova;
        this.tipNastave = tipNastave;
        this.nastavnik = nastavnik;
        this.obavestenja = obavestenja;
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public TipNastaveDTO getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastaveDTO tipNastave) {
        this.tipNastave = tipNastave;
    }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
    }

    public ArrayList<ObavestenjeDTO> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(ArrayList<ObavestenjeDTO> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public ArrayList<RealizacijaPredmetaDTO> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(ArrayList<RealizacijaPredmetaDTO> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public static ArrayList<NastavnikNaRealizacijiDTO> toDTOArrayList(Iterable<NastavnikNaRealizaciji> nastavniciNaRealizaciji, Boolean depth) {
        ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizacijiDTO = new ArrayList<>();
        for(NastavnikNaRealizaciji nastavnikNaRealizaciji : nastavniciNaRealizaciji) {
            nastavniciNaRealizacijiDTO.add(NastavnikNaRealizacijiDTO.toDTO(nastavnikNaRealizaciji, depth));
        }
        return nastavniciNaRealizacijiDTO;
    }

    public static ArrayList<NastavnikNaRealizacijiDTO> toDTOArrayList(Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji, Boolean depth) {
        ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizacijiDTO = new ArrayList<>();
        for(NastavnikNaRealizaciji nastavnikNaRealizaciji : nastavniciNaRealizaciji) {
            nastavniciNaRealizacijiDTO.add(NastavnikNaRealizacijiDTO.toDTO(nastavnikNaRealizaciji, depth));
        }
        return nastavniciNaRealizacijiDTO;
    }


    public static NastavnikNaRealizacijiDTO toDTO(NastavnikNaRealizaciji nastavnikNaRealizaciji, Boolean depth) {
        NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = new NastavnikNaRealizacijiDTO(nastavnikNaRealizaciji.getId(), nastavnikNaRealizaciji.getBrojCasova());
        if(depth) {
            nastavnikNaRealizacijiDTO.setTipNastave(TipNastaveDTO.toDTO(nastavnikNaRealizaciji.getTipNastave(), false));
            nastavnikNaRealizacijiDTO.setNastavnik(NastavnikDTO.toDTO(nastavnikNaRealizaciji.getNastavnik(), false));
            nastavnikNaRealizacijiDTO.setObavestenja(ObavestenjeDTO.toDTOArrayList(nastavnikNaRealizaciji.getObavestenja(),false));
            nastavnikNaRealizacijiDTO.setRealizacijePredmeta(RealizacijaPredmetaDTO.toDTOArrayList(nastavnikNaRealizaciji.getRealizacijePredmeta(), false));
        }
        return nastavnikNaRealizacijiDTO;
    }
}
