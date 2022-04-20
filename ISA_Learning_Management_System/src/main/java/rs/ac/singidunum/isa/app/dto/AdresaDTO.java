package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Adresa;

import java.util.ArrayList;
import java.util.Set;

public class AdresaDTO {
    private Long id;
    private String ulica;
    private String broj;

    private MestoDTO mesto;

    public AdresaDTO() {
    }

    public AdresaDTO(Long id, String ulica, String broj) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
    }

    public AdresaDTO(Long id, String ulica, String broj, MestoDTO mesto) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public MestoDTO getMesto() {
        return mesto;
    }

    public void setMesto(MestoDTO mesto) {
        this.mesto = mesto;
    }

    public static ArrayList<AdresaDTO> toDTOArrayList(Iterable<Adresa> adrese, Boolean depth) {
        ArrayList<AdresaDTO> adreseDTO = new ArrayList<>();
        for(Adresa adresa : adrese) {
            adreseDTO.add(AdresaDTO.toDTO(adresa, depth));
        }
        return adreseDTO;
    }

    public static ArrayList<AdresaDTO> toDTOArrayList(Set<Adresa> adrese, Boolean depth) {
        ArrayList<AdresaDTO> adreseDTO = new ArrayList<>();
        for(Adresa adresa : adrese) {
            adreseDTO.add(AdresaDTO.toDTO(adresa, depth));
        }
        return adreseDTO;
    }

    public static AdresaDTO toDTO(Adresa adresa, Boolean depth) {
        AdresaDTO adresaDTO = new AdresaDTO(adresa.getId(), adresa.getUlica(), adresa.getBroj(), null);
        if(depth) {
            adresaDTO.setMesto(MestoDTO.toDTO(adresa.getMesto(), false));
        }
        return adresaDTO;
    }
}
