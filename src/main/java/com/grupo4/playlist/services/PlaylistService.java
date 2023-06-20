package com.grupo4.playlist.services;

import java.util.List;

import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;

public interface PlaylistService {
	void save(SavePlaylistDTO info, User user) throws Exception;
	void deleteByTitle(String title) throws Exception;
	void updateSongs(SaveSongDTO song, String title);
	/*void deleteByTitleAndUser(String title, String user) throws Exception;
	void updateByTitleAndUser(String title, User user, SavePlaylistDTO info) throws Exception;*/
	List<Playlist> findAll();
	Playlist findOneByTitle(String title);
	//Playlist findOneByTitleAndUser(String title, String user);
}
