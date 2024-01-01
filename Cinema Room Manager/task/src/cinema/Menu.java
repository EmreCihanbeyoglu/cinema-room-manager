package cinema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public enum Menu {
    SHOW_THE_SEATS("1. Show the seats", 1),
    BUY_A_TICKET("2. Buy a ticket", 2),
    STATISTICS("3. Statistics", 3),
    EXIT("0. Exit", 0);

    private final String optionText;
    private final int optionIndex;

    private Menu(String optionText, int optionIndex) {
        this.optionText = optionText;
        this.optionIndex = optionIndex;
    }

    public String getOptionText() {
        return optionText;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public static int[] getMenuOptionNumbersAsArray() {
        ArrayList<Integer> numberOptions = new ArrayList<>();
        for(Menu option : values()) {
            numberOptions.add(option.optionIndex);
        }
        return numberOptions.stream().mapToInt(i -> i).toArray();
    }

    public static String getMenuOptionsAsString() {
        StringJoiner sj = new StringJoiner("\n");
        for(Menu option : values()) {
            sj.add(option.optionText);
        }
        return sj.toString();
    }
}
