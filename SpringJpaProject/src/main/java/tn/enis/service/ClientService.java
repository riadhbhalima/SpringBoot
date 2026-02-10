package tn.enis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.enis.dao.ClientRepository;
import tn.enis.entity.Client;
import tn.enis.exception.CompteNotFoundException;

@RequiredArgsConstructor
@Service
public class ClientService {
	private final ClientRepository clientRepository;

	public void saveOrUpdate(Client client) {
		clientRepository.save(client);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public void deleteById(Integer id) {
		clientRepository.deleteById(id);
	}

	public Client findById(Integer id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new CompteNotFoundException("Client with id " + id + " is not found."));
	}

	public List<Client> search(String keyword) {
		return clientRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(keyword, keyword);
	}

}
