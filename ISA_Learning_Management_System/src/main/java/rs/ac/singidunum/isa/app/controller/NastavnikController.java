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
import rs.ac.singidunum.isa.app.dto.NastavnikDTO;
import rs.ac.singidunum.isa.app.model.*;
import rs.ac.singidunum.isa.app.service.KorisnikService;
import rs.ac.singidunum.isa.app.service.NastavnikService;
import rs.ac.singidunum.isa.app.service.PravoPristupaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/nastavnici")
public class NastavnikController {
    @Autowired
    private NastavnikService nastavnikService;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<NastavnikDTO>> getAllNastavnici() {
        ArrayList<NastavnikDTO> nastavnici = NastavnikDTO.toDTOArrayList(nastavnikService.findAll(), true);
        return new ResponseEntity<ArrayList<NastavnikDTO>>(nastavnici, HttpStatus.OK);
    }

    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.GET)
    public ResponseEntity<NastavnikDTO> getNastavnik(@PathVariable("nastavnikId") Long nastavnikId) {
        Optional<Nastavnik> nastavnik = nastavnikService.findOne(nastavnikId);
        if(nastavnik.isPresent()) {
            NastavnikDTO nastavnikDTO = NastavnikDTO.toDTO(nastavnik.get(), true);
            return new ResponseEntity<NastavnikDTO>(nastavnikDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<NastavnikDTO> createNastavnik(@RequestBody Nastavnik nastavnik) {
//Dodato
        for(Zvanje z : nastavnik.getZvanja()) {
            for(Zvanje i : nastavnik.getZvanja()) {
                if(i == z) {
                    continue;
                }
                if(z.getNaucnaOblast().getId() == i.getNaucnaOblast().getId()) {
                    return new ResponseEntity<NastavnikDTO>(HttpStatus.BAD_REQUEST);
                }
            }
        }

        Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findPravoPristupaByNaziv("ROLE_NASTAVNIK");
        PravoPristupa pravoPristupaNastavnik = null;
        if(pravoPristupa.isEmpty()) {
            pravoPristupaNastavnik = pravoPristupaService.save(new PravoPristupa(null, "ROLE_NASTAVNIK"));
        } else {
            pravoPristupaNastavnik = pravoPristupa.get();
        }
        nastavnik.getKorisnik().setPravoPristupa(pravoPristupaNastavnik);
        Optional<Korisnik> korisnik = korisnikService.findOne(nastavnik.getKorisnik().getId());
        if(korisnik.get().getPravoPristupa().getNaziv().equals("ROLE_KORISNIK")) {
            try {
                nastavnikService.save(nastavnik);
                korisnik.get().setPravoPristupa(pravoPristupaNastavnik);
                korisnikService.save(korisnik.get());
                NastavnikDTO nastavnikDTO = NastavnikDTO.toDTO(nastavnik, true);
                return new ResponseEntity<NastavnikDTO>(nastavnikDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NastavnikDTO> updateNastavnik(@PathVariable("nastavnikId") Long nastavnikId, @RequestBody Nastavnik izmenjenNastavnik) {
        Nastavnik nastavnik = nastavnikService.findOne(nastavnikId).orElse(null);
        if(nastavnik != null) {
            if(nastavnik.getKorisnik().getPravoPristupa().getNaziv() == "ROLE_NASTAVNIK") {
                izmenjenNastavnik.setId(nastavnikId);
                izmenjenNastavnik = nastavnikService.save(izmenjenNastavnik);
                NastavnikDTO nastavnikDTO = NastavnikDTO.toDTO(izmenjenNastavnik, true);
                return new ResponseEntity<NastavnikDTO>(nastavnikDTO, HttpStatus.OK);
            }
            return new ResponseEntity<NastavnikDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{nastavnikId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<NastavnikDTO> deleteNastavnik(@PathVariable("nastavnikId") Long nastavnikId) {
        Optional<Nastavnik> nastavnik = nastavnikService.findOne(nastavnikId);
        if(nastavnik.isPresent()) {
//            Dodato
            Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findPravoPristupaByNaziv("ROLE_KORISNIK");
            Optional<Korisnik> korisnik = korisnikService.findOne(nastavnik.get().getKorisnik().getId());
            korisnik.get().setPravoPristupa(pravoPristupa.get());
            korisnikService.save(korisnik.get());
//
            nastavnikService.delete(nastavnikId);
            return new ResponseEntity<NastavnikDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<NastavnikDTO>(HttpStatus.NOT_FOUND);
    }
}
