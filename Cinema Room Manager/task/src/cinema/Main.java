package cinema;

public class Main {
    private static UserInterface userInterface;

    public static void main(String[] args) {
        start();
        processTheSelectedOption();
    }

    public static void start() {
        userInterface = new UserInterface();
    }

    public static void processTheSelectedOption() {
        int selectedOption = -1;
        while (selectedOption != 0) {
            selectedOption = userInterface.printMenuAndGetSelectedOption();
            switch (selectedOption) {
                case 1:
                    userInterface.printCurrentSeatingPlan();
                    break;
                case 2:
                    userInterface.sellTicket();
                    break;
                case 3: 
                    userInterface.printStatistics();
                    break;
                case 0:
                    userInterface.closeUserInterface();
                    break;
            }
        }
    }
}
