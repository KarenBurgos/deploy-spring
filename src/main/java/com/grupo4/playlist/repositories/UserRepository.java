package com.grupo4.playlist.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.dtos.UserInfoDTO;
import com.grupo4.playlist.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
	void deleteByCode(UUID code) throws Exception;
	void deleteByUsername(String user) throws Exception;
	User findOneByUsername(String username);
	User findOneByEmail(String email);
	List<User> findAll();
}