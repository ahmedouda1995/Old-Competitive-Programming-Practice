import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class LastManStanding {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = Integer.parseInt(in.readLine());
		StringTokenizer s = new StringTokenizer(in.readLine());
		int[] scores = new int[n];
		long total = 0;
		for (int i = 0; i < n; i++) {
			scores[i] = Integer.parseInt(s.nextToken());
			total += scores[i];
		}
		
		if(total >= n) {
			out.println("NO");
		}
		else {
			out.println("YES");
			int last = n - 1;
			for (int i = n - 1; i >= 0; i--) {
				for (int j = 0; j < scores[i]; j++) {
					out.println((i + 1) + " " + (last + 1));
					last--;
				}
			}
		}
		
		out.flush();
		out.close();
	}
}
