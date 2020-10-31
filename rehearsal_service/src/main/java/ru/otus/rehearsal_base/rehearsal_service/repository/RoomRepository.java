package ru.otus.rehearsal_base.rehearsal_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.Room;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomStatus;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    // TODO check for N+1
    List<Room> findAllByStatus(RoomStatus status);
}
