package ru.otus.rehearsal_base.rehearsal_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.Room;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomStatus;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("select r from Room r join fetch r.status join fetch r.roomType where r.status = :status")
    List<Room> findAllByStatus(RoomStatus status);
}
