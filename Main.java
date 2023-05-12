import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();

    }

    private static String generateStrongPW(int numberOfCharacters){
        String alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //26
        ArrayList<Character> alphabetUpperCharList = convertToCharacterArrayList(alphabetUpperCase);
        String alphabetLowerCase = "abcdefghijklmnopqrstuvwxyz"; //26
        ArrayList<Character> alphabetLowerCharList = convertToCharacterArrayList(alphabetLowerCase);
        String numbers = "0123456789123456789"; //19
        ArrayList<Character> numbersCharList = convertToCharacterArrayList(numbers);
        String symbols = "!@#$%^&*()-=,./:;<>"; //17
        ArrayList<Character> symbolsCharList = convertToCharacterArrayList(symbols);
        String combined = alphabetUpperCase + alphabetLowerCase + numbers + symbols; //79
        ArrayList<Character> combinedCharList = convertToCharacterArrayList(combined);
        ArrayList<Character> randomCombinedCharList = randomizeIndex(combinedCharList);

        ArrayList<Integer> randomNumbersList = new ArrayList<Integer>();
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        boolean containsNumbers = false;
        boolean containsSymbols = false;

        while(true){
            if(randomNumbersList.size() == numberOfCharacters){
                String password = "";
                for(int i=0; i<randomNumbersList.size(); i++){
                    password += randomCombinedCharList.get(i);
                }
                while(true){
                    for(int i=0; i<password.length(); i++){
                        if(alphabetUpperCharList.contains(password.charAt(i))){
                            containsUpperCase = true;
                        }
                        if(alphabetLowerCharList.contains(password.charAt(i))){
                            containsLowerCase = true;
                        }
                        if(numbersCharList.contains(password.charAt(i))){
                            containsNumbers = true;
                        }
                        if(symbolsCharList.contains(password.charAt(i))){
                            containsSymbols = true;
                        }
                    }
                    if(containsUpperCase && containsLowerCase && containsNumbers && containsSymbols){
                        return password;
                    }else{
                        ArrayList<Integer> usedIndexList = new ArrayList<Integer>();
                        ArrayList<Character> passwordCharList = convertToCharacterArrayList(password);
                        if(!containsUpperCase){
                            while(true){
                                int randomIndex = getRandomNumber(1, password.length());
                                int randomUpperCaseLetter = getRandomNumber(1, alphabetUpperCase.length());
                                if(!usedIndexList.contains(randomIndex)){
                                    passwordCharList.set((randomIndex-1), alphabetUpperCase.charAt(randomUpperCaseLetter-1));
                                    usedIndexList.add(randomIndex);
                                    break;
                                }
                            }
                        }
                        if(!containsLowerCase){
                            while(true){
                                int randomIndex = getRandomNumber(1, password.length());
                                int randomLowerCaseLetter = getRandomNumber(1, alphabetLowerCase.length());
                                if(!usedIndexList.contains(randomIndex)){
                                    passwordCharList.set((randomIndex-1), alphabetLowerCase.charAt(randomLowerCaseLetter-1));
                                    usedIndexList.add(randomIndex);
                                    break;
                                }
                            }
                        }
                        if(!containsNumbers){
                            while(true){
                                int randomIndex = getRandomNumber(1, password.length());
                                int randomNumber = getRandomNumber(1, numbers.length());
                                if(!usedIndexList.contains(randomIndex)){
                                    passwordCharList.set((randomIndex-1), numbers.charAt(randomNumber-1));
                                    usedIndexList.add(randomIndex);
                                    break;
                                }
                            }
                        }
                        if(!containsSymbols){
                            while(true){
                                int randomIndex = getRandomNumber(1, password.length());
                                int randomSymbol = getRandomNumber(1, symbols.length());
                                if(!usedIndexList.contains(randomIndex)){
                                    passwordCharList.set((randomIndex-1), symbols.charAt(randomSymbol-1));
                                    usedIndexList.add(randomIndex);
                                    break;
                                }
                            }
                        }
                        password = "";
                        for(int i=0; i<passwordCharList.size(); i++){
                            password += passwordCharList.get(i);
                        }

                    }
                }

            }
            randomNumbersList.add(getRandomNumber(1,79));
        }
    }
    private static ArrayList<Character> randomizeIndex(ArrayList<Character> combined){
        ArrayList<Character> newCombined = new ArrayList<Character>();
        ArrayList<Integer> usedNumbersList = new ArrayList<Integer>();
        while(true){
            if(newCombined.size() == combined.size()){
                return newCombined;
            }
            int randomIndex = getRandomNumber(1, combined.size());
            if(!usedNumbersList.contains(randomIndex)){
                newCombined.add(combined.get(randomIndex-1));
                usedNumbersList.add(randomIndex);
            }
        }
    }
    private static int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max - min + 1)) + min;

    }
    private static ArrayList<Character> convertToCharacterArrayList(String input){
        ArrayList<Character> charactersList = new ArrayList<Character>();
        for(int i=0; i<input.length(); i++){
            charactersList.add(input.charAt(i));
        }
        return charactersList;
    }
    private static void menu(){
        boolean exit = false;
        while(true){
            if(exit){
                break;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Strong Password Generator");
            System.out.println("Enter the number of characters for your password (has to be at least 15 characters): ");
            boolean hasInt = scanner.hasNextInt();
            if(hasInt){
                int input = scanner.nextInt();
                scanner.nextLine();
                if(input >= 15){
                    String password = generateStrongPW(input);
                    System.out.println("The password is: " + password);
                    while(true){
                        System.out.println("Choose one:");
                        System.out.println("1. Generate another password");
                        System.out.println("2. Restart");
                        System.out.println("3. Exit");
                        hasInt = scanner.hasNextInt();
                        if(hasInt){
                            int inputTwo = scanner.nextInt();
                            scanner.nextLine();
                            if(inputTwo == 1){
                                String anotherPassword = generateStrongPW(input);
                                System.out.println("The password is: " + anotherPassword);
                            }else if(inputTwo == 2){
                                break;
                            }else if (inputTwo == 3){
                                exit = true;
                                break;
                            }else{
                                System.out.println("Invalid choice. Please try again.");
                            }
                        }else{
                            System.out.println("You did not enter a valid number. Please try again.");
                        }
                    }


                }else{
                    System.out.println("The password is not at least 15 characters. Please try again.");
                }
            }else{
                System.out.println("You did not enter a valid number. Please try again.");
            }
        }


    }
}
