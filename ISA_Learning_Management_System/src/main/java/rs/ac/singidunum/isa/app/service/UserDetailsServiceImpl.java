package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Korisnik;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	KorisnikService korisnikService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Dobavljanje korisnika po korisnickom imenu.
		Optional<Korisnik> korisnik = korisnikService.findByKorisnickoIme(username);

		if(korisnik.isPresent()) {
			// Formiranje liste dodeljenih prava pristupa.
			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//			for(PravoPristupa userPermission : korisnik.get().getPravoPristupa()) {
//				grantedAuthorities.add(new SimpleGrantedAuthority(userPermission.getNaziv()));
//			}
			grantedAuthorities.add(new SimpleGrantedAuthority(korisnik.get().getPravoPristupa().getNaziv()));

			// Kreiranje korisnika na osnovu korisnickog imena, lozinke i dodeljenih prava pristupa.
//			return new org.springframework.security.core.userdetails.User(korisnik.get().getKorisnickoIme(), korisnik.get().getLozinka(), grantedAuthorities);
			return new org.springframework.security.core.userdetails.User(korisnik.get().getKorisnickoIme(), korisnik.get().getLozinka(), grantedAuthorities);
		}

		return null;
	}
}
