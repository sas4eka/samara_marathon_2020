public class Solver {
    static Answer solve(Input in) {
        Answer ans = new Answer(in);

        int cnt = in.n / in.k;
        for (int color = 0; color < in.k; color++) {
            for (int q = 0; q < cnt; q++) {
                ans.colors[color * cnt + q] = color;
            }
        }

        return ans;
    }
}
