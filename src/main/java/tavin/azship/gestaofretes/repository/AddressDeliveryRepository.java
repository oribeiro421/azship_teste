package tavin.azship.gestaofretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.model.AddressDelivery;

import java.util.List;

@Repository
public interface AddressDeliveryRepository extends JpaRepository<AddressDelivery, Long> {

    List<AddressDelivery> findByIdIn(List<Long> ids);


}
