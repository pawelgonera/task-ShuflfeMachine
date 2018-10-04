import java.util.*;

public class Main
{
    static Scanner sc  = new Scanner(System.in);
    public static void main(String[] args)
    {

        calculate();

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

    public static void calculate()
    {
        Integer x, y;

        System.out.println("Type first number");
        x = sc.nextInt();
        System.out.println("Type second number");
        y = sc.nextInt();

        System.out.println("Sum: " + Calculator.add(x, y));
        System.out.println("Subtract: " + Calculator.subtract(x, y));
        System.out.println("Multiply: " + Calculator.multiply(x, y));
        System.out.println("Divide: " + Calculator.divide(x, y));

    }
}
