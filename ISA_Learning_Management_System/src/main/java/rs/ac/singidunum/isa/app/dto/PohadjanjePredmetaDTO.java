package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.PohadjanjePredmeta;

import java.util.ArrayList;
import java.util.Set;

public class PohadjanjePredmetaDTO {
    private Long id;
    private int konacnaOcena;
    private RealizacijaPredmetaDTO realizacijaPredmeta;
    private StudentDTO student;

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

    public PohadjanjePredmetaDTO(Long id, int konacnaOcena, RealizacijaPredmetaDTO realizacijaPredmeta, StudentDTO student) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.student = student;
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

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
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
            pohadjanjePredmetaDTO.setStudent(StudentDTO.toDTO(pohadjanjePredmeta.getStudent(), false));
        }
        return pohadjanjePredmetaDTO;
    }
}
