package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Obavestenje;
import rs.ac.singidunum.isa.app.repository.ObavestenjeRepository;

import java.util.Optional;

@Service
public class ObavestenjeService {
    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    public ObavestenjeService() {
    }

    public ObavestenjeService(ObavestenjeRepository obavestenjeRepository) {
        this.obavestenjeRepository = obavestenjeRepository;
    }

    public ObavestenjeRepository getObavestenjeRepository() {
        return obavestenjeRepository;
    }

    public void setObavestenjeRepository(ObavestenjeRepository obavestenjeRepository) {
        this.obavestenjeRepository = obavestenjeRepository;
    }

    public Iterable<Obavestenje> findAll() {
        return this.obavestenjeRepository.findAll();
    }

    public Optional<Obavestenje> findOne(Long id) {
        return this.obavestenjeRepository.findById(id);
    }

    public Obavestenje save(Obavestenje obavestenje) {
        return this.obavestenjeRepository.save(obavestenje);
    }

    public void delete(Long id) {
        this.obavestenjeRepository.deleteById(id);
    }

    public void delete(Obavestenje obavestenje) {
        this.obavestenjeRepository.delete(obavestenje);
    }

    public Iterable<Obavestenje> getStudentObavestenja(Long id) {
        return this.obavestenjeRepository.getStudentObavestenja(id);
    }
}
