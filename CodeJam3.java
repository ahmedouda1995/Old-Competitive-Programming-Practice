import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class CodeJam3 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("bath.out");

		int t = sc.nextInt(), n, k, cases = 1;
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		
		while(t-- > 0) {
			n = sc.nextInt();
			k = sc.nextInt();
			pq.clear();
			
			pq.add(new Pair(0, n - 1));
			while(k-- > 1) {
				Pair p = pq.remove();
				int mid = (p.l + p.r) >> 1;
				pq.add(new Pair(p.l, mid - 1));
				pq.add(new Pair(mid + 1, p.r));
			}
			
			Pair p = pq.remove();
			int mid = (p.l + p.r) >> 1;
			int a = (mid - 1) - p.l + 1;
			int b = p.r - (mid + 1) + 1;
			if(a < 0) a = 0;
			if(b < 0) b = 0;
			
			out.printf("Case #%d: %d %d\n", cases++, max(a, b), min(a, b));
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int l, r;
		
		public Pair(int a, int b) {
			l = a; r = b;
		}

		@Override
		public int compareTo(Pair p) {
			return Integer.compare(p.r - p.l, r - l);
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