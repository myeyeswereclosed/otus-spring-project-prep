package ru.otus.rehearsal_base.rehearsal_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.Rehearsal;

import java.time.LocalDateTime;
import java.util.List;

public interface RehearsalRepository extends JpaRepository<Rehearsal, Long> {
    @Query(
        "select r from Rehearsal r " +
        "where r.room.id = :roomId and r.status = 'RESERVED' and r.startDatetime > :from and r.startDatetime < :to")
    List<Rehearsal> findReserved(
        @Param("roomId") int roomId,
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to
    );

    List<Rehearsal> findAllByArtist(Artist artist);
}
