import java.awt.*;

@FunctionalInterface
public interface PositionAlongSegments<T> {
    T getPosition(double denominator, Point p1, Point p2, Point p3);
}
