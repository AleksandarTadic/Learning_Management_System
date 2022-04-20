package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Fakultet;
import rs.ac.singidunum.isa.app.repository.FakultetRepository;

import java.util.Optional;

@Service
public class FakultetService {
    @Autowired
    private FakultetRepository fakultetRepository;

    public FakultetService() {
    }

    public FakultetService(FakultetRepository fakultetRepository) {
        this.fakultetRepository = fakultetRepository;
    }

    public FakultetRepository getFakultetRepository() {
        return fakultetRepository;
    }

    public void setFakultetRepository(FakultetRepository fakultetRepository) {
        this.fakultetRepository = fakultetRepository;
    }

    public Iterable<Fakultet> findAll() {
        return this.fakultetRepository.findAll();
    }

    public Optional<Fakultet> findOne(Long id) {
        return this.fakultetRepository.findById(id);
    }

    public Fakultet save(Fakultet fakultet) {
        return this.fakultetRepository.save(fakultet);
    }

    public void delete(Long id) {
        this.fakultetRepository.deleteById(id);
    }

    public void delete(Fakultet fakultet) {
        this.fakultetRepository.delete(fakultet);
    }
}
