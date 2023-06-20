package com.grupo4.playlist.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserDTO {
	@NotEmpty(message = "Username can't be empty")
	private String username;
	
	@NotEmpty(message = "Password can't be empty")
    @Size(min = 8, message = "Password must have a minimum of 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;
	
	@NotEmpty(message = "Password can't be empty")
	@Email(message = "Invalid email format")
	private String email;
}
