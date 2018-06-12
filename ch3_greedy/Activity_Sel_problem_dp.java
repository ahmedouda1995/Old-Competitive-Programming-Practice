package ch3_greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Activity_Sel_problem_dp {

	public static void main(String[] args) {
		Pair [] a = {new Pair(1, 4), new Pair(3, 5), new Pair(0, 6), new Pair(5, 7),
				new Pair(3, 9), new Pair(5, 9), new Pair(6, 10), new Pair(8, 11),
				new Pair(8, 12), new Pair(2, 14), new Pair(12, 16)};
		Pair [] b = {new Pair(1, 14), new Pair(4, 7), new Pair(7, 9), new Pair(15, 17)};
		System.out.println(solve(a));
		System.out.println(solvedp(a));
		System.out.println(minClassRoom(a));
		System.out.println(minClassRoom(b));
	}
	
	private static int minClassRoom(Pair[] a) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int depth = 0;
		for (int i = 0; i < a.length; i++) {
			while(!q.isEmpty() && q.peek() <= a[i].s) q.poll();
			q.offer(a[i].f);
			depth = Math.max(depth, q.size());
		}
		return depth;
	}

	private static int solvedp(Pair[] a) {
		int [] dp = new int[a.length];
		int maxSoFar = 1;
		for(int i = 0; i < a.length; ++i){
			int max = 0;
			for(int j = i - 1; j >= 0; --j){
				if(a[j].f <= a[i].s){
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
			maxSoFar = Math.max(maxSoFar, dp[i]);
		}
		System.out.println(Arrays.toString(dp));
		return maxSoFar;
	}

	private static int solve(Pair[] a) {
		int fin = 0;
		int res = 0;
		for (int i = 0; i < a.length; i++) {
			if(a[i].s >= fin){
				res++;
				fin = a[i].f;
			}
		}
		return res;
	}

	static class Pair {
		int s, f;
		public Pair(int s, int f) {
			this.s = s;
			this.f = f;
		}
	}
}
