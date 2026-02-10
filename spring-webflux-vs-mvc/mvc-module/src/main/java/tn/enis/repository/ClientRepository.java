package tn.enis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enis.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	List<Client> findByNomContainingIgnoreCase(String nom);
}