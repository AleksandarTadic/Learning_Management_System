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
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.service.MestoService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/mesta")
public class MestoController {
    @Autowired
    private MestoService mestoService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<MestoDTO>> getAllMesta() {
        ArrayList<MestoDTO> mesta = MestoDTO.toDTOArrayList(mestoService.findAll(), true);
        return new ResponseEntity<Iterable<MestoDTO>>(mesta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.GET)
    public ResponseEntity<MestoDTO> getMesto(@PathVariable("mestoId") Long mestoId) {
        Optional<Mesto> mesto = mestoService.findOne(mestoId);
        if(mesto.isPresent()) {
            MestoDTO mestoDTO = MestoDTO.toDTO(mesto.get(), true);
            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MestoDTO> createMesto(@RequestBody Mesto mesto) {
        try {
            mestoService.save(mesto);
            MestoDTO mestoDTO = MestoDTO.toDTO(mesto, true);
            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MestoDTO> updateMesto(@PathVariable("mestoId") Long mestoId, @RequestBody Mesto izmenjenoMesto) {
        Mesto mesto = mestoService.findOne(mestoId).orElse(null);
        if(mesto != null) {
            izmenjenoMesto.setId(mestoId);
            izmenjenoMesto = mestoService.save(izmenjenoMesto);
            MestoDTO mestoDTO = MestoDTO.toDTO(izmenjenoMesto, true);
            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MestoDTO> deleteMesto(@PathVariable("mestoId") Long mestoId) {
        if(mestoService.findOne(mestoId).isPresent()) {
            mestoService.delete(mestoId);
            return new ResponseEntity<MestoDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }
}
