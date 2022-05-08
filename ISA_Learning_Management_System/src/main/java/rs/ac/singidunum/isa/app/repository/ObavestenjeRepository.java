package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Obavestenje;

@Repository
public interface ObavestenjeRepository extends CrudRepository<Obavestenje, Long> {
    @Query(value = "SELECT o FROM Obavestenje o, RealizacijaPredmeta rp, PohadjanjePredmeta pp, Student s WHERE s.korisnik.id =:id AND pp.student.id = s.id AND pp.realizacijaPredmeta.id = rp.id AND rp.id = o.realizacijaPredmeta.id")
    Iterable<Obavestenje> getStudentObavestenja(Long id);
}
