package ru.andmosk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andmosk.entity.WikimediaEntity;

@Repository
public interface WikimediaRepository extends JpaRepository<WikimediaEntity,Long> {
}
