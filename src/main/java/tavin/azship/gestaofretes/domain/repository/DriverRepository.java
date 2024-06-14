package tavin.azship.gestaofretes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.domain.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
