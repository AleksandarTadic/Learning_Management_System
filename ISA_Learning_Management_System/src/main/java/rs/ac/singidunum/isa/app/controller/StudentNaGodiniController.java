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
import rs.ac.singidunum.isa.app.dto.StudentNaGodiniDTO;
import rs.ac.singidunum.isa.app.model.StudentNaGodini;
import rs.ac.singidunum.isa.app.service.StudentNaGodiniService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/studentiNaGodini")
public class StudentNaGodiniController {
    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<ArrayList<StudentNaGodiniDTO>> getAllStudentiNaGodini() {
        ArrayList<StudentNaGodiniDTO> studentiNaGodini = StudentNaGodiniDTO.toDTOArrayList(studentNaGodiniService.findAll(), true);

        return new ResponseEntity<ArrayList<StudentNaGodiniDTO>>(studentiNaGodini, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    public ResponseEntity<StudentNaGodiniDTO> getStudentNaGodini(@PathVariable("studentNaGodiniId") Long studentNaGodiniId) {
        Optional<StudentNaGodini> studentNaGodini = studentNaGodiniService.findOne(studentNaGodiniId);
        if(studentNaGodini.isPresent()) {
            StudentNaGodiniDTO studentNaGodiniDTO = StudentNaGodiniDTO.toDTO(studentNaGodini.get(), true);
            return new ResponseEntity<StudentNaGodiniDTO>(studentNaGodiniDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentNaGodiniDTO> createStudentNaGodini(@RequestBody StudentNaGodini studentNaGodini) {
        try {
            studentNaGodiniService.save(studentNaGodini);
            StudentNaGodiniDTO studentNaGodiniDTO = StudentNaGodiniDTO.toDTO(studentNaGodini, true);
            return new ResponseEntity<StudentNaGodiniDTO>(studentNaGodiniDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentNaGodiniDTO> updateStudentNaGodini(@PathVariable("studentNaGodiniId") Long studentNaGodiniId, @RequestBody StudentNaGodini izmenjenStudentNaGodini) {
        StudentNaGodini studentNaGodini = studentNaGodiniService.findOne(studentNaGodiniId).orElse(null);
        if(studentNaGodini != null) {
            izmenjenStudentNaGodini.setId(studentNaGodiniId);
            izmenjenStudentNaGodini = studentNaGodiniService.save(izmenjenStudentNaGodini);
            StudentNaGodiniDTO studentNaGodiniDTO = StudentNaGodiniDTO.toDTO(izmenjenStudentNaGodini, true);
            return new ResponseEntity<StudentNaGodiniDTO>(studentNaGodiniDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studentNaGodiniId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentNaGodiniDTO> deleteStudentNaGodini(@PathVariable("studentNaGodiniId") Long studentNaGodiniId) {
        if(studentNaGodiniService.findOne(studentNaGodiniId).isPresent()) {
            studentNaGodiniService.delete(studentNaGodiniId);
            return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<StudentNaGodiniDTO>(HttpStatus.NOT_FOUND);
    }
}
