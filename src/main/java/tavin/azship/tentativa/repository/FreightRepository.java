package tavin.azship.tentativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tavin.azship.tentativa.model.Freight;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {
}
