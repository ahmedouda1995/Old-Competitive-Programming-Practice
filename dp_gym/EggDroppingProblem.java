package dp_gym;

import java.util.Arrays;

public class EggDroppingProblem {

	static int memo[][][];
	static int memo2[][];
	static final int INF = (int) 1e9;
	
	public static void main(String[] args) {
		memo = new int[200][200][200];
		memo2 = new int[200][200];
		
		for(int i = 0; i < 200; ++i) Arrays.fill(memo2[i], -1);
		
		for(int i = 0; i < 200; ++i)
			for(int j = 0; j < 200; ++j)
				Arrays.fill(memo[i][j], -1);
		System.out.println(solve(199, 1, 100));
		System.out.println(solve2(2, 100));
	}

	private static int solve2(int eggs, int floors) {
		if(floors == 0) return 0;
		if(eggs == 0) return -1;
		if(floors == 1) return 1;
		
		if(memo2[floors][eggs] != -1)
			return memo2[floors][eggs];
		
		int min = INF;
		
		for(int i = 1; i <= floors; ++i) {
			int a = solve2(eggs - 1, i - 1);
			int b = solve2(eggs, floors - i);
			
			if(a != -1 && b != -1) {
				min = Math.min(min, 1 + Math.max(a, b));
			}
		}
		return memo2[floors][eggs] = min;
	}

	private static int solve(int eggs, int floorStart, int floorEnd) {
		if(floorStart > floorEnd) return 0;
		if(eggs == 0) return -1;
		if(floorStart == floorEnd) return 1;
		
		if(memo[floorStart][floorEnd][eggs] != -1)
			return memo[floorStart][floorEnd][eggs];
		
		int min = INF;
		
		for(int i = floorStart; i <= floorEnd; ++i) {
			int a = solve(eggs - 1, floorStart, i - 1);
			int b = solve(eggs, i + 1, floorEnd);
			
			if(a != -1 && b != -1) {
				min = Math.min(min, 1 + Math.max(a, b));
			}
		}
		return memo[floorStart][floorEnd][eggs] = min;
	}
}
