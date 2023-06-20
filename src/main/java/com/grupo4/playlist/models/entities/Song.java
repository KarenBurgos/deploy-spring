package com.grupo4.playlist.models.entities;

import java.util.UUID;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "duration")
	private Integer duration;
	
	@OneToMany(mappedBy = "song", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SongxPlaylist> Playlist;
	
	public Song(String title, Integer duration) {
		super();
		this.title = title;
		this.duration = duration;
	}
}
