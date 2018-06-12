package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVA_1203_Argus {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		String s;
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while(!sc.next().equals("#")){
			int id = sc.nextInt();
			int time = sc.nextInt();
			q.offer(new Pair(id, time));
			map.put(id, time);
		}
		int k = sc.nextInt();
		while(k-- > 0){
			Pair p = q.poll();
			out.println(p.id);
			q.add(new Pair(p.id, p.time + map.get(p.id)));
		}
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int id, time;
		public Pair(int id, int time) {
			this.id = id;
			this.time = time;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.time == o.time)
				return Integer.compare(this.id, o.id);
			return Integer.compare(this.time, o.time);
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