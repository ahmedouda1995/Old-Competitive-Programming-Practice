package math;

public class Combinations {

	static final int N = 1000;
	static int C[][] = new int[N + 1][N + 1];
	
	public static void main(String[] args) {
		process();
		
		System.out.println(C[50][0]);
	}
	
	static void process()
	{
		for(int n = 0; n <= N; ++n)
			for(int k = 0; k <= n; ++k)
				C[n][k] = (k == 0)?1:(n == 0)?1:C[n - 1][k - 1] + C[n - 1][k];
	}
	
}
