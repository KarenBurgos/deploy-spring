package com.grupo4.playlist.models.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SongInfoDTO {
	private UUID code;
	
	private String title;
	
	private String duration;
}
