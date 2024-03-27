package obstacles;

import common.Location;

/**
 * Represents a sensor on the map that detects intruders within a certain range
 * in a circular area.
 */
public class Sensor extends LocatableObstacle {
    private final double range;

    /**
     * Constructs a new Sensor object with the given location and range.
     * @param location The location of the sensor
     * @param range The range of the sensor
     */
    public Sensor(Location location, double range) {
        super(location);
        this.range = range;
    }
    @Override
    public boolean isLocationObstructed(int x, int y) {
        double distance = Math.sqrt(Math.pow(location.getX() - x, 2) + Math.pow(location.getY() - y, 2));
        return distance <= range;
    }
    @Override
    public char getSymbol() {
        return ObstacleType.SENSOR.getSymbol();
    }

    /**
     * Constructs a new Sensor object from the given string argument.
     * @param arg The string argument in the format "x,y,range" representing the location and range of the sensor
     * @return A new Sensor object with the given location and range
     */
    public static Sensor parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Sensor must have 3 inputs (x, y, range)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        double range = Double.parseDouble(parts[2]);
        Location location = new Location(x, y);
        return new Sensor(location, range);
    }
}