package acp_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lottery {

	static int N;
	static boolean sorted, unique;
	static long memo[][] = new long[101][9];
	
	public static void main(String[] args) {
		String arr[] = 	
				
			{"INDIGO: 93 8 T F",
			 "ORANGE: 29 8 F T",
			 "VIOLET: 76 6 F F",
			 "BLUE: 100 8 T T",
			 "RED: 99 8 T T",
			 "GREEN: 78 6 F T",
			 "YELLOW: 75 6 F F"};
		System.out.println(Arrays.toString(sortByOdds(arr)));
	}
	
	public static String[] sortByOdds(String[] rules)
	{
		ArrayList<Pair> sort = new ArrayList<Pair>();
		N = rules.length;
		
		String name;
		String in[];
		int x, y;
		
		for(String a : rules)
		{
			name = a.split(":")[0];
			in  = a.split(":")[1].split(" ");
			x = Integer.parseInt(in[1]);
			y = Integer.parseInt(in[2]);
			sorted = in[3].equals("T");
			unique = in[4].equals("T");
			
			for(int i = 0; i <= 100; ++i)
				Arrays.fill(memo[i], -1);
			sort.add(new Pair(solve(x, y), name));
		}
		
		Collections.sort(sort);
		
		String ret[] = new String[N];
		
		for(int i = 0; i < N; ++i)
			ret[i] = sort.get(i).s;
		
		return ret;
	}
	
	private static long solve(int max, int cards) {
		if(cards == 0) return 1;
		if(memo[max][cards] != -1) return memo[max][cards];
		long count = 0;
		
		for(int i = 1; i <= max; ++i)
		{
			if(sorted && unique)
				count += solve(i - 1, cards - 1);
			else if(sorted)
				count += solve(i, cards - 1);
			else if(unique)
				count += solve(max - 1, cards - 1);
			else
				count += solve(max, cards - 1);
		}
		
		return memo[max][cards] = count;
	}

	static class Pair implements Comparable<Pair>
	{
		long n;
		String s;
		
		public Pair(long a, String t) {
			n = a;
			s = t;
		}
		
		@Override
		public int compareTo(Pair p) {
			if(n == p.n)
				return s.compareTo(p.s);
			return Long.compare(n, p.n);
		}
	}
}
