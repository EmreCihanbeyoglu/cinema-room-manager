package cinema;

import java.text.DecimalFormat;

public class Statistics {
    private final CinemaRoom cinemaRoom;
    private final CashRegister cashRegister;
    
    public Statistics(CinemaRoom cinemaRoom, CashRegister cashRegister) {
        this.cinemaRoom = cinemaRoom;
        this.cashRegister = cashRegister;
    }
    
    public int getNumberOfPurchasedTicketsFromCinemaRoom() {
        return cinemaRoom.getTotalNumberOfSeatsInTheSaloon() - cinemaRoom.getNumberOfAvailableSeatInTheRoom();
    }
    
    public String calculatePurchasedTicketPercentage() {
        int totalNumberOfSeatInTheRoom = cinemaRoom.getTotalNumberOfSeatsInTheSaloon();
        int numberOfPurchasedTicket = getNumberOfPurchasedTicketsFromCinemaRoom();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double percentage= (numberOfPurchasedTicket * 100.0 / totalNumberOfSeatInTheRoom);
        return decimalFormat.format(percentage);
    }
    
    public int getCurrentIncomeFromCashRegister() {
        return cashRegister.getCurrentTotalProfit();
    }
    
    public int getExpectedTotalIncomeFromCinemaRoom() {
        return cashRegister.getExpectedMaximumProfit(cinemaRoom);
    }
    
    
}
