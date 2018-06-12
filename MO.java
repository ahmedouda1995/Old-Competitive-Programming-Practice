import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MO {

	static int N;
	static int votes[];
	static Query q[];
	static int sqrt;
	static int res[];
	static int freq[];
	static int blocks;
	static int sqrtDec[][];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), g;
		
		while(t-- > 0) {
			N = sc.nextInt();
			
			sqrt = (int) Math.sqrt(N);
			votes = new int[N];
			for(int i = 0; i < N; ++i) votes[i] = sc.nextInt();
			
			g = sc.nextInt();
			q = new Query[g];
			res = new int[g];
			freq = new int[N];
			blocks = N / sqrt + 1;
			sqrtDec = new int[blocks][N + 1];
			
			for(int i = 0; i < g; ++i)
				q[i] = new Query(sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
			
			process();
			
			for(int i = 0; i < g; ++i) out.println(res[i]);
		}
		
		out.flush();
		out.close();
	}
	
	private static void process() {
		Arrays.sort(q);
		
		int l = 1, r = 0;
		
		for(int i = 0; i < q.length; ++i) {
			while(r < q[i].r) add(++r);
			while(l < q[i].l) remove(l++);
			while(l > q[i].l) add(--l);
			while(r > q[i].r) remove(r--);
			
			res[q[i].id] = getAns(q[i].x);
		}
	}
	
	static int getAns(int x) {
		int ans = -1;
		for(int i = 0; i < sqrtDec.length; ++i) {
			if(sqrtDec[i][x] > 0) {
				for(int j = sqrt * i; j < sqrt * i + sqrt; ++j) {
					if(freq[j] == x)
						return j;
				}
			}
		}
		return ans;
	}

	static int getBlock(int idx) {
		return idx / sqrt;
	}
	
	static void add(int idx) {
		freq[votes[idx]]++;
		sqrtDec[getBlock(votes[idx])][freq[votes[idx]] - 1]--;
		sqrtDec[getBlock(votes[idx])][freq[votes[idx]]]++;
	}
	
	static void remove(int idx) {
		freq[votes[idx]]--;
		sqrtDec[getBlock(votes[idx])][freq[votes[idx]] + 1]--;
		sqrtDec[getBlock(votes[idx])][freq[votes[idx]]]++;
	}

	static class Query implements Comparable<Query> {
		int l, r, x, id;
		
		public Query(int a, int b, int c, int d) {
			l = a;
			r = b;
			x = c;
			id = d;
		}
		
		@Override
		public int compareTo(Query q) {
			if(Integer.compare(l / sqrt, q.l / sqrt) == 0)
				return Integer.compare(r, q.r);
			return Integer.compare(l / sqrt, q.l / sqrt);
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