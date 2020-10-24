package ru.otus.project.prep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.project.prep.domain.artist.Artist;
import ru.otus.project.prep.domain.rehearsal.Rehearsal;
import ru.otus.project.prep.domain.rehearsal.RehearsalStatus;
import ru.otus.project.prep.domain.room.Room;

import java.time.LocalDate;
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
