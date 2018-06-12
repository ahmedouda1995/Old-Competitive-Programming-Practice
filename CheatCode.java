import java.util.ArrayList;
import java.util.Arrays;


public class CheatCode {
	
	public static int[] matches(String keyPresses, String[] codes)
	{
		ArrayList<Pair> input = construct(keyPresses);
		ArrayList<Pair> curr = new ArrayList<CheatCode.Pair>();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		int ind = -1;
		
		for(String s : codes)
		{
			ind++;
			curr = construct(s);
			
			for(int i = 0, j; i < input.size(); ++i)
			{
				j = 0;
				while(i + j < input.size() && j < curr.size() && curr.get(j).c == input.get(i + j).c &&
						curr.get(j).occ <= input.get(i + j).occ)
					j++;
				if(j == curr.size()) { ret.add(ind); break; }
			}
		}
		
		int arr[] = new int[ret.size()];
		for(int i = 0; i < arr.length; ++i) arr[i] = ret.get(i);
		
		return arr;
	}

	private static ArrayList<Pair> construct(String s) {
		
		ArrayList<Pair> ret = new ArrayList<Pair>();
		
		int i = 0;
		
		while(i < s.length())
		{
			char c = s.charAt(i);
			int occ = 0;
			while(i < s.length() && s.charAt(i) == c) { i++; occ++; }
			ret.add(new Pair(c, occ));
		}
		
		return ret;
	}
	
	static class Pair { char c; int occ; Pair(char x, int y) { c = x; occ = y; }
	@Override
		public String toString() {
			return c + " " + occ;
		}}
}
