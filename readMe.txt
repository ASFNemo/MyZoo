COMP1202 Coursework - Zoo Simulation Coursework

Asher S Fischbaum

Parts completed: All

    N.B: the current simulation is set to run for 20 months before coming to an end.

Extensions completed:

    - Every month the current state of the zoo is saved into the zooCurrentState.txt file. If you would want to you
    could just copy and paste the contents into the myZoo.txt file and carry on from where you left of.

    - As a simple second extension I added the possibility that the animals get an ailment. This goes from high
    likely-hood low affect ailments like a broken leg that reduces the animals healths by 2 to very low likely-hood but
    high affect ailments like the black plague that kills the animal.

JavaDoc:

    You will likely realise tha most of my commenting is done in java doc. In these comments is generally explain what
    is happening in the method, what parameters it takes and what it returns. I have however used some in line
    commenting when I want to point something specific out.


Running this programme:

    To run this programme from the command line on mac follow the steps outlined below:

    - Move into the coursework directory using the instructions:                cd Coursework
    - Move into the src directory using the instruction:                        cd src
    - To show all the documents in the project type:                            ls
    - To compile the Zoo simulation, compile the Zoo class:                     javac Zoo.java
    - To run the simulation while passing my config file (myZoo.txt):           java Zoo ../myZoo.txt
    - To run the simulation with your own config file:                          java Zoo [route to your file]