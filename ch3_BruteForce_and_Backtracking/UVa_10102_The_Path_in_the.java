package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_10102_The_Path_in_the {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		ArrayList<Integer> onesRow = new ArrayList<Integer>();
		ArrayList<Integer> onesCol = new ArrayList<Integer>();
		ArrayList<Integer> threesRow = new ArrayList<Integer>();
		ArrayList<Integer> threesCol = new ArrayList<Integer>();
		while((s = sc.nextLine()) != null){
			int m = Integer.parseInt(s);
			onesRow.clear(); onesCol.clear(); threesRow.clear(); threesCol.clear();
			for (int i = 0; i < m; i++) {
				s = sc.nextLine();
				for (int j = 0; j < m; j++) {
					if(s.charAt(j) == '1'){
						onesRow.add(i); onesCol.add(j);
					}
					else if(s.charAt(j) == '3'){
						threesRow.add(i); threesCol.add(j);
					}
				}
			}
			
			int min, maxIndep = 0;
			for (int i = 0; i < onesRow.size(); i++) {
				min = Integer.MAX_VALUE;
				for (int j = 0; j < threesCol.size(); j++) {
					int steps = (Math.abs(onesRow.get(i) - threesRow.get(j)) + (Math.abs(onesCol.get(i) - threesCol.get(j))));
					min = Math.min(min, steps);
				}
				maxIndep = Math.max(maxIndep, min);
			}
			out.println(maxIndep);
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