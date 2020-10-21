package ru.otus.project.prep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.prep.domain.artist.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
