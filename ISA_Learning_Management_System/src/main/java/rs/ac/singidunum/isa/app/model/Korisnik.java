package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String korisnickoIme;
    @Column(nullable = false)
    private String lozinka;
    @Column(nullable = false, unique = true)
    private String email;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date datumRodjenja;

    @ManyToOne
    private PravoPristupa pravoPristupa;

    public Korisnik() {
    }

    public Korisnik(Long id, String korisnickoIme, String lozinka, String email, Date datumRodjenja) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
    }

    public Korisnik(Long id, String korisnickoIme, String lozinka, String email, Date datumRodjenja, PravoPristupa pravoPristupa) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
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
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public PravoPristupa getPravoPristupa() {
        return pravoPristupa;
    }

    public void setPravoPristupa(PravoPristupa pravoPristupa) {
        this.pravoPristupa = pravoPristupa;
    }
}
