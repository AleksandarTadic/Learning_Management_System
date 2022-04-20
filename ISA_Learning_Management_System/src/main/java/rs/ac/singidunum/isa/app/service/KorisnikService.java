package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Korisnik;
import rs.ac.singidunum.isa.app.repository.KorisnikRepository;

import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    public KorisnikService() {
    }

    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public KorisnikRepository getKorisnikRepository() {
        return korisnikRepository;
    }

    public void setKorisnikRepository(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public Iterable<Korisnik> findAll() {
        return this.korisnikRepository.findAll();
    }

    public Optional<Korisnik> findOne(Long id) {
        return this.korisnikRepository.findById(id);
    }

    public Korisnik save(Korisnik korisnik) {
        return this.korisnikRepository.save(korisnik);
    }

    public void delete(Long id) {
        this.korisnikRepository.deleteById(id);
    }

    public void delete(Korisnik korisnik) {
        this.korisnikRepository.delete(korisnik);
    }

    public Optional<Korisnik> findByKorisnickoIme(String korisnickoIme) {
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }
}
