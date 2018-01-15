/* Eleanor Chen
 * Scheduler.java
 * 
 * This class stores static methods used to schedule talks
 */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Scheduler 
{
    
    public Scheduler ()
    {
    }
    
    // makeTalks read file into ArrayList
    public static ArrayList<Talk> makeTalks(String fileName) throws IOException
    {   
        File inFile = new File (fileName);
        Scanner input = new Scanner(inFile);
        try
        {
            ArrayList <Talk> talkList = new ArrayList<Talk>();
            int i = 1;
                
            while(input.hasNext()) 
            {
                String line = input.nextLine();
                String[] tempArray = line.split("\\s+");
                
                // handle the wrong format 
                if (tempArray == null || tempArray.length != 3)
                {
                    System.out.println ("Wrong line format at line " + i);
                    System.exit (0); // if find something wrong then stop
                }
                else if (tempArray[1] == null || 
                         tempArray[1].length() != 4 ||
                         !isInteger(tempArray[1]) ||
                         tempArray[2] == null ||
                         !isInteger(tempArray[2]) ||
                         tempArray[2].length() != 4)
                {
                    System.out.println ("Wrong format for start or end time "
                                        +"at line "+ i); 
                    System.exit (0); // if find something wrong then stop
                    
                }
                
                Talk talk = new Talk(tempArray[0],tempArray[1],tempArray[2]);
                talkList.add(talk);
            }
            
            input.close();
            return talkList;
        }
    
        catch (Exception e)
        {
            System.out.println("Dude you messed up");
            System.out.println(e);
            input.close();
            return new ArrayList<Talk>();
        }
    }
    
    // schedule the max number of talks in one room
    public static ArrayList<Talk> scheduleTalks(ArrayList<Talk> scheduleList)
    {
        // sort the ArrayList
        Collections.sort(scheduleList);
        int i = 0;
        while (i<scheduleList.size()-1)
        {
            // if the time range of the next talk is inside the current talk
            // then remove the current talk
            if (scheduleList.get(i).getEndTime().compareTo(
                scheduleList.get(i+1).getEndTime())>=0)
            {
                scheduleList.remove(i);
            }
            // if the time range of the next talk overlaps the current talk
            // then remove the next talk
            else if (scheduleList.get(i).getEndTime().compareTo(
                     scheduleList.get(i+1).getStartTime())>0)
            {
                scheduleList.remove(i+1);
            }
            else 
            {
                i++;
            }
        }
        
        return scheduleList;
    }
    
    // method to check if the string is an integer
    private static boolean isInteger(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }   
    }
}
