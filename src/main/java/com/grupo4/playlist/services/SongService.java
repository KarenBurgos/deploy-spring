package com.grupo4.playlist.services;

import java.util.List;

import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;

public interface SongService {
	void save(SaveSongDTO info) throws Exception;
	void deleteByTitle(String title) throws Exception;
	void update(String title, SaveSongDTO info) throws Exception;
	List<Song> findAll();
	Song findOneByTitle(String title);
	List<Song> findByTitleContaining(String titlePart);
	
}
