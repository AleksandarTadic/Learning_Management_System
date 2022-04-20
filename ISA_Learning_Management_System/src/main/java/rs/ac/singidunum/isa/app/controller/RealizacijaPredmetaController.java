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
import rs.ac.singidunum.isa.app.dto.RealizacijaPredmetaDTO;
import rs.ac.singidunum.isa.app.model.RealizacijaPredmeta;
import rs.ac.singidunum.isa.app.service.RealizacijaPredmetaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/realizacijePredmeta")
public class RealizacijaPredmetaController {
    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<RealizacijaPredmetaDTO>> getAllRealizacijePredmeta() {
        ArrayList<RealizacijaPredmetaDTO> RealizacijePredmeta = RealizacijaPredmetaDTO.toDTOArrayList(realizacijaPredmetaService.findAll(), true);
        return new ResponseEntity<ArrayList<RealizacijaPredmetaDTO>>(RealizacijePredmeta, HttpStatus.OK);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.GET)
    public ResponseEntity<RealizacijaPredmetaDTO> getRealizacijaPredmeta(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId) {
        Optional<RealizacijaPredmeta> realizacijaPredmeta = realizacijaPredmetaService.findOne(realizacijaPredmetaId);
        if(realizacijaPredmeta.isPresent()) {
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = RealizacijaPredmetaDTO.toDTO(realizacijaPredmeta.get(), true);
            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<RealizacijaPredmetaDTO> createRealizacijaPredmeta(@RequestBody RealizacijaPredmeta realizacijaPredmeta) {
        try {
            realizacijaPredmetaService.save(realizacijaPredmeta);
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = RealizacijaPredmetaDTO.toDTO(realizacijaPredmeta, true);
            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<RealizacijaPredmetaDTO> updateRealizacijaPredmeta(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId, @RequestBody RealizacijaPredmeta izmenjenaRealizacijaPredmeta) {
        RealizacijaPredmeta realizacijaPredmeta = realizacijaPredmetaService.findOne(realizacijaPredmetaId).orElse(null);
        if(realizacijaPredmeta != null) {
            izmenjenaRealizacijaPredmeta.setId(realizacijaPredmetaId);
            izmenjenaRealizacijaPredmeta = realizacijaPredmetaService.save(izmenjenaRealizacijaPredmeta);
            RealizacijaPredmetaDTO realizacijaPredmetaDTO = RealizacijaPredmetaDTO.toDTO(izmenjenaRealizacijaPredmeta, true);
            return new ResponseEntity<RealizacijaPredmetaDTO>(realizacijaPredmetaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{realizacijaPredmetaId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<RealizacijaPredmetaDTO> deleteRealizacijaPredmeta(@PathVariable("realizacijaPredmetaId") Long realizacijaPredmetaId) {
        if(realizacijaPredmetaService.findOne(realizacijaPredmetaId).isPresent()) {
            realizacijaPredmetaService.delete(realizacijaPredmetaId);
            return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<RealizacijaPredmetaDTO>(HttpStatus.NOT_FOUND);
    }
}
