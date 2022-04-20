package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.NaucnaOblast;

import java.util.ArrayList;
import java.util.Set;

public class NaucnaOblastDTO {
    private Long id;
    private String naziv;

    public NaucnaOblastDTO() {
    }

    public NaucnaOblastDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    public static ArrayList<NaucnaOblastDTO> toDTOArrayList(Iterable<NaucnaOblast> naucneOblasti, Boolean depth) {
        ArrayList<NaucnaOblastDTO> naucneOblastiDTO = new ArrayList<>();
        for(NaucnaOblast naucnaOblast : naucneOblasti) {
            naucneOblastiDTO.add(NaucnaOblastDTO.toDTO(naucnaOblast, depth));
        }
        return naucneOblastiDTO;
    }

    public static ArrayList<NaucnaOblastDTO> toDTOArrayList(Set<NaucnaOblast> naucneOblasti, Boolean depth) {
        ArrayList<NaucnaOblastDTO> naucneOblastiDTO = new ArrayList<>();
        for(NaucnaOblast naucnaOblast : naucneOblasti) {
            naucneOblastiDTO.add(NaucnaOblastDTO.toDTO(naucnaOblast, depth));
        }
        return naucneOblastiDTO;
    }

    public static NaucnaOblastDTO toDTO(NaucnaOblast naucnaOblast, Boolean depth) {
        NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(naucnaOblast.getId(), naucnaOblast.getNaziv());
        return naucnaOblastDTO;
    }
}
