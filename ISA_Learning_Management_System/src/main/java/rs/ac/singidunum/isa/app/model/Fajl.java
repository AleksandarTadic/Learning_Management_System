package rs.ac.singidunum.isa.app.model;

import javax.persistence.*;

@Entity
public class Fajl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String opis;
    @Column(nullable = false)
    private String url;
    @ManyToOne(optional = true)
    private Obavestenje obavestenje;

    public Fajl() {
    }

    public Fajl(Long id, String opis, String url, Obavestenje obavestenje) {
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

    public Obavestenje getObavestenje() {
        return obavestenje;
    }

    public void setObavestenje(Obavestenje obavestenje) {
        this.obavestenje = obavestenje;
    }
}
