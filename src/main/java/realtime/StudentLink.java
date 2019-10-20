
package realtime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class StudentLink
{
    private final String githubLink = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
    private static MatchLinks regexMatches = new MatchLinks();
    private Document doc;
    public static String[][]studComment = new String[39][3];
    public static int n = 0;
    public static int m = 0;
    public static int o = 0;


    public static void main(String[] args)
    {
        new StudentLink().Main();

        for (int x=0; x < studComment.length;x++)
        {
          if(studComment[x][0] == null)
            {
                studComment[x][0] = " ";
                studComment[x][1] = " ";
                studComment[x][2] = " ";
            }
           else if(regexMatches.Match("^ ", studComment[x][1]))
            {
                studComment[x][1] = studComment[x][1].replaceAll("^ ","");
            }

        }
    }

    private void Main()
    {

        try
        {
            doc = Jsoup.connect(githubLink).get();
            String title = doc.title();

            Elements datalinks = doc.getElementsByClass("js-timeline-item js-timeline-progressive-focus-container").select("p");


            for (Element link : datalinks)
            {
                String [] splitNum=link.text().split(" ");
                for (int i = 0;i <splitNum.length;i++)
                {
                    if (regexMatches.Match("[0-9]",splitNum[i]) && splitNum[i].length()<=13)
                    {
                        String[] splitTex;
                        if (splitNum[i].length()>6)
                        {
                            splitTex = splitNum[i].split(":");
                            for (int j = 0;j<splitTex.length;j++)
                            {
                                if (splitTex[j].length()==6 && regexMatches.Match("[0-9]",splitTex[j]))
                                {studComment[n][0]=splitTex[1];
                                    n++;

                                }
                            }
                        }
                        else
                            {studComment[n][0]=splitNum[i];
                                n++;
                            }

                    }
                    else if (regexMatches.Match("https",splitNum[i]))
                        {
                            String[] splitTex;
                            if (regexMatches.Match("Link",splitNum[i]))
                            {
                                splitTex=splitNum[i].split("ink:");
                                for (int k = 0; k<splitTex.length;k++)
                                {
                                    if (splitTex[k].length()>1)
                                    {studComment[m][2]= splitTex[1];
                                        m++;
                                    }
                                }
                            }
                            else
                            {studComment[m][2]=splitNum[i];
                                m++;
                            }
                        }
                }
                String[] splitName =link.text().split("Name");
                for(String a: splitName)
                {
                    String[] splitName2 = a.split(":");
                    for(String b: splitName2)
                    {
                        String[] splitName3 = b.split("Link");
                        for(String c: splitName3)
                        {
                            String[] splitName4 = c.split("Matric no");
                            for (String d: splitName4)
                            {
                                String[] splitName5= d.split("link");
                                for(String e: splitName5)
                                {
                                    if(regexMatches.Match("[a-zA-Z]",e)
                                            && !regexMatches.Match("[0-9]",e)
                                            && !regexMatches.Match("https",e)
                                            && !regexMatches.Match("//",e)
                                            && !regexMatches.Match("[Mm]atri[cx]",e))
                                    {
                                      studComment[o][1] = e;
                                      o++;

                                    }
                                }
                            }
                        }

                    }
                }

            }
        }
        catch(IOException e)
            {
                e.printStackTrace();
            }
    }
}