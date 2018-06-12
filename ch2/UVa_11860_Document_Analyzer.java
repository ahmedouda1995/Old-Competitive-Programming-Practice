package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_11860_Document_Analyzer {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		ArrayList<String> a = new ArrayList<String>();
		TreeSet<String> set = new TreeSet<String>();
		int cases = 1;
		while(t-- > 0){
			String s;
			a.clear();
			set.clear();
			while(!(s = sc.nextLine()).equals("END")){
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < s.length(); ++i) {
					if(Character.isLetter(s.charAt(i)))
						sb.append(s.charAt(i));
					else if(sb.length() > 0){
						a.add(sb.toString());
						set.add(sb.toString());
						sb = new StringBuilder();
					}
				}
				if(sb.length() > 0){
					a.add(sb.toString());
					set.add(sb.toString());
					sb = new StringBuilder();
				}
			}
			TreeSet<String> copy = new TreeSet<String>();
			int st = 0, e = a.size() - 1;
			for (int i = 0; i < a.size(); i++) {
				int start = i, end = i;
				for (int j = i; j < a.size(); j++) {
					copy.add(a.get(j));
					if(copy.size() == set.size()){
						end = j;
						break;
					}
				}
				if(copy.size() == set.size() && (end - start) < (e - st)){
					st = start; e = end;
					if((e - st + 1) == set.size())
						break;
				}
				copy.clear();
			}
			out.println("Document " + cases + ": " + (st + 1) + " " + (e + 1));
			cases++;
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