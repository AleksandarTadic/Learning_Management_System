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

import rs.ac.singidunum.isa.app.dto.FakultetDTO;
import rs.ac.singidunum.isa.app.model.Fakultet;
import rs.ac.singidunum.isa.app.service.FakultetService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/fakulteti")
public class FakultetController {
    @Autowired
    private FakultetService fakultetService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<FakultetDTO>> getAllFakulteti() {
        ArrayList<FakultetDTO> fakulteti = FakultetDTO.toDTOArrayList(fakultetService.findAll(), true);
        return new ResponseEntity<ArrayList<FakultetDTO>>(fakulteti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.GET)
    public ResponseEntity<FakultetDTO> getFakultet(@PathVariable("fakultetId") Long fakultetId) {
        Optional<Fakultet> fakultet = fakultetService.findOne(fakultetId);
        if(fakultet.isPresent()) {
            FakultetDTO fakultetDTO = FakultetDTO.toDTO(fakultet.get(), true);
            return new ResponseEntity<FakultetDTO>(fakultetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FakultetDTO> createFakultet(@RequestBody Fakultet fakultet) {
        try {
            fakultetService.save(fakultet);
            FakultetDTO fakultetDTO = FakultetDTO.toDTO(fakultet, true);
            return new ResponseEntity<FakultetDTO>(fakultetDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FakultetDTO> updateFakultet(@PathVariable("fakultetId") Long fakultetId, @RequestBody Fakultet izmenjenFakultet) {
        Fakultet fakultet = fakultetService.findOne(fakultetId).orElse(null);
        if(fakultet != null) {
            izmenjenFakultet.setId(fakultetId);
            izmenjenFakultet = fakultetService.save(izmenjenFakultet);
            FakultetDTO fakultetDTO = FakultetDTO.toDTO(izmenjenFakultet, true);
            return new ResponseEntity<FakultetDTO>(fakultetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{fakultetId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FakultetDTO> deleteFakultet(@PathVariable("fakultetId") Long fakultetId) {
        if(fakultetService.findOne(fakultetId).isPresent()) {
            fakultetService.delete(fakultetId);
            return new ResponseEntity<FakultetDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<FakultetDTO>(HttpStatus.NOT_FOUND);
    }
}
