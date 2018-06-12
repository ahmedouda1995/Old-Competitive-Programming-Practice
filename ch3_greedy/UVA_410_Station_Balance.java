package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_410_Station_Balance {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		String s;
		int chambers, specimens;
		
		int setNo = 1;
		double am, imbalance, cm;
		DecimalFormat dc = new DecimalFormat("0.00000");
		while((s = br.readLine()) != null){
			am = 0;
			st = new StringTokenizer(s);
			chambers = Integer.parseInt(st.nextToken());
			specimens = Integer.parseInt(st.nextToken());
			s = br.readLine();
			st = new StringTokenizer(s);
			int [] a = new int[specimens];
			int [][] containers = new int[chambers][2];
			for(int i=0;i<specimens;i++){
				a[i] = Integer.parseInt(st.nextToken());
				am += a[i];
			}
			
			am = am / chambers;
			imbalance = 0;
			Arrays.sort(a);
			
			int i, k;
			for(i = a.length-1, k = 0;i>=0 && k < containers.length;i--, k++){
					containers[k][0] = a[i];
			}
			
			k--;
			
			for(;k>=0 && i>=0;k--, i--){
					containers[k][1] = a[i];
			}
			
			out.println("Set #" + setNo);
			for (int j = 0; j < containers.length; j++) {
				cm = 0;
				out.print(" " + j + ":");
				if(containers[j][0] != 0){
					out.print(" " + containers[j][0]);
					cm += containers[j][0];
					if(containers[j][1] != 0){
						out.print(" " + containers[j][1]);
						cm += containers[j][1];
					}
					
				}
				imbalance += Math.abs((cm - am));
				out.println();
			}
			out.println("IMBALANCE = " + dc.format(imbalance));
			out.println();
			setNo++;
		}
	
		out.flush();
		out.close();
	}
}
