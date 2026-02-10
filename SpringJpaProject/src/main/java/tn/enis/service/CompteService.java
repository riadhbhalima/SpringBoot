package tn.enis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.enis.dao.CompteRepository;
import tn.enis.entity.Compte;
import tn.enis.exception.CompteNotFoundException;

@RequiredArgsConstructor
@Service
public class CompteService {
	private final CompteRepository compteRepository;

	public void saveOrUpdate(Compte compte) {
		compteRepository.save(compte);
	}

	public List<Compte> findAll() {
		return compteRepository.findAll();
	}

	public void deleteById(Integer rib) {
		compteRepository.deleteById(rib);
	}

	public Compte findById(Integer rib) {
		return compteRepository.findById(rib)
				.orElseThrow(() -> new CompteNotFoundException("Compte with rib " + rib + " is not found."));
	}

	public List<Compte> search(String nomClient) {
		return compteRepository.findByClientNomContainingIgnoreCase(nomClient);
	}

}
