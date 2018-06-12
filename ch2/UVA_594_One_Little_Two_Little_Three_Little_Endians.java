package ch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class UVA_594_One_Little_Two_Little_Three_Little_Endians {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String in = "", tmp1, tmp2; int n, result;
		StringBuilder sb;
		while((in = br.readLine()) != null){
			n = Integer.parseInt(in);
			tmp1 = Integer.toBinaryString(n);
			tmp2 = "";
			while(true){
				if(tmp1.length() > 8)
					tmp2 = tmp1.substring(0, 8) + tmp2;
				else{
					tmp2 = tmp1 + "00000000".substring(0, 8 - tmp1.length()) + tmp2;
					break;
				}
				tmp1 = tmp1.substring(8);
			}
			out.println(tmp2);
			result = new BigInteger(tmp2.toString(), 2).intValue();
			out.println(result);
		}
		out.flush();
		out.close();
	}
}
