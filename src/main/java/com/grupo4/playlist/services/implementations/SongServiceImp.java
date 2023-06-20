package com.grupo4.playlist.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.repositories.SongRepository;
import com.grupo4.playlist.services.SongService;

import jakarta.transaction.Transactional;

@Service
public class SongServiceImp implements SongService {
	
	@Autowired
	private SongRepository songRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveSongDTO info) throws Exception {
		Song song = new Song(
					info.getTitle(),
					info.getDuration()
				);
		songRepository.save(song);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByTitle(String title) throws Exception {
		songRepository.deleteByTitle(title);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(String title, SaveSongDTO info) throws Exception {
		songRepository.findOneByTitle(title);
		songRepository.deleteByTitle(title);
		
		Song song = new Song(
				info.getTitle(),
				info.getDuration()
			);
		
		songRepository.save(song);
		
	}

	@Override
	public List<Song> findAll() {
		return songRepository.findAll();
	}

	@Override
	public Song findOneByTitle(String title) {
		return songRepository.findOneByTitle(title);
	}

	@Override
	public List<Song> findByTitleContaining(String titlePart) {
		List<Song> allSongs = songRepository.findAll();
		List<Song> songMatches = new ArrayList<>();
		songMatches = allSongs.stream()
				.filter(s -> s.getTitle().contains(titlePart))
				.collect(Collectors.toList());
		return songMatches;
	}
	

}
