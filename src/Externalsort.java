// On my honor:
//
// -I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// -All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// -I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
//
// Daniel Almeida (adaniel1)
// Yannik Sood (yannik24)

import java.io.IOException;

/**
 * This is the main method responsible for initiating the sort process
 * on an input file.
 * 
 * @author adaniel1 & yannik24
 */
public class Externalsort {
    
    /**
     * Default constructor
     */
    public Externalsort() {
        // nothing to do
    }

    /**
     * Main function reads user input file name and parses.
     * 
     * @param args          input arguments
     * @throws IOException  exception check for file name
     */
    public static void main(String[] args) throws IOException {
        new ExternalSorter(args[0]);
    }

}