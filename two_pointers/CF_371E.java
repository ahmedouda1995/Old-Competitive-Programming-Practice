package two_pointers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF_371E {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		Pair arr[] = new Pair[n];
		for(int i = 0; i < n; ++i) arr[i] = new Pair(sc.nextInt(), i + 1);
		
		int k = sc.nextInt();
		Arrays.sort(arr);
		
		int l = 0, r = k - 1;
		long dist = 0;
		
		long tmp = -arr[0].val;
		
		for(int i = 0; i <= r; ++i) {
			dist -= 1L * arr[i].val * (k - i - 1);
			dist += 1L * arr[i].val * i;
			tmp += arr[i].val;
		}
		
		long min = dist;
		int minPos = 0;
		r++;
		
		while(r < n) {
			dist -= 2L * tmp;
			dist += 1L * arr[l].val * (k - 1);
			dist += 1L * arr[r].val * (k - 1);
			l++; r++;
			tmp -= arr[l].val;
			tmp += arr[r - 1].val;
			
			if(dist < min) {
				min = dist;
				minPos = l;
			}
		}
		
		int i;
		for(i = minPos; i < minPos + k - 1; ++i)
			out.print(arr[i].idx + " ");
		
		out.println(arr[i].idx);
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		Integer val, idx;
		
		public Pair(int a, int b) {
			val = a; idx = b;
		}
		
		@Override
		public int compareTo(Pair p) {
			return Integer.compare(val, p.val);
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