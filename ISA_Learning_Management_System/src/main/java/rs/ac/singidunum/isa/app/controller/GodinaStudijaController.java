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
import rs.ac.singidunum.isa.app.dto.GodinaStudijaDTO;
import rs.ac.singidunum.isa.app.model.GodinaStudija;
import rs.ac.singidunum.isa.app.service.GodinaStudijaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/godineStudija")
public class GodinaStudijaController {
    @Autowired
    private GodinaStudijaService godinaStudijaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<GodinaStudijaDTO>> getAllGodineStudija() {
        ArrayList<GodinaStudijaDTO> godineStudija = GodinaStudijaDTO.toDTOArrayList(godinaStudijaService.findAll(), true);
        return new ResponseEntity<Iterable<GodinaStudijaDTO>>(godineStudija, HttpStatus.OK);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.GET)
    public ResponseEntity<GodinaStudijaDTO> getGodinaStudija(@PathVariable("godinaStudijaId") Long godinaStudijaId) {
        Optional<GodinaStudija> godinaStudija = godinaStudijaService.findOne(godinaStudijaId);
        if(godinaStudija.isPresent()) {
            GodinaStudijaDTO godinaStudijaDTO = GodinaStudijaDTO.toDTO(godinaStudija.get(), true);
            return new ResponseEntity<GodinaStudijaDTO>(godinaStudijaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<GodinaStudijaDTO> createGodinaStudija(@RequestBody GodinaStudija godinaStudija) {
        try {
            godinaStudijaService.save(godinaStudija);
            GodinaStudijaDTO godinaStudijaDTO = GodinaStudijaDTO.toDTO(godinaStudija, true);
            return new ResponseEntity<GodinaStudijaDTO>(godinaStudijaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<GodinaStudijaDTO> updateGodinaStudija(@PathVariable("godinaStudijaId") Long godinaStudijaId, @RequestBody GodinaStudija izmenjenaGodinaStudija) {
        GodinaStudija godinaStudija = godinaStudijaService.findOne(godinaStudijaId).orElse(null);
        if(godinaStudija != null) {
            izmenjenaGodinaStudija.setId(godinaStudijaId);
            izmenjenaGodinaStudija = godinaStudijaService.save(izmenjenaGodinaStudija);
            GodinaStudijaDTO godinaStudijaDTO = GodinaStudijaDTO.toDTO(izmenjenaGodinaStudija, true);
            return new ResponseEntity<GodinaStudijaDTO>(godinaStudijaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{godinaStudijaId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<GodinaStudijaDTO> deleteGodinaStudija(@PathVariable("godinaStudijaId") Long godinaStudijaId) {
        if(godinaStudijaService.findOne(godinaStudijaId).isPresent()) {
            godinaStudijaService.delete(godinaStudijaId);
            return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<GodinaStudijaDTO>(HttpStatus.NOT_FOUND);
    }
}
