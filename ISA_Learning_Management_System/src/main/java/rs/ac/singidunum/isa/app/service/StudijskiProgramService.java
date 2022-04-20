package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.StudijskiProgram;
import rs.ac.singidunum.isa.app.repository.StudijskiProgramRepository;

import java.util.Optional;

@Service
public class StudijskiProgramService {
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;

    public StudijskiProgramService() {
    }

    public StudijskiProgramService(StudijskiProgramRepository studijskiProgramRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    public StudijskiProgramRepository getStudijskiProgramRepository() {
        return studijskiProgramRepository;
    }

    public void setStudijskiProgramRepository(StudijskiProgramRepository studijskiProgramRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    public Iterable<StudijskiProgram> findAll() {
        return this.studijskiProgramRepository.findAll();
    }

    public Optional<StudijskiProgram> findOne(Long id) {
        return this.studijskiProgramRepository.findById(id);
    }

    public StudijskiProgram save(StudijskiProgram studijskiProgram) {
        return this.studijskiProgramRepository.save(studijskiProgram);
    }

    public void delete(Long id) {
        this.studijskiProgramRepository.deleteById(id);
    }

    public void delete(StudijskiProgram studijskiProgram) {
        this.studijskiProgramRepository.delete(studijskiProgram);
    }
}
