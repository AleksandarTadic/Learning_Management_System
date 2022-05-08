package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Fajl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class FajlDTO implements Serializable {
    private Long id;
    private String opis;
    private String url;
    private ObavestenjeDTO obavestenje;

    public FajlDTO() {
    }

    public FajlDTO(Long id, String opis, String url) {
        this.id = id;
        this.opis = opis;
        this.url = url;
    }

    public FajlDTO(Long id, String opis, String url, ObavestenjeDTO obavestenje) {
        this.id = id;
        this.opis = opis;
        this.url = url;
        this.obavestenje = obavestenje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ObavestenjeDTO getObavestenje() {
        return obavestenje;
    }

    public void setObavestenje(ObavestenjeDTO obavestenje) {
        this.obavestenje = obavestenje;
    }

    public static ArrayList<FajlDTO> toDTOArrayList(Iterable<Fajl> fajlovi, Boolean depth) {
        ArrayList<FajlDTO> fajloviDTO = new ArrayList<>();
        for(Fajl fajl : fajlovi) {
            fajloviDTO.add(FajlDTO.toDTO(fajl, depth));
        }
        return fajloviDTO;
    }

    public static ArrayList<FajlDTO> toDTOArrayList(Set<Fajl> fajlovi, Boolean depth) {
        ArrayList<FajlDTO> fajloviDTO = new ArrayList<>();
        for(Fajl fajl : fajlovi) {
            fajloviDTO.add(FajlDTO.toDTO(fajl, depth));
        }
        return fajloviDTO;
    }

    public static FajlDTO toDTO(Fajl fajl, Boolean depth) {
        FajlDTO fajlDTO = new FajlDTO(fajl.getId(), fajl.getOpis(), fajl.getUrl());
        if(depth) {
            fajlDTO.setObavestenje(ObavestenjeDTO.toDTO(fajl.getObavestenje(), false));
        }
        return fajlDTO;
    }
}
