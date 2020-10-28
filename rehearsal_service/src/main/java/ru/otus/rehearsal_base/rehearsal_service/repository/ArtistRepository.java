package ru.otus.rehearsal_base.rehearsal_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
