package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_10026_Shoemakers_Problem {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		Tuple [] a;
		
		while(t-- > 0){
			sc.nextLine();
			int n = sc.nextInt();
			a = new Tuple[n];
			for(int i = 0;i<n;i++)
				a[i] = new Tuple(sc.nextInt(), sc.nextInt(), i + 1);
			
			Arrays.sort(a);
			for(int i=0;i<a.length;i++){
				if(i == a.length-1)
					out.print(a[i].index);
				else out.print(a[i].index + " ");
			}
			out.println();
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static class Tuple implements Comparable<Tuple>{
		int time, fine, index;
		
		public Tuple(int time, int fine, int index) {
			this.time = time;
			this.fine = fine;
			this.index = index;
		}

		@Override
		public int compareTo(Tuple o) {
			if(Double.compare((this.time * 1.0) / this.fine, (1.0 * o.time) / o.fine) != 0)
				return Double.compare((this.time * 1.0) / this.fine, (1.0 * o.time) / o.fine);
			else return Integer.compare(this.index, o.index);
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
