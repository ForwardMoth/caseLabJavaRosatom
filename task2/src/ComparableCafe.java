import java.util.Comparator;

public class ComparableCafe implements Comparator<Cafe> {
    private final Coordinates userCoordinates;

    ComparableCafe(Coordinates userCoordinates) {
        this.userCoordinates = userCoordinates;
    }

    @Override
    public int compare(Cafe o1, Cafe o2) {
        return Integer.compare(
                userCoordinates.getEuclideanDistance(o1.coordinates()),
                userCoordinates.getEuclideanDistance(o2.coordinates())
        );
    }
}
