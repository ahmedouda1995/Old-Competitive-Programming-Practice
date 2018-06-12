import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Sam4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char s [] = br.readLine().toCharArray();
		
		for(int i = 0, j = s.length - 1; i < j; ++i, j--) s[j] = s[i];
		System.out.println(new String(s));	
	}
}
