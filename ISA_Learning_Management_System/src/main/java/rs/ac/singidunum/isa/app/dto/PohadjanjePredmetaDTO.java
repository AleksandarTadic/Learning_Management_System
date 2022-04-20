package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.PohadjanjePredmeta;

import java.util.ArrayList;
import java.util.Set;

public class PohadjanjePredmetaDTO {
    private Long id;
    private int konacnaOcena;
    private RealizacijaPredmetaDTO realizacijaPredmeta;

    private ArrayList<StudentDTO> studenti = new ArrayList<StudentDTO>();

    public PohadjanjePredmetaDTO() {
    }

    public PohadjanjePredmetaDTO(Long id, int konacnaOcena) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
    }

    public PohadjanjePredmetaDTO(Long id, int konacnaOcena, RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public PohadjanjePredmetaDTO(Long id, int konacnaOcena, RealizacijaPredmetaDTO realizacijaPredmeta, ArrayList<StudentDTO> studenti) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.studenti = studenti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(int konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public ArrayList<StudentDTO> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<StudentDTO> studenti) {
        this.studenti = studenti;
    }

    public static ArrayList<PohadjanjePredmetaDTO> toDTOArrayList(Iterable<PohadjanjePredmeta> pohadjanjaPredmeta, Boolean depth) {
        ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmetaDTO = new ArrayList<>();
        for(PohadjanjePredmeta pohadjanjePredmeta : pohadjanjaPredmeta) {
            pohadjanjaPredmetaDTO.add(PohadjanjePredmetaDTO.toDTO(pohadjanjePredmeta, depth));
        }
        return pohadjanjaPredmetaDTO;
    }

    public static ArrayList<PohadjanjePredmetaDTO> toDTOArrayList(Set<PohadjanjePredmeta> pohadjanjaPredmeta, Boolean depth) {
        ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmetaDTO = new ArrayList<>();
        for(PohadjanjePredmeta pohadjanjePredmeta : pohadjanjaPredmeta) {
            pohadjanjaPredmetaDTO.add(PohadjanjePredmetaDTO.toDTO(pohadjanjePredmeta, depth));
        }
        return pohadjanjaPredmetaDTO;
    }

    public static PohadjanjePredmetaDTO toDTO(PohadjanjePredmeta pohadjanjePredmeta, Boolean depth) {
        PohadjanjePredmetaDTO pohadjanjePredmetaDTO = new PohadjanjePredmetaDTO(pohadjanjePredmeta.getId(), pohadjanjePredmeta.getKonacnaOcena());
        if(depth) {
            pohadjanjePredmetaDTO.setRealizacijaPredmeta(RealizacijaPredmetaDTO.toDTO(pohadjanjePredmeta.getRealizacijaPredmeta(), false));
            pohadjanjePredmetaDTO.setStudenti(StudentDTO.toDTOArrayList(pohadjanjePredmeta.getStudenti(), false));
        }
        return pohadjanjePredmetaDTO;
    }
}
