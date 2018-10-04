import java.util.*;

public class Main
{
    static Scanner sc  = new Scanner(System.in);

    public static void main(String[] args)
    {

        createUser();

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

    public static void createUser()
    {
        String name, lastName;
        int age;

        System.out.println("Type a name");
        name = sc.next();

        System.out.println("Type a lastName");
        lastName = sc.next();

        System.out.println("Type your age");
        age = sc.nextInt();

        User user = new User(name, lastName, age);
        System.out.println("Created new user: " + user.toString());

    }
}
