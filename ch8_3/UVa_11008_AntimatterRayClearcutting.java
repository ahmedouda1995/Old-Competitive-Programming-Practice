package ch8_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11008_AntimatterRayClearcutting {

	static int N, M, INF = (int) 1e9;
	static int [] posX = new int[16];
	static int [] posY = new int[16];
	static int [][] missedPoints;
	static int memo[] = new int[1 << 16];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			N = sc.nextInt(); M = sc.nextInt();
			
			for(int i = 0; i < N; ++i) { posX[i] = sc.nextInt(); posY[i] = sc.nextInt(); }
			preprocess();
			Arrays.fill(memo, -1);
			
			out.printf("Case #%d:\n", cases++);
			out.println(solve((1 << N) - 1));
			if(t > 0) out.println();
		}

		out.flush();
		out.close();
	}
	
	static int countBits(int mask) {
		int res = 0;
		while(mask > 0) { if(mask % 2 == 1) res++; mask /= 2; }
		return res;
	}
	
	private static int solve(int mask) {
		if(N - countBits(mask) >= M) return 0;
		if(memo[mask] != -1) return memo[mask];
		
		int min = INF;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < N; ++i) if(((mask >> i) & 1) == 1) arr.add(i);
		
		for(int i = 0; i < arr.size(); ++i) {
			for(int j = i + 1; j < arr.size(); ++j) {
				min = Math.min(min, 1 + solve(mask & missedPoints[arr.get(i)][arr.get(j)]));
			}
		}
		if(min == INF) min = 1;
		return memo[mask] = min;
	}

	private static void preprocess() {
		missedPoints = new int[N][N];
		
		for(int i = 0; i < N; ++i)
			for(int j = i + 1; j < N; ++j) {
				int mask = (1 << N) - 1;
				
				mask = mask & ~(1 << i);
				mask = mask & ~(1 << j);
				
				for(int k = 0; k < N; ++k) {
					if(k != i && k != j) {
						int x1 = posX[i] - posX[j];
						int y1 = posY[i] - posY[j];
						int x2 = posX[i] - posX[k];
						int y2 = posY[i] - posY[k];
						
						if(x1 * y2 - x2 * y1 == 0)
							mask = mask & ~(1 << k);
					}
				}
				missedPoints[i][j] = mask;
			}
		
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