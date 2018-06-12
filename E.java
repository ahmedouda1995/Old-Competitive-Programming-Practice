import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class E {
	
	static Point[] black;
	static int memo[][][];
	static int H,W, n;
	
	static int[] fac, facI;
	static final int MOD = (int) 1e9 + 7;
	static int pow(int x, int p) {
		if(p == 0)
			return 1;
		int ans = 1;
		if(p % 2 == 1)
			ans = x;
		int c = pow(x, p / 2);
		ans = (int) ((((1l * ans * c) % MOD) * c) % MOD);
		return ans;
	}
	
	static int nCr(int n, int r) {
		return (int) ((((1l * fac[n] * facI[r]) % MOD) * facI[n - r]) % MOD);
	}
	
	static int solve(int count, int cur, int last) {
		if(cur == n + 1) {
			int x = H - black[last].x - 1;
			int y = W - black[last].y - 1;
			if(count == 0)
				return nCr(x + y, x);
			else
				return MOD-nCr(x + y, x);
		}
		
		if(memo[count][cur][last] != -1)
			return memo[count][cur][last];
		
		int leave = solve(count, cur + 1, last);
		
		if(black[cur].x >= black[last].x && black[cur].y >= black[last].y) {
			int x = black[cur].x - black[last].x;
			int y = black[cur].y - black[last].y;
			
			int take = (int) ((1l * nCr(x + y, x) * solve(count ^ 1, cur + 1, cur)) % MOD);
			leave = (int) ((1l * leave + take) % MOD);
		}
		
		return memo[count][cur][last] = leave;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		
		out.flush();
		out.close();
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}