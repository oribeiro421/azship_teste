package tavin.azship.gestaofretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.model.AddressCollect;
import tavin.azship.gestaofretes.model.AddressDelivery;

import java.util.List;

@Repository
public interface AddressCollectRepository extends JpaRepository<AddressCollect, Long> {

    List<AddressCollect> findByIdIn(List<Long> ids);

    @Query("FROM AddressCollect ac WHERE ac.active = false")
    List<AddressCollect> findByInactive();
}
