package Lab0324;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab0324 {

    static Random rand = new Random();

    public static void main(String[] args) {

        // A lot of time, programming starts with reading data and converting that data into some
        // other format.  For example, this message has the parts separated by vertical bars.
        String message = "Xavier|University|of|Louisiana|1925";
        String[] partsOfMessage = message.split("\\|"); // to split on | need to escape it with \\
        String xula = partsOfMessage[0];
        String univ = partsOfMessage[1];
        String of   = partsOfMessage[2];
        String la   = partsOfMessage[3];
        int year    = Integer.parseInt(partsOfMessage[4]);  // convert (String) year to an integer
        int age = 2021 - year;
        System.out.printf("\n\n%s %s was founded in %d so is %d years old.\n",
            xula, univ, year, age);

        // If I want to know whether "ty|of|Louis" is in the message
        if (message.contains("ty|of|Louis")) {
            System.out.println("Yup, 'ty|of|Louis' is in the message.\n\n");
        }

        // Every XCore Core Curriculum course is in the file 'courses.txt'.
        // Skim that file to see that each line of data has 5 values separated by vertical bars.
        // ART 1090|Art Appreciation|Explorations|Creative Expression & Engagement|3
        // The course, the title, the stage, the area, and the course's credit hours.


        // The user wants to know how many XCore courses XULA has.
        int numCourses = countCourses("courses.txt");
        System.out.println("There are " + numCourses + " courses in the XCore.");



        // TODO: #11 (20 points) - The user is only interested in XCore courses that have "Orleans"
        // in the title.  Write a function that given a (String) file name and a (String) target,
        // outputs the courses with that target.  Your output format is
        // {tab} {title} {tab} {course} {tab} {hours}

        // ROBERT Because any target can be passed as the 2nd argument,
        // your function name should not include the word "Orleans".
        displayOrleansXCORCourses("courses.txt", "Orleans");

    
        // TODO: #12 (25 points) - The user wants to know how many XCore courses there are in a
        // particular stage.  Write a function that give a (String) file name and a (String) stage,
        // returns the number of XCore courses in that stage.  Call your function in this driver to
        // calculate the number of Foundations courses, then the number of Explorations courses, and
        // then the number of 'Engagements with Knowledge and Practice' courses.
        countCoursesInStage("courses.txt", "Foundations");
        countCoursesInStage("courses.txt", "Explorations");
        countCoursesInStage("courses.txt", "Engagements with Knowledge and Practice");



        // TODO: #13 (10 points) - The user wonders how many total credit hours there are in XCore
        // courses.  Write a function that calculates this value based on courses in the file.

        // ROBERT Every course in "courses.txt" is an XCore course
        // but only some of the courses in that file have the subject of "XCOR".

        // ROBERT Here's a how-to for VS Code:
        // click anywhere in line 75 and use F12 to jump back and forth between call and definition.
        totalXCORCreditHours("courses.txt");
        
        
        // TODO: #21 (30 points) - The user is tired of 3 credit hour courses so wants to know the
        // courses that have credit hours different than 3.  Given a (String) filename, write a
        // function that returns a single String of all courses and their hours separated by commas.
        // For example, something like "CPSC 1724 4, CPSC 2735 5, Senior Capstone 1, "
        String Non3CreditCourses = displayNonThreeCreditCourses("courses.txt");
        System.out.println("Courses with credit hours different than 3:\n" + Non3CreditCourses);

        
        
        // TODO: #22 (15 points) - The user is tired of 3 credit hour courses so wants to know the
        // courses that have credit hours different than 3.  Given a (String) filename, write a
        // function that returns an array of Strings of all courses and their hours separated by
        // commas. For example in TODO 21, this return value would have 3 elements.
        String[] courseArray = addCoursesToArray("courses.txt");
        System.out.println(Arrays.toString(courseArray) + "\n");
        
        // TODO: Bonus #1 (20 points) - The user wants to know the shortest course title and the
        // longest course title.  Write a function that returns BOTH those values and then call your
        // function in the driver and output
        // The shortest title is '{shortest title}'.
        // The longest title is '{longest title}'.
        String[] shortestAndLongest = getShortestAndLongest(); 
        System.out.println("The shortest title is '" + shortestAndLongest[0] + "'.");
        System.out.println("The longest title is '" + shortestAndLongest[1] + "'.");
        

        // TODO: Bonus #2 (20 points) - The user only needs to take courses with a course number
        // of at least 3000.  Write a function that outputs those courses and this function must
        // also return the number of courses that were output.
        int numOutputCourses = getCoursesAtLeast3000();
        System.out.println("There were " + numOutputCourses + " courses ouputted");
   
    }


    // WRITE YOUR FUNCTION DEFINITIONS HERE!

    // TODO #11
    static void displayOrleansXCORCourses(String filename, String target) {
        System.out.println("\nXCOR courses with 'Orleans' in the title:");
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String crseTitle = parts[1];
                String crseStage = parts[2];
                String crseArea = parts[3];
                int crseHours = Integer.parseInt(parts[4]);

                // ROBERT Why do you need to check for "XCOR"?
                // Isn't the only important thing that the title includes the {target}?

                if (course.contains("XCOR") && crseTitle.contains(target)) {
                    System.out.printf("\t%s\t%s\t%d\n", crseTitle, course, crseHours);
                }
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // TODO #12

    // ROBERT This function should return the number of courses in the given {stage}.
    // This function should not do any output.

    static void countCoursesInStage(String filename, String stage) {
        try (Scanner sc = new Scanner(new File(filename))) {
            int courseCount = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String crseTitle = parts[1];
                String crseStage = parts[2];
                String crseArea = parts[3];
                int crseHours = Integer.parseInt(parts[4]);

                if (crseStage.equals(stage)) {
                    courseCount++;
                }
            }
            System.out.println("There are " + courseCount + " courses in the '" + stage + "' stage.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // TODO #13

    // ROBERT This function should not output and it must return a value.

    static void totalXCORCreditHours(String filename) {
        int totalHours = 0;
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String crseTitle = parts[1];
                String crseStage = parts[2];
                String crseArea = parts[3];
                String crseHours = parts[4];
                int credits = Integer.parseInt(parts[4]);

                if (course.contains("XCOR")) {
                    totalHours += credits;
                }
            }
            System.out.println("\nThere's a total of " + totalHours + " XCore credit hours.\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // TODO #21
    static String displayNonThreeCreditCourses(String filename) {
        String allCourses = "";
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String crseTitle = parts[1];
                String crseStage = parts[2];
                String crseArea = parts[3];
                String crseHours = parts[4];
                int credits = Integer.parseInt(parts[4]);
                
                if (credits != 3) {
                    allCourses = allCourses + course + " " + credits + ", ";
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    // TODO #22

    // ROBERT This is a nifty instance of re-using code from a previous function.
    // The only issue is the returned array has one element too many.  Fix this.
    static String[] addCoursesToArray(String filename) {
        String line = displayNonThreeCreditCourses("courses.txt");
        String[] courseArray = line.split(", ");
        return courseArray;
    }

    // TODO Bonus #1
    static String[] getShortestAndLongest() {
        int shortestLength = 40;
        String shortestTitle = "";
        int longestLength = 0;
        String longestTitle = "";
        String[] ShortestAndLongest = new String[2];
        try (Scanner sc = new Scanner(new File("courses.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String crseTitle = parts[1];
                String crseStage = parts[2];
                String crseArea = parts[3];
                int crseHours = Integer.parseInt(parts[4]);

                if (crseTitle.length() < shortestLength) {
                    shortestLength = crseTitle.length();
                    shortestTitle = crseTitle;
                }
                if (crseTitle.length() > longestLength) {
                    longestLength = crseTitle.length();
                    longestTitle = crseTitle;
                }
            }
            ShortestAndLongest[0] = shortestTitle;
            ShortestAndLongest[1] = longestTitle;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ShortestAndLongest;
    }

    // TODO Bonus #2
    static int getCoursesAtLeast3000() {
        System.out.println("\nCourses with a number of at least 3000:");
        int numCourses = 0;

        // ROBERT  Oh, no.  The user of this function can only ever use a file named "courses.txt".
        // Fix this so users can decide which file name of courses they want to use.
        try (Scanner sc = new Scanner(new File("courses.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String course = parts[0];
                String crseTitle = parts[1];
                String crseStage = parts[2];
                String crseArea = parts[3];
                int crseHours = Integer.parseInt(parts[4]);

                String[] nameParts = course.split(" ");
                String crseName = nameParts[0];
                int crseNumber = Integer.parseInt(nameParts[1]);

                // ROBERT Does this code work if the course is "CHEM 3456D"?
                if (crseNumber >= 3000) {
                    System.out.println(course);
                    numCourses++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numCourses;
    }


    public static int countCourses(String filename) {
        int count = 0;

        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");  // no need to split because I'm counting lines
                count = count + 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }


    
}
