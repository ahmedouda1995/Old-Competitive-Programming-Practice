package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int memo[];
	static int moves[] = {1, 2};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int pileSize = Integer.parseInt(br.readLine());
		
//		memo = new int[pileSize + 1];
//		Arrays.fill(memo, -1);
//		
//		System.out.println(isWinning(pileSize) > 0?"win":"lose");
//		System.out.println(Arrays.toString(memo));
		
		int arr[] = {0, 1, 0, 0, 0, 0, 0, 0};
		solve_nim(arr);
	}

	// Decision tree
	
	private static int isWinning(int pileSize) {
		if(pileSize == 0) return memo[pileSize] = 0;
		if(memo[pileSize] != -1) return memo[pileSize];
		
		int ans = 0;
		
		for(int i = 0; i < 2; ++i)
			if(pileSize >= moves[i] && isWinning(pileSize - moves[i]) == 0)
			{
				ans = 1;
				break;
			}
		
		return memo[pileSize] = ans;
	}
	
	// Nim game
	
	static void solve_nim(int piles[])
	{
		int xorSum = 0;
		for(int i : piles)
			xorSum ^= i;
		
		if(xorSum != 0)
		{
			System.out.println("First wins!");
			
			for(int i = 0; i < piles.length; ++i)
				if(piles[i] > (piles[i] ^ xorSum))
				{
					System.out.printf("First move: take %d from pile no. %d\n", piles[i] - (piles[i] ^ xorSum), i + 1);
					break;
				}
		}
		else
			System.out.println("Second wins!");
	}
	
	// misere Nim
	
	static boolean misereNim(int [] piles)
	{
		int n = piles.length;
		
		int isSpecial = 1, xorVal = 0;
		
		for(int i = 0; i < n; ++i)
		{
			xorVal ^= piles[i];
			isSpecial |= (piles[i] <= 1?1:0); // or op. was and in video
		}
		if(isSpecial == 1)
			return xorVal == 0;
		return xorVal != 0; // normal nim handling
	}
	
}
