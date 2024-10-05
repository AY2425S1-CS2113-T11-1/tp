package seedu.duke.parser;

import java.util.Scanner;

public class Parser {
    //public static Command parse(String line, int state) {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();


        String[] parts = line.split(" ");

        switch (parts[0]) {

        case "add":
            try {
                //return new AddPatientCommand(parts[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(" ");
            }
            break;

        case "list":
            //if (state == 0) {
                //return new ListPatientCommand();
            //} else if (state == 1) {
                //return new ListTaskCommand();
            //}
            break;

        case "delete":
            try {
                int index = Integer.parseInt(line.substring("delete ".length())) - 1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number out-of-range");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
            }

           // if (state == 0) {
                //return new DelPatientCommand(index);
           // } else if (state == 1) {
                // return new DelTaskCommand(index);
           // }
            break;

        case "select":
            try {
                int index = Integer.parseInt(line.substring("select ".length())) - 1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number out-of-range");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
            }

           // if (state == 0) {
                //return new SelectPatientCommand(index);
            //}
            break;

        case "mark":
            try {
                int index = Integer.parseInt(line.substring("mark ".length())) - 1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number out-of-range");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
            }

           // if (state == 1) {
                // return new markTaskCommand(index);
           // }
            break;

        case "unmark":
            try {
                int index = Integer.parseInt(line.substring("unmark ".length())) - 1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number out-of-range");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
            }

           // if (state == 1) {
                //  return new unmarkTaskCommand(index);
           // }
            break;
        }

       // return null;
    }
    /*
    public static void main(String[] args) {
        System.out.println("Hi");
    }
     */

}
