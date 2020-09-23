package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomGeneration {

    public List<Room> roomListws;
    public List<Room> roomListad;


    public RoomGeneration() {
        roomListws = new ArrayList<>();
        roomListad = new ArrayList<>();

        randomGen();
    }

    public void randomGen() {
        Random rand = new Random();
        int upperbound = 1000;

        for (int i=0; i<100; i++) {
            Room room = new Room("Room" + rand.nextInt(upperbound), rand.nextInt(upperbound));
            roomListws.add(room);
            Room room1 = new Room("Room" + rand.nextInt(upperbound), rand.nextInt(upperbound));
            roomListad.add(room1);
        }
    }

}
