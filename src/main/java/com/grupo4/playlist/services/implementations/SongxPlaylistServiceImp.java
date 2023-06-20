package com.grupo4.playlist.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.playlist.models.dtos.ResponsePlaylistDTO;
import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.models.entities.SongxPlaylist;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.repositories.PlaylistRepository;
import com.grupo4.playlist.repositories.SongRepository;
import com.grupo4.playlist.repositories.SongxPlaylistRepository;
import com.grupo4.playlist.services.PlaylistService;
import com.grupo4.playlist.services.SongxPlaylistService;

import jakarta.transaction.Transactional;

@Service
public class SongxPlaylistServiceImp implements SongxPlaylistService {
	@Autowired
	private SongxPlaylistRepository songxPlaylistRepository;
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private SongRepository songRepository;

	@Override
	public List<Song> findAllbySong(Playlist playlist) {
		
		return null;
	}

	@Override
	public ResponsePlaylistDTO findAllbyPlaylist(Playlist playlist) {
		String title = playlist.getTitle();
	    
	    List<Song> songs = songxPlaylistRepository.findAll().stream()
	            .filter(sp -> sp.getPlaylist().getTitle().equals(title))
	            .map(SongxPlaylist::getSong)
	            .collect(Collectors.toList());
		
		/*List<Map<String, Object>> songInfos = songxPlaylistRepository.findAll().stream()
	            .filter(sp -> sp.getPlaylist().getTitle().equals(title))
	            .map(sp -> {
	                Map<String, Object> songInfo = new HashMap<>();
	                songInfo.put("title", sp.getSong().getTitle());
	                songInfo.put("addDate", sp.getDateAdded());
	                return songInfo;
	            })
	            .collect(Collectors.toList());*/
	    
	    List<String> songTitles = songs.stream()
	            .map(Song::getTitle)
	            .collect(Collectors.toList());

		/*int totalDuration = songInfos.stream()
	            .mapToInt(songInfo -> ((Song) songInfo.get("title")).getDuration())
	            .sum();*/
	    
	    int totalDuration = songs.stream()
	            .mapToInt(Song::getDuration)
	            .sum();

	    int minutes = totalDuration / 60;
	    int seconds = totalDuration % 60; 
	    String formatDuration = minutes + ":" + String.format("%02d", seconds); 
	    
	    ResponsePlaylistDTO response = new ResponsePlaylistDTO(songTitles,formatDuration);
	    
	    return response;
	}

	@Override
	public void addSongToPlaylist(UUID songId, UUID playlistId) {
	    SongxPlaylist songxPlaylist = new SongxPlaylist();

	    Playlist playlist = playlistRepository.findOneByCode(playlistId);
	    songxPlaylist.setPlaylist(playlist);

	    Song song = songRepository.findOneByCode(songId);
	    songxPlaylist.setSong(song);

	    songxPlaylistRepository.save(songxPlaylist);
	}



}