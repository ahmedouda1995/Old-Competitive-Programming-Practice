package dp_gym;

import java.util.Arrays;

public class EditDistance {

	static String s1 = "", s2 = "bell";
	static int memo[][];
	
	public static void main(String[] args) {
		memo = new int[s1.length()][s2.length()];
		
		for(int i = 0; i < memo.length; ++i) Arrays.fill(memo[i], -1);
		
		System.out.println(solveTD(s1.length() - 1, s2.length() - 1));
	}

	private static int solveTD(int i, int j) {
		if(i == -1 && j == -1) return 0;
		if(i == -1) return j + 1;
		if(j == -1) return i + 1;
		
		if(memo[i][j] != -1) return memo[i][j];
		
		if(s1.charAt(i) == s2.charAt(j))
			return memo[i][j] = solveTD(i - 1, j - 1);
		
		else
			return memo[i][j] = 1 + Math.min(Math.min(solveTD(i - 1, j - 1),
					solveTD(i - 1, j)), solveTD(i, j - 1));
	}
}
