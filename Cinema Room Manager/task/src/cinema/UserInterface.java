package cinema;

import java.util.Scanner;
import java.util.StringJoiner;

public class UserInterface {
    private CinemaRoom cinemaRoom;
    private final Scanner scanner;
    private final Statistics statistics;

    private final CashRegister cashRegister;
    public UserInterface() {
        scanner = InputScanner.getInputScanner();
        getNumberOfRowsAndSeatsAndCreateCinemaRoom();
        cashRegister = new CashRegister();
        statistics = new Statistics(cinemaRoom, cashRegister);
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void printCurrentSeatingPlan() {
        int totalNumberOfRow = cinemaRoom.getTotalNumberOfRow();
        int totalNumberOfSeatInEachRow = cinemaRoom.getTotalNumberOfSeatInEachRow();
        char[][] seatingPlanAsArray = cinemaRoom.getSeatingPlanAsArray();
        int RADIX = 10;

        char[][] printedSeatingPlanArray = new char[totalNumberOfRow + 1][totalNumberOfSeatInEachRow + 1];
        // set values for the first line which represents row numbers
        printedSeatingPlanArray[0][0] = ' ';
        for(int i = 1; i <= totalNumberOfSeatInEachRow; i++) {
            printedSeatingPlanArray[0][i] = Character.forDigit(i, RADIX);
        }

        // set values for the other arrays / rows
        for (int i = 1; i <= totalNumberOfRow; i++) {
            printedSeatingPlanArray[i][0] = Character.forDigit(i, RADIX);
            for(int j = 1; j <= totalNumberOfSeatInEachRow; j++) {
                printedSeatingPlanArray[i][j] = seatingPlanAsArray[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder("Cinema:\n");
        for (char[] chars : printedSeatingPlanArray) {
            StringJoiner sj = new StringJoiner(" ");
            for (char aChar : chars) {
                sj.add(Character.toString(aChar));
            }
            sb
                    .append(sj)
                    .append("\n");

        }
        System.out.println(sb);
    }


    public void getNumberOfRowsAndSeatsAndCreateCinemaRoom() {
        int totalRowNumber = -1;
        int totalSeatNumberInEachRow = -1;
        try {
            boolean inputValidationFlagForRowNumber = true;
            boolean inputValidationFlagForSeatNumber = true;


            while(inputValidationFlagForRowNumber){
                System.out.println("Enter the number of rows:");
                totalRowNumber = scanner.nextInt();
                if(totalRowNumber <= 9 && totalRowNumber > 0){
                    inputValidationFlagForRowNumber = false;
                }
            }

            while(inputValidationFlagForSeatNumber){
                System.out.println("Enter the number of seats in each row:");
                totalSeatNumberInEachRow = scanner.nextInt();
                if(totalSeatNumberInEachRow <= 9 && totalSeatNumberInEachRow > 0){
                    inputValidationFlagForSeatNumber = false;
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        this.cinemaRoom = new CinemaRoom(totalRowNumber, totalSeatNumberInEachRow);
    }

    public int printMenuAndGetSelectedOption() {
        int selectedOption = -1;
        System.out.println(Menu.getMenuOptionsAsString());
        boolean inputValidationFlag = true;
        while(inputValidationFlag){
            selectedOption = scanner.nextInt();

            for(int option : Menu.getMenuOptionNumbersAsArray()){
                if(option == selectedOption) {
                    inputValidationFlag = false;
                    break;
                }
            }
        }
        return selectedOption;
    }

    public void sellTicket() {
        int ticketRowNumber = -1;
        int ticketSeatNumberInTheRow = -1;
        try {
            // check if given row and seat number in the range
            while (true) {
                System.out.println("Enter a row number:");
                ticketRowNumber = scanner.nextInt();

                System.out.println("Enter a seat number in that row:");
                ticketSeatNumberInTheRow = scanner.nextInt();

                if(!cinemaRoom.isSeatPresentInTheRoom(ticketRowNumber, ticketSeatNumberInTheRow)) {
                    System.out.println("Wrong input!");
                    continue;
                }

                if(cinemaRoom.isSeatPresentInTheRoom(ticketRowNumber, ticketSeatNumberInTheRow)) {
                    if(!cinemaRoom.isSeatAvailable(ticketRowNumber, ticketSeatNumberInTheRow)){
                        System.out.println("That ticket has already been purchased!");
                        continue;
                    }

                    if(cinemaRoom.isSeatAvailable(ticketRowNumber, ticketSeatNumberInTheRow)) {
                        cinemaRoom.setNewReservation(ticketRowNumber, ticketSeatNumberInTheRow);
                        int ticketPrice = cashRegister.calculateSeatPriceAndTotalProfit(cinemaRoom, ticketRowNumber);
                        System.out.printf("Ticket price: $%d\n", ticketPrice);
                        break;
                    }
                }


            }



        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }



    }

    public void printStatistics() {
        System.out.printf("Number of purchased tickets: %d\n", statistics.getNumberOfPurchasedTicketsFromCinemaRoom());
        System.out.println("Percentage: " + statistics.calculatePurchasedTicketPercentage() +"%");
        System.out.printf("Current income: $%d\n", statistics.getCurrentIncomeFromCashRegister());
        System.out.printf("Total income: $%d\n", statistics.getExpectedTotalIncomeFromCinemaRoom());
    }
    public void closeUserInterface() {
        InputScanner.closeInputScanner();
    }

}
