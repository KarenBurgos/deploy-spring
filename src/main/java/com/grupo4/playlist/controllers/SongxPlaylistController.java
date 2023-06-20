package com.grupo4.playlist.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.playlist.models.dtos.MessageDTO;
import com.grupo4.playlist.models.dtos.ResponsePlaylistDTO;
import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.dtos.SaveSongxPlaylist;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.services.PlaylistService;
import com.grupo4.playlist.services.SongService;
import com.grupo4.playlist.services.SongxPlaylistService;
import com.grupo4.playlist.services.UserService;
import com.grupo4.playlist.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songxplaylist")
public class SongxPlaylistController {
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SongService songService;
	
	@Autowired SongxPlaylistService songxPlaylistService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@GetMapping("/{playlist}")
	public ResponseEntity<?> findAllSongsInPlaylist(@PathVariable(name = "playlist") String playlist){
		Playlist playlistFound = playlistService.findOneByTitle(playlist);
		if(playlistFound == null) {
			return new ResponseEntity<>(
					new MessageDTO("Playlist not found"), HttpStatus.NOT_FOUND);
		};
		ResponsePlaylistDTO response = songxPlaylistService.findAllbyPlaylist(playlistFound);
		return new ResponseEntity<>(
					response,
					HttpStatus.OK
				);
	}
	
	@PostMapping("/save/{playlist}")
	public ResponseEntity<?> addSongToPlaylist(@RequestBody SaveSongxPlaylist info,@PathVariable(name = "playlist") String titlePlaylist) {
	    Song song = songService.findOneByTitle(info.getSongTitle());
	    Playlist playlist = playlistService.findOneByTitle(titlePlaylist);
	    if (song == null || playlist == null) {
	        return new ResponseEntity<>(
	                new MessageDTO("Playlist or song not found"), HttpStatus.NOT_FOUND);
	    }
	    try {
	        songxPlaylistService.addSongToPlaylist(song.getCode(), playlist.getCode());
	        return ResponseEntity.ok("Song added to playlist successfully");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
