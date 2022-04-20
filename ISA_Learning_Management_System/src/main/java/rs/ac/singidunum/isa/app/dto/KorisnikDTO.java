package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Korisnik;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class KorisnikDTO {
    private Long id;
    private String korisnickoIme;
    private String Lozinka;
    private String email;
    private Date datumRodjenja;

    private PravoPristupaDTO pravoPristupa;

    public KorisnikDTO() {
    }

    public KorisnikDTO(Long id, String korisnickoIme, String lozinka, String email, Date datumRodjenja) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        Lozinka = lozinka;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
    }

    public KorisnikDTO(Long id, String korisnickoIme, String lozinka, String email, Date datumRodjenja, PravoPristupaDTO pravoPristupa) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        Lozinka = lozinka;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.pravoPristupa = pravoPristupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public PravoPristupaDTO getPravoPristupa() {
        return pravoPristupa;
    }

    public void setPravoPristupa(PravoPristupaDTO pravoPristupa) {
        this.pravoPristupa = pravoPristupa;
    }

    public static ArrayList<KorisnikDTO> toDTOArrayList(Iterable<Korisnik> korisnici, Boolean depth) {
        ArrayList<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for(Korisnik korisnik: korisnici) {
            korisniciDTO.add(KorisnikDTO.toDTO(korisnik, depth));
        }
        return korisniciDTO;
    }

    public static ArrayList<KorisnikDTO> toDTOArrayList(Set<Korisnik> korisnici, Boolean depth) {
        ArrayList<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for(Korisnik korisnik: korisnici) {
            korisniciDTO.add(KorisnikDTO.toDTO(korisnik, depth));
        }
        return korisniciDTO;
    }

    public static KorisnikDTO toDTO(Korisnik korisnik, Boolean depth) {
        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik.getId(), korisnik.getKorisnickoIme(), null, korisnik.getEmail(), korisnik.getDatumRodjenja());
        if(depth) {
            korisnikDTO.setPravoPristupa(PravoPristupaDTO.toDTO(korisnik.getPravoPristupa(), false));
        }
        return korisnikDTO;
    }
}
