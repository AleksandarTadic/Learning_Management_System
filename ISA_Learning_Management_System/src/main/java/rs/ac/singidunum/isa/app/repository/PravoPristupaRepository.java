package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.PravoPristupa;

import java.util.Optional;

@Repository
public interface PravoPristupaRepository extends CrudRepository<PravoPristupa, Long> {
    @Query(value = "SELECT p FROM PravoPristupa p WHERE p.naziv = :naziv")
    Optional<PravoPristupa> findPravoPristupaByNaziv(String naziv);
}
