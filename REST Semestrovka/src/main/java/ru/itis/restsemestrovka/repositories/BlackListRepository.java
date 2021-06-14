package ru.itis.restsemestrovka.repositories;

public interface BlackListRepository {
	void save(String token);

	boolean exists(String token);
}
