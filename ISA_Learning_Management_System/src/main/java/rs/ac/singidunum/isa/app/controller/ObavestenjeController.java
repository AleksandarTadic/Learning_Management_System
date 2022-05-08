package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.app.dto.EmailDTO;
import rs.ac.singidunum.isa.app.dto.ObavestenjeDTO;
import rs.ac.singidunum.isa.app.dto.PriloziDTO;
import rs.ac.singidunum.isa.app.model.*;
import rs.ac.singidunum.isa.app.service.*;
import rs.ac.singidunum.isa.app.utils.TokenUtils;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/obavestenja")
public class ObavestenjeController {
    @Autowired
    private ObavestenjeService obavestenjeService;

    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private Queue obavestenjeQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ObavestenjeDTO>> getAllObavestenja() {
        ArrayList<ObavestenjeDTO> obavestenja = ObavestenjeDTO.toDTOArrayList(obavestenjeService.findAll(), true);

        return new ResponseEntity<Iterable<ObavestenjeDTO>>(obavestenja, HttpStatus.OK);
    }

    @RequestMapping(path = "/{obavestenjeId}", method = RequestMethod.GET)
    public ResponseEntity<ObavestenjeDTO> getObavestenje(@PathVariable("obavestenjeId") Long obavestenjeId) {
        Optional<Obavestenje> obavestenje = obavestenjeService.findOne(obavestenjeId);
        if(obavestenje.isPresent()) {
            ObavestenjeDTO obavestenjeDTO = ObavestenjeDTO.toDTO(obavestenje.get(), true);
            return new ResponseEntity<ObavestenjeDTO>(obavestenjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<ObavestenjeDTO> createObavestenje(@RequestBody Obavestenje obavestenje) {
        try {
            obavestenjeService.save(obavestenje);
            ObavestenjeDTO obavestenjeDTO = ObavestenjeDTO.toDTO(obavestenje, true);
//          UPGRADE EMAIL SERVICE
            try {
                ArrayList<String> emailovi = pohadjanjePredmetaService.findEmailByRealizacijaPredmeta(obavestenje.getRealizacijaPredmeta().getId());
                Optional<RealizacijaPredmeta> realizacijaPredmeta = realizacijaPredmetaService.findOne(obavestenje.getRealizacijaPredmeta().getId());
                if(realizacijaPredmeta.isPresent()) {
                    for(NastavnikNaRealizaciji n : realizacijaPredmeta.get().getNastavniciNaRealizaciji()) {
                        emailovi.add(n.getNastavnik().getKorisnik().getEmail());
                    }
                }
                EmailDTO emailDTO = new EmailDTO(emailovi, obavestenje.getSadrzaj(), obavestenje.getNaslov());
                ArrayList<PriloziDTO> prilozi = new ArrayList<>();
                for(Fajl f : obavestenje.getPrilozi()) {
                    prilozi.add(new PriloziDTO(f.getId(), f.getOpis(), f.getUrl()));
                }
                emailDTO.setPrilozi(prilozi);
                jmsTemplate.convertAndSend(obavestenjeQueue, emailDTO);
    //          =========
                for(String s: emailovi) {
                    System.out.println(s);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            return new ResponseEntity<ObavestenjeDTO>(obavestenjeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{obavestenjeId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ObavestenjeDTO> updateObavestenje(@PathVariable("obavestenjeId") Long obavestenjeId, @RequestBody Obavestenje izmenjenoObavestenje) {
        Obavestenje obavestenje = obavestenjeService.findOne(obavestenjeId).orElse(null);
        if(obavestenje != null) {
            izmenjenoObavestenje.setId(obavestenjeId);
            izmenjenoObavestenje = obavestenjeService.save(izmenjenoObavestenje);
            ObavestenjeDTO obavestenjeDTO = ObavestenjeDTO.toDTO(izmenjenoObavestenje, true);
//          UPGRADE EMAIL SERVICE
            try {
                ArrayList<String> emailovi = pohadjanjePredmetaService.findEmailByRealizacijaPredmeta(izmenjenoObavestenje.getRealizacijaPredmeta().getId());
                Optional<RealizacijaPredmeta> realizacijaPredmeta = realizacijaPredmetaService.findOne(obavestenje.getRealizacijaPredmeta().getId());
                if(realizacijaPredmeta.isPresent()) {
                    for(NastavnikNaRealizaciji n : realizacijaPredmeta.get().getNastavniciNaRealizaciji()) {
                        emailovi.add(n.getNastavnik().getKorisnik().getEmail());
                    }
                }
                EmailDTO emailDTO = new EmailDTO(emailovi, izmenjenoObavestenje.getSadrzaj(), izmenjenoObavestenje.getNaslov());
                ArrayList<PriloziDTO> prilozi = new ArrayList<>();
                for(Fajl f : izmenjenoObavestenje.getPrilozi()) {
                    prilozi.add(new PriloziDTO(f.getId(), f.getOpis(), f.getUrl()));
                }
                emailDTO.setPrilozi(prilozi);
                jmsTemplate.convertAndSend(obavestenjeQueue, emailDTO);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            return new ResponseEntity<ObavestenjeDTO>(obavestenjeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{obavestenjeId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ObavestenjeDTO> deleteObavestenje(@PathVariable("obavestenjeId") Long obavestenjeId) {
        if(obavestenjeService.findOne(obavestenjeId).isPresent()) {
            obavestenjeService.delete(obavestenjeId);
            return new ResponseEntity<ObavestenjeDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<ObavestenjeDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/studentObavestenja", method = RequestMethod.GET)
    @Secured("ROLE_STUDENT")
    public ResponseEntity<Iterable<ObavestenjeDTO>> getStudentObavestenja(@RequestHeader("Authorization")String token) {
        String username = tokenUtils.getUsername(token);
        Optional<Korisnik> korisnik = korisnikService.findByKorisnickoIme(username);
        if(korisnik.isPresent()) {
            ArrayList<ObavestenjeDTO> obavestenja = ObavestenjeDTO.toDTOArrayList(obavestenjeService.getStudentObavestenja(korisnik.get().getId()), false);
            return new ResponseEntity<Iterable<ObavestenjeDTO>>(obavestenja, HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<ObavestenjeDTO>>(HttpStatus.NOT_FOUND);
    }

}
