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
import rs.ac.singidunum.isa.app.dto.IshodDTO;
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Ishod;
import rs.ac.singidunum.isa.app.service.IshodService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/ishodi")
public class IshodController {
    @Autowired
    private IshodService ishodService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<IshodDTO>> getAllIshodi() {
        ArrayList<IshodDTO> ishodi = IshodDTO.toDTOArrayList(ishodService.findAll(), true);
        return new ResponseEntity<Iterable<IshodDTO>>(ishodi, HttpStatus.OK);
    }

    @RequestMapping(path = "/{ishodId}", method = RequestMethod.GET)
    public ResponseEntity<IshodDTO> getIshod(@PathVariable("ishodId") Long ishodId) {
        Optional<Ishod> ishod = ishodService.findOne(ishodId);
        if(ishod.isPresent()) {
            IshodDTO ishodDTO = IshodDTO.toDTO(ishod.get(), true);
            return new ResponseEntity<IshodDTO>(ishodDTO, HttpStatus.OK);
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<IshodDTO> createIshod(@RequestBody Ishod ishod) {
        try {
            ishodService.save(ishod);
            IshodDTO ishodDTO = IshodDTO.toDTO(ishod, true);
            return new ResponseEntity<IshodDTO>(ishodDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{ishodId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<IshodDTO> updateIshod(@PathVariable("ishodId") Long ishodId, @RequestBody Ishod izmenjenIshod) {
        Ishod ishod = ishodService.findOne(ishodId).orElse(null);
        if(ishod != null) {
            izmenjenIshod.setId(ishodId);
            izmenjenIshod = ishodService.save(izmenjenIshod);
            IshodDTO ishodDTO = IshodDTO.toDTO(izmenjenIshod, true);
            return new ResponseEntity<IshodDTO>(ishodDTO, HttpStatus.OK);
        }
        return new ResponseEntity<IshodDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{ishodId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<MestoDTO> deleteIshod(@PathVariable("ishodId") Long ishodId) {
        if(ishodService.findOne(ishodId).isPresent()) {
            ishodService.delete(ishodId);
            return new ResponseEntity<MestoDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }
}
