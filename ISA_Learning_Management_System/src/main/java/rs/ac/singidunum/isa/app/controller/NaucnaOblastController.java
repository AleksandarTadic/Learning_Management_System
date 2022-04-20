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
import rs.ac.singidunum.isa.app.dto.NaucnaOblastDTO;
import rs.ac.singidunum.isa.app.model.NaucnaOblast;
import rs.ac.singidunum.isa.app.service.NaucnaOblastService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/naucneOblasti")
public class NaucnaOblastController {
    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<NaucnaOblastDTO>> getAllNaucneOblasti() {
        ArrayList<NaucnaOblastDTO> naucneOblasti = NaucnaOblastDTO.toDTOArrayList(naucnaOblastService.findAll(), true);

        return new ResponseEntity<ArrayList<NaucnaOblastDTO>>(naucneOblasti, HttpStatus.OK);
    }


    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.GET)
    public ResponseEntity<NaucnaOblastDTO> getNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId) {
        Optional<NaucnaOblast> tipZvanja = naucnaOblastService.findOne(naucnaOblastId);
        if(tipZvanja.isPresent()) {
            NaucnaOblastDTO tipZvanjaDTO = NaucnaOblastDTO.toDTO(tipZvanja.get(), true);
            return new ResponseEntity<NaucnaOblastDTO>(tipZvanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NaucnaOblastDTO> createTipZvanja(@RequestBody NaucnaOblast naucnaOblast) {
        try {
            naucnaOblastService.save(naucnaOblast);
            NaucnaOblastDTO naucnaOblastDTO = NaucnaOblastDTO.toDTO(naucnaOblast, true);
            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NaucnaOblastDTO> updateNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId, @RequestBody NaucnaOblast izmenjenaNaucnaOblast) {
        NaucnaOblast naucnaOblast = naucnaOblastService.findOne(naucnaOblastId).orElse(null);
        if(naucnaOblast != null) {
            izmenjenaNaucnaOblast.setId(naucnaOblastId);
            izmenjenaNaucnaOblast = naucnaOblastService.save(izmenjenaNaucnaOblast);
            NaucnaOblastDTO naucnaOblastDTO = NaucnaOblastDTO.toDTO(izmenjenaNaucnaOblast, true);
            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NaucnaOblastDTO> deleteNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId) {
        if(naucnaOblastService.findOne(naucnaOblastId).isPresent()) {
            naucnaOblastService.delete(naucnaOblastId);
            return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }
}
