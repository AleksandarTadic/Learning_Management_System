package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Nastavnik;
import rs.ac.singidunum.isa.app.repository.NastavnikRepository;

import java.util.Optional;

@Service
public class NastavnikService {
    @Autowired
    private NastavnikRepository nastavnikRepository;

    public NastavnikService() {
    }

    public NastavnikService(NastavnikRepository nastavnikRepository) {
        this.nastavnikRepository = nastavnikRepository;
    }

    public NastavnikRepository getNastavnikRepository() {
        return nastavnikRepository;
    }

    public void setNastavnikRepository(NastavnikRepository nastavnikRepository) {
        this.nastavnikRepository = nastavnikRepository;
    }

    public Iterable<Nastavnik> findAll() {
        return this.nastavnikRepository.findAll();
    }

    public Optional<Nastavnik> findOne(Long id) {
        return this.nastavnikRepository.findById(id);
    }

    public Nastavnik save(Nastavnik nastavnik) {
        return this.nastavnikRepository.save(nastavnik);
    }

    public void delete(Long id) {
        this.nastavnikRepository.deleteById(id);
    }

    public void delete(Nastavnik nastavnik) {
        this.nastavnikRepository.delete(nastavnik);
    }
}
