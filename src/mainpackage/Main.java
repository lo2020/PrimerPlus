// added this comment, delete later

package mainpackage;

import java.util.Scanner;

public class Main {
    private static String input; // not for main()
    
    public static void main(String[] args) {
        Finder finder = new Finder();
        finder.setPriority(10); // mess around with this later to test for speed
        finder.start();
        
        Scanner scanner = new Scanner(System.in);
        
        mainLoop: while (true) {
            System.out.print("Enter a command: ");
            
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "exit": case "quit": case "stop": case "close":
                    break mainLoop; // also breaks out of switch block
                
                case "get": case "return":
                    System.out.print("Enter the index of the prime you wish to view: ");
                    
                    try {
                        System.out.println(finder.get(Integer.parseInt(scanner.nextLine()) - 2));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("That index is out of range.");
                    } catch (NumberFormatException e) {
                        System.out.println("That input was unrecognized. You must only type in whole numbers.");
                    }
                    
                    break;
                
                case "size": case "length":
                    System.out.println(finder.size());
                    break;
                
                case "last": case "end": case "recent":
                    System.out.println(finder.get(finder.size() - 1));
                    break;
                
                case "all":
                    finder.printAll();
                    break;
                
                case "help": case "options":
                    System.out.println("Possible inputs are as follows:\n\n" +
                            
                            "exit, quit, stop, close:\n" +
                            "\tExits the program.\n\n" +
                            
                            "get, return:\n" +
                            "\tReturn a specific prime number. Enter the index after you hit enter on this keyword.\n\n" +
                            
                            "size, length:\n" +
                            "\tPrints the number of primes found so far.\n\n" +
                            
                            "last, end, recent:\n" +
                            "\tprints the most recently discovered prime.\n\n" +
                            
                            "all:\n" +
                            "\tPrints ALL of the primes found, along with their index.\n\n" +
                            
                            "help, options:\n" +
                            "\tDisplays this window, of course.");
                
                case "live": case "go live":
                    goLive(scanner, finder);
                    break;
                
                case "":
                    break;
                
                default:
                    System.out.println("Invalid input. Type 'help' for a list of commands.");
            }
            
            System.out.println();
        }
        
        finder.interrupt();
        scanner.close();
        System.out.println("Closing.");
    }
    
    // goLive() is sloppy, improve later
    private static void goLive(Scanner scanner, Finder finder) {
        System.out.print("Press enter to go live, or enter anything else to cancel: ");
        input = scanner.nextLine();
        
        if (input.isEmpty()) {
            int size = finder.size();
            
            try {
                while (System.in.available() == 0) { // works?
                    System.out.print(size + ": ");
                    System.out.println(finder.get(size - 1));
        
                    while (size == finder.size()) { // wait until the list is bigger
                        Thread.yield(); // probably doesn't do anything
                    }
                    
                    size++;
                }
            } catch (java.io.IOException e) {
                System.out.println("An IO error has occurred.");
            }
            
            System.out.println("Live printing disabled.");
        } else {
            System.out.println("Live printing cancelled.");
        }
    }
}