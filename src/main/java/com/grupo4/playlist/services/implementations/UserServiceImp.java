package com.grupo4.playlist.services.implementations;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.GetPlaylistDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.dtos.UserInfoDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.repositories.PlaylistRepository;
import com.grupo4.playlist.repositories.UserRepository;
import com.grupo4.playlist.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Override
	public void login(LoginDTO info) throws Exception {
		User user = userRepository.findOneByUsername(info.getUsername());
		
		if(!user.getPassword().equals(info.getPassword())) {
			throw new Exception("Invalid credentials");
		}
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void saveUser(SaveUserDTO info) throws Exception {
		try {
			User newUser = new User(info.getUsername(), info.getPassword(), info.getEmail());
			userRepository.save(newUser);
		}catch(Exception e){
			throw new Exception("Error save user");
		}
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByCode(UUID code) throws Exception {
		userRepository.deleteByCode(code);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByUsername(String username) throws Exception {
	    userRepository.deleteByUsername(username);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void changePassword(ChangePasswordDTO info) throws Exception {
		User user = userRepository.findOneByUsername(info.getUsername());

        if (user == null || !user.getPassword().equals(info.getCurrentPassword())) {
            throw new Exception("400");
        }
        user.setPassword(info.getNewPassword());
        userRepository.save(user);
		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}

	@Override
	public User findOneByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	@Override
	public List<Playlist> playlistUser(GetPlaylistDTO info) {
		//obtengo todas las playlist
		List<Playlist> playlist = playlistRepository.findAll();
		
		//obtengo las playlist de un code de usuario
		List<Playlist> filteredPlaylists = playlist.stream()
	            .filter(p -> p.getUser().getUsername().equals(info.getUsername()))
	            .collect(Collectors.toList());
		System.out.println(info.getUsername()+info.getTitlePlay());
		
		//Creando un filtro
		filteredPlaylists = playlist.stream()
				.filter(p -> p.getTitle().contains(info.getTitlePlay()))
				.collect(Collectors.toList());

		return filteredPlaylists;
	}
	
	
	
}
