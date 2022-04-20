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
import rs.ac.singidunum.isa.app.dto.NastavnikNaRealizacijiDTO;
import rs.ac.singidunum.isa.app.model.NastavnikNaRealizaciji;
import rs.ac.singidunum.isa.app.service.NastavnikNaRealizacijiService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/NastavniciNaRealizaciji")
public class NastavnikNaRealizacijiController {
    @Autowired
    private NastavnikNaRealizacijiService nastavnikNaRealizacijiService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<NastavnikNaRealizacijiDTO>> getAllNastavniciNaRealizaciji() {
        ArrayList<NastavnikNaRealizacijiDTO> nastavniciNaRealizaciji = NastavnikNaRealizacijiDTO.toDTOArrayList(nastavnikNaRealizacijiService.findAll(), true);

        return new ResponseEntity<ArrayList<NastavnikNaRealizacijiDTO>>(nastavniciNaRealizaciji, HttpStatus.OK);
    }

    @RequestMapping(path = "/{nastavnikNaRealizacijiId}", method = RequestMethod.GET)
    public ResponseEntity<NastavnikNaRealizacijiDTO> getNastavnikNaRealizaciji(@PathVariable("nastavnikNaRealizacijiId") Long nastavnikNaRealizacijiId) {
        Optional<NastavnikNaRealizaciji> nastavnikNaRealizaciji = nastavnikNaRealizacijiService.findOne(nastavnikNaRealizacijiId);
        if(nastavnikNaRealizaciji.isPresent()) {
            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = NastavnikNaRealizacijiDTO.toDTO(nastavnikNaRealizaciji.get(), true);
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(nastavnikNaRealizacijiDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NastavnikNaRealizacijiDTO> createNastavnikNaRealizaciji(@RequestBody NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        try {
            nastavnikNaRealizacijiService.save(nastavnikNaRealizaciji);
            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = NastavnikNaRealizacijiDTO.toDTO(nastavnikNaRealizaciji, true);
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(nastavnikNaRealizacijiDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{nastavnikNaRealizacijiId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NastavnikNaRealizacijiDTO> updateNastavnikNaRealizaciji(@PathVariable("nastavnikNaRealizacijiId") Long nastavnikNaRealizacijiId, @RequestBody NastavnikNaRealizaciji izmenjenNastavnikNaRealizaciji) {
        NastavnikNaRealizaciji nastavnikNaRealizaciji = nastavnikNaRealizacijiService.findOne(nastavnikNaRealizacijiId).orElse(null);
        if(nastavnikNaRealizaciji != null) {
            izmenjenNastavnikNaRealizaciji.setId(nastavnikNaRealizacijiId);
            izmenjenNastavnikNaRealizaciji = nastavnikNaRealizacijiService.save(izmenjenNastavnikNaRealizaciji);
            NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO = NastavnikNaRealizacijiDTO.toDTO(izmenjenNastavnikNaRealizaciji, true);
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(nastavnikNaRealizacijiDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{nastavnikNaRealizacijiId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NastavnikNaRealizacijiDTO> deleteNastavnikNaRealizaciji(@PathVariable("nastavnikNaRealizacijiId") Long nastavnikNaRealizacijiId) {
        if(nastavnikNaRealizacijiService.findOne(nastavnikNaRealizacijiId).isPresent()) {
            nastavnikNaRealizacijiService.delete(nastavnikNaRealizacijiId);
            return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikNaRealizacijiDTO>(HttpStatus.NOT_FOUND);
    }
}
