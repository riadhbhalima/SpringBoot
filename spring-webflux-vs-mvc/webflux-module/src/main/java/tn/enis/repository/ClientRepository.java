package tn.enis.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.enis.entity.Client;

public interface ClientRepository extends R2dbcRepository<Client, Integer> {

	Flux<Client> findByNomContaining(String nom);
	Flux<Client> findByPrenom(String prenom);
	Flux<Client> findByNomAndPrenom(String nom, String prenom);
	Mono<Boolean> existsByNomAndPrenom(String nom, String prenom);
}