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
import rs.ac.singidunum.isa.app.dto.KorisnikDTO;
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Korisnik;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.service.KorisnikService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(path = "", method = RequestMethod.GET)
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<Iterable<KorisnikDTO>> getAllKorisnici() {
        ArrayList<KorisnikDTO> korisnici = KorisnikDTO.toDTOArrayList(korisnikService.findAll(), true);
        return new ResponseEntity<Iterable<KorisnikDTO>>(korisnici, HttpStatus.OK);
    }

    @RequestMapping(path = "/{korisnikId}", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> getKorisnik(@PathVariable("korisnikId") Long korisnikId) {
        Optional<Korisnik> korisnik = korisnikService.findOne(korisnikId);
        if(korisnik.isPresent()) {
            KorisnikDTO korisnikDTO = KorisnikDTO.toDTO(korisnik.get(), true);
            return new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.OK);
        }
        return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> createKorisnik(@RequestBody Korisnik korisnik) {
        try {
            korisnikService.save(korisnik);
            KorisnikDTO korisnikDTO = KorisnikDTO.toDTO(korisnik, true);
            return new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<KorisnikDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{korisnikId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> updateKorisnik(@PathVariable("korisnikId") Long korisnikId, @RequestBody Korisnik izmenjenKorisnik) {
        Korisnik korisnik = korisnikService.findOne(korisnikId).orElse(null);
        if(korisnik != null) {
            izmenjenKorisnik.setId(korisnikId);
            izmenjenKorisnik = korisnikService.save(izmenjenKorisnik);
            KorisnikDTO korisnikDTO = KorisnikDTO.toDTO(izmenjenKorisnik, true);
            return new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.OK);
        }
        return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{korisnikId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<KorisnikDTO> deleteKorisnik(@PathVariable("korisnikId") Long korisnikId) {
        if(korisnikService.findOne(korisnikId).isPresent()) {
            korisnikService.delete(korisnikId);
            return new ResponseEntity<KorisnikDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND);
    }
}
