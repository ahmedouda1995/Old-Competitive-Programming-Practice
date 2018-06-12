import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class CornersGame {

	static char grid[][];
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, -1, 0, 1};
	
	public static int countMoves(String[] board)
	{
		grid = new char[6][6];
		for(int i = 0; i < 6; ++i) grid[i] = board[i].toCharArray();
		
		HashSet<Long> set = new HashSet<Long>();
		
		Queue<Pair> q = new LinkedList<Pair>();
		
		long begState = 0b110000_110000_000000_000000_000000_000000L;
		q.add(new Pair(new State(begState), 0));
		
		while(!q.isEmpty())
		{
			Pair p = q.poll();
			if(set.contains(p.s.state)) continue;
			set.add(p.s.state);
			int arr[] = p.s.toArray();
			
			for(int i = 0; i < 8; i += 2)
				grid[arr[i]][arr[i + 1]] = 'a';
			if(finished(p.s.state)) return p.moves;
			
			int x, y, a, b;
			
			for(int i = 0; i < 8; i += 2)
			{
				x = arr[i]; y = arr[i + 1];
				
				for(int j = 0; j < 4; ++j)
				{
					a = x + dr[j];
					b = y + dc[j];
					
					if(valid(a, b) && grid[a][b] != 'f')
					{
						if(grid[a][b] == '.')
						{
							State nw = new State(arr, a, b, i);
							q.add(new Pair(nw, p.moves + 1));
						}
						else if(grid[a][b] == 'a' || grid[a][b] == 's')
						{
							a += dr[j]; b += dc[j];
							if(valid(a, b) && grid[a][b] == '.')
							{
								State nw = new State(arr, a, b, i);
								q.add(new Pair(nw, p.moves + 1));
							}
						}
					}
				}
			}
			for(int i = 0; i < 8; i += 2)
				grid[arr[i]][arr[i + 1]] = '.';
		}
		return -1;
	}
	
	static boolean valid(int x, int y)
	{
		return x >= 0 && x < 6 && y >= 0 && y < 6;
	}
	
	static boolean finished(long state)
	{
		return state == 0b000000_000000_000000_000000_000011_000011L;
	}
	
	static class State
	{
		long state;
		
		public State(long s) {
			state = s;
		}
		
		public State(int arr[], int a, int b, int i) {
			int x[] = new int[arr.length];
			
			System.arraycopy(arr, 0, x, 0, arr.length);
			x[i] = a; x[i + 1] = b;
			long st = 0;
			for(int j = 0; j < 8; j += 2)
				st |= (1L << (x[j] * 6 + x[j + 1]));
			state = st;
		}
		
		int[] toArray()
		{
			int a[] = new int[4];
			long s = state;
			int cnt = 0;
			int i = 0;
			while(s > 0)
			{
				if((s & 1) == 1) 
					a[i++] = cnt;
				s >>= 1;
				cnt++;
			}
			return new int[]{a[0] / 6, a[0] % 6, a[1] / 6, a[1] % 6,
					a[2] / 6, a[2] % 6,	a[3] / 6, a[3] % 6};
		}
	}
	
	static class Pair
	{
		State s;
		int moves;
		
		public Pair(State a, int m) {
			s = a;
			moves = m;
		}
	}
}
