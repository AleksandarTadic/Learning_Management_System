package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Univerzitet;
import rs.ac.singidunum.isa.app.repository.UniverzitetRepository;

import java.util.Optional;

@Service
public class UniverzitetService {
    @Autowired
    private UniverzitetRepository univerzitetRepository;

    public UniverzitetService() {
    }

    public UniverzitetService(UniverzitetRepository univerzitetRepository) {
        this.univerzitetRepository = univerzitetRepository;
    }

    public UniverzitetRepository getUniverzitetRepository() {
        return univerzitetRepository;
    }

    public void setUniverzitetRepository(UniverzitetRepository univerzitetRepository) {
        this.univerzitetRepository = univerzitetRepository;
    }

    public Iterable<Univerzitet> findAll() {
        return this.univerzitetRepository.findAll();
    }

    public Optional<Univerzitet> findOne(Long id) {
        return this.univerzitetRepository.findById(id);
    }

    public Univerzitet save(Univerzitet univerzitet) {
        return this.univerzitetRepository.save(univerzitet);
    }

    public void delete(Long id) {
        this.univerzitetRepository.deleteById(id);
    }

    public void delete(Univerzitet univerzitet) {
        this.univerzitetRepository.delete(univerzitet);
    }
}
