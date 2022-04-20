package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.NastavnikNaRealizaciji;
import rs.ac.singidunum.isa.app.repository.NastavnikNaRealizacijiRepository;

import java.util.Optional;

@Service
public class NastavnikNaRealizacijiService {
    @Autowired
    private NastavnikNaRealizacijiRepository nastavnikNaRealizacijiRepository;

    public NastavnikNaRealizacijiService() {
    }

    public NastavnikNaRealizacijiService(NastavnikNaRealizacijiRepository nastavnikNaRealizacijiRepository) {
        this.nastavnikNaRealizacijiRepository = nastavnikNaRealizacijiRepository;
    }

    public NastavnikNaRealizacijiRepository getNastavnikNaRealizacijiRepository() {
        return nastavnikNaRealizacijiRepository;
    }

    public void setNastavnikNaRealizacijiRepository(NastavnikNaRealizacijiRepository nastavnikNaRealizacijiRepository) {
        this.nastavnikNaRealizacijiRepository = nastavnikNaRealizacijiRepository;
    }

    public Iterable<NastavnikNaRealizaciji> findAll() {
        return this.nastavnikNaRealizacijiRepository.findAll();
    }

    public Optional<NastavnikNaRealizaciji> findOne(Long id) {
        return this.nastavnikNaRealizacijiRepository.findById(id);
    }

    public NastavnikNaRealizaciji save(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        return this.nastavnikNaRealizacijiRepository.save(nastavnikNaRealizaciji);
    }

    public void delete(Long id) {
        this.nastavnikNaRealizacijiRepository.deleteById(id);
    }

    public void delete(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        this.nastavnikNaRealizacijiRepository.delete(nastavnikNaRealizaciji);
    }
}
