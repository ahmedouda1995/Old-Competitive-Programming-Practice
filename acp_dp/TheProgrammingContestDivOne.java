package acp_dp;

import java.util.Arrays;

public class TheProgrammingContestDivOne {

	static int N, time;
	static Triple [] arr;
	static int memo[][];
	
	static class Triple implements Comparable<Triple> {
		long max, ppm, reqT;
		
		public Triple(int m, int p, int r) {
			max = m; ppm = p; reqT = r;
		}

		@Override
		public int compareTo(Triple t) {
			return Long.compare(reqT * t.ppm, t.reqT * ppm);
		}
	}
	
	public static int find(int T, int[] maxPoints, int[] pointsPerMinute, int[] requiredTime) {
		N = maxPoints.length; time = T;
		arr = new Triple[N];
		for(int i = 0; i < N; ++i) arr[i] = new Triple(maxPoints[i], pointsPerMinute[i], requiredTime[i]);
		Arrays.sort(arr);
		memo = new int[N][T + 1];
		for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
		return solve(0, 0);
	}

	private static int solve(int idx, int t) {
		if(idx == N) return 0;
		
		if(memo[idx][t] != -1) return memo[idx][t];
		
		int take = 0;
		
		long tmp = arr[idx].max - (t + arr[idx].reqT) * arr[idx].ppm;
		if(t + arr[idx].reqT <= time && tmp > 0)
			take = (int)tmp + solve(idx + 1, (int) (t + arr[idx].reqT));
		return memo[idx][t] =
				Math.max(take, solve(idx + 1, t));
	}
}