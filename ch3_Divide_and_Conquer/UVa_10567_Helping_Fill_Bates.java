package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa_10567_Helping_Fill_Bates {
	
	static ArrayList<Integer> [] lower = new ArrayList[26];
	static ArrayList<Integer> [] upper = new ArrayList[26];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s = sc.nextLine();
		
		for (int i = 0; i < 26; i++) lower[i] = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) upper[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= 'a')
				lower[s.charAt(i) - 'a'].add(i);
			else
				upper[s.charAt(i) - 'A'].add(i);
		}
		int n = sc.nextInt();
		for(int i = 0; i < n; ++i){
			char [] q = sc.nextLine().toCharArray();
			int start = 0, end = 0, position = 0; char c = q[0]; boolean printed = false;
			for (int j = 1; j < q.length; j++) {
				if(q[j] == q[j - 1])
					end++;
				else {
					int ch = check(c, start, end, position);
					if(ch == -1){
						out.println("Not matched"); printed = true;
						break;
					}
					start = i; end = i; c = q[j]; position = ch + 1;
				}
			}
			if(!printed){
				int ch = check(c, start, end, position);
				if(ch == -1){
					out.println("Not matched");
				}
				else{
					int begin = 0;
					if(q[0] >= 'a')
						begin = lower[q[0] - 'a'].get(0);
					else
						begin = upper[q[0] - 'A'].get(0);
					out.println("Matched " + begin + " " + ch);
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int check(char c, int start, int end, int position) {
		ArrayList<Integer> a;
		if(c >= 'a')
			a = lower[c - 'a'];
		else
			a = upper[c - 'A'];
		
		int pos = Collections.binarySearch(a, position);
		if(pos >= 0)
			return (a.size() - pos) >= (end - start + 1)?a.get(pos + (end - start)):-1;
			
		pos = -1 - pos;
		if(pos < a.size())
			return (a.size() - pos) >= (end - start + 1)?a.get(pos + (end - start)):-1;
			
		return -1;
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