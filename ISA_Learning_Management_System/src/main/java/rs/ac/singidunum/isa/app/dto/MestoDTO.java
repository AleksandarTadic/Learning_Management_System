package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Mesto;

import java.util.ArrayList;
import java.util.Set;

public class MestoDTO {
    private Long id;
    private String naziv;

    private DrzavaDTO drzava;

    public MestoDTO() {
    }

    public MestoDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public MestoDTO(Long id, String naziv, DrzavaDTO drzava) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
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

    public DrzavaDTO getDrzava() {
        return drzava;
    }

    public void setDrzava(DrzavaDTO drzava) {
        this.drzava = drzava;
    }

    public static ArrayList<MestoDTO> toDTOArrayList(Iterable<Mesto> mesta, Boolean depth) {
        ArrayList<MestoDTO> mestaDTO = new ArrayList<>();
        for(Mesto mesto: mesta) {
            mestaDTO.add(MestoDTO.toDTO(mesto, depth));
        }
        return mestaDTO;
    }

    public static ArrayList<MestoDTO> toDTOArrayList(Set<Mesto> mesta, Boolean depth) {
        ArrayList<MestoDTO> mestaDTO = new ArrayList<>();
        for(Mesto mesto: mesta) {
            mestaDTO.add(MestoDTO.toDTO(mesto, depth));
        }
        return mestaDTO;
    }

    public static MestoDTO toDTO(Mesto mesto, Boolean depth) {
        MestoDTO mestoDTO = new MestoDTO(mesto.getId(), mesto.getNaziv());
        if(depth) {
            mestoDTO.setDrzava(DrzavaDTO.toDTO(mesto.getDrzava(), false));
        }
        return mestoDTO;
    }
}
