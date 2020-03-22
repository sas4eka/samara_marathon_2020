import java.util.ArrayList;
import java.util.List;

public class Cluster {
    int color;
    List<Point> points;
    int score = -1;

    public Cluster(int color) {
        this.color = color;
        this.points = new ArrayList<>();
    }

    void add(Point p) {
        for (Point other : points) {
            int d = dist(p, other);
            if (d < score) {
                score = d;
            }
        }
        points.add(p);
    }

    void remove(Point p) {
        points.remove(p);
        boolean found = false;
        for (Point other : points) {
            int d = dist(p, other);
            if (d == score) {
                found = true;
                break;
            }
        }
        if (found) {
            score = -1;
        }
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    int getScore() {
        if (score == -1) {
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
            score = mn;
        }
        return score;
    }
}
