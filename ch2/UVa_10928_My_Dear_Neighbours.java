package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_10928_My_Dear_Neighbours {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		while(t-- > 0){
			int p = sc.nextInt(), min = Integer.MAX_VALUE;
			int [] a = new int[p];
			for(int i = 0;i < p;++i){
				String [] tmp = (sc.nextLine()).split(" ");
				a[i] = tmp.length;
				min = Math.min(min, a[i]);
			}
			String res = "";
			for(int i = 0;i < p;++i){
				if(a[i] == min)
					res += ((i + 1) + " ");
			}
			out.println(res.trim());
			if(t != 0)
				sc.nextLine();
		}
		
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