package tn.enis.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enis.entity.Client;
import tn.enis.service.ClientServiceMvc;

@RestController
@RequestMapping("/api/clients")
public class ClientControllerMvc {

	private final ClientServiceMvc clientService;

	public ClientControllerMvc(ClientServiceMvc clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
		return clientService.getClientById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Client createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
		return ResponseEntity.ok(clientService.updateClient(id, client));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
		clientService.deleteClient(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public List<Client> searchClients(@RequestParam String nom) {
		return clientService.searchByNom(nom);
	}

}