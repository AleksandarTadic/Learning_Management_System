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
import rs.ac.singidunum.isa.app.dto.FajlDTO;
import rs.ac.singidunum.isa.app.model.Fajl;
import rs.ac.singidunum.isa.app.service.FajlService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/fajlovi")
public class FajlController {
    @Autowired
    private FajlService fajlService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<FajlDTO>> getAllFajlovi() {
        ArrayList<FajlDTO> fajlovi = FajlDTO.toDTOArrayList(fajlService.findAll(), true);

        return new ResponseEntity<Iterable<FajlDTO>>(fajlovi, HttpStatus.OK);
    }

    @RequestMapping(path = "/{fajlId}", method = RequestMethod.GET)
    public ResponseEntity<FajlDTO> getFajl(@PathVariable("fajlId") Long fajlId) {
        Optional<Fajl> fajl = fajlService.findOne(fajlId);
        if(fajl.isPresent()) {
            FajlDTO fajlDTO = FajlDTO.toDTO(fajl.get(), true);
            return new ResponseEntity<FajlDTO>(fajlDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FajlDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FajlDTO> createFajl(@RequestBody Fajl fajl) {
        try {
            fajlService.save(fajl);
            FajlDTO fajlDTO = FajlDTO.toDTO(fajl, true);
            return new ResponseEntity<FajlDTO>(fajlDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<FajlDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{fajlId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FajlDTO> updateFajl(@PathVariable("fajlId") Long fajlId, @RequestBody Fajl izmenjenFajl) {
        Fajl fajl = fajlService.findOne(fajlId).orElse(null);
        if(fajl != null) {
            izmenjenFajl.setId(fajlId);
            izmenjenFajl = fajlService.save(izmenjenFajl);
            FajlDTO fajlDTO = FajlDTO.toDTO(izmenjenFajl, true);
            return new ResponseEntity<FajlDTO>(fajlDTO, HttpStatus.OK);
        }
        return new ResponseEntity<FajlDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{fajlId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FajlDTO> deleteFajl(@PathVariable("fajlId") Long fajlId) {
        if(fajlService.findOne(fajlId).isPresent()) {
            fajlService.delete(fajlId);
            return new ResponseEntity<FajlDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<FajlDTO>(HttpStatus.NOT_FOUND);
    }
}
