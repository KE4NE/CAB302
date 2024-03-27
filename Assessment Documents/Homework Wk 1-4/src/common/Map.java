package common;

import obstacles.Obstacle;
import pathFinding.BFSPathFinder;
import pathFinding.GridPathFinder;
import pathFinding.Path;

import java.util.ArrayList;

/**
 * Represents a map with obstacles.
 */
public class Map {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final int PADDING = 2;

    /**
     * Constructs a new Map object with the given obstacles.
     * @param obstacles The obstacles on the map
     */
    public Map(ArrayList<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    /**
     * Returns the obstacle at the given location, or null if there is no obstacle at the given location.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @return The obstacle at the given location, or null if there is no obstacle at the given location
     */
    private Obstacle getObstacleAtLocation(int x, int y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isLocationObstructed(x, y)) {
                return obstacle;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the map.
     * @return A string representation of the map
     */
    private String matrixToString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            for (char symbol : row) {
                sb.append(symbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the map with the given start and target locations.
     * @param start The start location
     * @param target The target location
     * @return A string representation of the map with the given start and target locations
     */
    public String getSolvedMap(Location start, Location target) {
        // Find the path
        GridPathFinder pathFinder = new BFSPathFinder(this);
        Path path = pathFinder.findPath(start, target);

        // Define the bounds (including padding) based on the start and target locations
        Location topLeft, bottomRight;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE,
                minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (Location location : path) {
            int x = location.getX();
            int y = location.getY();
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
        }
        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);

        // Create the map
        // +1 because the bounds are inclusive
        char[][] solvedMap = new char[bottomRight.getY() - topLeft.getY() + 1][bottomRight.getX() - topLeft.getX() + 1];
        for (int y = topLeft.getY(); y <= bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x <= bottomRight.getX(); x++) {
                // 1. Check location in path
                if (path.isLocationInPath(x, y)) {
                    solvedMap[y - topLeft.getY()][x - topLeft.getX()] = path.getSymbolForLocation(x, y);
                    continue;
                }

                // 2. Check obstruction
                Obstacle obstructingObstacle = getObstacleAtLocation(x, y);
                // Calculate the index in the map 2D array
                int j = y - topLeft.getY();
                int i = x - topLeft.getX();
                if (obstructingObstacle != null) {
                    solvedMap[j][i] = obstructingObstacle.getSymbol();
                    continue;
                }

                // 3. Empty space
                solvedMap[j][i] = '.';
            }
        }

        // Convert the map to a string
        return matrixToString(solvedMap);
    }

    /**
     * Returns true if the given location is obstructed by an obstacle, and false otherwise.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @return True if the given location is obstructed by an obstacle, and false otherwise
     */
    public boolean isLocationObstructed(int x, int y) {
        return getObstacleAtLocation(x, y) != null;
    }
}