/*
Node
CSC 372
Jack Pharies

Node class for Bayes Net


*/


import java.util.*;



public class Node {
    
    private String name;
    private ArrayList<ArrayList<String>> table;
    private ArrayList<Node> parents;


    // constructors
    public Node(String name)
    {
        this.name = name;

    }

    public Node(String name, ArrayList<Node> parents, ArrayList<ArrayList<String>> table)
    {
        this.name = name;

        if (parents.isEmpty() != true)
        {
            ArrayList<Node> temp = new ArrayList<Node>();
            for (int i = 0; i < parents.size(); i++)
            {
                temp.add(parents.get(i));
            }
            this.parents = temp;
        }
        if (table.isEmpty() != true)
        {
            ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < table.size(); i++)
            {
                ArrayList<String> temp2 = new ArrayList<String>();
                for (int j = 0; j < table.get(i).size(); j++)
                {
                    temp2.add(table.get(i).get(j));
                }
                temp.add(temp2);
            }
            this.table = temp;
        }
    }

    // getters and setters
    public String getName()
    {
        return this.name;
    }

    public ArrayList<ArrayList<String>> getTable()
    {
        return this.table;
    }

    public ArrayList<Node> getParents()
    {
        return this.parents;
    }

    public float getVal(ArrayList<String> bools, String charge)
    {
        if (bools.isEmpty() == true)
        {
            if (charge.equals("T"))
            {
                return Float.parseFloat(table.get(0).get(0));
            }
            else
            {
                return 1 - Float.parseFloat(table.get(0).get(0));
            }
            
        }
        else
        {
            int size = bools.size();
           
            for (int i = 0; i < this.table.size(); i++)
            {
                boolean gate = true;
                for (int j = 0; j < bools.size(); j++)
                {
                   
                    if (this.table.get(i).get(j).equals(bools.get(j)) != true)
                    {
                        gate = false;
                    }

                }
                if (gate == true)
                {
                    if (charge.equals("T") == true)
                    {
                        return Float.parseFloat(table.get(i).get(size));
                    }
                    else
                    {
                        return 1 - Float.parseFloat(table.get(i).get(size));
                    }
                }
                
            }
        }
        
        return 0;
    }




}
