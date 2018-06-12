import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class UVA_11714 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		long n;
		
		while(br.ready())
		{
			n = Long.parseLong(br.readLine());
			
			out.println(n - 1 + (long) Math.ceil(Math.log(n) / Math.log(2)) - 1);
		}
		
		out.flush();
		out.close();
	}
}
