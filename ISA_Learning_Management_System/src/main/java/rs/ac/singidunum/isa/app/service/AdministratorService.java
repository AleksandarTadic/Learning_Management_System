package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Administrator;
import rs.ac.singidunum.isa.app.repository.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorService() {
    }

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public AdministratorRepository getAdministratorRepository() {
        return administratorRepository;
    }

    public void setAdministratorRepository(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Iterable<Administrator> findAll() {
        return this.administratorRepository.findAll();
    }

    public Optional<Administrator> findOne(Long id) {
        return this.administratorRepository.findById(id);
    }

    public Administrator save(Administrator administrator) {
        return this.administratorRepository.save(administrator);
    }

    public void delete(Long id) {
        this.administratorRepository.deleteById(id);
    }

    public void delete(Administrator administrator) {
        this.administratorRepository.delete(administrator);
    }
}
