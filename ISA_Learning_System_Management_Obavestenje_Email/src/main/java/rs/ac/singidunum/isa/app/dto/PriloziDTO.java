package rs.ac.singidunum.isa.app.dto;

import java.io.Serializable;

public class PriloziDTO implements Serializable {
    private Long id;
    private String opis;
    private String url;

    public PriloziDTO() {
    }

    public PriloziDTO(Long id, String opis, String url) {
        this.id = id;
        this.opis = opis;
        this.url = url;
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

}
