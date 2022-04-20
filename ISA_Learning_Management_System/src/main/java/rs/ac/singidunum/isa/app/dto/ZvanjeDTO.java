package rs.ac.singidunum.isa.app.dto;

import rs.ac.singidunum.isa.app.model.Zvanje;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class ZvanjeDTO {
    private Long id;
    private Date datumIzbora;
    private Date datumPrestanka;

    private TipZvanjaDTO tipZvanja;
    private NaucnaOblastDTO naucnaOblast;

    private NastavnikDTO nastavnik;

    public ZvanjeDTO() {
    }

    public ZvanjeDTO(Long id, Date datumIzbora, Date datumPrestanka) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
    }

    public ZvanjeDTO(Long id, Date datumIzbora, Date datumPrestanka, TipZvanjaDTO tipZvanja, NaucnaOblastDTO naucnaOblast, NastavnikDTO nastavnik) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.tipZvanja = tipZvanja;
        this.naucnaOblast = naucnaOblast;
        this.nastavnik = nastavnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public TipZvanjaDTO getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanjaDTO tipZvanja) {
        this.tipZvanja = tipZvanja;
    }

    public NaucnaOblastDTO getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblastDTO naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
    }

    public static ArrayList<ZvanjeDTO> toDTOArrayList(Iterable<Zvanje> zvanja, Boolean depth) {
        ArrayList<ZvanjeDTO> zvanjaDTO = new ArrayList<>();
        for(Zvanje zvanje : zvanja) {
            zvanjaDTO.add(ZvanjeDTO.toDTO(zvanje, depth));
        }
        return zvanjaDTO;
    }

    public static ArrayList<ZvanjeDTO> toDTOArrayList(Set<Zvanje> zvanja, Boolean depth) {
        ArrayList<ZvanjeDTO> zvanjaDTO = new ArrayList<>();
        for(Zvanje zvanje : zvanja) {
            zvanjaDTO.add(ZvanjeDTO.toDTO(zvanje, depth));
        }
        return zvanjaDTO;
    }

    public static ZvanjeDTO toDTO(Zvanje zvanje, Boolean depth) {
        ZvanjeDTO zvanjeDTO = new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka());
        if(depth) {
            zvanjeDTO.setTipZvanja(TipZvanjaDTO.toDTO(zvanje.getTipZvanja(), false));
            zvanjeDTO.setNaucnaOblast(NaucnaOblastDTO.toDTO(zvanje.getNaucnaOblast(), false));
            zvanjeDTO.setNastavnik(NastavnikDTO.toDTO(zvanje.getNastavnik(), false));
        }
        return zvanjeDTO;
    }
}
