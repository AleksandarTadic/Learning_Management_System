package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.ObavestenjeDTO;
import rs.ac.singidunum.isa.app.model.Obavestenje;
import rs.ac.singidunum.isa.app.service.ObavestenjeService;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/obavestenja")
public class ObavestenjeController {
    @Autowired
    private ObavestenjeService obavestenjeService;

    @Autowired
    private Queue obavestenjeQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ObavestenjeDTO>> getAllObavestenja() {
        ArrayList<ObavestenjeDTO> obavestenja = ObavestenjeDTO.toDTOArrayList(obavestenjeService.findAll(), true);

        return new ResponseEntity<Iterable<ObavestenjeDTO>>(obavestenja, HttpStatus.OK);
    }

    @RequestMapping(path = "/{obavestenjeId}", method = RequestMethod.GET)
    public ResponseEntity<ObavestenjeDTO> getObavestenje(@PathVariable("obavestenjeId") Long obavestenjeId) {
        Optional<Obavestenje> obavestenje = obavestenjeService.findOne(obavestenjeId);
        if(obavestenje.isPresent()) {
            ObavestenjeDTO obavestenjeDTO = ObavestenjeDTO.toDTO(obavestenje.get(), true);
            return new ResponseEntity<ObavestenjeDTO>(obavestenjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ObavestenjeDTO> createObavestenje(@RequestBody Obavestenje obavestenje) {
        try {
            obavestenjeService.save(obavestenje);
            ObavestenjeDTO obavestenjeDTO = ObavestenjeDTO.toDTO(obavestenje, true);

//          POTREBNA NADOGRADNJA!!!!!!!!!!!!!!!
            jmsTemplate.convertAndSend(obavestenjeQueue, ObavestenjeDTO.toDTO(obavestenje, false));

            return new ResponseEntity<ObavestenjeDTO>(obavestenjeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{obavestenjeId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ObavestenjeDTO> updateObavestenje(@PathVariable("obavestenjeId") Long obavestenjeId, @RequestBody Obavestenje izmenjenoObavestenje) {
        Obavestenje obavestenje = obavestenjeService.findOne(obavestenjeId).orElse(null);
        if(obavestenje != null) {
            izmenjenoObavestenje.setId(obavestenjeId);
            izmenjenoObavestenje = obavestenjeService.save(izmenjenoObavestenje);
            ObavestenjeDTO obavestenjeDTO = ObavestenjeDTO.toDTO(izmenjenoObavestenje, true);

//          POTREBNA NADOGRADNJA!!!!!!!!!!!!!!!!
            jmsTemplate.convertAndSend(obavestenjeQueue, ObavestenjeDTO.toDTO(izmenjenoObavestenje, false));

            return new ResponseEntity<ObavestenjeDTO>(obavestenjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{obavestenjeId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ObavestenjeDTO> deleteObavestenje(@PathVariable("obavestenjeId") Long obavestenjeId) {
        if(obavestenjeService.findOne(obavestenjeId).isPresent()) {
            obavestenjeService.delete(obavestenjeId);
            return new ResponseEntity<ObavestenjeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.NOT_FOUND);
    }
}
