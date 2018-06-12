package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TwoSat2 {
	
	static int N, M;
	static char[][] grid;
	static ArrayList<Integer> adj[];
	static TreeMap<Pair, Integer> index;
	static int V;
	static int dr1[] = {0, 0, -1, 1};
	static int dc1[] = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		grid = new char[N][M];
		for(int i = 0; i < N; ++i)
			grid[i] = sc.nextLine().toCharArray();
		
		int W = 0, B = 0;
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < M; ++j)
			{
				if(grid[i][j] == 'B')
					B++;
				else if(grid[i][j] == 'W')
					W++;
			}
		if(2 * B != W)
		{
			System.out.println("NO");
			return;
		}
		
		V = B << 3;
		index = new TreeMap<Pair, Integer>();
		adj = new ArrayList[V];
		for(int i = 0; i < V; ++i)
			adj[i] = new ArrayList<Integer>();
		int nodes = 0;
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < M; ++j)
				if(grid[i][j] == 'B')
					index.put(new Pair(i, j), nodes++);
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < M; ++j)
			{
				if(grid[i][j] == 'B')
				{
					if(!valid(i, j))
					{
						System.out.println("NO");
						return;
					}
					addHorizontal(i, j);
					addVertical(i, j);
				}
			}
		scc();
		boolean consistent = true;
		for(int i = 0; i < B; ++i)
			for(int j = 0; j < 4; ++j)
				if(SCCIndex[(i << 3) + j] == SCCIndex[not((i << 3) + j)])
					consistent = false;
		System.out.println((consistent)?"YES":"NO");
	}
	
	private static void handleupperW(int a, int b) {
		int i, j, idxOld, idxCur;
		i = a - 1;
		j = b - 1;
		idxCur = index.get(new Pair(a, b)) << 3;
		
		if(inBound(i, j) && grid[i][j] == 'B')
		{
			idxOld = index.get(new Pair(i, j)) << 3;
			addClause(not(getR(idxOld)), not(getU(idxCur)));
		}
		
		i = a - 2;
		j = b;
		
		if(inBound(i, j) && grid[i][j] == 'B')
		{
			idxOld = index.get(new Pair(i, j)) << 3;
			addClause(not(getD(idxOld)), not(getU(idxCur)));
		}
		
		i = a - 1;
		j = b + 1;
		
		if(inBound(i, j) && grid[i][j] == 'B')
		{
			idxOld = index.get(new Pair(i, j)) << 3;
			addClause(not(getL(idxOld)), not(getU(idxCur)));
		}
	}

	private static void handleLeftW(int a, int b) {
		int i, j, idxOld, idxCur;
		i = a;
		j = b - 2;
		idxCur = index.get(new Pair(a, b)) << 3;
		
		if(inBound(i, j) && grid[i][j] == 'B')
		{
			idxOld = index.get(new Pair(i, j)) << 3;
			addClause(not(getR(idxOld)), not(getL(idxCur)));
		}
		
		i = a - 1;
		j = b - 1;
		
		if(inBound(i, j) && grid[i][j] == 'B')
		{
			idxOld = index.get(new Pair(i, j)) << 3;
			addClause(not(getD(idxOld)), not(getL(idxCur)));
		}
	}

	private static void addHorizontal(int i, int j) {
		boolean left, right;
		left = right = false;
		if(inBound(i, j - 1) && grid[i][j - 1] == 'W') left = true;
		if(inBound(i, j + 1) && grid[i][j + 1] == 'W') right = true;
		int idx = index.get(new Pair(i, j)) << 3;
		if(left && right)
		{
			addClause(getL(idx), getR(idx));
			handleLeftW(i, j);
			handleRightW(i, j);
		}
		else if(left)
		{
			addClause(getL(idx), getL(idx));
			handleLeftW(i, j);
		}
		else
		{
			addClause(getR(idx), getR(idx));
			handleRightW(i, j);
		}
	}
	
	private static void handleRightW(int a, int b) {
		int i, j, idxOld, idxCur;
		i = a - 1;
		j = b + 1;
		idxCur = index.get(new Pair(a, b)) << 3;
		
		if(inBound(i, j) && grid[i][j] == 'B')
		{
			idxOld = index.get(new Pair(i, j)) << 3;
			addClause(not(getD(idxOld)), not(getR(idxCur)));
		}
	}

	private static void addVertical(int i, int j) {
		boolean up, down;
		up = down = false;
		if(inBound(i - 1, j) && grid[i - 1][j] == 'W') up = true;
		if(inBound(i + 1, j) && grid[i + 1][j] == 'W') down = true;
		int idx = index.get(new Pair(i, j)) << 3;
		if(up && down)
		{
			addClause(getU(idx), getD(idx));
			handleupperW(i, j);
		}
		else if(up)
		{
			addClause(getU(idx), getU(idx));
			handleupperW(i, j);
		}
		else
			addClause(getD(idx), getD(idx));
	}

	static int getL(int idx) {return idx;}
	static int getR(int idx) {return idx + 1;}
	static int getU(int idx) {return idx + 2;}
	static int getD(int idx) {return idx + 3;}
	private static int not(int idx) {
		if(idx % 8 >= 4)
			return idx - 4;
		return idx + 4;
	}

	private static void addClause(int u, int v) {
		adj[not(u)].add(v);
		adj[not(v)].add(u);
	}

	private static boolean valid(int i, int j) {
		int k = 0;
		boolean found = false;
		for(; k < 2; ++k)
		{
			if(inBound(i + dr1[k], j + dc1[k]) && grid[i + dr1[k]][j + dc1[k]] == 'W')
				found = true;
		}
		
		if(!found) return false;
		found = false;
		for(; k < 4; ++k)
		{
			if(inBound(i + dr1[k], j + dc1[k]) && grid[i + dr1[k]][j + dc1[k]] == 'W')
				found = true;
		}
		return found;
	}

	static boolean inBound(int i, int j)
	{
		return i >= 0 && j >= 0 && i < N && j < M;
	}
	
	static class Pair implements Comparable<Pair>
	{
		int i, j;
		
		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return "(" + i + ", " + j + ")";
		}

		@Override
		public int compareTo(Pair p) {
			if(i == p.i)
				return j - p.j;
			return i - p.i;
		}
	}
	
    static Stack<Integer> stack;
    static int[] tin, tlow;
    static boolean[] inSCC;
    static int[] SCCIndex, Root;
    static int timer, SCCIndexer;

    static void scc()
    {
    	tin = new int[V];
        tlow = new int[V];
        inSCC = new boolean[V];
        SCCIndex = new int[V];
        Root = new int[V];
        stack = new Stack<>();

        for(int i = 0; i < V; ++i)
            if(tin[i] == 0)
                dfs(i);
    }
    
    static void dfs(int u) {
        tin[u] = tlow[u] = ++timer;
        stack.push(u);
        for(int v: adj[u]) {
            if(tin[v] == 0)
                dfs(v);
            if(!inSCC[v])
                tlow[u] = Math.min(tlow[u], tlow[v]);
        }
        if(tin[u] == tlow[u]) {

            while(true) {
                int v = stack.pop();
                SCCIndex[v] = SCCIndexer;
                inSCC[v] = true;
                if(v == u)
                {
                	Root[SCCIndexer] = v;
                	break;
                }
            }
            SCCIndexer++;
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
