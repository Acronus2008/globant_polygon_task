import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class GlobantPolygonCut {

    private final List<Point> polygon;

    public GlobantPolygonCut(List<Point> polygon) {
        this.polygon = polygon;
    }

    public List<List<Point>> cutPolygon(Point p1, Point p2) {
        var resultPolygons = new ArrayList<List<Point>>();

        List<Point> intersections = IntStream.range(0, polygon.size())
                .mapToObj(i -> {
                    var start = polygon.get(i);
                    var end = polygon.get((i + 1) % polygon.size());
                    return getIntersection(start, end, p1, p2);
                }).filter(Objects::nonNull)
                .toList();

        return getInterceptionsLists(intersections, resultPolygons);
    }

    private ArrayList<List<Point>> getInterceptionsLists(List<Point> intersections, ArrayList<List<Point>> resultPolygons) {
        if (intersections.isEmpty()) {
            resultPolygons.add(new ArrayList<>(polygon));
            return resultPolygons;
        }

        buildPolygons(intersections, resultPolygons);
        return resultPolygons;
    }


    private Point getIntersection(Point p1, Point p2, Point p3, Point p4) {
        double denominator = PolygonOperator.findIntersection(p1, p2, p3, p4);
        if (denominator == 0) return null; // The lines are parallel


        final var uA = PolygonOperator.calculateUaSegment.getPosition(denominator, p1, p2, p4);
        final var uB = PolygonOperator.calculateUbSegment.getPosition(denominator, p1, p2, p3);

        // Return null if the intersection is outside the segments
        return (uA < 0 || uA > 1 || uB < 0 || uB > 1) ? null : getPoint(p1, p2, uA);
    }

    private static Point getPoint(Point p1, Point p2, Double uA) {
        int x = (int) (p1.x + uA * (p2.x - p1.x));
        int y = (int) (p1.y + uA * (p2.y - p1.y));
        return new Point(x, y);
    }

    /**
     * The logic to build the resulting polygons must be implemented here, based on the calculated intersections. This method is a placeholder.
     * It must be adapted according to the intersection structure.
     * For simplicity, this example just returns a polygon based on the intersections.
     * This should be more complex
     *
     * @param intersections
     * @param resultPolygons
     */
    private void buildPolygons(List<Point> intersections, List<List<Point>> resultPolygons) {
        resultPolygons.add(new ArrayList<>(intersections));
    }
}
