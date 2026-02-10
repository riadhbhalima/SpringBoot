package tn.enis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tn.enis.dto.StudentDto;
import tn.enis.service.StudentService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping("/all")
	public String findAll(Model model) {
		model.addAttribute("students", studentService.findAll());
		return "students";
	}

	@ResponseBody // json
	@GetMapping("/all-json")
	public List<StudentDto> findAllJson() {
		return studentService.findAll();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		studentService.deleteById(id);
		return "redirect:/students/all";
	}

	@ResponseBody
	@PostMapping("/delete-ajax")
	public void deleteAjax(@RequestParam Integer id) {
		studentService.deleteById(id);
	}

	@PostMapping("/save")
	public String save(@ModelAttribute StudentDto studentDto, BindingResult result, Model model) {
		studentService.save(studentDto);
		return "redirect:/students/all";

	}

	/*
	 * @PostMapping("/save") public String save(@RequestParam String
	 * name, @RequestParam String gender) { StudentDto studentDto = new StudentDto(0,
	 * name, gender); studentService.save(studentDto); return
	 * "redirect:/students/all";
	 * 
	 * }
	 */

	@PostMapping("/search")
	public String search(@RequestParam String search, Model model) {
		if (search == null || "".equals(search.trim())) {
			return "redirect:/students/all";
		} else {
			model.addAttribute("students", studentService.search(search));
			return "students";
		}
	}

	@PostMapping("/edit")
	public String edit(@RequestParam Integer id, Model model) {
		StudentDto studentDto = studentService.findById(id);
		model.addAttribute("student", studentDto);
		return "edit-student";

	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("student") StudentDto student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("Error= " + result.getFieldError().getDefaultMessage());
			return "edit-student";
		}
		studentService.update(student);
		return "redirect:/students/all";

	}
}
