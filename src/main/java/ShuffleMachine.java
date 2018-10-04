import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShuffleMachine
{
    private final static Pattern FIELD_PATTERN = Pattern.compile("^\"(.+)\"$");
    //private final String filename = "22_zadanie_maszyna_losujaca_input.csv";
    private String filename;
    private int amountOfWinners;
    //private List<String[]> recordList = new ArrayList<String[]>();



    public ShuffleMachine(String filename, int amountOfWinners)
    {
        this.filename = filename;
        this.amountOfWinners = amountOfWinners;
    }

    private List<String[]> getFields()
    {
        List<String[]> recordList = new ArrayList<String[]>();

        try
        {
            String[] fields;
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String readLine;

            while((readLine = reader.readLine()) != null)
            {
                fields =  readLine.split(",");
                recordList.add(fields);
            }

            reader.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return recordList;
    }

    private List<String> getMails()
    {
        List<String[]> recordList = getFields();
        List<String> emailList = new ArrayList<String>();
        for(int i = 1; i < recordList.size(); i++)
        {
            String[] elementOfRecordList = recordList.get(i);
            for (int j = 0; j < elementOfRecordList.length - 1; j++)
            {
                String element = parseField(elementOfRecordList[j]);
                if(element.contains("@"))
                    emailList.add(element);
            }
        }

        return emailList;
    }

    public String getRecommender(String mailAdress)
    {
        List<String[]> recordList = getFields();
        String[] field;
        String[] drawnField = getFieldByMail(mailAdress);

        for(int i = 1; i < recordList.size(); i++)
        {
            field = recordList.get(i);

            String recommender = field[field.length - 1];

            //if(!recommender.equals(""))
            //{
                if (recommender.equals(drawnField[1]))
                    return drawnField[2];
           // }
        }

        return null;
    }

    public String[] getFieldByMail(String mailAdress)
    {
        List<String[]> recordList = getFields();
        String[] field;

        for(int i = 1; i < recordList.size(); i++)
        {
            field = recordList.get(i);

            String parseElement = parseField(field[2]);

            if(parseElement.equals(mailAdress))
                return field;
        }

        return null;
    }

    /**
     * Can be add a innerloop for universal purpose,
     * watch out for null value in last field in record,
     * use this for all get methods.
     *
     * @param mailAdress
     * @return
     */

    public int getFieldIndex(String mailAdress)
    {
        List<String[]> recordList = getFields();
        String[] field;
        for(int i = 1; i < recordList.size(); i++)
        {
            field = recordList.get(i);

            String element = parseField(field[2]);
            if(element.equals(mailAdress))
            {
                return i;
            }
        }

        return 0;
    }

    public int[] getCoupons()
    {
        List<String[]> recordList = getFields();
        String[] field;
        int[] coupons = new int[recordList.size() - 1];

        for(int i = 1; i < recordList.size(); i++)
        {
            field = recordList.get(i);


            String recommender = getRecommender(parseField(field[2]));
            if(field[2].equals(recommender))
                coupons[i-1] = 2;
            else
                coupons[i-1] = 1;
        }

        return coupons;
    }

    public String[] drawOfWinners()
    {
        String[] listOfWinner = new String[amountOfWinners];
        List<String> listOfMails = getMails();
        int[] coupons = getCoupons();

        for(int i = 0; i < listOfWinner.length; i++)
        {
            Collections.shuffle(listOfMails);

            String drawn = listOfMails.get(0);
            int index = getFieldIndex(drawn) - 1;
            coupons[index]--;

            if(coupons[index] < 1)
            {
                listOfWinner[i] = drawn;
                listOfMails.remove(0);
            }
            else
                listOfWinner[i] = drawn;

        }

        System.out.println(Arrays.toString(coupons));
        return listOfWinner;
    }

    private static String parseField(String field)
    {
        Matcher fieldMatcher = FIELD_PATTERN.matcher(field);

        if (fieldMatcher.matches())
        {
            return fieldMatcher.group(1);
        }

        return null;
    }
}
