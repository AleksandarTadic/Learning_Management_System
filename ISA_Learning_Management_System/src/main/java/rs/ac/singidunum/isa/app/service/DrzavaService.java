package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Drzava;
import rs.ac.singidunum.isa.app.repository.DrzavaRepository;

import java.util.Optional;

@Service
public class DrzavaService {
    @Autowired
    private DrzavaRepository drzavaRepository;

    public DrzavaService() {
    }

    public DrzavaService(DrzavaRepository drzavaRepository) {
        this.drzavaRepository = drzavaRepository;
    }

    public DrzavaRepository getDrzavaRepository() {
        return drzavaRepository;
    }

    public void setDrzavaRepository(DrzavaRepository drzavaRepository) {
        this.drzavaRepository = drzavaRepository;
    }

    public Iterable<Drzava> findAll() {
        return this.drzavaRepository.findAll();
    }

    public Optional<Drzava> findOne(Long id) {
        return this.drzavaRepository.findById(id);
    }

    public Drzava save(Drzava drzava) {
        return this.drzavaRepository.save(drzava);
    }

    public void delete(Long id) {
        this.drzavaRepository.deleteById(id);
    }

    public void delete(Drzava drzava) {
        this.drzavaRepository.delete(drzava);
    }
}
