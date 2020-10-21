package ru.otus.project.prep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.prep.domain.room.Room;
import ru.otus.project.prep.domain.room.RoomStatus;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByStatus(RoomStatus status);
}
