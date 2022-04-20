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

import rs.ac.singidunum.isa.app.dto.ZvanjeDTO;
import rs.ac.singidunum.isa.app.model.Zvanje;
import rs.ac.singidunum.isa.app.service.ZvanjeService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/zvanja")
public class ZvanjeController {
    @Autowired
    private ZvanjeService zvanjeService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<ZvanjeDTO>> getAllZvanja() {
        ArrayList<ZvanjeDTO> zvanja = ZvanjeDTO.toDTOArrayList(zvanjeService.findAll(), true);
        return new ResponseEntity<ArrayList<ZvanjeDTO>>(zvanja, HttpStatus.OK);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.GET)
    public ResponseEntity<ZvanjeDTO> getZvanje(@PathVariable("zvanjeId") Long zvanjeId) {
        Optional<Zvanje> zvanje = zvanjeService.findOne(zvanjeId);
        if(zvanje.isPresent()) {
            ZvanjeDTO zvanjeDTO = ZvanjeDTO.toDTO(zvanje.get(), true);
            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ZvanjeDTO> createZvanje(@RequestBody Zvanje zvanje) {
        try {
            Optional<Zvanje> nadjenoZvanje = this.zvanjeService.findZvanjeByNastavnikAndNaucnaOblast(zvanje.getNastavnik().getId(), zvanje.getNaucnaOblast().getId());
            if(nadjenoZvanje.isPresent()) {
                return new ResponseEntity<ZvanjeDTO>(HttpStatus.CONFLICT);
            }
            if(zvanje.getDatumPrestanka() != null) {
                if(zvanje.getDatumPrestanka().getTime() < zvanje.getDatumIzbora().getTime()) {
                    return new ResponseEntity<ZvanjeDTO>(HttpStatus.BAD_REQUEST);
                }
            }
            zvanjeService.save(zvanje);
            ZvanjeDTO zvanjeDTO = ZvanjeDTO.toDTO(zvanje, true);
            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ZvanjeDTO> updateZvanje(@PathVariable("zvanjeId") Long zvanjeId, @RequestBody Zvanje izmenjenoZvanje) {
        Zvanje zvanje = zvanjeService.findOne(zvanjeId).orElse(null);
        if(zvanje != null) {
            if(izmenjenoZvanje.getDatumPrestanka() != null) {
                if(izmenjenoZvanje.getDatumPrestanka().getTime() < izmenjenoZvanje.getDatumIzbora().getTime()) {
                    return new ResponseEntity<ZvanjeDTO>(HttpStatus.BAD_REQUEST);
                }
            }
            if((zvanje.getNaucnaOblast().getId() != izmenjenoZvanje.getNaucnaOblast().getId()) || (zvanje.getNastavnik().getId() != izmenjenoZvanje.getNastavnik().getId())) {
                return new ResponseEntity<ZvanjeDTO>(HttpStatus.BAD_REQUEST);
            }
            izmenjenoZvanje.setId(zvanjeId);
            izmenjenoZvanje = zvanjeService.save(izmenjenoZvanje);
            ZvanjeDTO zvanjeDTO = ZvanjeDTO.toDTO(izmenjenoZvanje, true);
            return new ResponseEntity<ZvanjeDTO>(zvanjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{zvanjeId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ZvanjeDTO> deleteZvanje(@PathVariable("zvanjeId") Long zvanjeId) {
        if(zvanjeService.findOne(zvanjeId).isPresent()) {
            zvanjeService.delete(zvanjeId);
            return new ResponseEntity<ZvanjeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<ZvanjeDTO>(HttpStatus.NOT_FOUND);
    }
}
