package obstacles;

import common.Location;

/**
 * Represents a guard on the map.
 */
public class Guard extends LocatableObstacle {
    /**
     * Constructs a new Guard object with the given location.
     * @param location The location of the guard
     */
    public Guard(Location location) {
        super(location);
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // A guard obstructs its own location
        return location.getX() == x && location.getY() == y;
    }

    @Override
    public char getSymbol() {
        return ObstacleType.GUARD.getSymbol();
    }

    /**
     * Constructs a new Guard object from the given string argument.
     * @param arg The string argument in the format "x,y" representing the location of the guard
     * @return A new Guard object with the given location
     */
    public static Guard parse(String arg) {
        Location location = Location.parse(arg);
        return new Guard(location);
    }
}