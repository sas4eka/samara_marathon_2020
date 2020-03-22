public class Solver {
    static public int burn_limit = 100;
    static public int climb_limit = 100;

    static Answer trivial(Input in) {
        Answer ans = new Answer(in);
        int cnt = in.n / in.k;
        for (int color = 0; color < in.k; color++) {
            for (int q = 0; q < cnt; q++) {
                ans.initColor(color * cnt + q, color);
            }
        }
        return ans;
    }

    static void burn(Answer ans) {
        long start = System.currentTimeMillis();
        Input in = ans.in;
        int cnt = 0;
        long tm = System.currentTimeMillis() - start;
        while (tm < burn_limit) {
            cnt++;
            tm = System.currentTimeMillis() - start;
            double temp = 1.0 * tm / burn_limit;
            int i = (int) (in.n * Math.random());
            int j = (int) (in.n * Math.random());
            if (ans.getColors()[i] != ans.getColors()[j]) {
                long prev = ans.getScore();
                ans.swap(i, j);
                long curr = ans.getScore();
                if (curr > prev) {
                    System.out.println(in.testName + " burn " + cnt + ": " + prev + " -> " + curr
                            + " at " + (System.currentTimeMillis() - start) + " ms");
                } else {
                    double p = Math.exp((curr - prev) / temp);
                    if (Math.random() < p) {
                        //keep
                    } else {
                        ans.swap(i, j);
                    }
                }
            }
        }
    }

    static void climb(Answer ans) {
        long start = System.currentTimeMillis();
        Input in = ans.in;
        long best = ans.getScore();
        boolean moar = true;
        int cnt = 0;
        while (moar) {
            cnt++;
            moar = false;
            for (int i = 0; i < in.n; i++) {
                long tm = System.currentTimeMillis() - start;
                if (tm > climb_limit) {
                    return;
                }
                for (int j = i + 1; j < in.n; j++) {
                    ans.swap(i, j);
                    long curr = ans.getScore();
                    if (curr > best) {
                        System.out.println(in.testName + " climb " + cnt + ": " + best + " -> " + curr
                                + " at " + (System.currentTimeMillis() - start) + " ms");
                        best = curr;
                        moar = true;
                    } else {
                        ans.swap(i, j);
                    }
                }
            }
        }
    }

    static Answer solve(Input in) {
        Answer ans = trivial(in);
        if (burn_limit > 0) burn(ans);
        if (climb_limit > 0) climb(ans);
        return ans;
    }
}
