/*
AHub
Jack Pharies
CSC 372


AHub is the hub that runs the Rejection Sampling programs
Basics are that it has a text loop that runs until the key word "exit" is typed


We assume that the user will make no error when typing commands

*/
import java.util.*;


public class Ahub {


    public static void main(String[] args)
    {
        RejSampling testRej = new RejSampling();
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

            testRej.rejectionSampling(arguments);

            System.out.print(" ");
            }

        }
    }
    
}
