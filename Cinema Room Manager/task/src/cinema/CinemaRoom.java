package cinema;

import java.util.Arrays;

public class CinemaRoom {
    private final int totalNumberOfRow;
    private final int totalNumberOfSeatInEachRow;
    private final int totalNumberOfSeatsInTheSaloon;
    private char[][] seatingPlanAsArray;
    public CinemaRoom(int totalNumberOfRow, int totalNumberOfSeatInEachRow) {
        this.totalNumberOfRow = totalNumberOfRow;
        this.totalNumberOfSeatInEachRow = totalNumberOfSeatInEachRow;
        totalNumberOfSeatsInTheSaloon = calculateTotalNumberOfSeatsInTheSaloon();
        setInitialSeatingPlan();
    }

    public int calculateTotalNumberOfSeatsInTheSaloon() {
        return this.totalNumberOfRow * this.totalNumberOfSeatInEachRow;
    }
    public int getTotalNumberOfRow() {
        return totalNumberOfRow;
    }

    public int getTotalNumberOfSeatInEachRow() {
        return totalNumberOfSeatInEachRow;
    }

    public int getTotalNumberOfSeatsInTheSaloon() {
        return totalNumberOfSeatsInTheSaloon;
    }

    public char[][] getSeatingPlanAsArray() {
        return seatingPlanAsArray;
    }

    private void setInitialSeatingPlan() {
        seatingPlanAsArray = new char[totalNumberOfRow][totalNumberOfSeatInEachRow];
        for(int i = 0; i < totalNumberOfRow; i++) {
            Arrays.fill(seatingPlanAsArray[i], 'S');
        }
    }

    public void setNewReservation(int ticketRowNumber, int ticketSeatNumberInTheRow) {
        seatingPlanAsArray[ticketRowNumber - 1][ticketSeatNumberInTheRow - 1] = 'B';
    }

    public int getNumberOfAvailableSeatInTheRoom() {
        int count = 0;
        for(char[] row : seatingPlanAsArray) {
            for(char seat : row) {
                if(seat == 'S') {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isSeatAvailable(int rowNumber, int seatNumberInTheRow) {
        return seatingPlanAsArray[rowNumber - 1][seatNumberInTheRow - 1] == 'S';
    }

    public boolean isSeatPresentInTheRoom(int rowNumber, int seatNumberInTheRow) {
        return (rowNumber > 0 && rowNumber <= totalNumberOfRow) && (seatNumberInTheRow > 0 && seatNumberInTheRow <= totalNumberOfSeatInEachRow);
    }

}
