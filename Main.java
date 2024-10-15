import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testIntersectingSegments();
        testParallelSegments();
        testTouchingAtEdge();
        testNoIntersection();
    }

    private static void testNoIntersection() {
        var polygon = List.of(new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4));
        final var result = new GlobantPolygonCut(polygon).cutPolygon(new Point(5, 5), new Point(6, 6));
        System.out.printf("Must return the original polygon %b",  1 == result.size());
        System.out.println();
        assert polygon == result.get(0);
        System.out.printf("It must be the same polygon %b",  polygon == result.get(0));
        System.out.println();
    }

    private static void testTouchingAtEdge() {
        var polygon = List.of(new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4));
        final var result =  new GlobantPolygonCut(polygon).cutPolygon(new Point(4, -1), new Point(4, 5));
        System.out.printf("There must be 2 resulting polygons %b",  2 == result.size());
        assert 2 == result.size();
        System.out.println();
    }

    private static void testParallelSegments() {
        var polygon = List.of(new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4));
        final var result = new GlobantPolygonCut(polygon).cutPolygon(new Point(2, 1), new Point(2, 1));
        System.out.printf("Must return the original polygon %b",  2 == result.size());
        System.out.println();
        assert 1 == result.size();
        System.out.printf("It must be the same polygon %b",  polygon == result.get(0));
        System.out.println();
        assert polygon == result.get(0);
    }

    private static void testIntersectingSegments() {
        var polygon = Arrays.asList(new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4));
        final var result = new GlobantPolygonCut(polygon).cutPolygon(new Point(2, -1), new Point(2, 5));
        assert 2 == result.size();
    }
}
