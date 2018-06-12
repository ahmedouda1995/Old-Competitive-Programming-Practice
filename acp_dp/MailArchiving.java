package acp_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MailArchiving {

	static ArrayList<Integer> arr = new ArrayList<Integer>();
	static Integer val[];
	static int memo[][];
	static final int INF = (int) 1e9;
	
	public static int minSelections(String[] destFolders)
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int idx = 0;
		
		for(int i = 0; i < destFolders.length; ++i)
		{
			if(!map.containsKey(destFolders[i]))
				map.put(destFolders[i], idx++);
			
			if(i == 0)
				arr.add(map.get(destFolders[i]));
			else
			{
				if(!destFolders[i].equals(destFolders[i - 1]))
					arr.add(map.get(destFolders[i]));
			}
		}
		System.out.println(arr);
		
		memo = new int[arr.size()][arr.size()];
		
		for(int i = 0; i < arr.size(); ++i)
			Arrays.fill(memo[i], -1);
		
		return solve(0, arr.size() - 1);
	}

	private static int solve(int i, int j) {
		if(i > j) return 0;
		if(i == j) return 1;
		if(memo[i][j] != -1) return memo[i][j];
		
		int min = j - i + 1;
		
		for(int k = i; k < j; ++k)
			min = Math.min(min, solve(i, k) + solve(k + 1, j) - (arr.get(i) == arr.get(j)?1:0));
		
		return memo[i][j] = min;
	}
}
