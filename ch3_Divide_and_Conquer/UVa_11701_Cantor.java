package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;


// precision error - very good & important problem

public class UVa_11701_Cantor {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));

		String s;
		while(!(s = sc.nextLine()).equals("END")){
			boolean printed = false;
			TreeSet<Integer> set = new TreeSet<Integer>();
			double d = Double.parseDouble(s);
			int n = (int) (d * 1000000);
			if(d == 0 || d == 1)
				out.println("MEMBER");
			else {
				while(true){
					set.add(n);
					if(((n * 3) / 1000000) == 1){
						out.println("NON-MEMBER");
						printed = true;
						break;
					}
					n = (n * 3) % 1000000;
					if(set.contains(n)){
						out.println("MEMBER");
						printed = true;
						break;
					}
				}
				if(!printed)
					out.println("MEMBER");
			}
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