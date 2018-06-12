package acp_dp;

import java.util.Arrays;

public class MarblesRegroupingHard {

	// nice problem view bit mask for colors fits in memory but as boxes used for sure TLE
	
	static final int INF = (int) 1e9;
	static int N, M;
	static int a[][];
	static int b[];
	static int memo[][] = new int[50][1 << 14];
	
	public static int minMoves(String[] boxes) {
		N = boxes.length; M = boxes[0].split(" ").length;
		a = new int[N][M]; b = new int[N];
		String s[];
		for(int i = 0; i < N; ++i) {
			Arrays.fill(memo[i], -1);
			s = boxes[i].split(" ");
			for(int j = 0; j < M; ++j) {
				a[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i = 0; i < M; ++i)
			for(int j = 0; j < N; ++j)
				b[i] += a[j][i];
		return solve(0, 0);
	}

	private static int solve(int idx, int mask) { // idx up to N
		if(idx == N) {
			if(mask == (1 << M) - 1)
				return 0;
			else
				return INF;
		}
		if(memo[idx][mask] != -1) return memo[idx][mask];
		int min = solve(idx + 1, mask);
		
		for(int i = 0; i < M; ++i) {
			if(((mask >> i) & 1) == 0)
				min = Math.min(min, (b[i] - a[idx][i]) + solve(idx + 1, mask | (1 << i)));
		}
		
		return memo[idx][mask] = min;
	}
}
