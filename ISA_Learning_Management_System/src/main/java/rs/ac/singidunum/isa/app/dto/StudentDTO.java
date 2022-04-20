package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Student;

import java.util.ArrayList;
import java.util.Set;

public class StudentDTO {
    private Long id;
    private String jmbg;
    private String ime;
    private AdresaDTO adresa;
    private KorisnikDTO korisnik;

    private ArrayList<StudentNaGodiniDTO> studentNaGodini = new ArrayList<StudentNaGodiniDTO>();

    private ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta = new ArrayList<PohadjanjePredmetaDTO>();

    public StudentDTO() {
    }

    public StudentDTO(Long id, String jmbg, String ime) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
    }

    public StudentDTO(Long id, String jmbg, String ime, AdresaDTO adresa, KorisnikDTO korisnik) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.korisnik = korisnik;
    }

    public StudentDTO(Long id, String jmbg, String ime, AdresaDTO adresa, KorisnikDTO korisnik, ArrayList<StudentNaGodiniDTO> studentNaGodini, ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.adresa = adresa;
        this.korisnik = korisnik;
        this.studentNaGodini = studentNaGodini;
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public ArrayList<StudentNaGodiniDTO> getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(ArrayList<StudentNaGodiniDTO> studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }

    public ArrayList<PohadjanjePredmetaDTO> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }

    public static ArrayList<StudentDTO> toDTOArrayList(Iterable<Student> studenti, Boolean depth) {
        ArrayList<StudentDTO> studentiDTO = new ArrayList<>();
        for(Student student : studenti) {
            studentiDTO.add(StudentDTO.toDTO(student, depth));
        }
        return studentiDTO;
    }

    public static ArrayList<StudentDTO> toDTOArrayList(Set<Student> studenti, Boolean depth) {
        ArrayList<StudentDTO> studentiDTO = new ArrayList<>();
        for(Student student : studenti) {
            studentiDTO.add(StudentDTO.toDTO(student, depth));
        }
        return studentiDTO;
    }

    public static StudentDTO toDTO(Student student, Boolean depth) {
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getJmbg(), student.getIme());
        if(depth) {
            studentDTO.setAdresa(AdresaDTO.toDTO(student.getAdresa(), false));
            studentDTO.setKorisnik(KorisnikDTO.toDTO(student.getKorisnik(), false));
            studentDTO.setStudentNaGodini(StudentNaGodiniDTO.toDTOArrayList(student.getStudentNaGodini(), false));
            studentDTO.setPohadjanjaPredmeta(PohadjanjePredmetaDTO.toDTOArrayList(student.getPohadjanjaPredmeta(), false));
        }
        return studentDTO;
    }
}
