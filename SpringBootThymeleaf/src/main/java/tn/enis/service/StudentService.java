package tn.enis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tn.enis.dto.StudentDto;
import tn.enis.exception.StudentNotFoudException;

@Service
public class StudentService {
	private List<StudentDto> students = new ArrayList<>();
	private static final AtomicInteger idCounter = new AtomicInteger(2);

	public StudentService() {
		students.add(new StudentDto(1, "xavier", 'M'));
		students.add(new StudentDto(2, "mamadou", 'M'));
	}

	public void save(StudentDto studentDto) {
		studentDto.setId(idCounter.incrementAndGet());
		students.add(studentDto);
	}

	public List<StudentDto> findAll() {
		return students;
	}

	public void deleteById(Integer id) {
		students.removeIf(obj -> obj.getId() == id);

	}

	public StudentDto findById(Integer id) {
		return students.stream().filter(obj -> obj.getId() == id).findFirst()
				.orElseThrow(() -> new StudentNotFoudException("Student with id " + id + " is not found"));
	}

	public void update(StudentDto studentDto) {
		students.replaceAll(obj -> obj.getId() == studentDto.getId() ? studentDto : obj);
	}
	public List<StudentDto> search(String search) {
		return students.stream().filter(person -> person.getName().toLowerCase().contains(search.toLowerCase()))
				.collect(Collectors.toList());
	}

}
