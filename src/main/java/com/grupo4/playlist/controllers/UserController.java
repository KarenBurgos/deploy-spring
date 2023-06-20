package com.grupo4.playlist.controllers;

import java.util.UUID;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.GetPlaylistDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.MessageDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.services.UserService;
import com.grupo4.playlist.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.login(info);
			return new ResponseEntity<>(new MessageDTO("login OK!"), HttpStatus.OK);
		}catch(Exception e){
			if (e.getMessage().equals("Invalid credentials")) {
	            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
	        } else {
	            e.printStackTrace();
	            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
		}
		
	}
	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> saveUser(@RequestBody @Valid SaveUserDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.saveUser(info);
			return new ResponseEntity<>(new MessageDTO("user created!"), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<?> deleteByUsername(@PathVariable(name = "username") String username){
		if(userService.findOneByUsername(username) == null){
			return new ResponseEntity<>(new MessageDTO("User not found"), HttpStatus.NOT_FOUND);
		}
		
		try {
			userService.deleteByUsername(username);
			return new ResponseEntity<>(new MessageDTO("User delete!"), HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDTO info, BindingResult validations){
		if(userService.findOneByUsername(info.getUsername()) == null){
			return new ResponseEntity<>(
					new MessageDTO("Username not found"), HttpStatus.NOT_FOUND);
		}
		else if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()),
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.changePassword(info);
			return new ResponseEntity<>(new MessageDTO("password update!"), HttpStatus.OK);
			
		}catch(Exception e){
			if(e.getMessage().equals("400")){
				return new ResponseEntity<>(new MessageDTO("Wrong password"), HttpStatus.BAD_REQUEST);
			}
			else {
				e.printStackTrace();
				return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
	}
	
	//Modificar este servicio porque debe mostrarse el UserInfoDTO sin la contraseña
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<User> users = userService.findAll(); 
		return new ResponseEntity<>(
				users,
				HttpStatus.OK
			);
	}
	
	@GetMapping("/by/{username}")
	public ResponseEntity<?> findOneByUsername(@PathVariable(name = "username") String username){
		User user = userService.findOneByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(
					new MessageDTO("user not found"),
					HttpStatus.NOT_FOUND
				);

		}
		return new ResponseEntity<>(
				user,
				HttpStatus.OK
			);
	}
	
	@GetMapping("/playlist")
	public ResponseEntity<?> playlistUser(@RequestParam("username") String username, @RequestParam("titlePlay") String titlePlay) {
	    GetPlaylistDTO info = new GetPlaylistDTO(username, titlePlay);
	    List<Playlist> playlist = userService.playlistUser(info);
	    return new ResponseEntity<>(playlist, HttpStatus.OK);
	}
}
