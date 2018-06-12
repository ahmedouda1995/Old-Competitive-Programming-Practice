import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sam5 {

	static int N, M, K, T;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		T = sc.nextInt();
		
		Integer people[] = new Integer[N];
		Integer shelter[] = new Integer[M];
		
		for(int i = 0; i < N; ++i) people[i] = sc.nextInt();
		for(int i = 0; i < M; ++i) shelter[i] = sc.nextInt();
		Arrays.sort(people); Arrays.sort(shelter);
		
		int i = 0, j  = 0;
		
		PriorityQueue<Integer> ppq = new PriorityQueue<Integer>();
		PriorityQueue<Pair> shq = new PriorityQueue<Pair>();
		
		int res = 0;
		
		while(i < N || j < M) {
			if((i < N && j == M) || (i < N && people[i] <= shelter[j])) {
				boolean added = false;
				while(!shq.isEmpty()) {
					if(Math.abs(shq.peek().a - people[i]) <= T) {
						if(shq.peek().b == 1) shq.poll();
						else
							shq.add(new Pair(shq.peek().a, shq.poll().b - 1));
						added = true;
						res++;
						break;
					}
					else
						shq.poll();
				}
				
				if(!added) ppq.add(people[i]);
				
				i++;
			}
			else {
				
				int cap = K;
				
				while(!ppq.isEmpty() && cap > 0) {
					if(Math.abs(ppq.peek() - shelter[j]) <= T) {
						cap--; res++;
					}
					ppq.poll();
				}
				
				if(cap > 0) shq.add(new Pair(shelter[j], cap));
				
				j++;
			}
		}
		
		out.println(res);
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		Integer a, b;
		
		public Pair(int x, int y) {
			a = x; b = y;
		}

		@Override
		public int compareTo(Pair p) {
			return Integer.compare(a, p.a);
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