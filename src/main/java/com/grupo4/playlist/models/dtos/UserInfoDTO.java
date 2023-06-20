package com.grupo4.playlist.models.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
	private UUID code;
	
	private String username;
	
	private String password;
}
