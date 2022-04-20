package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.TipNastave;

import java.util.ArrayList;
import java.util.Set;

public class TipNastaveDTO {
    private Long id;
    private String naziv;

    public TipNastaveDTO() {
    }

    public TipNastaveDTO(Long id, String naziv) {
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

    public static ArrayList<TipNastaveDTO> toDTOArrayList(Iterable<TipNastave> tipoviNastave, Boolean depth) {
        ArrayList<TipNastaveDTO> tipoviNastaveDTO = new ArrayList<>();
        for(TipNastave tipNastave : tipoviNastave) {
            tipoviNastaveDTO.add(TipNastaveDTO.toDTO(tipNastave, depth));
        }
        return tipoviNastaveDTO;
    }

    public static ArrayList<TipNastaveDTO> toDTOArrayList(Set<TipNastave> tipoviNastave, Boolean depth) {
        ArrayList<TipNastaveDTO> tipoviNastaveDTO = new ArrayList<>();
        for(TipNastave tipNastave : tipoviNastave) {
            tipoviNastaveDTO.add(TipNastaveDTO.toDTO(tipNastave, depth));
        }
        return tipoviNastaveDTO;
    }

    public static TipNastaveDTO toDTO(TipNastave tipNastave, Boolean depth) {
        TipNastaveDTO tipNastaveDTO = new TipNastaveDTO(tipNastave.getId(), tipNastave.getNaziv());
        return tipNastaveDTO;
    }
}
