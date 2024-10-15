import java.awt.*;
import java.util.function.BiPredicate;


public class PolygonOperator {
    private PolygonOperator() {
    }

    static double findIntersection(Point p1, Point p2, Point p3, Point p4) {
        return (p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x);
    }

    /**
     * true if the intercept is within the segments
     */
    public static final BiPredicate<Double, Double> interceptIsWithinTheSegments = (uA, uB) -> uA < 0 || uA > 1 || uB < 0 || uB > 1;

    /**
     * uA calculates the position along the first segment (p1 to p2) where the intersection occurs, in terms of the distance relative to the segment length.
     * If uA is between 0 and 1, it means that the intersection is within the segment.
     */
    public static final PositionAlongSegments<Double> calculateUaSegment = (d, p1, p2, p3) -> ((p1.x - p2.x) * (p2.y - p3.y) - (p1.y - p2.y) * (p2.x - p3.x)) / d;

    /**
     * uB calculates the position along the second segment (p3 to p4) where the intersection occurs.
     * As with uA, if uB is between 0 and 1, it means the intersection is within the segment.
     */
    public static final PositionAlongSegments<Double> calculateUbSegment = (d, p1, p2, p3) -> ((p1.x - p3.x) * (p1.y - p2.y) - (p1.y - p3.y) * (p1.x - p2.x)) / d;
}
