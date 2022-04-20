package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Univerzitet;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class UniverzitetDTO {
    private Long id;
    private String naziv;
    private Date datumOsnivanja;

    private AdresaDTO adresa;
    private NastavnikDTO rektor;
    private ArrayList<FakultetDTO> fakulteti = new ArrayList<FakultetDTO>();

    public UniverzitetDTO() {
    }

    public UniverzitetDTO(Long id, String naziv, Date datumOsnivanja) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
    }

    public UniverzitetDTO(Long id, String naziv, Date datumOsnivanja, AdresaDTO adresa, NastavnikDTO rektor) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
        this.rektor = rektor;
    }

    public UniverzitetDTO(Long id, String naziv, Date datumOsnivanja, AdresaDTO adresa, NastavnikDTO rektor, ArrayList<FakultetDTO> fakulteti) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
        this.rektor = rektor;
        this.fakulteti = fakulteti;
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

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public NastavnikDTO getRektor() {
        return rektor;
    }

    public void setRektor(NastavnikDTO rektor) {
        this.rektor = rektor;
    }

    public ArrayList<FakultetDTO> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(ArrayList<FakultetDTO> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public static ArrayList<UniverzitetDTO> toDTOArrayList(Iterable<Univerzitet> univerziteti, Boolean depth) {
        ArrayList<UniverzitetDTO> univerzitetiDTO = new ArrayList<>();
        for(Univerzitet univerzitet : univerziteti) {
            univerzitetiDTO.add(UniverzitetDTO.toDTO(univerzitet, depth));
        }
        return univerzitetiDTO;
    }

    public static ArrayList<UniverzitetDTO> toDTOArrayList(Set<Univerzitet> univerziteti, Boolean depth) {
        ArrayList<UniverzitetDTO> univerzitetiDTO = new ArrayList<>();
        for(Univerzitet univerzitet : univerziteti) {
            univerzitetiDTO.add(UniverzitetDTO.toDTO(univerzitet, depth));
        }
        return univerzitetiDTO;
    }

    public static UniverzitetDTO toDTO(Univerzitet univerzitet, Boolean depth) {
        UniverzitetDTO univerzitetDTO = new UniverzitetDTO(univerzitet.getId(), univerzitet.getNaziv(), univerzitet.getDatumOsnivanja());
        if(depth) {
            univerzitetDTO.setAdresa(AdresaDTO.toDTO(univerzitet.getAdresa(), false));
            univerzitetDTO.setRektor(NastavnikDTO.toDTO(univerzitet.getRektor(), false));
            univerzitetDTO.setFakulteti(FakultetDTO.toDTOArrayList(univerzitet.getFakulteti(), false));
        }
        return univerzitetDTO;
    }
}
