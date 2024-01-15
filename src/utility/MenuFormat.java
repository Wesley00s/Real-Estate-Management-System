package utility;

import java.util.List;

public class MenuFormat {
    public static void printMenu(List<String> options, String msg) {
        if (options == null || options.isEmpty()) {
            System.out.println("List of options must be non-empty and non-null.");
            return;
        }
        int cont = 1;
        String replace = msg.replaceAll(".", "-");
        StringBuilder listOptions = new StringBuilder(STR."""
                                              \n\t---\{replace}---
                                                \t|  \{msg}  |
                                                \t---\{replace}---
                                                """);

        for (String option : options) {

            if (option.equals(options.getLast())) {
                option = STR."\u001B[31m\{option}\u001B[0m";
            }

            listOptions.append(STR."""
                            \t>>      ( \{cont++} ) \{option}
                            """);

        }
        listOptions.append(STR."\t---\{replace}---\n>> ");
        System.out.print(listOptions);
    }
}