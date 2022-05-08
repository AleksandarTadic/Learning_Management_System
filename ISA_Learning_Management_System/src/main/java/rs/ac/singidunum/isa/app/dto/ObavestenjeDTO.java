package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Obavestenje;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class ObavestenjeDTO {
    private Long id;
    private Date vremePostavljanja;
    private String sadrzaj;
    private String naslov;
    private NastavnikNaRealizacijiDTO nastavnikNaRealizaciji;
    private RealizacijaPredmetaDTO realizacijaPredmeta;
    private ArrayList<FajlDTO> prilozi = new ArrayList<>();

    public ObavestenjeDTO() {
    }

    public ObavestenjeDTO(Long id, Date vremePostavljanja, String sadrzaj, String naslov) {
        this.id = id;
        this.vremePostavljanja = vremePostavljanja;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
    }

    public ObavestenjeDTO(Long id, Date vremePostavljanja, String sadrzaj, String naslov, NastavnikNaRealizacijiDTO nastavnikNaRealizaciji, RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.id = id;
        this.vremePostavljanja = vremePostavljanja;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public ObavestenjeDTO(Long id, Date vremePostavljanja, String sadrzaj, String naslov, NastavnikNaRealizacijiDTO nastavnikNaRealizaciji, RealizacijaPredmetaDTO realizacijaPredmeta, ArrayList<FajlDTO> prilozi) {
        this.id = id;
        this.vremePostavljanja = vremePostavljanja;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.prilozi = prilozi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVremePostavljanja() {
        return vremePostavljanja;
    }

    public void setVremePostavljanja(Date vremePostavljanja) {
        this.vremePostavljanja = vremePostavljanja;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public NastavnikNaRealizacijiDTO getNastavnikNaRealizaciji() {
        return nastavnikNaRealizaciji;
    }

    public void setNastavnikNaRealizaciji(NastavnikNaRealizacijiDTO nastavnikNaRealizaciji) {
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
    }

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public ArrayList<FajlDTO> getPrilozi() {
        return prilozi;
    }

    public void setPrilozi(ArrayList<FajlDTO> prilozi) {
        this.prilozi = prilozi;
    }

    public static ArrayList<ObavestenjeDTO> toDTOArrayList(Iterable<Obavestenje> obavestenja, Boolean depth) {
        ArrayList<ObavestenjeDTO> obavestenjaDTO = new ArrayList<>();
        for(Obavestenje obavestenje : obavestenja) {
            obavestenjaDTO.add(ObavestenjeDTO.toDTO(obavestenje, depth));
        }
        return obavestenjaDTO;
    }

    public static ArrayList<ObavestenjeDTO> toDTOArrayList(Set<Obavestenje> obavestenja, Boolean depth) {
        ArrayList<ObavestenjeDTO> obavestenjaDTO = new ArrayList<>();
        for(Obavestenje obavestenje : obavestenja) {
            obavestenjaDTO.add(ObavestenjeDTO.toDTO(obavestenje, depth));
        }
        return obavestenjaDTO;
    }

    public static ObavestenjeDTO toDTO(Obavestenje obavestenje, Boolean depth) {
        ObavestenjeDTO obavestenjeDTO = new ObavestenjeDTO(obavestenje.getId(), obavestenje.getVremePostavljanja(), obavestenje.getSadrzaj(), obavestenje.getNaslov());
        if(depth) {
            obavestenjeDTO.setNastavnikNaRealizaciji(NastavnikNaRealizacijiDTO.toDTO(obavestenje.getNastavnikNaRealizaciji(), false));
            obavestenjeDTO.setRealizacijaPredmeta(RealizacijaPredmetaDTO.toDTO(obavestenje.getRealizacijaPredmeta(), false));
            obavestenjeDTO.setPrilozi(FajlDTO.toDTOArrayList(obavestenje.getPrilozi(), false));
        }
        return obavestenjeDTO;
    }
}

