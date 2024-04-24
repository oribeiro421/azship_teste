package tavin.azship.gestaofretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.model.Freight;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long>, JpaSpecificationExecutor<Freight> {

    @Query("SELECT f " +
            "FROM Freight f " +
            "JOIN f.properties p " +
            "WHERE KEY(p)= :property_key")
    Optional<Freight> findByProperties(@Param("property_key") String key);


}
