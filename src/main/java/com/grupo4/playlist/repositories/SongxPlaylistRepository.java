package com.grupo4.playlist.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.models.entities.SongxPlaylist;

public interface SongxPlaylistRepository extends ListCrudRepository<SongxPlaylist, UUID> {
	//void saveSongtoPlaylist(SongxPlaylist songxPlaylist);
	//List<Song> findAllbySong(Playlist playlist);
	//List<Playlist> findAllbyPlaylist();
	

}
