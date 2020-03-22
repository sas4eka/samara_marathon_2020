import java.util.ArrayList;
import java.util.List;

public class Input {
    String testName;
    int n;
    int k;
    List<Point> points;

    public Input(String testName, int n, int k) {
        this.testName = testName;
        this.n = n;
        this.k = k;
        this.points = new ArrayList<>();
    }
}
