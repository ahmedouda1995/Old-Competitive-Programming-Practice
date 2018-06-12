package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class PersistentDS2 {

	static final int MAX = (int) 1e5 + 5;
	static ArrayList<Edge> adj[];
	static int versions = 1, curVersion = 0;
	static ArrayList<Integer> versionQuery[];
	static int sumQs = 0;
	static long sum[];
	static LinkedList<Integer> Q;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), u, v;
		Q = new LinkedList<>();
		versionQuery = new ArrayList[n + 2];
		adj = new ArrayList[n + 2];
		for(int i = 0; i <= n; ++i)
		{
			versionQuery[i] = new ArrayList<>();
			adj[i] = new ArrayList<>();
		}
		while(n-- > 0)
		{
			switch(sc.next().charAt(0))
			{
			case 's': versionQuery[curVersion].add(sumQs++); break;
			case 'e':
				u = curVersion;
				v = versions;
				curVersion = versions++;
				adj[u].add(new Edge(v, 0, sc.nextInt()));
				break;
			case 'd':
				u = curVersion;
				v = versions;
				curVersion = versions++;
				adj[u].add(new Edge(v, 1, -1));
				break;
			case 'c': curVersion = sc.nextInt(); break;
			}
		}
		sum = new long[sumQs];
		dfs(0, 0);
		for(long res : sum) out.println(res);
		
		out.flush();
		out.close();
	}
	
	private static void dfs(int u, long total) {
		for(int q : versionQuery[u])
			sum[q] = total;
		for(Edge e : adj[u])
		{
			if(e.op == 0) // enqueue
			{
				Q.addLast(e.enqVal);
				dfs(e.to, total + e.enqVal);
				Q.removeLast();
			}
			else
			{
				if(Q.isEmpty())
					dfs(e.to, 0);
				else
				{
					int tmp = Q.removeFirst();
					dfs(e.to, total - tmp);
					Q.addFirst(tmp);
				}
			}
		}
	}

	static class Edge
	{
		int to, op; // 0: enqueue, 1: dequeue
		int enqVal;
		
		public Edge(int v, int o, int e) {
			to = v;
			op = o;
			enqVal = e;
		}
	}
	
	static class Scanner {
		BufferedReader br;
		StringTokenizer st;

		public Scanner(InputStream in) {
			br = new BufferedReader(new InputStreamReader(in));
		}

		public Scanner(String file) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(file));
		}

		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return st.nextToken();
		}

		String nextLine() throws IOException {
			return br.readLine();
		}

		int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(next());
		}

		long nextLong() throws NumberFormatException, IOException {
			return Long.parseLong(next());
		}

		double nextDouble() throws NumberFormatException, IOException {
			return Double.parseDouble(next());
		}

		boolean ready() throws IOException {
			return br.ready();
		}
	}
}