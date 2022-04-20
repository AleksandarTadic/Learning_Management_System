package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Ishod;

import java.util.ArrayList;
import java.util.Set;

public class IshodDTO {
    private Long id;
    private String opis;
    private PredmetDTO predmet;

    public IshodDTO() {
    }

    public IshodDTO(Long id, String opis) {
        this.id = id;
        this.opis = opis;
    }

    public IshodDTO(Long id, String opis, PredmetDTO predmet) {
        this.id = id;
        this.opis = opis;
        this.predmet = predmet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public static ArrayList<IshodDTO> toDTOArrayList(Iterable<Ishod> ishodi, Boolean depth) {
        ArrayList<IshodDTO> ishodiDTO = new ArrayList<>();
        for(Ishod ishod : ishodi) {
            ishodiDTO.add(IshodDTO.toDTO(ishod, depth));
        }
        return ishodiDTO;
    }

    public static ArrayList<IshodDTO> toDTOArrayList(Set<Ishod> ishodi, Boolean depth) {
        ArrayList<IshodDTO> ishodiDTO = new ArrayList<>();
        for(Ishod ishod : ishodi) {
            ishodiDTO.add(IshodDTO.toDTO(ishod, depth));
        }
        return ishodiDTO;
    }

    public static IshodDTO toDTO(Ishod ishod, Boolean depth) {
        IshodDTO ishodDTO = new IshodDTO(ishod.getId(), ishod.getOpis());
        if(depth) {
            ishodDTO.setPredmet(PredmetDTO.toDTO(ishod.getPredmet(), false));
        }
        return ishodDTO;
    }
}
