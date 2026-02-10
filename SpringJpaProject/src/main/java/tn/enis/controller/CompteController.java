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
import tn.enis.entity.Compte;
import tn.enis.service.ClientService;
import tn.enis.service.CompteService;

@Controller
@RequestMapping("/comptes")
@RequiredArgsConstructor
public class CompteController {

	private final CompteService compteService;
	private final ClientService clientService;

	@GetMapping("/all")
	public String findAll(Model model) {
		model.addAttribute("comptes", compteService.findAll());
		return "comptes";
	}

	@ResponseBody
	@GetMapping("/all-json")
	public List<Compte> findAllJson() {
		return compteService.findAll();
	}

	@GetMapping("/delete/{rib}")
	public String delete(@PathVariable Integer rib) {
		compteService.deleteById(rib);
		return "redirect:/comptes/all";
	}

	@ResponseBody
	@PostMapping("/delete-ajax")
	public void deleteAjax(@RequestParam Integer rib) {
		compteService.deleteById(rib);
	}

	@PostMapping("/save")
	public String save(@RequestParam Integer idClient, @RequestParam float solde) {
		
		Compte compte = new Compte(solde, clientService.findById(idClient));
		compteService.saveOrUpdate(compte);
		return "redirect:/comptes/all";

	}

	@PostMapping("/search")
	public String search(@RequestParam String search, Model model) {
		if (search == null || "".equals(search.trim())) {
			return "redirect:/comptes/all";
		} else {
			model.addAttribute("comptes", compteService.search(search));
			return "comptes";
		}

	}

	@PostMapping("/edit")
	public String edit(@RequestParam Integer rib, Model model) {
		Compte compte = compteService.findById(rib);
		model.addAttribute("compte", compte);
		return "edit-compte";

	}

	@PostMapping("/update")
	public String update(@ModelAttribute Compte compte) {
		compteService.saveOrUpdate(compte);
		return "redirect:/comptes/all";

	}
}
