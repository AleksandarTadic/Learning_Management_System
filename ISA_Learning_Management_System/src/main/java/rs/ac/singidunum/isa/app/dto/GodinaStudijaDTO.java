package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.GodinaStudija;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class GodinaStudijaDTO {
    private Long id;
    private Date godina;
    private ArrayList<PredmetDTO> predmeti = new ArrayList<PredmetDTO>();

    private ArrayList<StudijskiProgramDTO> studijskiProgrami = new ArrayList<StudijskiProgramDTO>();

    public GodinaStudijaDTO() {
    }

    public GodinaStudijaDTO(Long id, Date godina) {
        this.id = id;
        this.godina = godina;
    }

    public GodinaStudijaDTO(Long id, Date godina, ArrayList<PredmetDTO> predmeti, ArrayList<StudijskiProgramDTO> studijskiProgrami) {
        this.id = id;
        this.godina = godina;
        this.predmeti = predmeti;
        this.studijskiProgrami = studijskiProgrami;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGodina() {
        return godina;
    }

    public void setGodina(Date godina) {
        this.godina = godina;
    }

    public ArrayList<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(ArrayList<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public ArrayList<StudijskiProgramDTO> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(ArrayList<StudijskiProgramDTO> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }

    public static ArrayList<GodinaStudijaDTO> toDTOArrayList(Iterable<GodinaStudija> godineStudija, Boolean depth) {
        ArrayList<GodinaStudijaDTO> godineStudijaDTO = new ArrayList<>();
        for(GodinaStudija godinaStudija : godineStudija) {
            godineStudijaDTO.add(GodinaStudijaDTO.toDTO(godinaStudija, depth));
        }
        return godineStudijaDTO;
    }

    public static ArrayList<GodinaStudijaDTO> toDTOArrayList(Set<GodinaStudija> godineStudija, Boolean depth) {
        ArrayList<GodinaStudijaDTO> godineStudijaDTO = new ArrayList<>();
        for(GodinaStudija godinaStudija : godineStudija) {
            godineStudijaDTO.add(GodinaStudijaDTO.toDTO(godinaStudija, depth));
        }
        return godineStudijaDTO;
    }

    public static GodinaStudijaDTO toDTO(GodinaStudija godinaStudija, Boolean depth) {
        GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(godinaStudija.getId(), godinaStudija.getGodina());
        if(depth) {
            godinaStudijaDTO.setPredmeti(PredmetDTO.toDTOArrayList(godinaStudija.getPredmeti(), false));
            godinaStudijaDTO.setStudijskiProgrami(StudijskiProgramDTO.toDTOArrayList(godinaStudija.getStudijskiProgrami(), false));
        }
        return godinaStudijaDTO;
    }
}
