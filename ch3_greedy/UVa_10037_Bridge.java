package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10037_Bridge {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, f1, f2, s1, s2;
		int a[];
		
		while(t-- > 0) {
			n = sc.nextInt();
			StringBuilder res = new StringBuilder();
			a = new int[n];
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			Arrays.sort(a);
			
			if(n == 1) {
				out.println(a[0]);
				out.println(a[0]);
			} else if(n == 2) {
				out.println(a[1]);
				out.println(a[0] + " " + a[1]);
			} else {
				f1 = a[0]; f2 = a[1];
				s1 = a[n - 1];
				StringBuilder sb = new StringBuilder();
				if(n > 3)
					s2 = a[n - 2];
				else
					s2 = -1;
				int k = n - 3, time = 0;
				// s1 + s2 + 2 * f1 or s1 + 2 * f2 + f1 -> f1 + s2 or 2 * f2
				while(s2 != -1) {
					if(f1 + s2 < 2 * f2) {
						sb.append(f1 + " " + s1 + "\n");
						sb.append(f1 + "\n");
						sb.append(f1 + " " + s2 + "\n");
						sb.append(f1 + "\n");
						time += s1 + s2 + 2 * f1;
						if(k >= 2) s1 = a[k--]; else s1 = -1;
						if(k >= 2) s2 = a[k--]; else s2 = -1;
					}
					else {
						sb.append(f1 + " " + f2 + "\n");
						sb.append(f1 + "\n");
						sb.append(s2 + " " + s1 + "\n");
						sb.append(f2 + "\n");
						time += s1 + 2 * f2 + f1;
						if(k >= 2) s1 = a[k--]; else s1 = -1;
						if(k >= 2) s2 = a[k--]; else s2 = -1;
					}
				}
				
				if(s1 == -1) {
					sb.append(f1 + " " + f2 + "\n");
					time += f2;
				}
				else {
					sb.append(f1 + " " + f2 + "\n");
					sb.append(f1 + "\n");
					sb.append(f1 + " " + s1 + "\n");
					time += f2 + f1 + s1;
				}
				out.println(time);
				out.print(sb);
			}
			
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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