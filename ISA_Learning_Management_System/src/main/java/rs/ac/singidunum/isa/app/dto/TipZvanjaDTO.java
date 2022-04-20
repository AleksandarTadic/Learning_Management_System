package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.TipZvanja;

import java.util.ArrayList;
import java.util.Set;

public class TipZvanjaDTO {
    private Long id;
    private String naziv;

    public TipZvanjaDTO() {
    }

    public TipZvanjaDTO(Long id, String naziv) {
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

    public static ArrayList<TipZvanjaDTO> toDTOArrayList(Iterable<TipZvanja> tipoviZvanja, Boolean depth) {
        ArrayList<TipZvanjaDTO> tipoviZvanjaDTO = new ArrayList<>();
        for(TipZvanja tipZvanja : tipoviZvanja) {
            tipoviZvanjaDTO.add(TipZvanjaDTO.toDTO(tipZvanja, depth));
        }
        return tipoviZvanjaDTO;
    }

    public static ArrayList<TipZvanjaDTO> toDTOArrayList(Set<TipZvanja> tipoviZvanja, Boolean depth) {
        ArrayList<TipZvanjaDTO> tipoviZvanjaDTO = new ArrayList<>();
        for(TipZvanja tipZvanja : tipoviZvanja) {
            tipoviZvanjaDTO.add(TipZvanjaDTO.toDTO(tipZvanja, depth));
        }
        return tipoviZvanjaDTO;
    }

    public static TipZvanjaDTO toDTO(TipZvanja tipZvanja, Boolean depth) {
        TipZvanjaDTO tipZvanjaDTO = new TipZvanjaDTO(tipZvanja.getId(), tipZvanja.getNaziv());
        return tipZvanjaDTO;
    }
}
