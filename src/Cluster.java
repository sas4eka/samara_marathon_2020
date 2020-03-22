import java.util.ArrayList;
import java.util.List;

public class Cluster {
    int color;
    List<Point> points;

    public Cluster(int color) {
        this.color = color;
        this.points = new ArrayList<>();
    }

    void add(Point p) {
        points.add(p);
    }

    void remove(Point p) {
        points.remove(p);
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    int getScore() {
        int mn = 1_000_000_000;
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point other = points.get(j);
                int d = dist(p, other);
                if (d < mn) {
                    mn = d;
                }
            }
        }
        return mn;
    }
}
