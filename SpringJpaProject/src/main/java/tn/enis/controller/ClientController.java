package tn.enis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import tn.enis.entity.Client;
import tn.enis.service.ClientService;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@GetMapping("/all")
	public String findAll(Model model) {
		model.addAttribute("clients", clientService.findAll());
		return "clients"; // clients.html
	}

	@ResponseBody
	@GetMapping("/all-json")
	public List<Client> findAllJson() {
		return clientService.findAll();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		clientService.deleteById(id);
		return "redirect:/clients/all";
	}

	@ResponseBody
	@PostMapping("/delete-ajax")
	public void deleteAjax(@RequestParam Integer id) {
		clientService.deleteById(id);
	}

	// Version alternative avec DELETE HTTP
	@ResponseBody
	@PostMapping("/delete-ajax/{id}")
	public void deleteAjaxPath(@PathVariable Integer id) {
		clientService.deleteById(id);
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Client client) {
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

	// Alternative si vous passez les paramètres séparément
	@PostMapping("/save-params")
	public String saveParams(@RequestParam String nom, @RequestParam String prenom) {
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

	@PostMapping("/search")
	public String search(@RequestParam String search, Model model) {
		if (search == null || search.trim().isEmpty()) {
			return "redirect:/clients/all";
		} else {
			model.addAttribute("clients", clientService.search(search));
			return "clients";
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Client client = clientService.findById(id);
		model.addAttribute("client", client);
		return "client-form";
	}

	// Version POST pour l'édition (comme dans CompteController)
	@PostMapping("/edit")
	public String editPost(@RequestParam Integer id, Model model) {
		Client client = clientService.findById(id);
		model.addAttribute("client", client);
		return "client-form";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Client client) {
		clientService.saveOrUpdate(client);
		return "redirect:/clients/all";
	}

}
