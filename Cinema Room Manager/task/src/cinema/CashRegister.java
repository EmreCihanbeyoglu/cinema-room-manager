package cinema;

public class CashRegister {
    private final int LIMIT_SEAT_NUMBER_FOR_PRICE;
    private final int FRONT_PRICE;
    private final int BACK_PRICE;
    private int totalProfit;

    public CashRegister() {
        this.LIMIT_SEAT_NUMBER_FOR_PRICE = 60;
        this.FRONT_PRICE = 10;
        this.BACK_PRICE = 8;
        this.totalProfit = 0;
    }
    public int calculateSeatPriceAndTotalProfit(CinemaRoom cinemaRoom, int reservationRowNumber) {

        int totalNumberOfSeats = cinemaRoom.getTotalNumberOfSeatsInTheSaloon();
        int totalRowNumber = cinemaRoom.getTotalNumberOfRow();
        int priceForReservation = 0;

        if(totalNumberOfSeats <= LIMIT_SEAT_NUMBER_FOR_PRICE) {
            priceForReservation = FRONT_PRICE;
        } else if (reservationRowNumber <= totalRowNumber / 2){
            priceForReservation = FRONT_PRICE;
        } else {
            priceForReservation = BACK_PRICE;
        }
        totalProfit += priceForReservation;
        return priceForReservation;
    }

    public int getCurrentTotalProfit() {
        return totalProfit;
    }

    public int getExpectedMaximumProfit(CinemaRoom cinemaRoom) {
        int profit = 0;
        int totalNumberOfSeats = cinemaRoom.getTotalNumberOfSeatsInTheSaloon();
        int numberOfRows = cinemaRoom.getTotalNumberOfRow();
        int numberOfSeats = cinemaRoom.getTotalNumberOfSeatInEachRow();
        if(totalNumberOfSeats <= 60) {
            profit = totalNumberOfSeats * 10;
        } else {
            profit = ((numberOfRows / 2 * 10) + (numberOfRows - numberOfRows / 2) * 8) * numberOfSeats;
        }

        return profit;
    }

}
