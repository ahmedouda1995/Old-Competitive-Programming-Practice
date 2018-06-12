package ch2;

import java.util.Scanner;

public class UVA_11173_Grey_Codes_reversed {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine(), 2);
		
		int mask;
		
		for(mask = (n >> 1);mask != 0; mask >>= 1){
			n = n ^ mask;
		}
		System.out.println(n);
	}
}
