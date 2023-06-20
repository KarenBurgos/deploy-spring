package com.grupo4.playlist.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;

public interface SongRepository extends ListCrudRepository<Song, UUID> {
	void deleteByTitle(String title);
	Song findOneByTitle(String title);
	Song findOneByCode(UUID code);
	List<Song> findByTitleContaining(String titlePart);
}
