import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UnLinker
{
//	public static void main(String[] args)
//	{
//		System.out.println(clean("q,Rp3.tvt.ewwwww.ebwww.UQ.info5:.edup.tvwww.www.AW"));
//	}
	
	public static String clean(String text)
	{
		String prefix = "((http://)|(http://www\\.)|(www\\.))";
		String mid = "([a-zA-Z0-9.]+)";
		String suffix = "((\\.com)|(\\.org)|(\\.edu)|(\\.info)|(\\.tv))";
		String regex = prefix + mid + suffix;
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(text);
		
		StringBuilder sb = new StringBuilder();
		
		int i = 0, c = 1;
		
		while(matcher.find())
		{
			while(i < matcher.start()) sb.append(text.charAt(i++));
			sb.append("OMIT" + c++);
			i = matcher.end();
		}
		
		while(i < text.length()) sb.append(text.charAt(i++));
		
		return sb.toString();
	}
}
