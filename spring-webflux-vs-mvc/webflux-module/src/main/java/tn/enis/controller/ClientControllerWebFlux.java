package tn.enis.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.enis.entity.Client;
import tn.enis.service.ClientServiceWebFlux;

@RestController
@RequestMapping("/api/clients")
public class ClientControllerWebFlux {

	private final ClientServiceWebFlux clientService;

	public ClientControllerWebFlux(ClientServiceWebFlux clientService) {
		this.clientService = clientService;
	}

	// GET all - Flux (stream réactif)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Client> getAllClients() {
		return clientService.getAllClients();
	}

	// GET all avec streaming Server-Sent Events
	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Client> streamClients() {
		return clientService.getAllClients().delayElements(Duration.ofSeconds(1));
	}

	// GET by ID
	@GetMapping("/{id}")
	public Mono<Client> getClientById(@PathVariable Integer id) {
		return clientService.getClientById(id);
	}

	// POST - Création
	@PostMapping
	public Mono<Client> createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}

	// PUT - Mise à jour
	@PutMapping("/{id}")
	public Mono<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
		return clientService.updateClient(id, client);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public Mono<Void> deleteClient(@PathVariable Integer id) {
		return clientService.deleteClient(id);
	}

	// Recherche par nom
	@GetMapping("/search")
	public Flux<Client> searchClients(@RequestParam String nom) {
		return clientService.searchByNom(nom);
	}

}