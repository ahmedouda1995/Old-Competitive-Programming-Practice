package ch3_greedy;

import java.util.Scanner;

public class CoinChange {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] change = {1, 5, 10, 25};
		int n = sc.nextInt(), coinCount = 0;
		
		for(int i=change.length-1;i>=0 && n > 0;i--){
			coinCount += (n / change[i]);
			n -= (n / change[i]) * change[i];
		}
		System.out.println(coinCount);
		sc.close();
	}
}
