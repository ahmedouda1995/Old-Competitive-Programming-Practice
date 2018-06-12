package acp_dp;

import java.util.Arrays;

public class PrimeSoccer {

	static double prob;
	static double memo[][] = new double[18][19];
	static int prime = Integer.parseInt("0100010100010101100", 2);
	
	public static double getProbability(int skillOfTeamA, int skillOfTeamB) {
		prob = skillOfTeamA / 100.0;
		for(int i = 0; i < 18; ++i) Arrays.fill(memo[i], -1);
		double a = solve(0, 0);
		
		prob = skillOfTeamB / 100.0;
		for(int i = 0; i < 18; ++i) Arrays.fill(memo[i], -1);
		double b = solve(0, 0);
		
		return a + b - a * b;
	}

	private static double solve(int match, int goals) {
		if(match == 18) return (((prime >> goals) & 1) == 1)?1:0;
		if(memo[match][goals] != -1) return memo[match][goals];
		
		return memo[match][goals] =
				prob * solve(match + 1, goals + 1) + (1 - prob) * solve(match + 1, goals);
	}
}
