/**
 * Caesar Cipher
 * The goal for this project is to write a program that encrypts and decrypts a
 * text files using a Caesar cipher. The program first displays a menu for the user.
 * The menu contains 3 options to encrypt a file, to decrypt a file, or to quit 
 * the program. The user enters 1, 2, or 3 for their choice. If the user chooses either
 * 1 or 2, the program will then ask the user for the file name and the key. The key would
 * be used to encrypt or decrypt a file. A new file is made with a txt or enc extension
 * depending if the user wanted encrypt or decrypt a file. The menu is then 
 * displayed again for the user to make their choice and the process is repeated,
 * until the user chooses option 3. 
 */
package caesar_cipher;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class CaesarCipher {

    /**
     * This method displays a menu for the user. The user can choose from
     * options 1-3 to encrypt, decrypt, or quit the program. 
     * Precondition: A user must enter an int value of either 1, 2, or 3. 
     * Postcondition: If a value other then 1, 2, or 3 was entered an error message will output
     * asking the user to re-enter the value. If one 1, 2, or 3 was inputed that
     * number would be returned.
     * @return An int value of either 1, 2, or 3 is returned
     */
    public static int getMenuOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.println("3. Quit");
        System.out.println("What would you like to do?");
        int menuInput = input.nextInt();
        while (!(menuInput == 1 || menuInput == 2 || menuInput == 3)) {
            System.out.println("That is an invalid menu option. Please re-enter 1, 2, or 3 as your menu option. ");
            menuInput = input.nextInt();
        }
        input.close();
        return menuInput;
    }

    /**
     * This method will make a new enc file from a txt file and encrypt it with
     * a number key. The method will change each character, depending on the key
     * entered by the user. 
     * Precondition: The user has entered a name for a file and has entered a 
     * int number for the key. 
     * Postcondition: If the user entered a correct txt file and a key number. 
     * A new file with a enc extension would be made that encrypted the original
     * data from the txt file with the given key. If the user inputed an incorrect 
     * file then the program would output an error message that the file was the incorrect
     * type.
     * @param inputFile - The file which is going to be encrypted.
     * @param key - The number which the file is going to be encrypted by.
     */
    public static void encrypt(File inputFile, int key) throws Exception {
        Scanner input = new Scanner(inputFile);
        String fileName = inputFile.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (fileExtension.equals(".txt")) {
            fileName = fileName.replace(".txt", ".enc");
            PrintWriter output = new PrintWriter(fileName);
            while (input.hasNext()) {
                String text = input.nextLine();
                for (int i = 0; i < text.length(); i++) {
                    char character = text.charAt(i);
                    character += key;
                    output.print(character);
                }
                output.println();
            }
            input.close();
            output.close();
            System.out.println("The file has been encrypted.");
        } else {
            System.out.println("That is not correct type of file. It does not have a txt extension.");
        }
    }

    /**
     * This method will make a new txt file from a enc file and decrypt it with
     * a number key. The method will change each character back to its original
     * character depending on the key entered by the user. 
     * Precondition: The user has entered a name for a file and has entered a 
     * int number for the key. 
     * Postcondition: If the user entered a correct enc file and a key
     * number. A new file with a txt extension would be made that decrypted the
     * encrypted data from the enc file with the given key. If the user inputed
     * an incorrect file then the program would output an error message that the
     * file was the incorrect type.
     * @param inputFile - The file which is going to be decrypted.
     * @param key - The number which the file is going to be decrypted by.
     */
    public static void decrypt(File inputFile, int key) throws Exception {
        Scanner input = new Scanner(inputFile);
        String fileName = inputFile.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (fileExtension.equals(".enc")) {
            fileName = fileName.replace(".enc", ".txt");
            PrintWriter output = new PrintWriter(fileName);
            while (input.hasNext()) {
                String text = input.nextLine();
                for (int i = 0; i < text.length(); i++) {
                    char character = text.charAt(i);
                    character -= key;
                    output.print(character);
                }
                output.println();
            }
            input.close();
            output.close();
            System.out.println("The file has been decrpyted.");
        } else {
            System.out.println("That is not correct type of file. It does not have a enc extension.");
        }
    }

    /**
     * This method ask the user for a file name and will return the file that
     * the user has inputed. 
     * Precondition: The user has entered 1 or 2 for the menu option. The user
     * has chosen to encrypt or decrypt a file, and now needs to input a file name. 
     * Postcondition: The name of the file the user has inputed is returned.
     * @return A file entered by the user is returned.
     */
    public static File getFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of the file?");
        String fileName = input.next();
        File inputFile = new File(fileName);
        input.close();
        return inputFile;
    }

    /**
     * This method ask the user for a key which would be used to encrypt or
     * decrypt a file. 
     * Precondition: The user has entered 1 or 2 for the menu option. The user 
     * has chosen to encrypt or decrypt a file and now needs a key to use for those files. 
     * Postcondition: The user enters an int number and that number is returned.
     * @return An int number entered by the user is returned.
     */
    public static int getKey() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is the key for this file?");
        int key = input.nextInt();
        input.close();
        return key;
    }

    public static void main(String[] args) throws Exception {
        //This calls the method which displays the menu. The number that the user 
        //enters is then intilized in menuOption.
        int menuOption = getMenuOption();
        //This while loop will keep on repeating, until the user chooses option 3
        //which will stop the loop and quit the program.
        while (!(menuOption == 3)) {
            //If the users chooses option 1, the user will then enter a file name and 
            //a key. Then a new file would be made using the informaton from the orginal
            //file and using the key to encrpyt it. 
            if (menuOption == 1) {
                encrypt(getFile(), getKey());
                //If the users chooses option 2, the user will then enter a file name and 
                //a key. Then a new file would be made using the informaton from the orginal
                //file and using the key to decrpyt it. 
            } else if (menuOption == 2) {
                decrypt(getFile(), getKey());
            }
            //This redisplays the menu for the user to choose their options again.
            menuOption = getMenuOption();
        }

    }

}
