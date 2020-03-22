public class Solver {

    static Answer trivial(Input in) {
        Answer ans = new Answer(in);
        int cnt = in.n / in.k;
        for (int color = 0; color < in.k; color++) {
            for (int q = 0; q < cnt; q++) {
                ans.setColor(color * cnt + q, color);
            }
        }
        return ans;
    }

    static void climb(Answer ans) {
        long start = System.currentTimeMillis();
        Input in = ans.in;
        long best = ans.getScore();
        boolean moar = true;
        long limit = 1000;
        int cnt = 0;
        while (moar) {
            cnt++;
            moar = false;
            for (int i = 0; i < in.n; i++) {
                long tm = System.currentTimeMillis() - start;
                if (tm > limit) {
                    return;
                }
                for (int j = i + 1; j < in.n; j++) {
                    ans.swap(i, j);
                    long curr = ans.getScore();
                    if (curr > best) {
                        System.out.println(in.testName + " climb " + cnt + ": " + best + " -> " + curr);
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
        climb(ans);
        return ans;
    }
}
