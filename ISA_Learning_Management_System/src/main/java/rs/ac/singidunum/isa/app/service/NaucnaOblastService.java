package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.NaucnaOblast;
import rs.ac.singidunum.isa.app.repository.NaucnaOblastRepository;

import java.util.Optional;

@Service
public class NaucnaOblastService {
    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;

    public NaucnaOblastService() {
    }

    public NaucnaOblastService(NaucnaOblastRepository naucnaOblastRepository) {
        this.naucnaOblastRepository = naucnaOblastRepository;
    }

    public NaucnaOblastRepository getNaucnaOblastRepository() {
        return naucnaOblastRepository;
    }

    public void setNaucnaOblastRepository(NaucnaOblastRepository naucnaOblastRepository) {
        this.naucnaOblastRepository = naucnaOblastRepository;
    }

    public Iterable<NaucnaOblast> findAll() {
        return this.naucnaOblastRepository.findAll();
    }

    public Optional<NaucnaOblast> findOne(Long id) {
        return this.naucnaOblastRepository.findById(id);
    }

    public NaucnaOblast save(NaucnaOblast naucnaOblast) {
        return this.naucnaOblastRepository.save(naucnaOblast);
    }

    public void delete(Long id) {
        this.naucnaOblastRepository.deleteById(id);
    }

    public void delete(NaucnaOblast naucnaOblast) {
        this.naucnaOblastRepository.delete(naucnaOblast);
    }
}
