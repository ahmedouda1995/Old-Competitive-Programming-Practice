package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_10815_Andys_First_Dictionary {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeSet<String> set = new TreeSet<String>();
		String s;
		while((s = br.readLine()) != null){
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); ++i) {
				if(Character.isLetter(s.charAt(i)))
					sb.append(s.charAt(i));
				else if(sb.length() > 0){
					set.add(new String(sb).toLowerCase());
					sb = new StringBuilder();
				}
			}
			// check after loop extremely important wrong more than once in different problems
			if(sb.length() > 0){
				set.add(new String(sb).toLowerCase());
			}
		}
		
		for(String a : set)
			out.println(a);
		
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