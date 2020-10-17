package ru.otus.project.prep.domain.room;

import ru.otus.project.prep.domain.artist.ArtistType;

public class Room {
    private long id;
    private String name;
    private String description;
    private RoomStatus status;
    private ArtistType artistType;
    private int price;
}
