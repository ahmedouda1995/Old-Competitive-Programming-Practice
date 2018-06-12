import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class BonusesAndTeleports {

	static final long INF = (long) 1e18;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		StringTokenizer s = new StringTokenizer(in.readLine());
		long n = Long.parseLong(s.nextToken());
		int m = Integer.parseInt(s.nextToken());
		
		TreeSet<Long> portals = new TreeSet<Long>();
		s = new StringTokenizer(in.readLine());
		for (long i = 0; i < n; i++) {
			portals.add(Long.parseLong(s.nextToken()));
		}
		
		long[] bonuses = new long[m];
		s = new StringTokenizer(in.readLine());
		for (int i = 0; i < m; i++) {
			bonuses[i] = Long.parseLong(s.nextToken());
		}
		
		long res = 0;
		long pos = portals.first();
		boolean portal = true;
		for (int i = 0; i < bonuses.length; i++) {
			long dD = Math.abs(pos - bonuses[i]);
			Long bBonP = portals.lower(bonuses[i]);
			Long aBonP = portals.higher(bonuses[i]);
			
			long bBonD = INF, aBonD = INF;
			if(bBonP != null) bBonD = Math.abs(bonuses[i] - bBonP);
			if(aBonP != null) aBonD = Math.abs(bonuses[i] - aBonP);
			
			if(portal) {
				if(dD <= Math.min(bBonD, aBonD)) {
					res += dD;
				}
				else {
					res += Math.min(bBonD, aBonD);
				}
			}
			else {
				Long bPosP = portals.lower(pos);
				Long aPosP = portals.higher(pos);
				
				long bPosD = INF, aPosD = INF;
				if(bPosP != null) bPosD = Math.abs(pos - bPosP);
				if(aPosP != null) aPosD = Math.abs(pos - aPosP);
				
				if(dD <= Math.min(bPosD, aPosD) + Math.min(bBonD, aBonD)) {
					res += dD;
				}
				else {
					res += Math.min(bPosD, aPosD) + Math.min(bBonD, aBonD);
				}
			}
			
			pos = bonuses[i];
			portal = portals.contains(pos);
		}
		
		Long bPosP = portals.lower(pos);
		Long aPosP = portals.higher(pos);
		
		long bPosD = INF, aPosD = INF;
		if(bPosP != null) bPosD = Math.abs(pos - bPosP);
		if(aPosP != null) aPosD = Math.abs(pos - aPosP);
		if(!portal)
			res += Math.min(bPosD, aPosD);
		
		out.println(res);
		
		out.flush();
		out.close();
	}
}
