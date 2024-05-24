package tavin.azship.gestaofretes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;

import java.util.List;

@Repository
public interface AddressDeliveryRepository extends JpaRepository<AddressDelivery, Long> {

    List<AddressDelivery> findByIdIn(List<Long> ids);

    @Query("FROM AddressDelivery ad WHERE ad.active = false")
    List<AddressDelivery> findByInactive();
}
