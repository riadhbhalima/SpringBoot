package tn.enis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.enis.entity.Client;
import tn.enis.repository.ClientRepository;

@Service
@Transactional
public class ClientServiceMvc {

	private final ClientRepository clientRepository;

	public ClientServiceMvc(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Optional<Client> getClientById(Integer id) {
		return clientRepository.findById(id);
	}

	public Client createClient(Client client) {
		return clientRepository.save(client);
	}

	public Client updateClient(Integer id, Client clientDetails) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client non trouvé"));

		client.setNom(clientDetails.getNom());
		client.setPrenom(clientDetails.getPrenom());

		return clientRepository.save(client);
	}

	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}

	public List<Client> searchByNom(String nom) {
		return clientRepository.findByNomContainingIgnoreCase(nom);
	}
}