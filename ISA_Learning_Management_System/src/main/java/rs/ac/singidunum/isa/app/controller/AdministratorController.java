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
import rs.ac.singidunum.isa.app.dto.AdministratorDTO;
import rs.ac.singidunum.isa.app.model.Administrator;
import rs.ac.singidunum.isa.app.model.Korisnik;
import rs.ac.singidunum.isa.app.model.PravoPristupa;
import rs.ac.singidunum.isa.app.service.AdministratorService;
import rs.ac.singidunum.isa.app.service.KorisnikService;
import rs.ac.singidunum.isa.app.service.PravoPristupaService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/administratori")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ArrayList<AdministratorDTO>> getAllAdministratori() {
        ArrayList<AdministratorDTO> administratori = AdministratorDTO.toDTOArrayList(administratorService.findAll(), true);

        return new ResponseEntity<ArrayList<AdministratorDTO>>(administratori, HttpStatus.OK);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdministratorDTO> getAdministrator(@PathVariable("administratorId") Long administratorId) {
        Optional<Administrator> administrator = administratorService.findOne(administratorId);
        if(administrator.isPresent()) {
            AdministratorDTO administratorDTO = AdministratorDTO.toDTO(administrator.get(), true);
            return new ResponseEntity<AdministratorDTO>(administratorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdministratorDTO> createAdministrator(@RequestBody Administrator administrator) {
        Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findPravoPristupaByNaziv("ROLE_ADMIN");
        PravoPristupa pravoPristupaAdministrator = null;
        if(pravoPristupa.isEmpty()) {
            pravoPristupaAdministrator = pravoPristupaService.save(new PravoPristupa(null, "ROLE_ADMIN"));
        } else {
            pravoPristupaAdministrator = pravoPristupa.get();
        }
        administrator.getKorisnik().setPravoPristupa(pravoPristupaAdministrator);

        Optional<Korisnik> korisnik = korisnikService.findOne(administrator.getKorisnik().getId());
        if(korisnik.get().getPravoPristupa().getNaziv().equals("ROLE_KORISNIK")) {
            try {
                administratorService.save(administrator);
                korisnik.get().setPravoPristupa(pravoPristupaAdministrator);
                korisnikService.save(korisnik.get());
                AdministratorDTO administratorDTO = AdministratorDTO.toDTO(administrator, true);
                return new ResponseEntity<AdministratorDTO>(administratorDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdministratorDTO> updateAdministrator(@PathVariable("administratorId") Long administratorId, @RequestBody Administrator izmenjenAdministrator) {
        Administrator administrator = administratorService.findOne(administratorId).orElse(null);
        if(administrator != null ) {
            if(administrator.getKorisnik().getPravoPristupa().getNaziv() == "ROLE_ADMIN") {
                izmenjenAdministrator.setId(administratorId);
                izmenjenAdministrator = administratorService.save(izmenjenAdministrator);
                AdministratorDTO administratorDTO = AdministratorDTO.toDTO(izmenjenAdministrator, true);
                return new ResponseEntity<AdministratorDTO>(administratorDTO, HttpStatus.OK);
            }
            return new ResponseEntity<AdministratorDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{administratorId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<AdministratorDTO> deleteAdministrator(@PathVariable("administratorId") Long administratorId) {
        if(administratorService.findOne(administratorId).isPresent()) {
            administratorService.delete(administratorId);
            return new ResponseEntity<AdministratorDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
    }
}
