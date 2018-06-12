package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_11991_EasyProblem_from_Rujia_Liu {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		String s;
		while((s = sc.nextLine()) != null){
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			//ArrayList<Integer> [] a = new ArrayList[10000001];
			TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
			for (int i = 0; i < n; ++i) {
				int k = sc.nextInt();
				if(map.get(k) == null)
					map.put(k, new ArrayList<Integer>());
				map.get(k).add(i + 1);
			}
			for (int i = 0; i < m; i++) {
				int p1 = sc.nextInt(), p2 = sc.nextInt();
				if(p1 > map.get(p2).size())
					out.println(0);
				else
					out.println(map.get(p2).get(p1 - 1));
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