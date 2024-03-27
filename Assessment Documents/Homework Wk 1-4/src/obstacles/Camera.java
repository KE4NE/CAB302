package obstacles;

import common.*;

/**
 * Represents a camera on the map that obstructs all locations in its cone of vision.
 */
public class Camera extends LocatableObstacle {
    private final Direction direction;
    /**
     * Constructs a new Camera object with the given location and direction.
     * @param location The location of the camera
     * @param direction The direction of the camera
     */
    public Camera(Location location, Direction direction) {
        super(location);
        this.direction = direction;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // Cameras obstruct all locations in their cone of vision
        int xDiff = x - location.getX();
        int yDiff = y - location.getY();
        switch (direction) {
            case NORTH:
                return Math.abs(xDiff) <= -yDiff;
            case EAST:
                return Math.abs(yDiff) <= xDiff;
            case SOUTH:
                return Math.abs(xDiff) <= yDiff;
            case WEST:
                return Math.abs(yDiff) <= -xDiff;
            default:
                throw new IllegalStateException("Camera direction must be one of NORTH, EAST, SOUTH, WEST");
        }
    }

    @Override
    public char getSymbol() {
        return ObstacleType.CAMERA.getSymbol();
    }

    /**
     * Constructs a new Camera object from the given string argument.
     * @param arg The string argument in the format "x,y,direction" representing the location and direction of the camera
     * @return A new Camera object with the given location and direction
     */
    public static Camera parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Camera must have 3 inputs (x, y, direction)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.parse(parts[2]);
        Location location = new Location(x, y);
        return new Camera(location, direction);
    }
}