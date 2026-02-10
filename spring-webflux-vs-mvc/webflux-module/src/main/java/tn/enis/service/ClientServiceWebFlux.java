package tn.enis.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.enis.entity.Client;
import tn.enis.repository.ClientRepository;

	@Service
	public class ClientServiceWebFlux {
	    
	    private final ClientRepository clientRepository;
	    
	    public ClientServiceWebFlux(ClientRepository clientRepository) {
	        this.clientRepository = clientRepository;
	    }
	    
	    // Récupérer tous les clients
	    public Flux<Client> getAllClients() {
	        return clientRepository.findAll();
	    }
	    
	    // Récupérer un client par ID
	    public Mono<Client> getClientById(Integer id) {
	        return clientRepository.findById(id)
	                .switchIfEmpty(Mono.error(new RuntimeException("Client non trouvé avec l'ID: " + id)));
	    }
	    
	    // Créer un nouveau client
	    public Mono<Client> createClient(Client client) {
	        // Vérifier si le client existe déjà
	        return clientRepository.existsByNomAndPrenom(client.getNom(), client.getPrenom())
	                .flatMap(exists -> {
	                    if (exists) {
	                        return Mono.error(new RuntimeException("Client existe déjà"));
	                    }
	                    return clientRepository.save(client);
	                });
	    }
	    
	    // Alternative création simplifiée
	    public Mono<Client> createClient(String nom, String prenom) {
	        Client client = new Client(nom, prenom);
	        return clientRepository.save(client);
	    }
	    
	    // Mettre à jour un client
	    public Mono<Client> updateClient(Integer id, Client client) {
	        return clientRepository.findById(id)
	                .flatMap(existingClient -> {
	                    existingClient.setNom(client.getNom());
	                    existingClient.setPrenom(client.getPrenom());
	                    return clientRepository.save(existingClient);
	                })
	                .switchIfEmpty(Mono.error(new RuntimeException("Client non trouvé avec l'ID: " + id)));
	    }
	    
	    // Supprimer un client
	    public Mono<Void> deleteClient(Integer id) {
	        return clientRepository.findById(id)
	                .flatMap(client -> clientRepository.deleteById(id))
	                .switchIfEmpty(Mono.error(new RuntimeException("Client non trouvé avec l'ID: " + id)));
	    }
	    
	    // Recherche par nom 
	    public Flux<Client> searchByNom(String nom) {
	        return clientRepository.findByNomContaining( nom);
	    }
	    
	    // Recherche par prénom (avec like)
	 
}