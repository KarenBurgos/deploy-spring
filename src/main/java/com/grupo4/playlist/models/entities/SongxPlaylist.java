package com.grupo4.playlist.models.entities;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songxplaylist")
public class SongxPlaylist {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@ManyToOne
    @JoinColumn(name = "playlist_code", nullable = true)
    Playlist playlist;
	
	@ManyToOne
    @JoinColumn(name = "song_code", nullable = true)
    Song song;
	
	@Column(name = "date_added")
	private Timestamp dateAdded;

	public SongxPlaylist(Playlist playlist, Song song) {
		super();
		this.playlist = playlist;
		this.song = song;
	}
	
	@PrePersist
	private void prePersist() {
		dateAdded = new Timestamp(System.currentTimeMillis());
	}
}

