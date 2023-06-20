package com.grupo4.playlist.services;

import java.util.List;
import java.util.UUID;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.GetPlaylistDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.dtos.UserInfoDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;

public interface UserService {
	
	void login(LoginDTO info) throws Exception;
	void saveUser(SaveUserDTO info) throws Exception;
	void deleteByCode(UUID code) throws Exception;
	void deleteByUsername(String user) throws Exception;
	void changePassword(ChangePasswordDTO info) throws Exception;
	List<User> findAll();
	User findOneByUsername(String username);
	User findOneByEmail(String email);
	List<Playlist> playlistUser(GetPlaylistDTO info);
}
