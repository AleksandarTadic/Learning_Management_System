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
import rs.ac.singidunum.isa.app.dto.PohadjanjePredmetaDTO;
import rs.ac.singidunum.isa.app.model.PohadjanjePredmeta;
import rs.ac.singidunum.isa.app.service.PohadjanjePredmetaService;


import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/pohadjanjaPredmeta")
public class PohadjanjePredmetaController {
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<PohadjanjePredmetaDTO>> getAllPohadjanjaPredmeta() {
        ArrayList<PohadjanjePredmetaDTO> pohadjanjaPredmeta = PohadjanjePredmetaDTO.toDTOArrayList(pohadjanjePredmetaService.findAll(), true);

        return new ResponseEntity<ArrayList<PohadjanjePredmetaDTO>>(pohadjanjaPredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.GET)
    public ResponseEntity<PohadjanjePredmetaDTO> getPohadjanjePredmeta(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        Optional<PohadjanjePredmeta> pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId);
        if(pohadjanjePredmeta.isPresent()) {
            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = PohadjanjePredmetaDTO.toDTO(pohadjanjePredmeta.get(), true);
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PohadjanjePredmetaDTO> createPohadjanjePredmeta(@RequestBody PohadjanjePredmeta pohadjanjePredmeta) {
        try {
            pohadjanjePredmetaService.save(pohadjanjePredmeta);
            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = PohadjanjePredmetaDTO.toDTO(pohadjanjePredmeta, true);
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PohadjanjePredmetaDTO> updatePohadjanjePredmeta(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId, @RequestBody PohadjanjePredmeta izmenjenoPohadjanjePredmeta) {
        PohadjanjePredmeta pohadjanjePredmeta = pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).orElse(null);
        if(pohadjanjePredmeta != null) {
            izmenjenoPohadjanjePredmeta.setId(pohadjanjePredmetaId);
            izmenjenoPohadjanjePredmeta = pohadjanjePredmetaService.save(izmenjenoPohadjanjePredmeta);
            PohadjanjePredmetaDTO pohadjanjePredmetaDTO = PohadjanjePredmetaDTO.toDTO(izmenjenoPohadjanjePredmeta, true);
            return new ResponseEntity<PohadjanjePredmetaDTO>(pohadjanjePredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{pohadjanjePredmetaId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PohadjanjePredmetaDTO> deletePohadjanjePredmeta(@PathVariable("pohadjanjePredmetaId") Long pohadjanjePredmetaId) {
        if(pohadjanjePredmetaService.findOne(pohadjanjePredmetaId).isPresent()) {
            pohadjanjePredmetaService.delete(pohadjanjePredmetaId);
            return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PohadjanjePredmetaDTO>(HttpStatus.NOT_FOUND);
    }
}
