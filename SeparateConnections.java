import java.util.Arrays;


public class SeparateConnections {

	static int N;
	static int memo[][];
	static String [] chk;
	
	public static int howMany(String[] mat)
	{
		chk = mat;
		N = mat.length;
		memo = new int[N][(1 << N) + 1];
		for(int i = 0; i < N; ++i)
			Arrays.fill(memo[i], -1);
		return solve(0, 0);
	}

	private static int solve(int idx, int mask) {
		if(idx == N) return 0;
		if(memo[idx][mask] != -1) return memo[idx][mask];
		
		int ret = solve(idx + 1, mask);
		
		if((mask & (1 << idx)) == 0)
		{
			for(int i = 0; i < N; ++i)
			{
				if(i != idx && (mask & (1 << i)) == 0 && chk[idx].charAt(i) == 'Y')
					ret = Math.max(ret, 2 + solve(idx + 1, ((mask | (1 << i)) | (1 << idx))));
			}
		}
		return memo[idx][mask] = ret;
	}
}
