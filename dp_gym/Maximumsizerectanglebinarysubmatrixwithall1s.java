package dp_gym;

import java.util.Stack;

public class Maximumsizerectanglebinarysubmatrixwithall1s {

	static int N = 4, M = 4;
	static int grid[][] = {{1, 1, 1, 0},
		                   {1, 1, 1, 0},
		                   {1, 1, 1, 0},
		                   {1, 1, 1, 0},};
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		int max = hist(grid[0]);
		
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				if(grid[i][j] == 1)
					grid[i][j] += grid[i - 1][j];
			}
			max = Math.max(max, hist(grid[i]));
		}
		return max;
	}

	private static int hist(int[] a) {
		Stack<Integer> st = new Stack<Integer>();
		
		int i = 0;
		int max = 0;
		
		while(i < M) {
			if(st.isEmpty() || a[st.peek()] <= a[i])
				st.push(i++);
			else {
				while(!st.isEmpty() && a[st.peek()] > a[i]) {
					int top = st.pop();
					max = Math.max(max, a[top] * ((st.isEmpty())?i:i - st.peek() - 1));
				}
			}
		}
		
		while(!st.isEmpty()) {
			int top = st.pop();
			max = Math.max(max, a[top] * ((st.isEmpty())?i:i - st.peek() - 1));
		}
		return max;
	}
}
