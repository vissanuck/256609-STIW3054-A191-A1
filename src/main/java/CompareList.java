import java.util.*;

public class CompareList
{
    private static StudentDetail studentDetail = new StudentDetail();
    private static StudentLink studentLink = new StudentLink();

    private static int totalCommented = 0;
    private static int totalNotCommented = 0;
    private static int totalAnonCommented = 0;

    private static String[] studList = new String[45];
    private static String[] matList = new String[39];
    private static String[] reList = new String[39];
    private static String[] priList = new String[39];

    private static String[] Title = {"No.", "Matric", "Name", "Github Link"};

    static String[][] studDet = new String[39][4];
    static String[][] studNum = new String[39][4];
    static String[][] studlin = new String[39][4];


    public static void main(String[] args)
    {

        studentDetail.main(args);

        studentLink.main(args);


        for (int i = 0; i < StudentDetail.studentsList.length; i++)
        {
            studList[i] = StudentDetail.studentsList[i][0];
        }

        for (int i = 0; i < StudentLink.studComment.length; i++)
        {
            matList[i] = StudentLink.studComment[i][0];
        }

        for (int i = 0; i < StudentLink.studComment.length; i++)
        {
            if (Arrays.asList(studList).contains(StudentLink.studComment[i][0]) && !StudentLink.studComment[i][0].equals(" "))
            {
                studDet[0] = Title;
                studDet[totalCommented + 1][0] = String.valueOf(totalCommented + 1);
                studDet[totalCommented + 1][1] = StudentLink.studComment[i][0];
                studDet[totalCommented + 1][2] = StudentLink.studComment[i][1];
                studDet[totalCommented + 1][3] = StudentLink.studComment[i][2];
                totalCommented++;
            }
            else if (!Arrays.asList(studList).contains(StudentLink.studComment[i][0]) && !StudentLink.studComment[i][0].equals(" "))
            {
                studNum[0] = Title;
                studNum[totalAnonCommented + 1][0] = String.valueOf(totalAnonCommented + 1);
                studNum[totalAnonCommented + 1][1] = StudentLink.studComment[i][0];
                studNum[totalAnonCommented + 1][2] = StudentLink.studComment[i][1];
                studNum[totalAnonCommented + 1][3] = StudentLink.studComment[i][2];
                totalAnonCommented++;
            } else if (!Arrays.asList(matList).contains(StudentDetail.studentsList[i][0]) && StudentDetail.studentsList[i][0] != null && !StudentDetail.studentsList[i][0].equals("254660"))
            {
                studlin[0] = Title;
                studlin[totalNotCommented + 1][0] = String.valueOf(totalNotCommented + 1);
                studlin[totalNotCommented + 1][1] = StudentDetail.studentsList[i][0];
                studlin[totalNotCommented + 1][2] = StudentDetail.studentsList[i][1];
                studlin[totalNotCommented + 1][3] = "Not Submitted";
                totalNotCommented++;
            }
        }

        for (int i = 0; i < StudentDetail.studentsList.length; i++)
        {
            if (!Arrays.asList(matList).contains(StudentDetail.studentsList[i][0]) && StudentDetail.studentsList[i][0] != null && !StudentDetail.studentsList[i][0].equals("254660"))
            {
                studlin[0] = Title;
                studlin[totalNotCommented + 1][0] = String.valueOf(totalNotCommented + 1);
                studlin[totalNotCommented + 1][1] = StudentDetail.studentsList[i][0];
                studlin[totalNotCommented + 1][2] = StudentDetail.studentsList[i][1];
                studlin[totalNotCommented + 1][3] = "Not Submitted";
                totalNotCommented++;
            }
        }

        System.out.println("List of Student Submit");
        priList = reList(studDet);
        for (int i = 0; i < reList.length; i++) {
            if (priList[i] != null) {
                System.out.println(priList[i]);
            }
        }
        clear(priList);
        System.out.println("\nList of Student Not Submit");
        priList = reList(studlin);
        for (int i = 0; i < reList.length; i++)
        {
            if (priList[i] != null)
            {
                System.out.println(priList[i]);
            }
        }
        clear(priList);
        System.out.println("\nList of Unknown Submit");
        priList = reList(studNum);
        for (int i = 0; i < reList.length; i++)
        {
            if (priList[i] != null)
            {
                System.out.println(priList[i]);
            }
        }
    }

    private static String[] reList(String[][] list)
    {
        for (int i = 0; i < list.length; i++)
        {
            if (i == 1)
            {
                reList[i] = String.format("|%5s|%8s|%39s|%40s|", "-----", "--------", "---------------------------------------", "----------------------------------------");
            } else if (i == 0 && list[i][0] != null)
            {
                reList[i] = String.format("| %-4s| %-7s| %-38s| %-39s|", list[i][0], list[i][1], list[i][2], list[i][3]);
            } else if (list[i - 1][0] != null)
            {
                reList[i] = String.format("| %-4s| %-7s| %-38s| %-39s|", list[i - 1][0], list[i - 1][1], list[i - 1][2], list[i - 1][3]);
            }
        }
        return reList;
    }

    private static void clear(String[] s)
    {
        Arrays.fill(s, null);
    }
}