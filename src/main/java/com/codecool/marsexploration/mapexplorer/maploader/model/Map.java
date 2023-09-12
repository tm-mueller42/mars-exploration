package com.codecool.marsexploration.mapexplorer.maploader.model;

import java.util.List;

public class Map {
    private String[][] representation;

    public Map(String[][] representation) {
        this.representation = representation;
    }

    public int getDimension() {
        return representation.length;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : arr) {
            StringBuilder s = new StringBuilder();
            for (String string : strings) {
                s.append(string == null ? " " : string);
            }

            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public String getByCoordinate(Coordinate coordinate) {
        return representation[coordinate.X()][coordinate.Y()];
    }

    public boolean isEmpty(Coordinate coordinate) {
        return representation[coordinate.X()][coordinate.Y()] == null
                || representation[coordinate.X()][coordinate.Y()].isEmpty()
                || representation[coordinate.X()][coordinate.Y()].equals(" ");
    }

    public List<Coordinate> getAdjacent(Coordinate coordinate) {
        int x = coordinate.X();
        int y = coordinate.Y();

        //check if even on map, and only return those

        return List.of(
                new Coordinate(x - 1, y - 1),
                new Coordinate(x, y - 1),
                new Coordinate(x + 1, y - 1),
                new Coordinate(x + 1, y),
                new Coordinate(x + 1, y + 1),
                new Coordinate(x, y + 1),
                new Coordinate(x - 1, y + 1),
                new Coordinate(x - 1, y)
        );
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}
