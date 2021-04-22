/*
fullDisjunction
Jack Pharies
CSC 372


Creates a Bayes net and uses a full disjunction tabel to calculate any probability



*/


import java.util.*;


public class fullDisjunction {


    private ArrayList<ArrayList<String>> fullTable;
    private ArrayList<Node> bayesNet;

    /*
        Constructor
        has commented out code to test accuracy
    */
    public fullDisjunction() {
        this.fullTable = new ArrayList<ArrayList<String>>();
        this.bayesNet = new ArrayList<Node>();

        setupBayes();
        setupDisJunction();



        /*
        float sumB = 0;
        float sumE = 0;

        for (int i = 0; i < fullTable.size(); i++)
        {
            
            if (fullTable.get(i).get(0).equals("T"))
            {
                sumB = sumB + Float.parseFloat(fullTable.get(i).get(5));
            }
            if (fullTable.get(i).get(1).equals("T"))
            {
                sumE = sumE + Float.parseFloat(fullTable.get(i).get(5));
            }
            
        }

        System.out.println(sumB);
        System.out.println(sumE);
        */

    }


    /* 
        does the math behind calculating a full disction
        Param: an set of arguments
        used to calulate probailities on a BayesNet
    */
    public void calculate(ArrayList<String> arguments)
    {
        boolean givenGate = false;
        ArrayList<String> givenArgs = new ArrayList<String>();
        for (int i = 0; i < arguments.size(); i++)
        {
            if (givenGate == true)
            {
                givenArgs.add(arguments.get(i));
            }
            if (arguments.get(i).equals("given"))
            {
                givenGate = true;
            }

        }
        
        ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
            int firstPos = -1;
            String charge;

            if (arguments.get(0).charAt(0) == 'B')
            {
                firstPos = 0;
            }
            if (arguments.get(0).charAt(0) == 'E')
            {
                firstPos = 1;
            }
            if (arguments.get(0).charAt(0) == 'A')
            {
                firstPos = 2;
            }
            if (arguments.get(0).charAt(0) == 'J')
            {
                firstPos = 3;
            }
            if (arguments.get(0).charAt(0) == 'M')
            {
                firstPos = 4;
            }

            if (arguments.get(0).charAt(1) == 't')
            {
                charge = "T";
            }
            else
            {
                charge = "F";
            }

        for (int i = 0; i < fullTable.size(); i++)
        {
            
            if (fullTable.get(i).get(firstPos).equals(charge) == true)
            {
                lines.add(fullTable.get(i));
            }
        }

        for (int i = 1; i < arguments.size(); i++)
        {
            ArrayList<ArrayList<String>> newLines = new ArrayList<ArrayList<String>>();
            if (arguments.get(i).equals("given") == false)
            {
                int pos = -1;
                String bool;

                if (arguments.get(i).charAt(0) == 'B')
                {
                    pos = 0;
                }
                if (arguments.get(i).charAt(0) == 'E')
                {
                    pos = 1;
                }
                if (arguments.get(i).charAt(0) == 'A')
                {
                    pos = 2;
                }
                if (arguments.get(i).charAt(0) == 'J')
                {
                    pos = 3;
                }
                if (arguments.get(i).charAt(0) == 'M')
                {
                    pos = 4;
                }

                if (arguments.get(i).charAt(1) == 't')
                {
                    bool = "T";
                }
                else
                {
                    bool = "F";
                }

                for (int j = 0; j < lines.size(); j++)
                {
                    if (lines.get(j).get(pos).equals(bool) == true)
                    {
                        newLines.add(lines.get(j));
                    }
                }
                lines = newLines;
            }
        }

        float sum = 0;
        for (int i = 0; i < lines.size(); i++)
        {
            sum = sum + Float.parseFloat(lines.get(i).get(5));
        }






        if (givenGate == true)
        {
            firstPos = -1;
            ArrayList<ArrayList<String>> givenLines = new ArrayList<ArrayList<String>>();

            if (givenArgs.get(0).charAt(0) == 'B')
            {
                firstPos = 0;
            }
            if (givenArgs.get(0).charAt(0) == 'E')
            {
                firstPos = 1;
            }
            if (givenArgs.get(0).charAt(0) == 'A')
            {
                firstPos = 2;
            }
            if (givenArgs.get(0).charAt(0) == 'J')
            {
                firstPos = 3;
            }
            if (givenArgs.get(0).charAt(0) == 'M')
            {
                firstPos = 4;
            }

            if (givenArgs.get(0).charAt(1) == 't')
            {
                charge = "T";
            }
            else
            {
                charge = "F";
            }

            for (int i = 0; i < fullTable.size(); i++)
            {
            
                if (fullTable.get(i).get(firstPos).equals(charge) == true)
                {
                    givenLines.add(fullTable.get(i));
                }
            }
            
            for (int i = 1; i < givenArgs.size(); i++)
            {
                ArrayList<ArrayList<String>> newLines = new ArrayList<ArrayList<String>>();
                if (givenArgs.get(i).equals("given") == false)
                {
                    int pos = -1;
                    String bool;
    
                    if (givenArgs.get(i).charAt(0) == 'B')
                    {
                        pos = 0;
                    }
                    if (givenArgs.get(i).charAt(0) == 'E')
                    {
                        pos = 1;
                    }
                    if (givenArgs.get(i).charAt(0) == 'A')
                    {
                        pos = 2;
                    }
                    if (givenArgs.get(i).charAt(0) == 'J')
                    {
                        pos = 3;
                    }
                    if (givenArgs.get(i).charAt(0) == 'M')
                    {
                        pos = 4;
                    }
    
                    if (givenArgs.get(i).charAt(1) == 't')
                    {
                        bool = "T";
                    }
                    else
                    {
                        bool = "F";
                    }
    
                    for (int j = 0; j < givenLines.size(); j++)
                    {
                        if (givenLines.get(j).get(pos).equals(bool) == true)
                        {
                            newLines.add(givenLines.get(j));
                        }
                    }
                    givenLines = newLines;
                }
            }
            float givenSum = 0;
            for (int i = 0; i < givenLines.size(); i++)
            {
                givenSum = givenSum + Float.parseFloat(givenLines.get(i).get(5));
            }

            sum = sum / givenSum;

        }

        System.out.println("The probability is :");
        System.out.println(sum);
        System.out.println("______________________");










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
        alarmP.add(burg);
        alarmP.add(earth);
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

    /*
        setting up the disjunction provided
        used to set up the disjunction table
    */
    private void setupDisJunction()
    {
        for (int a = 0; a < 2; a++) // burgerlary
        {
            for (int b = 0; b < 2; b++) // earthquake
            {
                for (int c = 0; c < 2; c++) // alarm
                {
                    for (int d = 0; d < 2; d++) // John calls
                    {
                        for (int e = 0; e < 2; e++) // Mary calls
                        {
                            
                            ArrayList<String> temp = new ArrayList<String>();
                            String aS;
                            if (a == 0)
                            {
                                 aS = "T";
                            }
                            else
                            {
                                 aS = "F";
                            }
                            String bS;
                            if (b == 0)
                            {
                                 bS = "T";
                            }
                            else
                            {
                                 bS = "F";
                            }
                            String cS;
                            if (c == 0)
                            {
                                 cS = "T";
                            }
                            else
                            {
                                 cS = "F";
                            }
                            String dS;
                            if (d == 0)
                            {
                                 dS = "T";
                            }
                            else
                            {
                                 dS = "F";
                            }
                            String eS;
                            if (e == 0)
                            {
                                 eS = "T";
                            }
                            else
                            {
                                 eS = "F";
                            }

                            temp.add(aS);
                            temp.add(bS);
                            temp.add(cS);
                            temp.add(dS);
                            temp.add(eS);

                            Node burg = this.bayesNet.get(0);
                            ArrayList<String> temp1 = new ArrayList<String>();
                            Float val1 = burg.getVal(temp1, aS);

                            Node earth = this.bayesNet.get(1);
                            ArrayList<String> temp2 = new ArrayList<String>();
                            Float val2 = earth.getVal(temp2, bS);


                            Node alarm = this.bayesNet.get(2);
                            ArrayList<String> temp3 = new ArrayList<String>();
                            temp3.add(aS);
                            temp3.add(bS);
                            Float val3 = alarm.getVal(temp3, cS);


                            Node jc = this.bayesNet.get(3);
                            ArrayList<String> temp4 = new ArrayList<String>();
                            temp4.add(cS);
                            Float val4 = jc.getVal(temp4, dS);

                            Node mc = this.bayesNet.get(4);
                            ArrayList<String> temp5 = new ArrayList<String>();
                            temp5.add(cS);
                            Float val5 = mc.getVal(temp5, eS);
                            


                            Float finalVal = val1 * val2 * val3 * val4 * val5;

                            temp.add(String.valueOf(finalVal));
                            
                            this.fullTable.add(temp);

                        }
                    }

                }

            }
        

        }
        
    }









}