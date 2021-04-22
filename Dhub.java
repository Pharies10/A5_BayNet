/*
DHub
Jack Pharies
CSC 372


DHub is the hub that runs the full disjunction programs
Basics are that it has a text loop that runs until the key word "exit" is typed


We assume that the user will make no error when typing commands

*/
import java.util.*;

public class Dhub {


    public static void main(String[] args)
    {
        fullDisjunction testDis = new fullDisjunction();
        boolean go = true;
        while (go == true)
        {

            System.out.println("Please type in a Bayes net problem");
            System.out.println("Type exit to quit");

            String answer = System.console().readLine();

            if (answer.equals("exit") == true)
            {
                go = false;
            }
            else 
            {
            String[] splitArgs = answer.split(" ");

            ArrayList<String> arguments = new ArrayList<String>();
            for (String i : splitArgs)
            {
                arguments.add(i);
            }

            testDis.calculate(arguments);

            System.out.print(" ");
            }

        }
        

    }

}