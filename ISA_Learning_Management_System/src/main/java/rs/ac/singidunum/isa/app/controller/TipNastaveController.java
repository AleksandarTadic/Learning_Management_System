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

import rs.ac.singidunum.isa.app.dto.TipNastaveDTO;
import rs.ac.singidunum.isa.app.model.TipNastave;
import rs.ac.singidunum.isa.app.service.TipNastaveService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/tipoviNastave")
public class TipNastaveController {
    @Autowired
    private TipNastaveService tipNastaveService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<TipNastaveDTO>> getAllTipoviNastave() {
        ArrayList<TipNastaveDTO> tipoviNastave = TipNastaveDTO.toDTOArrayList(tipNastaveService.findAll(), true);
        return new ResponseEntity<ArrayList<TipNastaveDTO>>(tipoviNastave, HttpStatus.OK);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.GET)
    public ResponseEntity<TipNastaveDTO> getTipNastave(@PathVariable("tipNastaveId") Long tipNastaveId) {
        Optional<TipNastave> tipNastave = tipNastaveService.findOne(tipNastaveId);
        if(tipNastave.isPresent()) {
            TipNastaveDTO tipNastaveDTO = TipNastaveDTO.toDTO(tipNastave.get(), true);
            return new ResponseEntity<TipNastaveDTO>(tipNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TipNastaveDTO> createTipNastave(@RequestBody TipNastave tipNastave) {
        try {
            tipNastaveService.save(tipNastave);
            TipNastaveDTO tipNastaveDTO = TipNastaveDTO.toDTO(tipNastave, true);
            return new ResponseEntity<TipNastaveDTO>(tipNastaveDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TipNastaveDTO> updateTipNastave(@PathVariable("tipNastaveId") Long tipNastaveId, @RequestBody TipNastave izmenjenTipNastave) {
        TipNastave tipNastave = tipNastaveService.findOne(tipNastaveId).orElse(null);
        if(tipNastave != null) {
            izmenjenTipNastave.setId(tipNastaveId);
            izmenjenTipNastave = tipNastaveService.save(izmenjenTipNastave);
            TipNastaveDTO tipNastaveDTO = TipNastaveDTO.toDTO(izmenjenTipNastave, true);
            return new ResponseEntity<TipNastaveDTO>(tipNastaveDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{tipNastaveId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TipNastaveDTO> deleteTipNastave(@PathVariable("tipNastaveId") Long tipNastaveId) {
        if(tipNastaveService.findOne(tipNastaveId).isPresent()) {
            tipNastaveService.delete(tipNastaveId);
            return new ResponseEntity<TipNastaveDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipNastaveDTO>(HttpStatus.NOT_FOUND);
    }
}
