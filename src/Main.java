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

    static public BufferedReader currentAnswer = null;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            Solver.burn_limit = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            Solver.climb_limit = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            currentAnswer = new BufferedReader(new InputStreamReader(new FileInputStream(args[2])));
        }
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        writer = new PrintWriter("out.txt");
        banana();
        reader.close();
        writer.close();
    }

    static Input readInput(String testName) throws IOException {
        int n = nextInt();
        int k = nextInt();
        Input in = new Input(testName, n, k);
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            Point p = new Point(x, y);
            in.points.add(p);
        }
        return in;
    }

    static Answer readCurrentAnswer(Input in) throws IOException {
        String line = currentAnswer.readLine();
        String[] strings = line.split(" ");
        String colors = strings[2];
        Answer ans = new Answer(in);
        for (int i = 0; i < in.n; i++) {
            int color = colors.charAt(i) - 'A';
            ans.initColor(i, color);
        }
        return ans;
    }

    static void printAnswer(Answer ans) {
        for (int color : ans.getColors()) {
            char c = (char) ('A' + color);
            writer.print(c);
        }
        writer.println();
    }

    static long totalScore = 0;

    static void processTest(String testName) throws IOException {
        Input in = readInput(testName);
        //Renderer.render(testName, in.points);
        Answer ans;
        if (currentAnswer == null) {
            ans = Solver.solve(in);
        } else {
            ans = readCurrentAnswer(in);
            Solver.climb(ans);
        }
        long score = ans.getScore();
        System.out.println(testName + " score: " + score);
        totalScore += score;
        printAnswer(ans);
    }

    static void banana() throws IOException {
        long start = System.currentTimeMillis();
        int t = nextInt();
        //t = 1;
        for (int tst = 1; tst <= t; tst++) {
            writer.print("Case #" + tst + ": ");
            processTest("test" + tst);
        }
        System.out.println("Total score: " + totalScore / t);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
    }
}
