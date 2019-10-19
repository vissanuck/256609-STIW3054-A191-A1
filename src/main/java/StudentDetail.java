import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class StudentDetail
{
    public static String[][] studentsList = new String[45][2];
    private static MatchLinks regexMatches = new MatchLinks();
    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args)
    {
        new StudentDetail().Main();

    }

    private void Main()
    {
       regexMatches = new MatchLinks();


        try
        {
            String githubLink = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
            Document document = Jsoup.connect(githubLink).get();
            Elements links = document.getElementsByClass("markdown-body").select("td");
            for (Element link : links)
            {
                if (link.text().length() == 6 && regexMatches.Match("[0-9]", link.text()) && x < studentsList.length)
                {
                    studentsList[x][0] = link.text();
                    x++;
                }
                else if (regexMatches.Match("[a-zA-Z]", link.text()) && y < studentsList.length)
                {
                    studentsList[y][1] = link.text();
                    y++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
