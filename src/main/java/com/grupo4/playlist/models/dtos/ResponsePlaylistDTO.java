package com.grupo4.playlist.models.dtos;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePlaylistDTO {
	private List<String> songs;
	private String totalDuration;
	
}
