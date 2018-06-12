package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_00417_Word_Index {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	
		generate(map);
		while((s = br.readLine()) != null) out.println(map.get(s) != null?(int)map.get(s):0);
		
		out.flush();
		out.close();
	}
	
	private static void generate(TreeMap<String, Integer> map) {
		Queue<String> q = new LinkedList<String>();
		for (char c = 'a'; c <= 'z'; c++) q.offer(String.valueOf(c));
		String s; int count = 1;
		
		while(!q.isEmpty()){
			s = q.poll();
			map.put(s, count);
			++count;
			
			if(s.length() == 5) continue;
			
			for(char c = (char) (s.charAt(s.length() - 1) + 1);c <= 'z';c++) q.offer(s + c);
			if(s.length() < 2)
				System.out.println(q);
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

