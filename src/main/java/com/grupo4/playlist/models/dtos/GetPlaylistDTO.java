package com.grupo4.playlist.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPlaylistDTO {
	private String username;
	private String titlePlay;
}
