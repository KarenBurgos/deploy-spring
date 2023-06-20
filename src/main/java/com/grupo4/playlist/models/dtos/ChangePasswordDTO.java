package com.grupo4.playlist.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordDTO {
	@NotEmpty(message = "Current user can't be empty")
	private String username;
	
	@NotEmpty(message = "Current password can't be empty")
    private String currentPassword;

    @NotEmpty(message = "New password can't be empty")
    @Size(min = 5, message = "New password must have a minimum of 5 characters")
    private String newPassword;
}
