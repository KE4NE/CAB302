package common;

import java.util.Objects;

/**
 * Represents a location on the map.
 */
public class Location {
    private final int x;
    private final int y;

    /**
     * Constructs a new Location object with the given x and y coordinates.
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the location.
     * @return The x coordinate of the location
     */
    public int getX() { return x; }

    /**
     * Returns the y coordinate of the location.
     * @return The y coordinate of the location
     */
    public int getY() { return y; }

    /**
     * Returns whether the given location is equal to this location.
     * @param obj The object to compare to
     * @return True if the given location is equal to this location, and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return x == location.x && y == location.y;
    }

    /**
     * Returns the hash code of this location.
     * @return The hash code of this location
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns a string representation of this location.
     * @return A string representation of this location
     */
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Parses the given string into a Location object.
     * @param s The string to parse, in the format "x,y"
     * @return The Location object parsed from the given string
     */
    public static Location parse(String s) {
        String[] parts = s.split(",");
        return new Location(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}