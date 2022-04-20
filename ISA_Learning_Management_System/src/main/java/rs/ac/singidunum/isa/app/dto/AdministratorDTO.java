package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Administrator;

import java.util.ArrayList;
import java.util.Set;

public class AdministratorDTO {
    private Long id;
    private KorisnikDTO korisnik;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Long id) {
        this.id = id;
    }

    public AdministratorDTO(Long id, KorisnikDTO korisnik) {
        this.id = id;
        this.korisnik = korisnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public static ArrayList<AdministratorDTO> toDTOArrayList(Iterable<Administrator> administratori, Boolean depth) {
        ArrayList<AdministratorDTO> administratoriDTO = new ArrayList<>();
        for(Administrator administrator : administratori) {
            administratoriDTO.add(AdministratorDTO.toDTO(administrator, depth));
        }
        return administratoriDTO;
    }

    public static ArrayList<AdministratorDTO> toDTOArrayList(Set<Administrator> administratori, Boolean depth) {
        ArrayList<AdministratorDTO> administratoriDTO = new ArrayList<>();
        for(Administrator administrator : administratori) {
            administratoriDTO.add(AdministratorDTO.toDTO(administrator, depth));
        }
        return administratoriDTO;
    }

    public static AdministratorDTO toDTO(Administrator administrator, Boolean depth) {
        AdministratorDTO administratorDTO = new AdministratorDTO(administrator.getId(), null);
        if(depth) {
            administratorDTO.setKorisnik(KorisnikDTO.toDTO(administrator.getKorisnik(), false));
        }
        return administratorDTO;
    }
}
