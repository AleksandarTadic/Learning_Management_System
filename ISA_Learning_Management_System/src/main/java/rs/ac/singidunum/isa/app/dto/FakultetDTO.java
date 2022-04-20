package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Fakultet;

import java.util.ArrayList;
import java.util.Set;

public class FakultetDTO {
    private Long id;
    private String naziv;
    private UniverzitetDTO univerzitet;
    private NastavnikDTO dekan;
    private AdresaDTO adresa;
    private ArrayList<StudijskiProgramDTO> studijskiProgrami = new ArrayList<StudijskiProgramDTO>();

    public FakultetDTO() {
    }

    public FakultetDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public FakultetDTO(Long id, String naziv, UniverzitetDTO univerzitet, NastavnikDTO dekan, AdresaDTO adresa) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.adresa = adresa;
    }

    public FakultetDTO(Long id, String naziv, UniverzitetDTO univerzitet, NastavnikDTO dekan, AdresaDTO adresa, ArrayList<StudijskiProgramDTO> studijskiProgrami) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.adresa = adresa;
        this.studijskiProgrami = studijskiProgrami;
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

    public UniverzitetDTO getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(UniverzitetDTO univerzitet) {
        this.univerzitet = univerzitet;
    }

    public NastavnikDTO getDekan() {
        return dekan;
    }

    public void setDekan(NastavnikDTO dekan) {
        this.dekan = dekan;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public ArrayList<StudijskiProgramDTO> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(ArrayList<StudijskiProgramDTO> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }

    public static ArrayList<FakultetDTO> toDTOArrayList(Iterable<Fakultet> fakulteti, Boolean depth) {
        ArrayList<FakultetDTO> fakultetiDTO = new ArrayList<>();
        for(Fakultet fakultet : fakulteti) {
            fakultetiDTO.add(FakultetDTO.toDTO(fakultet, depth));
        }
        return fakultetiDTO;
    }

    public static ArrayList<FakultetDTO> toDTOArrayList(Set<Fakultet> fakulteti, Boolean depth) {
        ArrayList<FakultetDTO> fakultetiDTO = new ArrayList<>();
        for(Fakultet fakultet : fakulteti) {
            fakultetiDTO.add(FakultetDTO.toDTO(fakultet, depth));
        }
        return fakultetiDTO;
    }


    public static FakultetDTO toDTO(Fakultet fakultet, Boolean depth) {
        FakultetDTO fakultetDTO = new FakultetDTO(fakultet.getId(), fakultet.getNaziv());
        if(depth) {
            fakultetDTO.setUniverzitet(UniverzitetDTO.toDTO(fakultet.getUniverzitet(), false));
            fakultetDTO.setDekan(NastavnikDTO.toDTO(fakultet.getDekan(), false));
            fakultetDTO.setAdresa(AdresaDTO.toDTO(fakultet.getAdresa(), false));
            fakultetDTO.setStudijskiProgrami(StudijskiProgramDTO.toDTOArrayList(fakultet.getStudijskiProgrami(), false));
        }
        return fakultetDTO;
    }
}
