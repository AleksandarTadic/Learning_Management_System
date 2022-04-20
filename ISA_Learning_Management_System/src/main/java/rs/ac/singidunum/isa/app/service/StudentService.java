package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.Student;
import rs.ac.singidunum.isa.app.repository.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService() {
    }

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> findAll() {
        return this.studentRepository.findAll();
    }

    public Optional<Student> findOne(Long id) {
        return this.studentRepository.findById(id);
    }

    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }

    public void delete(Student student) {
        this.studentRepository.delete(student);
    }
}
