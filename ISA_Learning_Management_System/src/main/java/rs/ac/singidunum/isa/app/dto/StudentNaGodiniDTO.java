package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.StudentNaGodini;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class StudentNaGodiniDTO {
    private Long id;
    private Date datumUpisa;
    private String brojIndeksa;
    private GodinaStudijaDTO godinaStudija;

    private StudentDTO student;

    public StudentNaGodiniDTO() {
    }

    public StudentNaGodiniDTO(Long id, Date datumUpisa, String brojIndeksa) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
    }

    public StudentNaGodiniDTO(Long id, Date datumUpisa, String brojIndeksa, GodinaStudijaDTO godinaStudija, StudentDTO student) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
        this.godinaStudija = godinaStudija;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public GodinaStudijaDTO getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public static ArrayList<StudentNaGodiniDTO> toDTOArrayList(Iterable<StudentNaGodini> studentiNaGodini, Boolean depth) {
        ArrayList<StudentNaGodiniDTO> studentiNaGodiniDTO = new ArrayList<>();
        for(StudentNaGodini studentNaGodini : studentiNaGodini) {
            studentiNaGodiniDTO.add(StudentNaGodiniDTO.toDTO(studentNaGodini, depth));
        }
        return studentiNaGodiniDTO;
    }

    public static ArrayList<StudentNaGodiniDTO> toDTOArrayList(Set<StudentNaGodini> studentiNaGodini, Boolean depth) {
        ArrayList<StudentNaGodiniDTO> studentiNaGodiniDTO = new ArrayList<>();
        for(StudentNaGodini studentNaGodini : studentiNaGodini) {
            studentiNaGodiniDTO.add(StudentNaGodiniDTO.toDTO(studentNaGodini, depth));
        }
        return studentiNaGodiniDTO;
    }

    public static StudentNaGodiniDTO toDTO(StudentNaGodini studentNaGodini, Boolean depth) {
        StudentNaGodiniDTO studentNaGodiniDTO = new StudentNaGodiniDTO(studentNaGodini.getId(), studentNaGodini.getDatumUpisa(), studentNaGodini.getBrojIndeksa());
        if(depth) {
            studentNaGodiniDTO.setGodinaStudija(GodinaStudijaDTO.toDTO(studentNaGodini.getGodinaStudija(), false));
            studentNaGodiniDTO.setStudent(StudentDTO.toDTO(studentNaGodini.getStudent(), false));
        }
        return studentNaGodiniDTO;
    }
}
