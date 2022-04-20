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
import rs.ac.singidunum.isa.app.dto.DrzavaDTO;
import rs.ac.singidunum.isa.app.model.Drzava;
import rs.ac.singidunum.isa.app.service.DrzavaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/drzave")
public class DrzavaController {
    @Autowired
    private DrzavaService drzavaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<DrzavaDTO>> getAllDrzave() {
        ArrayList<DrzavaDTO> drzave = DrzavaDTO.toDTOArrayList(drzavaService.findAll(), true);

        return new ResponseEntity<Iterable<DrzavaDTO>>(drzave, HttpStatus.OK);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.GET)
    public ResponseEntity<DrzavaDTO> getDrzava(@PathVariable("drzavaId") Long drzavaId) {
        Optional<Drzava> drzava = drzavaService.findOne(drzavaId);
        if(drzava.isPresent()) {
            DrzavaDTO drzavaDTO = DrzavaDTO.toDTO(drzava.get(), true);
            return new ResponseEntity<DrzavaDTO>(drzavaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DrzavaDTO> createDrzava(@RequestBody Drzava drzava) {
        try {
            drzavaService.save(drzava);
            DrzavaDTO drzavaDTO = DrzavaDTO.toDTO(drzava, true);
            return new ResponseEntity<DrzavaDTO>(drzavaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DrzavaDTO> updateDrzava(@PathVariable("drzavaId") Long drzavaId, @RequestBody Drzava izmenjenaDrzava) {
        Drzava drzava = drzavaService.findOne(drzavaId).orElse(null);
        if(drzava != null) {
            izmenjenaDrzava.setId(drzavaId);
            izmenjenaDrzava = drzavaService.save(izmenjenaDrzava);
            DrzavaDTO drzavaDTO = DrzavaDTO.toDTO(izmenjenaDrzava, true);
            return new ResponseEntity<DrzavaDTO>(drzavaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{drzavaId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DrzavaDTO> deleteDrzava(@PathVariable("drzavaId") Long drzavaId) {
        if(drzavaService.findOne(drzavaId).isPresent()) {
            drzavaService.delete(drzavaId);
            return new ResponseEntity<DrzavaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<DrzavaDTO>(HttpStatus.NOT_FOUND);
    }
}