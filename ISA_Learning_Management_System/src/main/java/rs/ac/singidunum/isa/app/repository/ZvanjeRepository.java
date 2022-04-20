package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Zvanje;

import java.util.Optional;

@Repository
public interface ZvanjeRepository extends CrudRepository<Zvanje, Long> {
    @Query(value = "SELECT z FROM Zvanje z WHERE z.nastavnik.id =:nastavnikId AND z.naucnaOblast.id =:naucnaOblastId")
    public Optional<Zvanje> findZvanjeByNastavnikAndNaucnaOblast(Long nastavnikId, Long naucnaOblastId);
}
