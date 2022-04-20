package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Drzava;

import java.util.ArrayList;
import java.util.Set;

public class DrzavaDTO {
    private Long id;
    private String naziv;

    private ArrayList<MestoDTO> mesta = new ArrayList<MestoDTO>();

    public DrzavaDTO() {
    }

    public DrzavaDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public DrzavaDTO(Long id, String naziv, ArrayList<MestoDTO> mesta) {
        this.id = id;
        this.naziv = naziv;
        this.mesta = mesta;
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

    public ArrayList<MestoDTO> getMesta() {
        return mesta;
    }

    public void setMesta(ArrayList<MestoDTO> mesta) {
        this.mesta = mesta;
    }

    public static ArrayList<DrzavaDTO> toDTOArrayList(Iterable<Drzava> drzave, Boolean depth) {
        ArrayList<DrzavaDTO> drzaveDTO = new ArrayList<>();
        for(Drzava drzava : drzave) {
            drzaveDTO.add(DrzavaDTO.toDTO(drzava, depth));
        }
        return drzaveDTO;
    }

    public static ArrayList<DrzavaDTO> toDTOArrayList(Set<Drzava> drzave, Boolean depth) {
        ArrayList<DrzavaDTO> drzaveDTO = new ArrayList<>();
        for(Drzava drzava : drzave) {
            drzaveDTO.add(DrzavaDTO.toDTO(drzava, depth));
        }
        return drzaveDTO;
    }

    public static DrzavaDTO toDTO(Drzava drzava, Boolean depth) {
        DrzavaDTO drzavaDTO = new DrzavaDTO(drzava.getId(), drzava.getNaziv());
        if(depth) {
            drzavaDTO.setMesta(MestoDTO.toDTOArrayList(drzava.getMesta(), false));
        }
        return drzavaDTO;
    }
}
