package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class All_Permutations {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		while(t-- > 0){
			char chars [] = sc.next().toCharArray();
			ArrayList<String> arr = new ArrayList<String>();
			permute(0, chars, out, arr);
			Collections.sort(arr);
			for (int i = 0; i < arr.size(); i++) {
				out.print((i != arr.size() - 1)?(arr.get(i) + " "):arr.get(i));
			}
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void permute(int i, char[] chars, PrintWriter out, ArrayList<String> arr) {
		if(i == chars.length)
			arr.add(new String(chars));
		else {
			for(int j = i; j < chars.length; ++j){
				swap(i, j, chars);
				permute(i + 1, chars, out, arr);
				swap(i, j, chars);
			}
		}
	}

	private static void swap(int i, int j, char[] chars) {
		char tmp = chars[i]; chars[i] = chars[j]; chars[j] = tmp;
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
