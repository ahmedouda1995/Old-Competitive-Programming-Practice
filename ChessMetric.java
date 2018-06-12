import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class ChessMetric {

	static int fx, fy;
	static int N, M;
	static long memo[][][];
	static int dr[] = {1, -1, 0, 0, 1, 2, 1, 2, -1, -2, -1, -2, 1, -1, 1, -1};
	static int dc[] = {0 , 0, -1, 1, 2, 1, -2, -1, 2, 1, -2, -1, 1, -1, -1, 1};
	
//	public static void main(String[] args) {
//		System.out.println(howMany(3, new int[]{0, 0}, new int[]{0,0}, 2));
//	}
	
	public static long howMany(int size, int[] start, int[] end, int numMoves)
	{
		N = size;
		M = numMoves;
		fx = end[0]; fy = end[1];
		memo = new long[N][N][M];
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				Arrays.fill(memo[i][j], -1);
		return solve(start[0], start[1], 0);
	}
	
	private static long solve(int x, int y, int m) {
		if(m > M) return 0;
		if(m == M)
		{
			if(x == fx && y == fy)
				return 1;
			return 0;
		}
		if(memo[x][y][m] != -1) return memo[x][y][m];
		
		long ways = 0;
		int a, b;
		
		for(int i = 0; i < 16; ++i)
		{
			a = x + dr[i];
			b = y + dc[i];
			if(!valid(a, b)) continue;
			ways += solve(a, b, m + 1);
		}
		
		return memo[x][y][m] = ways;
	}

	static boolean valid(int x, int y)
	{
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
