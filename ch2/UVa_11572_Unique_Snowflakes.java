package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_11572_Unique_Snowflakes {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt(), maxSoFar;
		TreeSet<Integer> set = new TreeSet<Integer>();
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while(t-- > 0){
			int n = sc.nextInt();
			maxSoFar = 0;
			set.clear();
			map.clear();
			int [] a = new int[n];
			for(int i = 0;i < n;++i){
				a[i] = sc.nextInt();
			}
			int i = 0;
			while(i < n){
				if(!set.contains(a[i])){
					set.add(a[i]);
					map.put(a[i], i);
					maxSoFar = Math.max(maxSoFar, set.size());
				}
				else{
					set.clear();
					i = map.get(a[i]);
					map.clear();
				}
				i++;
			}
			maxSoFar = Math.max(maxSoFar, set.size());
			out.println(maxSoFar);
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