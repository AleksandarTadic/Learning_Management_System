package rs.ac.singidunum.isa.app.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class EmailDTO implements Serializable {
    private ArrayList<String> emailovi;
    private String sadrzaj;
    private String naslov;
    private ArrayList<PriloziDTO> prilozi;

    public EmailDTO() {
    }

    public EmailDTO(ArrayList<String> emailovi, String sadrzaj, String naslov) {
        this.emailovi = emailovi;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
    }

    public EmailDTO(ArrayList<String> emailovi, String sadrzaj, String naslov, ArrayList<PriloziDTO> prilozi) {
        this.emailovi = emailovi;
        this.sadrzaj = sadrzaj;
        this.naslov = naslov;
        this.prilozi = prilozi;
    }

    public ArrayList<String> getEmailovi() {
        return emailovi;
    }

    public void setEmailovi(ArrayList<String> emailovi) {
        this.emailovi = emailovi;
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

    public ArrayList<PriloziDTO> getPrilozi() {
        return prilozi;
    }

    public void setPrilozi(ArrayList<PriloziDTO> prilozi) {
        this.prilozi = prilozi;
    }
}
