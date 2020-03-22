import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer {
    Input in;
    private int[] colors;

    public Answer(Input in) {
        this.in = in;
        colors = new int[in.n];
    }

    int[] getColors() {
        return colors;
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    void setColor(int i, int c) {
        colors[i] = c;
    }

    void swap(int i, int j) {
        int t = colors[i];
        colors[i] = colors[j];
        colors[j] = t;
    }

    long getScore() {
        List<List<Point>> pointsByType = new ArrayList<>();
        for (int color = 0; color < in.k; color++) {
            pointsByType.add(new ArrayList<>());
        }
        int[] mind = new int[in.k];
        Arrays.fill(mind, 1_000_000_000);
        for (int i = 0; i < in.n; i++) {
            Point p = in.points.get(i);
            int color = colors[i];
            List<Point> existing = pointsByType.get(color);
            for (Point other : existing) {
                int d = dist(p, other);
                if (d < mind[color]) {
                    mind[color] = d;
                }
            }
            existing.add(p);
        }
        long score = 0;
        for (int x : mind) {
            score += x;
        }
        return score;
    }
}
