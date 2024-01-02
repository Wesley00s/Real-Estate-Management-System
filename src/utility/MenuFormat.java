package utility;

import java.util.List;

public class MenuFormat {
    public static void printMenu(List<String> options, String msg) {
        if(options == null || options.isEmpty()) {
            System.out.println("List of options must be non-empty and non-null.");
            return;
        };

        String replace = msg.replaceAll(".", "-");
        StringBuilder listOptions = new StringBuilder(STR."""

                                                \t---\{replace}---
                                                \t|  \{msg}  |
                                                \t---\{replace}---
                                                """);
        for(String option : options) {
            listOptions.append(STR."""
                            \t>>      ( \{option.charAt(0)} ) \{option}
                            """);
        }

        listOptions.append(STR."\t---\{replace}---\n>> ");
        System.out.print(listOptions);
    }
}