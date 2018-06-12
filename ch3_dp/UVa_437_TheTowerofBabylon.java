package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_437_TheTowerofBabylon {

	static int dp[] = new int[90];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, cases = 1;
		Triple t[] = new Triple[90];
		while((n = sc.nextInt()) != 0) {
			int x, y, z, a, b;
			int k = 0;
			for(int i = 0; i < n; ++i) {
				x = sc.nextInt(); y = sc.nextInt(); z = sc.nextInt();
				a = (y > z)?y:z;
				b = (y > z)?z:y;
				t[k++] = new Triple(x, a, b);
				a = (x > z)?x:z;
				b = (x > z)?z:x;
				t[k++] = new Triple(y, a, b);
				a = (x > y)?x:y;
				b = (x > y)?y:x;
				t[k++] = new Triple(z, a, b);
			}
			
			Arrays.sort(t, 0, 3 * n);
			System.out.println(Arrays.toString(t));
			
			out.printf("Case %d: maximum height = %d\n", cases++, lis(t, 3 * n));
		}
		
		out.flush();
		out.close();
	}
	
	private static int lis(Triple[] t, int n) {
		int max = 0;
		dp[0] = t[0].h;
		for(int i = 1; i < n; ++i) {
			int maxSoFar = 0;
			for(int j = i - 1; j >= 0; --j) {
				if(check(t[i], t[j])) {
					maxSoFar = Math.max(maxSoFar, dp[j]);
				}
			}
			dp[i] = maxSoFar + t[i].h;
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	private static boolean check(Triple t1, Triple t2) {
		int l1 = t1.l, l2 = t2.l, w1 = t1.w, w2 = t2.w;
		
		if((l1 < l2 && w1 < w2) || (l1 < w2 && w1 < l2))
			return true;
		return false;
	}

	static class Triple implements Comparable<Triple>{
		int h, l, w;
		
		public Triple(int h, int l, int w) {
			this.h = h;
			this.l = l;
			this.w = w;
		}

		@Override
		public int compareTo(Triple t) {
			if(Integer.compare(t.l, this.l) == 0)
				return Integer.compare(t.w, this.w);
			return Integer.compare(t.l, this.l);
		}
		
		@Override
		public String toString() {
			return "(" + h + ", " + l + ", " + w + ")";
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