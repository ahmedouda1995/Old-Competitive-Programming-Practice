package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// SUPER IMPORTANT OPTIMIZATION IN LOOP OF int j = pos + max, k = i + max & LOWER_BOUND IMPLEMENTATION

public class UVa_12192_Grapevine {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n, m;
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0){
			int [][] a = new int[n][m];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					a[i][j] = sc.nextInt();
				
			int q = sc.nextInt();
			while(q-- > 0){
				int l = sc.nextInt(), h = sc.nextInt();
				int max = 0;
				for(int i = 0; i < n; ++i) {
					int pos = lower_bound(a[i], l);
					if(pos < 0) continue;
					int side = max;
					for (int j = pos + max, k = i + max; j < a[i].length && k < a.length; j++, k++) {
						if(a[k][j] <= h)
							side++;
						else
							break;
					}
					max = Math.max(max, side);
				}
				out.println(max);
			}
			out.println("-");
		}
		
		out.flush();
		out.close();
	}
	
	private static int lower_bound(int [] a, int k){
		int l = 0, h = a.length - 1, mid = (l + h) / 2;
		int result = -1;
		while(l <= h){
			if(k <= a[mid]){
				result = mid;
				h = mid - 1;
			}
			else 
				l = mid + 1;
			mid = (l + h) / 2;
		}
		return result;
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