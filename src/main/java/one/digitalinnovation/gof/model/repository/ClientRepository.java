package one.digitalinnovation.gof.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovation.gof.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
