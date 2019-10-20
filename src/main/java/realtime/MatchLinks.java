package realtime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchLinks
{
    public boolean Match(String pattern, String link)
    {
        Pattern ptn = Pattern.compile(pattern);
        Matcher mt = ptn.matcher(link);
        return mt.find();
    }


}
