import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cyberline
{	
	public static String lastCyberword(String cyberline)
	{
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile("[A-Za-z0-9-@]+");
		matcher = pattern.matcher(cyberline);
		
		String out = null;
		
		while(matcher.find())
			if(matcher.group().matches(".*[A-Za-z0-9@].*"))
				out = matcher.group();
		
		return out.replaceAll("-+", "");
	}
}
