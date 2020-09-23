package com.company;

import java.util.List;

public class Room {

    private final String name;
    private final int coin;
    private List<Room> rooms;

    public Room(String name, int coin) {
        this.name = name;
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public int getCoin() {
        return coin;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
