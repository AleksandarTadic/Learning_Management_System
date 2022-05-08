package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.StudentDTO;
import rs.ac.singidunum.isa.app.model.Administrator;
import rs.ac.singidunum.isa.app.model.Korisnik;
import rs.ac.singidunum.isa.app.model.PravoPristupa;
import rs.ac.singidunum.isa.app.model.Student;
import rs.ac.singidunum.isa.app.service.KorisnikService;
import rs.ac.singidunum.isa.app.service.PravoPristupaService;
import rs.ac.singidunum.isa.app.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/studenti")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<ArrayList<StudentDTO>> getAllStudenti() {
        ArrayList<StudentDTO> studenti = StudentDTO.toDTOArrayList(studentService.findAll(), true);

        return new ResponseEntity<ArrayList<StudentDTO>>(studenti, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("studentId") Long studentId) {
        Optional<Student> student = studentService.findOne(studentId);
        if(student.isPresent()) {
            StudentDTO studentDTO = StudentDTO.toDTO(student.get(), true);
            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student) {
        Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findPravoPristupaByNaziv("ROLE_STUDENT");
        PravoPristupa pravoPristupaStudent = null;
        if(pravoPristupa.isEmpty()) {
            pravoPristupaStudent = pravoPristupaService.save(new PravoPristupa(null, "ROLE_STUDENT"));
        } else {
            pravoPristupaStudent = pravoPristupa.get();
        }
        student.getKorisnik().setPravoPristupa(pravoPristupaStudent);

        Optional<Korisnik> korisnik = korisnikService.findOne(student.getKorisnik().getId());
        if(korisnik.get().getPravoPristupa().getNaziv().equals("ROLE_KORISNIK")) {
            try {
                studentService.save(student);
                korisnik.get().setPravoPristupa(pravoPristupaStudent);
                korisnikService.save(korisnik.get());
                StudentDTO studentDTO = StudentDTO.toDTO(student, true);
                return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student izmenjenStudent) {
        Student student = studentService.findOne(studentId).orElse(null);
        if((student != null)) {
            if(student.getKorisnik().getPravoPristupa().getNaziv() == "ROLE_STUDENT") {
                izmenjenStudent.setId(studentId);
                izmenjenStudent = studentService.save(izmenjenStudent);
                StudentDTO studentDTO = StudentDTO.toDTO(izmenjenStudent, true);
                return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
            }
            return new ResponseEntity<StudentDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable("studentId") Long studentId) {
        Optional<Student> student = studentService.findOne(studentId);
        if(student.isPresent()) {
//            Dodato
            Optional<PravoPristupa> pravoPristupa = pravoPristupaService.findPravoPristupaByNaziv("ROLE_KORISNIK");
            Optional<Korisnik> korisnik = korisnikService.findOne(student.get().getKorisnik().getId());
            korisnik.get().setPravoPristupa(pravoPristupa.get());
            korisnikService.save(korisnik.get());
//
            studentService.delete(studentId);
            return new ResponseEntity<StudentDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
    }
}
