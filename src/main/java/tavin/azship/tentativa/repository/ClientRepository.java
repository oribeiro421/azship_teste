package tavin.azship.tentativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tavin.azship.tentativa.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
