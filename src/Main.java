import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tokenizer;

    static String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        writer = new PrintWriter("out.txt");
        banana();
        reader.close();
        writer.close();
    }

    static Input readInput() throws IOException {
        int n = nextInt();
        int k = nextInt();
        Input in = new Input(n, k);
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            Point p = new Point(i, x, y);
            in.points.add(p);
        }
        return in;
    }

    static void printAnswer(Answer ans) {
        for (int color : ans.colors) {
            char c = (char) ('A' + color);
            writer.print(c);
        }
        writer.println();
    }

    static void processTest(String testName) throws IOException {
        Input in = readInput();
        Renderer.render(testName, in.points);
        Answer ans = Solver.solve(in);
        printAnswer(ans);
    }

    static void banana() throws IOException {
        int t = nextInt();
        for (int tst = 1; tst <= t; tst++) {
            writer.print("Case #" + tst + ": ");
            processTest("test" + tst);
        }
    }
}
