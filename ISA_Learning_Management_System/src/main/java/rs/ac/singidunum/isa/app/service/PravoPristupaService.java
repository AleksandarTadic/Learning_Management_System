package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.PravoPristupa;
import rs.ac.singidunum.isa.app.repository.PravoPristupaRepository;

import java.util.Optional;

@Service
public class PravoPristupaService {
    @Autowired
    private PravoPristupaRepository pravoPristupaRepository;

    public PravoPristupaService() {
    }

    public PravoPristupaService(PravoPristupaRepository pravoPristupaRepository) {
        this.pravoPristupaRepository = pravoPristupaRepository;
    }

    public PravoPristupaRepository getPravoPristupaRepository() {
        return pravoPristupaRepository;
    }

    public void setPravoPristupaRepository(PravoPristupaRepository pravoPristupaRepository) {
        this.pravoPristupaRepository = pravoPristupaRepository;
    }

    public Iterable<PravoPristupa> findAll() {
        return this.pravoPristupaRepository.findAll();
    }

    public Optional<PravoPristupa> findOne(Long id) {
        return this.pravoPristupaRepository.findById(id);
    }

    public PravoPristupa save(PravoPristupa pravoPristupa) {
        return this.pravoPristupaRepository.save(pravoPristupa);
    }

    public void delete(Long id) {
        this.pravoPristupaRepository.deleteById(id);
    }

    public void delete(PravoPristupa pravoPristupa) {
        this.pravoPristupaRepository.delete(pravoPristupa);
    }

    public Optional<PravoPristupa> findPravoPristupaByNaziv(String naziv) {
        return this.pravoPristupaRepository.findPravoPristupaByNaziv(naziv);
    }
}
