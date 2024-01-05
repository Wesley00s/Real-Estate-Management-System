package utility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuFormat {
    public static void printMenu(List<String> options, String msg) {
        if (options == null || options.isEmpty()) {
            System.out.println("List of options must be non-empty and non-null.");
            return;
        }

        String replace = msg.replaceAll(".", "-");
        StringBuilder listOptions = new StringBuilder(STR."""
                                              \n\t---\{replace}---
                                                \t|  \{msg}  |
                                                \t---\{replace}---
                                                """);

        Set<Character> uniqueStartingLetters = new HashSet<>();

        for (String option : options) {
            char startingLetter = option.charAt(0);

            while (uniqueStartingLetters.contains(startingLetter)) {
                startingLetter = (char) (startingLetter + 1);
            }

            if (option.equals(options.getLast())) {
                option = STR."\u001B[31m\{option}\u001B[0m";
            }

            uniqueStartingLetters.add(startingLetter);
            listOptions.append(STR."""
                            \t>>      ( \{startingLetter} ) \{option}
                            """);
        }

        listOptions.append(STR."\t---\{replace}---\n>> ");
        System.out.print(listOptions);
    }
}