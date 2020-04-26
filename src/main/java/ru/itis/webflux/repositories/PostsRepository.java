package ru.itis.webflux.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.webflux.entities.DbPostRecord;

@Repository
public interface PostsRepository extends JpaRepository<DbPostRecord, Long> {
}
