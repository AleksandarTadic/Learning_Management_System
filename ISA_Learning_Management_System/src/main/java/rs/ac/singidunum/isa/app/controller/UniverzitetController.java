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
import rs.ac.singidunum.isa.app.dto.UniverzitetDTO;
import rs.ac.singidunum.isa.app.model.Univerzitet;
import rs.ac.singidunum.isa.app.service.UniverzitetService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/univerziteti")
public class UniverzitetController {
    @Autowired
    private UniverzitetService univerzitetService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<UniverzitetDTO>> getAllUniverziteti() {
        ArrayList<UniverzitetDTO> univerziteti = UniverzitetDTO.toDTOArrayList(univerzitetService.findAll(), true);
        return new ResponseEntity<ArrayList<UniverzitetDTO>>(univerziteti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.GET)
    public ResponseEntity<UniverzitetDTO> getUniverzitet(@PathVariable("univerzitetId") Long univerzitetId) {
        Optional<Univerzitet> univerzitet = univerzitetService.findOne(univerzitetId);
        if(univerzitet.isPresent()) {
            UniverzitetDTO univerzitetDTO = UniverzitetDTO.toDTO(univerzitet.get(), true);
            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UniverzitetDTO> createUniverzitet(@RequestBody Univerzitet univerzitet) {
        try {
            univerzitetService.save(univerzitet);
            UniverzitetDTO univerzitetDTO = UniverzitetDTO.toDTO(univerzitet, true);
            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UniverzitetDTO> updateUniverzitet(@PathVariable("univerzitetId") Long univerzitetId, @RequestBody Univerzitet izmenjenUniverzitet) {
        Univerzitet univerzitet = univerzitetService.findOne(univerzitetId).orElse(null);
        if(univerzitet != null) {
            izmenjenUniverzitet.setId(univerzitetId);
            izmenjenUniverzitet = univerzitetService.save(izmenjenUniverzitet);
            UniverzitetDTO univerzitetDTO = UniverzitetDTO.toDTO(izmenjenUniverzitet, true);
            return new ResponseEntity<UniverzitetDTO>(univerzitetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{univerzitetId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UniverzitetDTO> deleteUniverzitet(@PathVariable("univerzitetId") Long univerzitetId) {
        if(univerzitetService.findOne(univerzitetId).isPresent()) {
            univerzitetService.delete(univerzitetId);
            return new ResponseEntity<UniverzitetDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<UniverzitetDTO>(HttpStatus.NOT_FOUND);
    }
}
