package com.grupo4.playlist.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.models.entities.SongxPlaylist;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.repositories.PlaylistRepository;
import com.grupo4.playlist.services.PlaylistService;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImp implements PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SavePlaylistDTO info, User user) throws Exception {
		Playlist playlist = new Playlist(
				info.getTitle(),
				info.getDescription(),
				user
			);
		playlistRepository.save(playlist);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByTitle(String title) throws Exception {
		playlistRepository.deleteByTitle(title);
		
	}


	@Override
	public List<Playlist> findAll() {
		List<Playlist> playlists = playlistRepository.findAll();
		return playlists;
	}

	@Override
	public Playlist findOneByTitle(String title) {
		Playlist playlist = playlistRepository.findOneByTitle(title);
		return playlist;
	}

	@Override
	public void updateSongs(SaveSongDTO song, String title) {
	    /*Playlist playlist = playlistRepository.findOneByTitle(title);
	    Song newSong = new Song(
	            song.getTitle(),
	            song.getDuration()
	    );
	    SongxPlaylist newSongxPlaylist = new SongxPlaylist(
	            playlist,
	            newSong
	    );
	    List<SongxPlaylist> songList = new ArrayList<>();
	    songList.add(newSongxPlaylist);
	    playlist.setSongs(songList);

	    // Guardar los cambios en la base de datos
	    playlistRepository.save(playlist);*/
	}


}
