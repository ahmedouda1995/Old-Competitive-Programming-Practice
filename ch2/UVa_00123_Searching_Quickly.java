package ch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class UVa_00123_Searching_Quickly {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String in = "";
		ArrayList<Tuple> a = new ArrayList<Tuple>();
		HashSet<String> h = new HashSet<String>();
		while(!(in = br.readLine()).equals("::")){
			h.add(in.toLowerCase());
		}
		
		while((in = br.readLine()) != null){
			String [] tmp = in.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				if(!h.contains(tmp[i].toLowerCase())){
					a.add(new Tuple(tmp[i].toLowerCase(), i, tmp));
				}
			}
		}
		
		Collections.sort(a);
		StringBuilder sb;
		for (int i = 0; i < a.size(); i++) {
			sb = new StringBuilder();
			int pos = a.get(i).pos;
			for (int j = 0; j < a.get(i).sentence.length; j++) {
				if(j == 0){
					if(j == pos)
						sb.append(a.get(i).sentence[0].toUpperCase());
					else
						sb.append(a.get(i).sentence[0].toLowerCase());
				}
				else if(j == pos){
					sb.append(" ");
					sb.append(a.get(i).sentence[j].toUpperCase());
				}
				else{
					sb.append(" ");
					sb.append(a.get(i).sentence[j].toLowerCase());
				}
			}
			out.println(sb);
		}
		
		out.flush();
		out.close();
	}
	
	static class Tuple implements Comparable<Tuple>{
		String keyword;
		String [] sentence;
		int pos;
		
		public Tuple(String keyword, int pos, String [] sentence) {
			this.keyword = keyword;
			this.pos = pos;
			this.sentence = sentence;
		}
		
		@Override
		public int compareTo(Tuple t) {
			//if(keyword.compareTo(t.keyword) != 0)
				return keyword.compareTo(t.keyword);
			//else return Integer.compare(pos, t.pos);
		}
	}
	
}
