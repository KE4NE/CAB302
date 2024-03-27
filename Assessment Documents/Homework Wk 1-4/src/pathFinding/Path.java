package pathFinding;
import common.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Represents a path on the map.
 */
public class Path implements Iterable<Location> {
    private List<Location> inner;
    /**
     * Constructs a new Path object using an ArrayList of locations.
     * @param inner The path
     */
    public Path(List<Location> inner) {
        this.inner = inner;
    }

    /**
     * Returns whether the given location is in the path.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @return True if the given location is in the path, and false otherwise
     */
    public boolean isLocationInPath(int x, int y) {
        return inner.contains(new Location(x, y));
    }

    /**
     * Returns the symbol that represents the given location on the path.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @return The symbol that represents the given location on the path
     */
    public char getSymbolForLocation(int x, int y) {
        Location location = new Location(x, y);
        int locationIndex = inner.indexOf(location);
        if (locationIndex == 0) {
            return 'S';
        }
        if (locationIndex == inner.size() - 1) {
            return 'E';
        }
        Location previousLocation = inner.get(locationIndex - 1);
        Location nextLocation = inner.get(locationIndex + 1);
        Direction directionFrom = Direction.getDirection(previousLocation, location);
        Direction directionTo = Direction.getDirection(location, nextLocation);
        return getSymbolFromDirections(directionFrom, directionTo);
    }

    /**
     * Get the symbol based on the directions of the current point from the previous point
     * and the next point from the current point.
     * @param from The direction of the current point from the previous point
     * @param to The direction of the next point from the current point
     * @return The symbol that represents the given location on the path
     */
    private char getSymbolFromDirections(Direction from, Direction to) {
        if (from == to) {
            switch (from) {
                case NORTH, SOUTH:
                    return '║';
                case EAST, WEST:
                    return '═';
                default:
                    break;
            }
        }
        if (from == Direction.SOUTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.NORTH) {
            return '╚';
        }
        if (from == Direction.SOUTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.NORTH) {
            return '╝';
        }
        if (from == Direction.NORTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.SOUTH) {
            return '╔';
        }
        if (from == Direction.NORTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.SOUTH) {
            return '╗';
        }
        return ' ';
    }

    @Override
    public Iterator<Location> iterator() {
        return inner.iterator();
    }

    @Override
    public void forEach(Consumer<? super Location> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Location> spliterator() {
        return Iterable.super.spliterator();
    }
}