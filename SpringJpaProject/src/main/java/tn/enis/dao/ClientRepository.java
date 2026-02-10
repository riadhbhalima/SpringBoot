package tn.enis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enis.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	List<Client> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String keyword, String keyword2);


}
