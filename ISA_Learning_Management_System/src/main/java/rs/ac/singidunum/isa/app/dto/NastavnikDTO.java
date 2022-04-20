package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Nastavnik;

import java.util.ArrayList;
import java.util.Set;

public class NastavnikDTO {
    private Long id;
    private String ime;
    private String biografija;
    private String jmbg;

    private AdresaDTO adresa;
    private KorisnikDTO korisnik;

    private ArrayList<ZvanjeDTO> zvanja = new ArrayList<>();

    public NastavnikDTO() {
    }

    public NastavnikDTO(Long id, String ime, String biografija, String jmbg) {
        this.id = id;
        this.ime = ime;
        this.biografija = biografija;
        this.jmbg = jmbg;
    }

    public NastavnikDTO(Long id, String ime, String biografija, String jmbg, AdresaDTO adresa, KorisnikDTO korisnik, ArrayList<ZvanjeDTO> zvanja) {
        this.id = id;
        this.ime = ime;
        this.biografija = biografija;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.korisnik = korisnik;
        this.zvanja = zvanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public ArrayList<ZvanjeDTO> getZvanja() {
        return zvanja;
    }

    public void setZvanja(ArrayList<ZvanjeDTO> zvanja) {
        this.zvanja = zvanja;
    }

    public static ArrayList<NastavnikDTO> toDTOArrayList(Iterable<Nastavnik> nastavnici, Boolean depth) {
        ArrayList<NastavnikDTO> nastavniciDTO = new ArrayList<>();
        for(Nastavnik nastavnik : nastavnici) {
            nastavniciDTO.add(NastavnikDTO.toDTO(nastavnik, depth));
        }
        return nastavniciDTO;
    }

    public static ArrayList<NastavnikDTO> toDTOArrayList(Set<Nastavnik> nastavnici, Boolean depth) {
        ArrayList<NastavnikDTO> nastavniciDTO = new ArrayList<>();
        for(Nastavnik nastavnik : nastavnici) {
            nastavniciDTO.add(NastavnikDTO.toDTO(nastavnik, depth));
        }
        return nastavniciDTO;
    }

    public static NastavnikDTO toDTO(Nastavnik nastavnik, Boolean depth) {
        NastavnikDTO nastavnikDTO = new NastavnikDTO(nastavnik.getId(), nastavnik.getIme(), nastavnik.getBiografija(), nastavnik.getJmbg());
        if(depth) {
            nastavnikDTO.setAdresa(AdresaDTO.toDTO(nastavnik.getAdresa(), false));
            nastavnikDTO.setKorisnik(KorisnikDTO.toDTO(nastavnik.getKorisnik(), false));
            nastavnikDTO.setZvanja(ZvanjeDTO.toDTOArrayList(nastavnik.getZvanja(), false));
        }
        return nastavnikDTO;
    }
}
