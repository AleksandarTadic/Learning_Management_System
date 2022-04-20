package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.PravoPristupa;

import java.util.ArrayList;
import java.util.Set;

public class PravoPristupaDTO {
    private Long id;
    private String naziv;

    public PravoPristupaDTO() {
    }

    public PravoPristupaDTO(Long id, String naziv) {
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

    public static ArrayList<PravoPristupaDTO> toDTOArrayList(Iterable<PravoPristupa> pravaPristupa, Boolean depth) {
        ArrayList<PravoPristupaDTO> pravaPristupaDTO = new ArrayList<>();
        for(PravoPristupa pravoPristupa: pravaPristupa) {
            pravaPristupaDTO.add(PravoPristupaDTO.toDTO(pravoPristupa, depth));
        }
        return pravaPristupaDTO;
    }

    public static ArrayList<PravoPristupaDTO> toDTOArrayList(Set<PravoPristupa> pravaPristupa, Boolean depth) {
        ArrayList<PravoPristupaDTO> pravaPristupaDTO = new ArrayList<>();
        for(PravoPristupa pravoPristupa: pravaPristupa) {
            pravaPristupaDTO.add(PravoPristupaDTO.toDTO(pravoPristupa, depth));
        }
        return pravaPristupaDTO;
    }

    public static PravoPristupaDTO toDTO(PravoPristupa pravoPristupa, Boolean depth) {
        PravoPristupaDTO pravoPristupaDTO = new PravoPristupaDTO(pravoPristupa.getId(), pravoPristupa.getNaziv());
        return pravoPristupaDTO;
    }
}
