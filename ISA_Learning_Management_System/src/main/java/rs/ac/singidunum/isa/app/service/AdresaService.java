package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Adresa;
import rs.ac.singidunum.isa.app.repository.AdresaRepository;

import java.util.Optional;

@Service
public class AdresaService {
    @Autowired
    private AdresaRepository adresaRepository;

    public AdresaService() {
    }

    public AdresaService(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    public AdresaRepository getAdresaRepository() {
        return adresaRepository;
    }

    public void setAdresaRepository(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    public Iterable<Adresa> findAll() {
        return this.adresaRepository.findAll();
    }

    public Optional<Adresa> findOne(Long id) {
        return this.adresaRepository.findById(id);
    }

    public Adresa save(Adresa adresa) {
        return this.adresaRepository.save(adresa);
    }

    public void delete(Long id) {
        this.adresaRepository.deleteById(id);
    }

    public void delete(Adresa adresa) {
        this.adresaRepository.delete(adresa);
    }
}
