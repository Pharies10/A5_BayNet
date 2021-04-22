/*
RejSampling
Jack Pharies
CSC 372


Creates a Bayes net and uses Rejection Sampling tabel to calculate any probability



*/


import java.util.*;



public class RejSampling {

    private ArrayList<Node> bayesNet;

    // constructor
    public RejSampling()
    {
        
        this.bayesNet = new ArrayList<Node>();

        setupBayes();

        
    }
    
    /*
    rejection Sampling
    Params: String of arguments
    Uses the rejection sampling formula to approximate bayes net

    Note it runs 100,000,000 different scenarios
    */
    public void rejectionSampling(ArrayList<String> arguments)
    {
        boolean givenGate = false;
        ArrayList<String> givenArgs = new ArrayList<String>();
        for (int i = 0; i < arguments.size(); i++)
        {
            if (givenGate == true)
            {
                givenArgs.add(arguments.get(i));
            }
            if (arguments.get(i).equals("given") == true)
            {
                givenGate = true;
            }

        }


        int gCount = 0;
        int match = 0;

        for (int i = 0; i < 100000000; i++)
        {
            ArrayList<String> rand = priorSampling();

            int matchGate = arguments.size();
            if (givenGate == true)
            {
                matchGate--;
            }
            for (int m = 0; m < arguments.size(); m++)
            {
                for (int n = 0; n < rand.size(); n++)
                {
                    
                    if (arguments.get(m).equals(rand.get(n)) == true)
                    {
                        matchGate--;
                    }

                }

                
            }
            if (matchGate <= 0)
            {
                match++;
            }
            int evidenceGate = givenArgs.size();
            if (givenGate == true)
            {
                for (int m = 0; m < givenArgs.size(); m++)
                {
                    for (int n = 0; n < rand.size(); n++)
                    {
                        if (givenArgs.get(m).equals(rand.get(n))==true)
                        {
                            evidenceGate--;
                        }
                    }
                
                }
                if (evidenceGate <= 0)
                {
                    gCount++;
                }
            }
            else
            {
                gCount++;
            }


            


        }
        float sum = (float) match /gCount;


            System.out.println("The probability is :");
            System.out.println(sum);
            System.out.println("______________________");
            

    }




    /*
        prior sampling
        Returns: Array list of strings
        Used to generate random values for the bayes net
    */
    private ArrayList<String> priorSampling()
    {
        ArrayList<String> randomVals = new ArrayList<String>();


        String B;
        double b = Math.random();
        if (b <= 0.02)
        {
             B = "Bt";
        }
        else
        {
             B = "Bf";
        }
        randomVals.add(B);

        String E;
        double e = Math.random();
        if (e <= 0.03)
        {
             E = "Et";
        }
        else
        {
             E = "Ef";
        }
        randomVals.add(E);


        String A = "";
        double a = Math.random();
        if (E.equals("Et") == true && B.equals("Bt") == true)
        {
        
            if (a <= 0.97)
            {
                A = "At";
            }
            else
            {
                A = "Af";
            }
        }
        
        if (E.equals("Et") == false && B.equals("Bt") == true)
        {
        
            if (a <= 0.92)
            {
                A = "At";
            }
            else
            {
                A = "Af";
            }
        }

        if (E.equals("Et") == true && B.equals("Bt") == false)
        {
        
            if (a <= 0.36)
            {
                A = "At";
            }
            else
            {
                A = "Af";
            }
        }

        if (E.equals("Et") == false && B.equals("Bt") == false)
        {
        
            if (a <= 0.03)
            {
                A = "At";
            }
            else
            {
                A = "Af";
            }
        }

        randomVals.add(A);

        String J = "";
        double j = Math.random();

        if (A.equals("At") == true)
        {
            if (j <= 0.85)
            {
                J = "Jt";
            }
            else
            {
                J = "Jf";
            }
        }
        else
        {
            if (j <= 0.07)
            {
                J = "Jt";
            }
            else
            {
                J = "Jf";
            }

        }
        randomVals.add(J);

        String M = "";
        double m = Math.random();
        if (A.equals("At") == true)
        {
            if (m <= 0.69)
            {
                 M = "Mt";
            }
            else
            {
                M = "Mf";
            }   
        }
        else
        {
            if (m <= 0.02)
            {
                 M = "Mt";
            }
            else
            {
                M = "Mf";
            } 

        }
        randomVals.add(M);

        return randomVals;
    }

     /*
        setting up the network provided
        used to set up the initial Bayes Net
    */
    private void setupBayes()
    {
        // Burgerlary Node
        ArrayList<Node> burgP = new ArrayList<Node>();
        ArrayList<ArrayList<String>> burgVals = new ArrayList<ArrayList<String>>();
        ArrayList<String> burgVal1 = new ArrayList<String>();
        burgVal1.add("0.02");
        burgVals.add(burgVal1);

        Node burg = new Node("Burglary", burgP, burgVals);

        // Earthquake Node
        ArrayList<Node> earthP = new ArrayList<Node>();
        ArrayList<ArrayList<String>> earthVals = new ArrayList<ArrayList<String>>();
        ArrayList<String> earthVal1 = new ArrayList<String>();
        earthVal1.add("0.03");
        earthVals.add(earthVal1);

        Node earth = new Node("Earth", earthP, earthVals);

        // Alarm Node
        ArrayList<Node> alarmP = new ArrayList<Node>();
        alarmP.add(earth);
        alarmP.add(burg);
        ArrayList<ArrayList<String>> alarmVals = new ArrayList<ArrayList<String>>();
        ArrayList<String> alarmVal1 = new ArrayList<String>();
        alarmVal1.add("T");
        alarmVal1.add("T");
        alarmVal1.add("0.97");
        alarmVals.add(alarmVal1);

        ArrayList<String> alarmVal2 = new ArrayList<String>();
        alarmVal2.add("T");
        alarmVal2.add("F");
        alarmVal2.add("0.92");
        alarmVals.add(alarmVal2);

        ArrayList<String> alarmVal3 = new ArrayList<String>();
        alarmVal3.add("F");
        alarmVal3.add("T");
        alarmVal3.add("0.36");
        alarmVals.add(alarmVal3);

        ArrayList<String> alarmVal4 = new ArrayList<String>();
        alarmVal4.add("F");
        alarmVal4.add("F");
        alarmVal4.add("0.03");
        alarmVals.add(alarmVal4);

        Node alarm = new Node("Alarm", alarmP, alarmVals);

        // JohnCalls Node
        ArrayList<Node> jcallP = new ArrayList<Node>();
        jcallP.add(alarm);
        ArrayList<ArrayList<String>> jcallVals = new ArrayList<ArrayList<String>>();
        ArrayList<String> jcallVal1 = new ArrayList<String>();
        jcallVal1.add("T");
        jcallVal1.add("0.85");
        jcallVals.add(jcallVal1);

        ArrayList<String> jcallVal2 = new ArrayList<String>();
        jcallVal2.add("F");
        jcallVal2.add("0.07");
        jcallVals.add(jcallVal2);

        Node JCalls = new Node("JohnCalls", jcallP, jcallVals);


        // MaryCalls Node
        ArrayList<Node> mcallP = new ArrayList<Node>();
        mcallP.add(alarm);
        ArrayList<ArrayList<String>> mcallVals = new ArrayList<ArrayList<String>>();
        ArrayList<String> mcallVal1 = new ArrayList<String>();
        mcallVal1.add("T");
        mcallVal1.add("0.69");
        mcallVals.add(mcallVal1);

        ArrayList<String> mcallVal2 = new ArrayList<String>();
        mcallVal2.add("F");
        mcallVal2.add("0.02");
        mcallVals.add(mcallVal2);

        Node MCalls = new Node("MaryCalls", mcallP, mcallVals);

        this.bayesNet.add(burg);
        this.bayesNet.add(earth);
        this.bayesNet.add(alarm);
        this.bayesNet.add(JCalls);
        this.bayesNet.add(MCalls);

        

    }

}
