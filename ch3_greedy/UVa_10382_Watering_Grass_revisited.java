package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10382_Watering_Grass_revisited {
	
	
	// KEPT ON GETTING WRONG ANSWER BECAUSE r * r WOULD OVERFLOW
	// BUT AFTER THE (double) CAST I GOT AC
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s; int n, l, w;
		while((s = sc.nextLine()) != null){
			String [] tmp = s.split(" ");
			n = Integer.parseInt(tmp[0]);
			l = Integer.parseInt(tmp[1]);
			w = Integer.parseInt(tmp[2]);
			
			Pair a[] = new Pair[n];
			
			int k = 0;
			for(int i = 0; i < n; ++i) {
				int pos = sc.nextInt(), r = sc.nextInt();
				
				if((double)r * r > (((double)w * w) / 4.0)){
					double dx = Math.sqrt((double)r * r - (((double)w * w) / 4.0));
					a[i] = new Pair(pos - dx, pos + dx);
					k++;
				}
				else
					a[i] = new Pair(Double.MAX_VALUE, Double.MAX_VALUE);
			}
			
			Arrays.sort(a);
			
			double start = 0.0;
			int i = 0, res = 0;
			double furthest = 0;
			while(i < k && furthest < l){
				if(a[i].from <= start){
					if(a[i].to > furthest){
						furthest = a[i].to;
					}
				}
				else {
					start = furthest;
					res++;
					if(a[i].from <= start){
						if(a[i].to > furthest){
							furthest = a[i].to;
						}
					}
					else
						break;
				}
				++i;
			}
			if(furthest >= l) {
				if(start < l)
					res++;
				out.println(res);
			}
			else
				out.println(-1);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		double from, to;
		
		public Pair(double from, double to) {
			this.from = from;
			this.to = to;
		}

		public int compareTo(Pair p) {
			if(Double.compare(this.from, p.from) == 0)
				return Double.compare(p.to, this.to);
			else
				return Double.compare(this.from, p.from);
		}
		
		public String toString() {
			return "(" + from + ", " + to + ")";
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