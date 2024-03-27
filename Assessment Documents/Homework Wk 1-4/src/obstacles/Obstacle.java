package obstacles;

/**
 * Represents an obstacle that can block some locations on the map,
 * and has a representative symbol
 */
public interface Obstacle {
    /**
     * Returns the symbol that represents the obstacle on the map
     * @return The symbol that represents the obstacle on the map
     */
    char getSymbol();

    /**
     * Returns true if the given location is obstructed by the obstacle, and false otherwise
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @return True if the given location is obstructed by the obstacle, and false otherwise
     */
    boolean isLocationObstructed(int x, int y);
}