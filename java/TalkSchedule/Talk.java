/* Eleanor Chen 
 * Talk.java
 * 
 * This class is used to model a talk (start and stop times, title of speaker)
 */

public class Talk implements Comparable<Talk>
{
    //instance variables
    private String startTime;
    private String endTime;
    private String title;
    
    public Talk(String t, String sTime, String eTime)
    {
        startTime = sTime;
        endTime = eTime;
        title = t;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getStartTime()
    {
        return startTime;
    }
    
    public String getEndTime()
    {
        return endTime;
    }
    
    // compare talks by starting times, if they equal each other
    // then compare the end time
    public int compareTo(Talk t)
    {
        if (startTime.compareTo(t.getStartTime()) == 0)
        {
            return endTime.compareTo(t.getEndTime());
        }
        else 
        {
            return startTime.compareTo(t.getStartTime());
        }
    }
    
    // provide the print to string
    public String toString()
    {
        return title+"    "+ startTime +"    "+ endTime;
    }
    
}
