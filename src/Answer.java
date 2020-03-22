import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer {
    Input in;
    private int[] colors;
    private List<Cluster> clusters;
    private int[] scoreByColor;
    long score;

    public Answer(Input in) {
        this.in = in;
        colors = new int[in.n];
        clusters = new ArrayList<>();
        for (int color = 0; color < in.k; color++) {
            clusters.add(new Cluster(color));
        }
    }

    int[] getColors() {
        return colors;
    }

    void initColor(int i, int c) {
        //should call exactly once
        colors[i] = c;
        clusters.get(c).add(in.points.get(i));
    }

    void swap(int i, int j) {
        int ci = colors[i];
        int cj = colors[j];
        score -= scoreByColor[ci];
        score -= scoreByColor[cj];
        Point pi = in.points.get(i);
        Point pj = in.points.get(j);
        clusters.get(ci).remove(pi);
        clusters.get(cj).remove(pj);
        colors[i] = cj;
        colors[j] = ci;
        clusters.get(ci).add(pj);
        clusters.get(cj).add(pi);
        scoreByColor[ci] = getColorScore(ci);
        scoreByColor[cj] = getColorScore(cj);
        score += scoreByColor[ci];
        score += scoreByColor[cj];
    }

    int getColorScore(int color) {
        return clusters.get(color).getScore();
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
