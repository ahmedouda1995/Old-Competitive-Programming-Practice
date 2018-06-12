package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10422_KnightsinFEN {

	// very important --- states bfs backtracking
	// i think meet in the middle
	static int[] dx = new int[] {-1, -1, -2, -2, 1, 1, 2, 2};
	static int[] dy = new int[] {-2, 2, -1, 1, -2, 2, -1, 1};
	static TreeMap<State, Integer> reachableStates;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		char[][] finalState = {"11111".toCharArray(),
						 	   "01111".toCharArray(),
						       "00 11".toCharArray(),
						       "00001".toCharArray(),
						       "00000".toCharArray()};
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			reachableStates = new TreeMap<State, Integer>();
			char[][] currState = new char[5][5];
			for(int i = 0; i < 5; ++i)
				currState[i] = sc.nextLine().toCharArray();
			
			bfs(new State(currState), false, reachableStates);
			State s = new State(finalState);
			int res = bfs(new State(finalState), true, new TreeMap<State, Integer>());
		
			if(res == -1)
				out.println("Unsolvable in less than 11 move(s).");
			else
				out.printf("Solvable in %d move(s).\n", res);
		}
		
		out.flush();
		out.close();
	}
	
	private static int bfs(State s, boolean query, TreeMap<State, Integer> map) {
		int x, y, mask;
		Queue<State> q = new LinkedList<State>();
		map.put(s, 0);	
		q.add(s);

		while(!q.isEmpty()) {
			s = q.remove();
			int moves = map.get(s);
			if(query && reachableStates.containsKey(s))
				return moves + reachableStates.get(s);
			
			if(moves == 5)
				continue;
			
			x = s.x; y = s.y; mask = s.mask;
			
			int posCur = getMaskIndex(x, y);
			for(int k = 0; k < 8; ++k) {
				int i = x + dx[k], j = y + dy[k];
				if(valid(i, j)) {
					int posNxt = getMaskIndex(i, j), bit = (mask >> posNxt) & 1;
					int nxt = mask & ~(1<<posNxt) | bit<<posCur;
					s = new State(i, j, nxt);
					if(!map.containsKey(s)) {
						map.put(s, moves + 1);
						q.add(s);
					}
				}
			}
		}
		return -1;
	}
	
	static int getMaskIndex(int x, int y) {	return x * 5 + y; }

	static boolean valid(int x, int y) { return x >= 0 && y >= 0 && x < 5 && y  < 5; }

	static class State implements Comparable<State> {
		int x, y, mask;
		
		State(int x, int y, int mask) { this.x = x; this.y = y; this.mask = mask; }
		
		State(char[][] grid) {
			for(int i = 0, k = 0; i < 5; ++i)
				for(int j = 0; j < 5; ++j, ++k)
					if(grid[i][j] != ' ')
						mask |= (grid[i][j]-'0')<<k;
					else {
						x = i; y = j;
					}
		}

		@Override
		public int compareTo(State s) {
			if(mask != s.mask)
				return mask - s.mask;
			else if(x != s.x)
				return x - s.x;
			return y - s.y;
				
		}
	}
	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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