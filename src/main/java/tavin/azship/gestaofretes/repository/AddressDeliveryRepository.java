package tavin.azship.gestaofretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tavin.azship.gestaofretes.model.AddressDelivery;

@Repository
public interface AddressDeliveryRepository extends JpaRepository<AddressDelivery, Long> {
}
