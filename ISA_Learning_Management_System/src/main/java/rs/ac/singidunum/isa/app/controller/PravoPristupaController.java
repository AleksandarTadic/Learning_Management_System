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
import rs.ac.singidunum.isa.app.dto.PravoPristupaDTO;
import rs.ac.singidunum.isa.app.model.PravoPristupa;
import rs.ac.singidunum.isa.app.service.PravoPristupaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/pravaPristupa")
public class PravoPristupaController {
    @Autowired
    private PravoPristupaService pravoPristupaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Iterable<PravoPristupaDTO>> getAllPravaPristupa() {
        ArrayList<PravoPristupaDTO> pravaPristupa = PravoPristupaDTO.toDTOArrayList(pravoPristupaService.findAll(), true);
        return new ResponseEntity<Iterable<PravoPristupaDTO>>(pravaPristupa, HttpStatus.OK);
    }

    @RequestMapping(path = "/{pravoPristupaId}", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PravoPristupaDTO> getPravoPristupa(@PathVariable("pravoPristupaId") Long pravoPristupaId) {
        Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findOne(pravoPristupaId);
        if(pravoPristupa.isPresent()) {
            PravoPristupaDTO pravoPristupaDTO = PravoPristupaDTO.toDTO(pravoPristupa.get(), true);
            return new ResponseEntity<PravoPristupaDTO>(pravoPristupaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PravoPristupaDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PravoPristupaDTO> createPravoPristupa(@RequestBody PravoPristupa pravoPristupa) {
        try {
            pravoPristupaService.save(pravoPristupa);
            PravoPristupaDTO pravoPristupaDTO = PravoPristupaDTO.toDTO(pravoPristupa, true);
            return new ResponseEntity<PravoPristupaDTO>(pravoPristupaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PravoPristupaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{pravoPristupaId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PravoPristupaDTO> updatePravoPristupa(@PathVariable("pravoPristupaId") Long pravoPristupaId, @RequestBody PravoPristupa izmenjenoPravoPristupa) {
        PravoPristupa pravoPristupa = pravoPristupaService.findOne(pravoPristupaId).orElse(null);
        if(pravoPristupa != null) {
            izmenjenoPravoPristupa.setId(pravoPristupaId);
            izmenjenoPravoPristupa = pravoPristupaService.save(izmenjenoPravoPristupa);
            PravoPristupaDTO pravoPristupaDTO = PravoPristupaDTO.toDTO(izmenjenoPravoPristupa, true);
            return new ResponseEntity<PravoPristupaDTO>(pravoPristupaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PravoPristupaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{pravoPristupaId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<PravoPristupaDTO> deletePravoPristupa(@PathVariable("pravoPristupaId") Long pravoPristupaId) {
        if(pravoPristupaService.findOne(pravoPristupaId).isPresent()) {
            pravoPristupaService.delete(pravoPristupaId);
            return new ResponseEntity<PravoPristupaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PravoPristupaDTO>(HttpStatus.NOT_FOUND);
    }
}
