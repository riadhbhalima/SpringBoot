package tn.enis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	private Integer id;

	@NotBlank(message = "{NotEmpty}")
	@Size(min = 2, max = 50, message = "{student.name.msg}") // : a un probleme dans cette
																						// vesion
	// @Pattern(regexp = ".{2,50}", message = "{student.name.pattern}")
	private String name;

	private char gender;
}
