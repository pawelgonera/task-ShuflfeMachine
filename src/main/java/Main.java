import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc  = new Scanner(System.in);

        System.out.println("Podaj pełną ściężkę do pliku csv, razem z naswą pliku i rozszerzeniem .csv");
        String filename = sc.next();
        int amountOfWinners = 0;
        boolean flag = true;
        while(flag)
        {
            System.out.println("Podaj ilość ilość wygranych maili");
            amountOfWinners = sc.nextInt();
            if (amountOfWinners < 1)
                System.out.println("Ilość wygranych nie może być mniejsza niż jeden !");
            else
                flag = false;
        }


        ShuffleMachine shuffleMachine = new ShuffleMachine(filename, amountOfWinners);


        System.out.println(Arrays.toString((shuffleMachine.drawOfWinners())));




    }
}
