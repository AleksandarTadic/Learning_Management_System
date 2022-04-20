package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.StudentNaGodini;
import rs.ac.singidunum.isa.app.repository.StudentNaGodiniRepository;

import java.util.Optional;

@Service
public class StudentNaGodiniService {
    @Autowired
    private StudentNaGodiniRepository studentNaGodiniRepository;

    public StudentNaGodiniService() {
    }

    public StudentNaGodiniService(StudentNaGodiniRepository studentNaGodiniRepository) {
        this.studentNaGodiniRepository = studentNaGodiniRepository;
    }

    public StudentNaGodiniRepository getStudentNaGodiniRepository() {
        return studentNaGodiniRepository;
    }

    public void setStudentNaGodiniRepository(StudentNaGodiniRepository studentNaGodiniRepository) {
        this.studentNaGodiniRepository = studentNaGodiniRepository;
    }

    public Iterable<StudentNaGodini> findAll() {
        return this.studentNaGodiniRepository.findAll();
    }

    public Optional<StudentNaGodini> findOne(Long id) {
        return this.studentNaGodiniRepository.findById(id);
    }

    public StudentNaGodini save(StudentNaGodini studentNaGodini) {
        return this.studentNaGodiniRepository.save(studentNaGodini);
    }

    public void delete(Long id) {
        this.studentNaGodiniRepository.deleteById(id);
    }

    public void delete(StudentNaGodini studentNaGodini) {
        this.studentNaGodiniRepository.delete(studentNaGodini);
    }
}
