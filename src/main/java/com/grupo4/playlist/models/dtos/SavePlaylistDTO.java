package com.grupo4.playlist.models.dtos;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavePlaylistDTO {
	
	@NotEmpty(message = "Title can't be empty")
	private String title;

	@NotEmpty(message = "Description can't be empty")
	private String description;
	
	@NotEmpty(message = "User can't be empty")
	private String user;
}
