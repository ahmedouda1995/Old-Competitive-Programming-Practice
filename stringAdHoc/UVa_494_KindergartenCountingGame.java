package stringAdHoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UVa_494_KindergartenCountingGame {

	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		while(br.ready()) {
			out.println(br.readLine().replaceAll("[^A-Za-z]+", " ").trim().split(" ").length);
		}
		out.flush();
		out.close();
	}
}
