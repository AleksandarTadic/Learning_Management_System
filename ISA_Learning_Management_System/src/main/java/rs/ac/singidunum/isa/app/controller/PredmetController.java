package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.PredmetDTO;
import rs.ac.singidunum.isa.app.model.Korisnik;
import rs.ac.singidunum.isa.app.model.Predmet;
import rs.ac.singidunum.isa.app.service.KorisnikService;
import rs.ac.singidunum.isa.app.service.PredmetService;
import rs.ac.singidunum.isa.app.utils.TokenUtils;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/predmeti")
public class PredmetController {
    @Autowired
    private PredmetService predmetService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<PredmetDTO>> getAllPredmeti() {
        ArrayList<PredmetDTO> predmeti = PredmetDTO.toDTOArrayList(predmetService.findAll(), true);
        return new ResponseEntity<Iterable<PredmetDTO>>(predmeti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{predmetId}", method = RequestMethod.GET)
    public ResponseEntity<PredmetDTO> getPredmet(@PathVariable("predmetId") Long predmetId) {
        Optional<Predmet> predmet = predmetService.findOne(predmetId);
        if(predmet.isPresent()) {
            PredmetDTO predmetDTO = PredmetDTO.toDTO(predmet.get(), true);
            return new ResponseEntity<PredmetDTO>(predmetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<PredmetDTO> createPredmet(@RequestBody Predmet predmet) {
        try {
            predmetService.save(predmet);
            PredmetDTO predmetDTO = PredmetDTO.toDTO(predmet, true);
            return new ResponseEntity<PredmetDTO>(predmetDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{predmetId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<PredmetDTO> updatePredmet(@PathVariable("predmetId") Long predmetId, @RequestBody Predmet izmenjenPredmet) {
        Predmet predmet = predmetService.findOne(predmetId).orElse(null);
        if(predmet != null) {
            izmenjenPredmet.setId(predmetId);
            izmenjenPredmet = predmetService.save(izmenjenPredmet);
            PredmetDTO predmetDTO = PredmetDTO.toDTO(izmenjenPredmet, true);
            return new ResponseEntity<PredmetDTO>(predmetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{predmetId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<PredmetDTO> deletePredmet(@PathVariable("predmetId") Long predmetId) {
        if(predmetService.findOne(predmetId).isPresent()) {
            predmetService.delete(predmetId);
            return new ResponseEntity<PredmetDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<PredmetDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/studentPredmetiNepolozeni", method = RequestMethod.GET)
    @Secured("ROLE_STUDENT")
    public ResponseEntity<Iterable<PredmetDTO>> getStudentPredmetiNepolozeni(@RequestHeader("Authorization")String token) {
        String username = tokenUtils.getUsername(token);
        Optional<Korisnik> korisnik = korisnikService.findByKorisnickoIme(username);
        if(korisnik.isPresent()) {
            ArrayList<PredmetDTO> predmeti = PredmetDTO.toDTOArrayList(predmetService.getStudentPredmetiNepolozeni(korisnik.get().getId()), true);

            return new ResponseEntity<Iterable<PredmetDTO>>(predmeti, HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<PredmetDTO>>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/studentPredmetiPolozeni", method = RequestMethod.GET)
    @Secured("ROLE_STUDENT")
    public ResponseEntity<Iterable<PredmetDTO>> getStudentPredmetiPolozeni(@RequestHeader("Authorization")String token) {
        String username = tokenUtils.getUsername(token);
        Optional<Korisnik> korisnik = korisnikService.findByKorisnickoIme(username);
        if(korisnik.isPresent()) {
            ArrayList<PredmetDTO> predmeti = PredmetDTO.toDTOArrayList(predmetService.getStudentPredmetiPolozeni(korisnik.get().getId()), true);

            return new ResponseEntity<Iterable<PredmetDTO>>(predmeti, HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<PredmetDTO>>(HttpStatus.NOT_FOUND);
    }
}
