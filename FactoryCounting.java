import java.util.Arrays;

public class FactoryCounting {
	
	static int adj[];
	static int k;
	
	public static long count(int n, int m, String[] county)
	{
		k = county.length;
		adj = new int[k];
		for(int i = 0; i < k; ++i)
		{
			int mask = 0;
			for(int j = 0; j < k; ++j)
			{
				if(county[i].charAt(j) == 'Y')
					mask |= (1 << j);
			}
			adj[i] = mask;
		}
		
		return solve(0, (1 << (k + 1)) - 1, 0, n, m);
	}

	private static long solve(int maskTaken, int maskConnected, int idx, int n, int m) {
		if(n > 0)
		{
			long res = 0;
			for(int i = idx; i < k; ++i)
				res += solve(maskTaken | (1 << i), maskConnected & adj[i], i + 1, n - 1, m);
			return res;
		}
		else
		{
			int c = 0;
			for(int i = 0; i < k; ++i)
			{
				if((maskTaken & (1 << i)) == 0 && (maskConnected & (1 << i)) > 0)
					c++;
			}
			
			return nCk(c, m);
		}
	}
	
	static int nCk(int N, int K)		// O(K)
	{
		if(K > N)
			return 0;
		int res = 1;
		for(int i = 1; i <= K; ++i)
			res = (N - K + i) * res / i;
		return res;
	}
}
