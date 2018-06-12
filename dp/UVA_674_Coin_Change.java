package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class UVA_674_Coin_Change {

	static int memo[][] = new int[7490][5];
	static int change[] = {50, 25, 10, 5, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = "";
		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}
		while((s = br.readLine()) != null){
			int money = Integer.parseInt(s);
			out.println(ways(money, 0));
		}
		out.flush();
		out.close();
	}

	private static int ways(int remMoney, int denomination) {
		if(remMoney == 0)
			return 1;
		if(remMoney < 0)
			return 0;
		if(denomination > 4)
			return 0;
		if(memo[remMoney][denomination] != -1)
			return memo[remMoney][denomination];
		
		return memo[remMoney][denomination] = ways(remMoney, denomination+1) 
				+ ways(remMoney-change[denomination], denomination);
	}
}
