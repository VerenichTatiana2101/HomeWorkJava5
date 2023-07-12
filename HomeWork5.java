import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class HomeWork5 {
    public static void main(String[] args) {
        Map<String, LinkedList<String>> phoneBook = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            System.out.print("\033[H\033[2J");
            choice();
            int userChoice = userCh(5);
            rec(userChoice, phoneBook);
            System.out.println("Do you want to continue? (y/n)");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("n")) {
                repeat = false;
            }
        }
        sc.close();
    }

    static void choice() {
        System.out.println("1 - show contacts ");
        System.out.println("2 - add contact ");
        System.out.println("3 - search contact");
        System.out.println("4 - delete contact");
        System.out.println("5 - exit");
    }

    static int userCh(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number from 1 to 5: ");
        while (!sc.hasNextInt()) {
            System.out.println("Error! Please enter a number: ");
            sc.next();
        }
        int userChoice = sc.nextInt();
        return userChoice;
    }

    static void rec(int userCh, Map<String, LinkedList<String>> myMap) {
        Map<String, LinkedList<String>> result;
        switch (userCh) {
            case 1:
                showContacts(myMap);
                break;
            case 2:
                result = addphonebook(myMap);
                System.out.println(result);
                break;
            case 3:
                searchContact(myMap);
                break;
            case 4:
                deleteContact(myMap);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Error! Please, enter a number from 1 to 5.");
        }
    }

    //метод запроса данных у пользователя
    static String dataRequest() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input a surname of contact: ");
        String surname = input.nextLine().toUpperCase();
        System.out.print("Input a name of contact: ");
        String name = input.nextLine().toUpperCase();
        String fullName = surname + " " + name;
        return fullName;
    }

    //метод добавления контакта
    static Map<String, LinkedList<String>> addphonebook(Map<String, LinkedList<String>> myPhoneBook) {
        Scanner input = new Scanner(System.in);
        String fullName = dataRequest();
        System.out.print("Input a number phone: ");
        String phoneNumber = input.nextLine().trim();
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            if (myPhoneBook.containsKey(fullName)) {
                myPhoneBook.get(fullName).add(phoneNumber);
            } else {
                myPhoneBook.put(fullName, new LinkedList<>());
                myPhoneBook.get(fullName).add(phoneNumber);
            }
        }
        return myPhoneBook;
    }
    //метод печати всего списка контактов
    static void showContacts(Map<String, LinkedList<String>> myPhoneBook) {
        if (myPhoneBook.isEmpty()) {
            System.out.println("Phone book is empty.");
        } else {
            for (Map.Entry<String, LinkedList<String>> contact : myPhoneBook.entrySet()) {
                System.out.printf("Contact: %s, Phone number: %s\n", contact.getKey(), contact.getValue());
            }
        }
    }

    static void searchContact(Map<String, LinkedList<String>> myPhoneBook) {
        Scanner input = new Scanner(System.in);
        String fullName = dataRequest();
        boolean found = false;
        for (Map.Entry<String, LinkedList<String>> contact : myPhoneBook.entrySet()) {
            if (contact.getKey().toUpperCase().contains(fullName.toUpperCase())) {
                System.out.printf("Contact: %s, Phone number(s): %s\n", contact.getKey(),
                        contact.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No such contact");
        }
    }

    //метод удаления контакта
    static Map<String, LinkedList<String>> deleteContact(Map<String, LinkedList<String>> myPhoneBook) {
        String fullName = dataRequest();
        if (myPhoneBook.containsKey(fullName)) {
            System.out.println(myPhoneBook.get(fullName));
            myPhoneBook.remove(fullName);
            System.out.println("Contact deleted");
        } else {
            System.out.println("There is no such contact, or the data is entered incorrectly");
        }
        return myPhoneBook;
    }
}
