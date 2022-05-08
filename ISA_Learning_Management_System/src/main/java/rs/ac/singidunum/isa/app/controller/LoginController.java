package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.KorisnikDTO;
import rs.ac.singidunum.isa.app.dto.TokenDTO;
import rs.ac.singidunum.isa.app.model.Korisnik;
import rs.ac.singidunum.isa.app.model.PravoPristupa;
import rs.ac.singidunum.isa.app.service.KorisnikService;
import rs.ac.singidunum.isa.app.service.PravoPristupaService;
import rs.ac.singidunum.isa.app.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PravoPristupaService pravoPristupaService;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> login(@RequestBody KorisnikDTO korisnik) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(korisnik.getKorisnickoIme(), korisnik.getLozinka());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(korisnik.getKorisnickoIme());
            String jwt = tokenUtils.generateToken(userDetails);
            TokenDTO jwtDTO = new TokenDTO(jwt);
            return new ResponseEntity<TokenDTO>(jwtDTO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<TokenDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<KorisnikDTO> register(@RequestBody KorisnikDTO korisnik) {
        Korisnik noviKorisnik = new Korisnik(null, korisnik.getKorisnickoIme(), passwordEncoder.encode(korisnik.getLozinka()), korisnik.getEmail(), korisnik.getDatumRodjenja());
        Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findPravoPristupaByNaziv("ROLE_KORISNIK");
        if(pravoPristupa.isPresent()) {
            noviKorisnik.setPravoPristupa(pravoPristupa.get());
        } else {
            PravoPristupa novoPravoPristupa = pravoPristupaService.save(new PravoPristupa(null, "ROLE_KORISNIK"));
            noviKorisnik.setPravoPristupa(novoPravoPristupa);
        }
        korisnikService.save(noviKorisnik);
        return new ResponseEntity<KorisnikDTO>(KorisnikDTO.toDTO(noviKorisnik, true), HttpStatus.OK);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ResponseEntity<KorisnikDTO> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
//        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<KorisnikDTO>(HttpStatus.OK);
    }
}
