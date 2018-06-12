package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11550_Demanding_Dilemma {

	// each column has two vertices only and no multiple edges
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		label: while(t-- > 0){
			int n = sc.nextInt(), m = sc.nextInt();
			Pair [] a = new Pair[m];
			for (int i = 0; i < a.length; i++) {
				a[i] = new Pair(-1, -1);
			}
			boolean wrong = false;
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					int k = sc.nextInt();
					if(k == 1){
						if(a[j].x == -1)
							a[j].x = i;
						else if(a[j].y == -1)
							a[j].y = i;
						else
							wrong = true;
					}
				}
			}
			if(wrong){
				out.println("No");
				continue;
			}
			Arrays.sort(a);
			for (int i = 0; i < a.length - 1; i++) {
				if((a[i].x == -1) || (a[i].x != -1 && a[i].y == -1) || (a[i].x == a[i + 1].x && a[i].y == a[i + 1].y)){
					out.println("No");
					continue label;
				}
			}
			int i = a.length - 1;
			if((i >= 0) && ((a[i].x == -1) || (a[i].x != -1 && a[i].y == -1)))
				out.println("No");
			else
				out.println("Yes");
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.x == p.x)
				return this.y - p.y;
			else
				return this.x - p.x;
		}
		public String toString(){
			return x + " " + y;
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