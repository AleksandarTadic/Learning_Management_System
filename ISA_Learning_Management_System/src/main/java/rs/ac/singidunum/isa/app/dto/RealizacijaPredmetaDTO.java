package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.NastavnikNaRealizaciji;
import rs.ac.singidunum.isa.app.model.Obavestenje;
import rs.ac.singidunum.isa.app.model.RealizacijaPredmeta;

import java.util.ArrayList;
import java.util.Set;

public class RealizacijaPredmetaDTO {
    private Long id;
    private PredmetDTO predmet;

    private ArrayList<ObavestenjeDTO> obavestenja = new ArrayList<>();

    private ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji = new ArrayList<NastavnikNaRealizacijiDTO>();

    public RealizacijaPredmetaDTO() {
    }

    public RealizacijaPredmetaDTO(Long id) {
        this.id = id;
    }

    public RealizacijaPredmetaDTO(Long id, PredmetDTO predmet) {
        this.id = id;
        this.predmet = predmet;
    }

    public RealizacijaPredmetaDTO(Long id, PredmetDTO predmet, ArrayList<ObavestenjeDTO> obavestenja, ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji) {
        this.id = id;
        this.predmet = predmet;
        this.obavestenja = obavestenja;
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public ArrayList<ObavestenjeDTO> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(ArrayList<ObavestenjeDTO> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public ArrayList<NastavnikNaRealizacijiDTO> getNastavniciNaRealizaciji() {
        return nastavniciNaRealizaciji;
    }

    public void setNastavniciNaRealizaciji(ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji) {
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public static ArrayList<RealizacijaPredmetaDTO> toDTOArrayList(Iterable<RealizacijaPredmeta> realizacijePredmeta, Boolean depth) {
        ArrayList<RealizacijaPredmetaDTO> realizacijePredmetaDTO = new ArrayList<>();
        for(RealizacijaPredmeta realizacijaPredmeta : realizacijePredmeta) {
            realizacijePredmetaDTO.add(RealizacijaPredmetaDTO.toDTO(realizacijaPredmeta, depth));
        }
        return realizacijePredmetaDTO;
    }

    public static ArrayList<RealizacijaPredmetaDTO> toDTOArrayList(Set<RealizacijaPredmeta> realizacijePredmeta, Boolean depth) {
        ArrayList<RealizacijaPredmetaDTO> realizacijePredmetaDTO = new ArrayList<>();
        for(RealizacijaPredmeta realizacijaPredmeta : realizacijePredmeta) {
            realizacijePredmetaDTO.add(RealizacijaPredmetaDTO.toDTO(realizacijaPredmeta, depth));
        }
        return realizacijePredmetaDTO;
    }

    public static RealizacijaPredmetaDTO toDTO(RealizacijaPredmeta realizacijaPredmeta, Boolean depth) {
        RealizacijaPredmetaDTO realizacijaPredmetaDTO = new RealizacijaPredmetaDTO(realizacijaPredmeta.getId());
        if(depth) {
            realizacijaPredmetaDTO.setPredmet(PredmetDTO.toDTO(realizacijaPredmeta.getPredmet(), false));
            realizacijaPredmetaDTO.setObavestenja(ObavestenjeDTO.toDTOArrayList(realizacijaPredmeta.getObavestenja(), false));
            realizacijaPredmetaDTO.setNastavniciNaRealizaciji(NastavnikNaRealizacijiDTO.toDTOArrayList(realizacijaPredmeta.getNastavniciNaRealizaciji(), false));
        }
        return realizacijaPredmetaDTO;
    }
}
