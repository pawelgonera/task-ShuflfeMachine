import java.util.*;

public class Main
{
    private static Scanner sc  = new Scanner(System.in);


    public static void main(String[] args)
    {

        //System.out.println("Podaj pełną ściężkę do pliku csv, razem z naswą pliku i rozszerzeniem .csv");
        String filename = "list_of_participants.csv";//sc.next();
        int amountOfWinners = 0;
        boolean flag = true;
        while(flag)
        {
            ShuffleMachine shuffleMachine = new ShuffleMachine(filename);
            int maxWinners = shuffleMachine.getAmountOfWinners();
            System.out.println("Podaj ilość wygranych maili");
            amountOfWinners = sc.nextInt();
            if (amountOfWinners < 1 || amountOfWinners > maxWinners)
                System.out.println("Ilość wygranych nie może być mniejsza niż jeden lub większa niż " + maxWinners + " !");
            else
                flag = false;
        }

        ShuffleMachine shuffleMachine = new ShuffleMachine(filename, amountOfWinners);

        for(Participant participant : shuffleMachine.getWinners()){
            System.out.println(participant);
        }

    }

}
