package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TwoSat1 {
	
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n, m;
		n = sc.nextInt();
		m = sc.nextInt();
		
		adj = new ArrayList[(n << 1) + 2];
		for(int i = 0; i < (n << 1) + 2; ++i)
			adj[i] = new ArrayList<Integer>();
		int op, x, y;
		
		while(m-- > 0)
		{
			op = sc.nextInt();
			x = sc.nextInt() << 1;
			y = sc.nextInt() << 1;
			
			switch (op) {
			case 1:	addClause(x, y); break;
			case 2: addClause(x, y); addClause(x ^ 1, y ^ 1);break;
			case 3: addClause(x ^ 1, y); addClause(x, y ^ 1);break;
			case 4: addClause(x ^ 1, y);break;
			case 5: addClause(x ^ 1, y ^ 1);break;
			}
		}
		
		scc((n << 1) + 2);
		
		boolean consistent = true;
		for(int i = 1; i <= n; ++i)
			if(SCCIndex[i << 1] == SCCIndex[i << 1 | 1])
				consistent = false;
		if(consistent)
		{
			System.out.println("YES");
			int [] truthValue = new int[SCCIndexer];
			Arrays.fill(truthValue, -1);
			for(int c = 0; c < SCCIndexer; ++c)
			{
				if(truthValue[c] == -1)
				{
					truthValue[c] = 1;
					int nc = SCCIndex[Root[c] ^ 1];
					truthValue[nc] = 0;
				}
			}
			for(int i = 1; i <= n; ++i)
				System.out.print(truthValue[SCCIndex[i << 1]] + " ");
		}
		else
			System.out.println("NO");
		
		out.flush();
		out.close();
	}
	
    static Stack<Integer> stack;
    static int[] tin, tlow;
    static boolean[] inSCC;
    static int[] SCCIndex, Root;
    static int timer, SCCIndexer;

    static void scc(int V)
    {
    	tin = new int[V];
        tlow = new int[V];
        inSCC = new boolean[V];
        SCCIndex = new int[V];
        Root = new int[V];
        stack = new Stack<>();

        for(int i = 2; i < V; ++i)
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
	
	static void addClause(int x, int y)
	{
		adj[x ^ 1].add(y);
		adj[y ^ 1].add(x);
	}

	static int neg(int u)
	{
		return (u << 1) | 1;
	}
	
	static int pos(int u)
	{
		return (u << 1);
	}
	
	static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}