import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer {
    Input in;
    private int[] colors;
    private List<List<Point>> pointsByColor;
    private int[] scoreByColor;
    long score;

    public Answer(Input in) {
        this.in = in;
        colors = new int[in.n];
        pointsByColor = new ArrayList<>();
        for (int color = 0; color < in.k; color++) {
            pointsByColor.add(new ArrayList<>());
        }
    }

    int[] getColors() {
        return colors;
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    void initColor(int i, int c) {
        //should call exactly once
        colors[i] = c;
        pointsByColor.get(c).add(in.points.get(i));
    }

    void swap(int i, int j) {
        int ci = colors[i];
        int cj = colors[j];
        score -= scoreByColor[ci];
        score -= scoreByColor[cj];
        Point pi = in.points.get(i);
        Point pj = in.points.get(j);
        pointsByColor.get(ci).remove(pi);
        pointsByColor.get(cj).remove(pj);
        colors[i] = cj;
        colors[j] = ci;
        pointsByColor.get(ci).add(pj);
        pointsByColor.get(cj).add(pi);
        scoreByColor[ci] = getColorScore(ci);
        scoreByColor[cj] = getColorScore(cj);
        score += scoreByColor[ci];
        score += scoreByColor[cj];
    }

    int getColorScore(int color) {
        List<Point> pts = pointsByColor.get(color);
        int mn = 1_000_000_000;
        for (int i = 0; i < pts.size(); i++) {
            Point p = pts.get(i);
            for (int j = i + 1; j < pts.size(); j++) {
                Point other = pts.get(j);
                int d = dist(p, other);
                if (d < mn) {
                    mn = d;
                }
            }
        }
        return mn;
    }

    long getScore() {
        if (scoreByColor == null) {
            score = 0;
            scoreByColor = new int[in.k];
            Arrays.fill(scoreByColor, 1_000_000_000);
            for (int color = 0; color < in.k; color++) {
                scoreByColor[color] = getColorScore(color);
            }
            for (int x : scoreByColor) {
                score += x;
            }
        }
        return score;
    }
}
