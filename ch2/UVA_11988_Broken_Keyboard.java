package ch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class UVA_11988_Broken_Keyboard {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = "";
		LinkedList<String> l = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		char homeEnd = '[';
		
		while((s = br.readLine()) != null){
			sb = new StringBuilder();
			l.clear();
			for(int i=0;i<s.length();i++){
				if(s.charAt(i) == '['){
					if(sb.length() != 0){
						if(homeEnd == '[')
							l.addFirst(new String(sb));
						else 
							l.addLast(new String(sb));
							
						sb = new StringBuilder();
					}
					homeEnd = '[';
				}
				else if(s.charAt(i) == ']'){
					if(sb.length() != 0){
						if(homeEnd == '[')
							l.addFirst(new String(sb));
						else 
							l.addLast(new String(sb));
							
						sb = new StringBuilder();
					}
					
					homeEnd = ']';
				}
				else if(s.charAt(i) != '[' && s.charAt(i) != ']')
					sb.append(s.charAt(i));
			}
			
			if(sb.length() > 0){
				if(homeEnd == '[')
					l.addFirst(new String(sb));
				else l.addLast(new String(sb));
			}
			
			for(String i : l)
				out.print(i);
			
			out.println();
		}
		out.flush();
		out.close();
	}
}
