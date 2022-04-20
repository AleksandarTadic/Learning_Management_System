package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.AdresaDTO;
import rs.ac.singidunum.isa.app.model.Adresa;
import rs.ac.singidunum.isa.app.service.AdresaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/adrese")
public class AdresaController {
    @Autowired
    private AdresaService adresaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AdresaDTO>> getAllAdrese() {
        ArrayList<AdresaDTO> adrese = AdresaDTO.toDTOArrayList(adresaService.findAll(), true);
        return new ResponseEntity<Iterable<AdresaDTO>>(adrese, HttpStatus.OK);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.GET)
    public ResponseEntity<AdresaDTO> getAdresa(@PathVariable("adresaId") Long adresaId) {
        Optional<Adresa> adresa = adresaService.findOne(adresaId);
        if(adresa.isPresent()) {
            AdresaDTO adresaDTO = AdresaDTO.toDTO(adresa.get(), true);
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdresaDTO> createAdresa(@RequestBody Adresa adresa) {
        try {
            adresaService.save(adresa);
            AdresaDTO adresaDTO = AdresaDTO.toDTO(adresa, true);
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdresaDTO> updateAdresa(@PathVariable("adresaId") Long adresaId, @RequestBody Adresa izmenjenaAdresa) {
        Adresa adresa = adresaService.findOne(adresaId).orElse(null);
        if(adresa != null) {
            izmenjenaAdresa.setId(adresaId);
            izmenjenaAdresa = adresaService.save(izmenjenaAdresa);
            AdresaDTO adresaDTO = AdresaDTO.toDTO(izmenjenaAdresa, true);
            return new ResponseEntity<AdresaDTO>(adresaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{adresaId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdresaDTO> deleteAdresa(@PathVariable("adresaId") Long adresaId) {
        if(adresaService.findOne(adresaId).isPresent()) {
            adresaService.delete(adresaId);
            return new ResponseEntity<AdresaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<AdresaDTO>(HttpStatus.NOT_FOUND);
    }
}
