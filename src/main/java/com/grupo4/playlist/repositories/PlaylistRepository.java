package com.grupo4.playlist.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;

public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID> {
	void deleteByTitle(String title);
	/*void deleteByTitleAndUser(String title, String user);
	void updateByTitleAndUser(String title, User user, SavePlaylistDTO info);*/
	Playlist findOneByTitle(String title);
	Playlist findOneByCode(UUID code);
	//Playlist findOneByTitleAndUser(String title, String user);
}
