package tavin.azship.gestaofretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.model.Freight;

import java.util.Optional;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {

    Optional<Freight> findByCodigo(String codigo);
}
