package com.grupo4.playlist.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.grupo4.playlist.models.dtos.MessageDTO;
import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.dtos.SongInfoDTO;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.services.SongService;
import com.grupo4.playlist.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/song")
public class SongController {
	@Autowired
	private SongService songService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllSongs(@RequestParam(required = false) String titlePart) {
	    try {
	        List<Song> songs;
	        List<SongInfoDTO> songsFormat = new ArrayList<>();

	        if (titlePart != null && !titlePart.isEmpty()) {
	            songs = songService.findByTitleContaining(titlePart);
	        } else {
	            songs = songService.findAll();
	        }

	        for (Song song : songs) {
	            int durationInSeconds = song.getDuration();
	            int minutes = durationInSeconds / 60;
	            int seconds = durationInSeconds % 60;
	            String formattedDuration = String.format("%d:%02d", minutes, seconds);

	            SongInfoDTO songDTO = new SongInfoDTO(
	                    song.getCode(),
	                    song.getTitle(),
	                    formattedDuration
	            );

	            songsFormat.add(songDTO);
	        }

	        return new ResponseEntity<>(songsFormat, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(
	                new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@GetMapping("/all/secs")
	public ResponseEntity<?> findAllSongsInSeconds() {
		try {
			List<Song> songs = songService.findAll();
		    return new ResponseEntity<>(songs, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}



	
	@GetMapping("/{title}")
	public ResponseEntity<?> findOneByTitle(@PathVariable(name = "title") String title) {
		Song song = songService.findOneByTitle(title);
		
		if(song == null) {
			return new ResponseEntity<>(
					new MessageDTO("Song not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(song, HttpStatus.OK);
	}
	
	@PutMapping("/{title}")
	public ResponseEntity<?> update(@PathVariable(name = "title") String title, @RequestBody @Valid
			SaveSongDTO info, BindingResult validations) {
		if(songService.findOneByTitle(title) == null){
			return new ResponseEntity<>(
					new MessageDTO("Song not found"), HttpStatus.NOT_FOUND);
		}
		else if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		try {
			songService.update(title, info);
			return new ResponseEntity<>(
					new MessageDTO("Song updated"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody @Valid SaveSongDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			songService.save(info);
			return new ResponseEntity<>(
					new MessageDTO("Song added"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{title}")
	public ResponseEntity<?> deleteByTitle(@PathVariable(name = "title") String title){
		if(songService.findOneByTitle(title) == null){
			return new ResponseEntity<>(
					new MessageDTO("Song not found"), HttpStatus.NOT_FOUND);
		}
		try {
			songService.deleteByTitle(title);
			return new ResponseEntity<>(
					new MessageDTO("Song deleted"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
