package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Fajl;
import rs.ac.singidunum.isa.app.repository.FajlRepository;

import java.util.Optional;

@Service
public class FajlService {
    @Autowired
    private FajlRepository fajlRepository;

    public FajlService() {
    }

    public FajlService(FajlRepository fajlRepository) {
        this.fajlRepository = fajlRepository;
    }

    public FajlRepository getFajlRepository() {
        return fajlRepository;
    }

    public void setFajlRepository(FajlRepository fajlRepository) {
        this.fajlRepository = fajlRepository;
    }

    public Iterable<Fajl> findAll() {
        return this.fajlRepository.findAll();
    }

    public Optional<Fajl> findOne(Long id) {
        return this.fajlRepository.findById(id);
    }

    public Fajl save(Fajl fajl) {
        return this.fajlRepository.save(fajl);
    }

    public void delete(Long id) {
        this.fajlRepository.deleteById(id);
    }

    public void delete(Fajl fajl) {
        this.fajlRepository.delete(fajl);
    }
}
