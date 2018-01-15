This program creates a list of talks chosen by the maximum number of talks from 
a text file. To run the program, you type "java scheduleTest" in terminal. The file 
format in each line is: first title then whitespace or tab, four digit 
startTime, whiteSpace or tab, and four digit endTime. Both startTime and 
endTime are in 24 hour time format. The title was only one word, as they were 
given in the talks.txt file. 
To design this, Talk.java implements comparable interface using the compareTo()
method, which is used to sort the arrayList. First, you compare the starting 
time, and if they are equal, you then comare the end time. The comparison of 
the two are regular string comparisons. I also created a toString method to
print out the talk. in Schedule.java class, I implemented two static methods
makeTalks and scheduleTalks. makeTalks reads a text file and creates an 
ArrayList. IF the format of the text file is wrong, the program will print out
the line number where the wrong format occurs and exit the program. sceduleTalks
provides an algorithm for scheduling  the maximum number of talks in one room.
First, I sorted the talks, then I looped through each talk while comparing each
talk with the next talk. If the time range of the next talk is within the
current talk, then, remove the current talk. If the time range of the next
talk overlaps with the current talk, then remove the next talk. If there are
not conflicts with the next talk and the current talk, then go to the next
loop.