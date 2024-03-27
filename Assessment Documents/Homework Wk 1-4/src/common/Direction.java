package common;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    /**
     * Parses a direction from a string (n, s, e, w).
     * @param arg The string to parse
     * @return The direction represented by the string
     */
    public static Direction parse(String arg) {
        switch (arg.toUpperCase()) {
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            default:
                throw new IllegalArgumentException("Direction must be one of n, s, e, w");
        }
    }

    /**
     * Returns the direction from one location to another.
     * @param from The location to start from
     * @param to The location to end at
     * @return The direction from the first location to the second location
     */
    public static Direction getDirection(Location from, Location to) {
        int xDiff = to.getX() - from.getX();
        int yDiff = to.getY() - from.getY();
        if (xDiff == 0 && yDiff == 0) {
            return null;
        }
        if (xDiff == 0) {
            return yDiff > 0 ? SOUTH : NORTH;
        }
        if (yDiff == 0) {
            return xDiff > 0 ? EAST : WEST;
        }
        return null;
    }
}