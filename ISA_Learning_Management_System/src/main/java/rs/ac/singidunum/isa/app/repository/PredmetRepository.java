package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Predmet;

@Repository
public interface PredmetRepository extends CrudRepository<Predmet, Long> {
    @Query(value = "SELECT p FROM Predmet p, Student s, PohadjanjePredmeta pp, RealizacijaPredmeta rp WHERE s.korisnik.id =:id AND pp.student.id = s.id AND pp.realizacijaPredmeta.id = rp.id AND rp.predmet.id = p.id AND pp.konacnaOcena IS NULL")
    Iterable<Predmet> getStudentPredmetiNepolozeni(Long id);

    @Query(value = "SELECT p FROM Predmet p, Student s, PohadjanjePredmeta pp, RealizacijaPredmeta rp WHERE s.korisnik.id =:id AND pp.student.id = s.id AND pp.realizacijaPredmeta.id = rp.id AND rp.predmet.id = p.id AND pp.konacnaOcena IS NOT NULL")
    Iterable<Predmet> getStudentPredmetiPolozeni(Long id);
}
