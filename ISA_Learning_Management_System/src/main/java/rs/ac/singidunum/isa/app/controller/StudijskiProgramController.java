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
import rs.ac.singidunum.isa.app.dto.StudijskiProgramDTO;
import rs.ac.singidunum.isa.app.model.StudijskiProgram;
import rs.ac.singidunum.isa.app.service.StudijskiProgramService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/studijskiProgrami")
public class StudijskiProgramController {
    @Autowired
    private StudijskiProgramService studijskiProgramService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<StudijskiProgramDTO>> getAllStudijskiProgrami() {
        ArrayList<StudijskiProgramDTO> studijskiProgrami = StudijskiProgramDTO.toDTOArrayList(studijskiProgramService.findAll(), true);
        return new ResponseEntity<ArrayList<StudijskiProgramDTO>>(studijskiProgrami, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studijskiProgramId}", method = RequestMethod.GET)
    public ResponseEntity<StudijskiProgramDTO> getStudijskiProgram(@PathVariable("studijskiProgramId") Long studijskiProgramId) {
        Optional<StudijskiProgram> studijskiProgram = studijskiProgramService.findOne(studijskiProgramId);
        if(studijskiProgram.isPresent()) {
            StudijskiProgramDTO studijskiProgramDTO = StudijskiProgramDTO.toDTO(studijskiProgram.get(), true);
            return new ResponseEntity<StudijskiProgramDTO>(studijskiProgramDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudijskiProgramDTO> createStudijskiProgram(@RequestBody StudijskiProgram studijskiProgram) {
        try {
            studijskiProgramService.save(studijskiProgram);
            StudijskiProgramDTO studijskiProgramDTO = StudijskiProgramDTO.toDTO(studijskiProgram, true);
            return new ResponseEntity<StudijskiProgramDTO>(studijskiProgramDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{studijskiProgramId}", method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudijskiProgramDTO> updateStudijskiProgram(@PathVariable("studijskiProgramId") Long studijskiProgramId, @RequestBody StudijskiProgram izmenjenStudijskiProgram) {
        StudijskiProgram studijskiProgram = studijskiProgramService.findOne(studijskiProgramId).orElse(null);
        if(studijskiProgram != null) {
            izmenjenStudijskiProgram.setId(studijskiProgramId);
            izmenjenStudijskiProgram = studijskiProgramService.save(izmenjenStudijskiProgram);
            StudijskiProgramDTO studijskiProgramDTO = StudijskiProgramDTO.toDTO(izmenjenStudijskiProgram, true);
            return new ResponseEntity<StudijskiProgramDTO>(studijskiProgramDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{studijskiProgramId}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudijskiProgramDTO> deleteStudijskiProgram(@PathVariable("studijskiProgramId") Long studijskiProgramId) {
        if(studijskiProgramService.findOne(studijskiProgramId).isPresent()) {
            studijskiProgramService.delete(studijskiProgramId);
            return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }
}
