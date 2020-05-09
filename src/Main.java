import Infrastructure.IPersonRepository;
import Infrastructure.Person;
import Infrastructure.PersonRepositoryFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static IPersonRepository _repository;

    public static void main(String[] args) {

        StartApplication();

    }

    private static void StartApplication() {
        PersonRepositoryFactory factory = new PersonRepositoryFactory(ChooseRepository());
        _repository = factory.RetrieveRepository();

        do {
            System.out.println("Person List Application");
            System.out.println("[1] Show List");
            System.out.println("[2] Add Person");
            System.out.println("[3] Update Person");
            System.out.println("[4] Delete Person");
            System.out.println("[R] Restart Application");
            PrintLine();
            HandleMenuChoice();


        } while (true);
    }

    // region Function Methods
    private static String ChooseRepository() {

        boolean isValid = false;
        String input = "";
        String[] validChoices = new String[]{"1", "2"};
        do {
            System.out.println("[1] Memory [Temp Repository]");
            System.out.println("[2] Json File [Persistent Repository]");
            PrintLine();
            input = ReadLine("Please choose Repository");
            ClearConsole();
        } while (Arrays.asList(validChoices).contains(input) == false);

        switch (input) {
            case "1":
                return "Memory";
            case "2":
                return "Json";
        }

        return null;
    }

    private static void HandleMenuChoice() {
        String input = ReadLine("Please make a valid selection");

        switch (input) {
            case "1":
                ShowList();
                break;
            case "2":
                AddPerson();
                break;
            case "3":
                EditPerson();
                break;
            case "4":
                RemovePerson();
                break;
            case "R":
                ClearConsole();
                StartApplication();
                break;
            default:
                HandleMenuChoice();
                break;
        }

        ReadLine("Press any key to return to main menu");
        ClearConsole();
    }

    private static void ShowList() {
        ClearConsole();
        System.out.println("List of Records");

        ArrayList<Person> records = _repository.List();
        if (records.size() == 0) {
            System.out.println("There is no person in the list yet");
        } else {

            PrintLine();
            records.forEach(x -> {

                System.out.println(MessageFormat.format("[{0}] {1}", x.getId(), x.getFullName()));

            });
            PrintLine();
        }

    }

    private static void AddPerson() {
        ClearConsole();
        System.out.println("Adding New Person");
        PrintLine();
        _repository.Add(ReadLine("First Name:"), ReadLine("Last Name:"));
        ShowList();
    }

    private static void EditPerson() {
        ShowList();
        String id = ReadLine("Please type Id of the person, you would like to edit");
        _repository.Update(Integer.parseInt(id), ReadLine("New First Name:"), ReadLine("New Last Name:"));
        ShowList();

    }

    private static void RemovePerson() {
        ShowList();
        String id = ReadLine("Please type Id of the person, you would like to remove");
        _repository.Remove(Integer.parseInt(id));
        ShowList();
    }

// endregion

    //region Console Helpers
    private static String ReadLine(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void PrintLine() {
        System.out.println("----------------------");
    }

    private static void ClearConsole() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_1);
            Thread.sleep(30);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }

    }

// endregion
}
