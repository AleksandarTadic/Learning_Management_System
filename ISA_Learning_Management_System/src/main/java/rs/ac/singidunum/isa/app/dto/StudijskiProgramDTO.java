package rs.ac.singidunum.isa.app.dto;



import rs.ac.singidunum.isa.app.model.StudijskiProgram;

import java.util.ArrayList;
import java.util.Set;

public class StudijskiProgramDTO {
    private Long id;
    private String naziv;
    private NastavnikDTO rukovodilac;
    private FakultetDTO fakultet;

    private ArrayList<GodinaStudijaDTO> godineStudija = new ArrayList<GodinaStudijaDTO>();

    public StudijskiProgramDTO() {
    }

    public StudijskiProgramDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public StudijskiProgramDTO(Long id, String naziv, NastavnikDTO rukovodilac, FakultetDTO fakultet) {
        this.id = id;
        this.naziv = naziv;
        this.rukovodilac = rukovodilac;
        this.fakultet = fakultet;
    }

    public StudijskiProgramDTO(Long id, String naziv, NastavnikDTO rukovodilac, FakultetDTO fakultet, ArrayList<GodinaStudijaDTO> godineStudija) {
        this.id = id;
        this.naziv = naziv;
        this.rukovodilac = rukovodilac;
        this.fakultet = fakultet;
        this.godineStudija = godineStudija;
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

    public NastavnikDTO getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(NastavnikDTO rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public FakultetDTO getFakultet() {
        return fakultet;
    }

    public void setFakultet(FakultetDTO fakultet) {
        this.fakultet = fakultet;
    }

    public ArrayList<GodinaStudijaDTO> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(ArrayList<GodinaStudijaDTO> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public static ArrayList<StudijskiProgramDTO> toDTOArrayList(Iterable<StudijskiProgram> studijskiProgrami, Boolean depth) {
        ArrayList<StudijskiProgramDTO> studijskiProgramiDTO = new ArrayList<>();
        for(StudijskiProgram studijskiProgram : studijskiProgrami) {
            studijskiProgramiDTO.add(StudijskiProgramDTO.toDTO(studijskiProgram, depth));
        }
        return studijskiProgramiDTO;
    }

    public static ArrayList<StudijskiProgramDTO> toDTOArrayList(Set<StudijskiProgram> studijskiProgrami, Boolean depth) {
        ArrayList<StudijskiProgramDTO> studijskiProgramiDTO = new ArrayList<>();
        for(StudijskiProgram studijskiProgram : studijskiProgrami) {
            studijskiProgramiDTO.add(StudijskiProgramDTO.toDTO(studijskiProgram, depth));
        }
        return studijskiProgramiDTO;
    }


    public static StudijskiProgramDTO toDTO(StudijskiProgram studijskiProgram, Boolean depth) {
        StudijskiProgramDTO studijskiProgramDTO = new StudijskiProgramDTO(studijskiProgram.getId(), studijskiProgram.getNaziv());
        if(depth) {
            studijskiProgramDTO.setRukovodilac(NastavnikDTO.toDTO(studijskiProgram.getRukovodilac(), false));
            studijskiProgramDTO.setFakultet(FakultetDTO.toDTO(studijskiProgram.getFakultet(), false));
            studijskiProgramDTO.setGodineStudija(GodinaStudijaDTO.toDTOArrayList(studijskiProgram.getGodineStudija(), false));
        }
        return studijskiProgramDTO;
    }
}
