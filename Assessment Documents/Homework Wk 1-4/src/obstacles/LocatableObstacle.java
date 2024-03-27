package obstacles;

import common.Location;

/**
 * Represents an obstacle that can be located on the map.
 */
public abstract class LocatableObstacle implements Obstacle {
    protected final Location location;

    /**
     * Constructs a new LocatableObstacle object with the given location.
     * @param location The location of the obstacle
     */
    public LocatableObstacle(Location location) {
        this.location = location;
    }

    /**
     * Constructs a new LocatableObstacle object with the given x and y coordinates.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     */
    public LocatableObstacle(int x, int y) {
        this.location = new Location(x, y);
    }

    /**
     * Returns the location of the obstacle.
     * @return The location of the obstacle
     */
    public Location getLocation() {
        return location;
    }

    public abstract boolean isLocationObstructed(int x, int y);
    public abstract char getSymbol();
}