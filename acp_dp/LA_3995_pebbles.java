package acp_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LA_3995_pebbles {

	static int N, M;
	static int grid[][] = new int[15][15];
	static ArrayList<Integer> subMaskList[];
	static int memo[][] = new int[15][1 << 15];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		StringTokenizer st;
		
		while(sc.ready()) {
			int i = 0;
			while((s = sc.nextLine()) != null && !s.isEmpty()) {
				st = new StringTokenizer(s);
				M = st.countTokens();
				for(int j = 0; j < M; ++j)
					grid[i][j] = Integer.parseInt(st.nextToken());
				i++;
			}
			N = i;
			subMaskList = new ArrayList[1 << N];
			for(i = 0; i < (1 << N); ++i) subMaskList[i] = new ArrayList<Integer>();
			preprocess();
			for(i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			
			out.println(solve(0, 0));
		}

		out.flush();
		out.close();
	}
	
	private static int solve(int idx, int mask) {
		if(idx == N) return 0;
		if(memo[idx][mask] != -1) return memo[idx][mask];
		
		int max = 0;
		
		for(int subMask : subMaskList[mask])
			max = Math.max(max, getScore(idx, subMask) + solve(idx + 1, subMask));
		return memo[idx][mask] = max;
	}

	private static int getScore(int idx, int subMask) {
		int sum = 0;
		for(int i = 0; i < N; ++i) {
			if(((subMask >> i) & 1) == 1)
				sum += grid[idx][i];
		}
		return sum;
	}

	private static void preprocess() {
		for(int mask = 0; mask < (1 << N); ++mask) {
			int subMaskCopy = ((~mask) & ((1 << N) - 1));
			int subMask = subMaskCopy;
			
			while(true) {
				boolean valid = true;
				for(int i = 0; i < N; ++i) {
					if(((subMask >> i) & 1) == 1) {
						if(notValid(subMask, mask, i)) { valid = false; break; }
					}
				}
				
				if(valid) subMaskList[mask].add(subMask);
				
				if(subMask == 0) break;
				subMask = ((subMask - 1) & subMaskCopy);
			}
		}
	}

	private static boolean notValid(int subMask, int mask, int i) {
		return (i > 0 && ((subMask >> (i - 1)) & 1) == 1)
			|| (i > 0 && ((mask >> (i - 1)) & 1) == 1)
			|| (i < N - 1 && ((mask >> (i + 1)) & 1) == 1);
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