import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class RouletteGame {

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        User userOne = new User("Suroviy", "Lox", 6000);
        start(userOne);
    }

    public static void start(User user) {
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.println(TEXT_PURPLE + "Your balance is: " + user.getBalance() + "!" + TEXT_RESET);
            System.out.println(TEXT_PURPLE + "Choose amount of bid!" + TEXT_RESET);
            int bid = s.nextInt();
            if (user.getBalance() < bid) {
                System.out.println(TEXT_PURPLE + "You not have enough money!" + TEXT_RESET);
                continue;
            }
            int generatedNum = generateNumber();

            isWin(generatedNum, bid, user);
        }
    }

    public static int generateNumber() {
        Random random = new Random();
        return random.nextInt(36);
    }

    public static void isWin(int generatedNum, int bet, User user) {
        Scanner s = new Scanner(System.in);
        int bettedNumber;
        System.out.println(TEXT_PURPLE + "Choose Even, Odd or number!" + TEXT_RESET);
        String choose = s.nextLine();

        switch (choose.toLowerCase()) {
            case "even": {
                if (generatedNum == 0) {
                    System.out.println(TEXT_RED + "You lose! Number is " + generatedNum + "!" + TEXT_RESET);
                    user.setBalance(user.getBalance() - bet);
                    break;
                } else if (generatedNum % 2 == 0) {
                    System.out.println(TEXT_GREEN + "You won " + bet * 2 + "! Number is " + generatedNum + "!" + TEXT_RESET);
                    user.setBalance(user.getBalance() + (bet * 2));
                    break;
                } else {
                    System.out.println(TEXT_RED + "You lose! Number is " + generatedNum + "!" + TEXT_RESET);
                    user.setBalance(user.getBalance() - bet);
                    break;
                }
            }

            case "odd": {
                if (generatedNum == 0) {
                    System.out.println(TEXT_RED + "You lose! Number is " + generatedNum + "!" + TEXT_RESET);
                    user.setBalance(user.getBalance() - bet);
                    break;
                } else if (generatedNum % 2 != 0) {
                    System.out.println(TEXT_GREEN + "You won " + bet * 2 + "! Number is " + generatedNum + "!" + TEXT_RESET);
                    user.setBalance(user.getBalance() + (bet * 2));
                    break;
                } else {
                    System.out.println(TEXT_RED + "You lose! Number is " + generatedNum + "!" + TEXT_RESET);
                    user.setBalance(user.getBalance() - bet);
                    break;
                }
            }

            case "number": {
                while (true) {
                    System.out.println(TEXT_PURPLE + "Choose number in range from 0 to 36!" + TEXT_RESET);
                    bettedNumber = s.nextInt();
                    if (bettedNumber < 0 || bettedNumber > 36) {
                        continue;
                    } else {
                        if (bettedNumber == generatedNum) {
                            System.out.println(TEXT_GREEN + "You won " + bet * 36 + "! Number is " + generatedNum + "!" + TEXT_RESET);
                            user.setBalance(user.getBalance() + (bet * 36));
                            break;
                        } else {
                            System.out.println(TEXT_RED + "You lose! Number is " + generatedNum + "!" + TEXT_RESET);
                            user.setBalance(user.getBalance() - bet);
                            break;
                        }
                    }
                }
            }
        }
    }
}
