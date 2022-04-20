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
import rs.ac.singidunum.isa.app.dto.TipZvanjaDTO;
import rs.ac.singidunum.isa.app.model.TipZvanja;
import rs.ac.singidunum.isa.app.service.TipZvanjaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/tipoviZvanja")
public class TipZvanjaController {
    @Autowired
    private TipZvanjaService tipZvanjaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<TipZvanjaDTO>> getAllTipoviZvanja() {
        ArrayList<TipZvanjaDTO> tipoviZvanja = TipZvanjaDTO.toDTOArrayList(tipZvanjaService.findAll(), true);

        return new ResponseEntity<ArrayList<TipZvanjaDTO>>(tipoviZvanja, HttpStatus.OK);
    }

    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.GET)
    public ResponseEntity<TipZvanjaDTO> getTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId) {
        Optional<TipZvanja> tipZvanja = tipZvanjaService.findOne(tipZvanjaId);
        if(tipZvanja.isPresent()) {
            TipZvanjaDTO tipZvanjaDTO = TipZvanjaDTO.toDTO(tipZvanja.get(), true);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TipZvanjaDTO> createTipZvanja(@RequestBody TipZvanja tipZvanja) {
        try {
            tipZvanjaService.save(tipZvanja);
            TipZvanjaDTO tipZvanjaDTO = TipZvanjaDTO.toDTO(tipZvanja, true);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TipZvanjaDTO> updateTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId, @RequestBody TipZvanja izmenjenTipZvanja) {
        TipZvanja tipZvanja = tipZvanjaService.findOne(tipZvanjaId).orElse(null);
        if(tipZvanja != null) {
            izmenjenTipZvanja.setId(tipZvanjaId);
            izmenjenTipZvanja = tipZvanjaService.save(izmenjenTipZvanja);
            TipZvanjaDTO tipZvanjaDTO = TipZvanjaDTO.toDTO(izmenjenTipZvanja, true);
            return new ResponseEntity<TipZvanjaDTO>(tipZvanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{tipZvanjaId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TipZvanjaDTO> deleteTipZvanja(@PathVariable("tipZvanjaId") Long tipZvanjaId) {
        if(tipZvanjaService.findOne(tipZvanjaId).isPresent()) {
            tipZvanjaService.delete(tipZvanjaId);
            return new ResponseEntity<TipZvanjaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<TipZvanjaDTO>(HttpStatus.NOT_FOUND);
    }
}
