package encryptiondecryptionn;

  
import java.util.Scanner;

public class CaesarCipherEncoderDecoder {
    static final char[] ALPHABET = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static char[] alphabetEncode = new char[ALPHABET.length];
    public static final int ENCODE = 1;
    public static final int DECODE = 2;
    public static final int SHIFT_NOT_KNOWN = 1;
    public static final int SHIFT_KNOWN = 2;

    private static int getChoice() {
        int userValue = 0;
        boolean error = false;

        Scanner scan = null;
        do {
            try {
                scan = new Scanner(System.in);
                userValue = scan.nextInt();

                error = false;
            } catch (Exception e) {
                System.out.println("\nThis value is not available, please try again.");
                error = true;
            }
        } while (error || (userValue != ENCODE && userValue != DECODE && userValue != SHIFT_NOT_KNOWN && userValue != SHIFT_KNOWN));
        return userValue;
    }

    private static int getShift() {
        int userValue = 0;
        boolean error = false;

        Scanner scan = null;
        do {
            try {
                scan = new Scanner(System.in);
                userValue = scan.nextInt();

                error = false;
            } catch (Exception e) {
                System.out.println("\nThis value is not available, please try again.");
                error = true;
            }
        } while (error);
        return userValue;
    }

    private static String getMessage(String messageEncoreOrDecode) {
        System.out.println("Enter the message that you want to " + messageEncoreOrDecode + " .");

        String userValue = "";
        boolean error = false;

        Scanner scan = null;
        do {
            try {
                scan = new Scanner(System.in);
                userValue = scan.nextLine();

                error = false;
            } catch (Exception e) {
                System.out.println("\nThis value is not available, please try again.");
                error = true;
            }
        } while (error);
        return userValue;
    }

    public static char[] changeAlphabet(int position) {
        char[] alphabetEncode = new char[ALPHABET.length];

        for (int i = 0; i < ALPHABET.length; i++) {
            if (i + position >= ALPHABET.length) {
                alphabetEncode[i] = ALPHABET[i + position - 26];
            } else {
                alphabetEncode[i] = ALPHABET[i + position];
            }
        }
        return alphabetEncode;
    }

    private static void encodeDecode(char[] message, char[] actualAlphabet, char[] newAlphabet) {
        System.out.print("Your message : ");

        for (char c: message) {
            for (int i = 0; i < actualAlphabet.length; i++) {
                if (Character.toUpperCase(c) == actualAlphabet[i]) {
                    System.out.print(newAlphabet[i]);
                }
            }
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Ceasar encoder/decoder.");

        System.out.println("Do you want to code or decode ?");
        System.out.println("Enter 1 if you want to encode.");
        System.out.println("Enter 2 if you want to decode.");
        int choice = getChoice();
        int choiceBruteForce;

        if (choice == ENCODE) {
            String message = getMessage("encode");
            System.out.println("Enter 1 if you want to encode and you want to try all the possibilityes of shift.");
            System.out.println("Enter 2 if you want to encode and you know the shift that you want.");
            choiceBruteForce = getChoice();

            if (choiceBruteForce == SHIFT_NOT_KNOWN) {
                for (int i = 1; i < ALPHABET.length; i++) {
                    alphabetEncode = changeAlphabet(i);
                    System.out.print("Shift of : " + i + " ");
                    encodeDecode(message.toCharArray(), ALPHABET, alphabetEncode);
                }
            }

            if (choiceBruteForce == SHIFT_KNOWN) {
                System.out.println("Enter the value of the shift");
                int shiftValue = getShift();
                alphabetEncode = changeAlphabet(shiftValue);
                encodeDecode(message.toCharArray(), ALPHABET, alphabetEncode);
            }
        }

        if (choice == DECODE) {
            String message = getMessage("decode");
            System.out.println("Enter 1 if you want to decode and you don't know the shift.");
            System.out.println("Enter 2 if you want to decode and you know the shift.");
            choiceBruteForce = getChoice();

            if (choiceBruteForce == SHIFT_NOT_KNOWN) {
                for (int i = 1; i < ALPHABET.length; i++) {
                    alphabetEncode = changeAlphabet(i);
                    System.out.print("Shift of : " + i + " ");
                    encodeDecode(message.toCharArray(), alphabetEncode, ALPHABET);
                }
            }

            if (choiceBruteForce == SHIFT_KNOWN) {
                System.out.println("Enter the value of the shift");
                int shiftValue = getShift();
                alphabetEncode = changeAlphabet(shiftValue);
                encodeDecode(message.toCharArray(), alphabetEncode, ALPHABET);
            }
        }
    }
}