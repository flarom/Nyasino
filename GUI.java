import java.util.Scanner;

public class GUI {
    // ansi colors
    public static final String colorReset = "\u001B[0m"; // reset colors
    // color foreground
    public static final String fgBlk = "\u001B[30m";    // foreground black
    public static final String fgRed = "\u001B[31m";    // foreground red
    public static final String fgGrn = "\u001B[32m";    // foreground green
    public static final String fgYlw = "\u001B[33m";    // foreground yellow
    public static final String fgBlu = "\u001B[34m";    // foreground blue
    public static final String fgPrp = "\u001B[35m";    // foreground purple
    public static final String fgCyn = "\u001B[36m";    // foreground cyan
    public static final String fgWit = "\u001B[37m";    // foreground white
    public static final String fgGry = "\u001B[90m";    // foreground gray
    // color background
    public static final String bgBlk = "\u001B[40m";    // background black
    public static final String bgRed = "\u001B[41m";    // background red
    public static final String bgGrn = "\u001B[42m";    // background green
    public static final String bgYlw = "\u001B[43m";    // background yellow
    public static final String bgBlu = "\u001B[44m";    // background blue
    public static final String bgPrp = "\u001B[45m";    // background purple
    public static final String bgCyn = "\u001B[46m";    // background cyan
    public static final String bgWit = "\u001B[47m";    // background white
    public static final String bgGry = "\u001B[100m";   // background gray

    private Scanner scan = new Scanner(System.in);

    private int maxLineLenght = 80;
    private int maxBoxLenght = maxLineLenght - 2;
    private int maxTextLineLenght = maxBoxLenght - 2;

    /**
     * Draw a horizontal separator, for use outside of boxes
     */
    public void drawSeparator() {
        System.out.println("\n " + "─".repeat(maxBoxLenght) + " \n");
    }

    /**
     * Draw the top part of a box
     */
    public void drawBoxTop() {
        System.out.println("╭" + "─".repeat(maxBoxLenght) + "╮");
    }

    /**
     * Draw a horizontal separator, for use inside of boxes
     */
    public void drawBoxSeparator() {
        System.out.println("├" + "─".repeat(maxBoxLenght) + "┤");
    }

    /**
     * Draw a empty line inside of a box
     */
    public void drawBoxEmptyLine() {
        System.out.println("│" + " ".repeat(maxBoxLenght) + "│");
    }

    /**
     * Draw the bottom part of a box
     */
    public void drawBoxBottom() {
        System.out.println("╰" + "─".repeat(maxBoxLenght) + "╯");
    }

    /**
     * Draw the top part of a input field
     */
    public void drawInputTop() {
        System.out.println("╭" + "╴".repeat(maxBoxLenght) + "╮");
    }

    /**
     * Draw the bottom part of a input field
     */
    public void drawInputBottom() {
        System.out.println("╰" + "╴".repeat(maxBoxLenght) + "╯");
    }

    /**
     * Draw a box line with a title in the center
     * 
     * @param title The text string to be written
     */
    public void drawTitle(String title) {
        int titleLength = title.length();

        if (titleLength > maxTextLineLenght) {
            title = title.substring(0, maxTextLineLenght);
            titleLength = maxTextLineLenght;
        }

        int spacesToAdd = (maxTextLineLenght - titleLength) / 2;
        String titlePadding = " ".repeat(spacesToAdd);

        System.out.println(
                "│ " + titlePadding + title + " ".repeat(maxTextLineLenght - titleLength - spacesToAdd) + " │");
    }

    /**
     * Draw multiple lines of text inside of a box
     * 
     * @param text The text to be written
     */
    public void drawText(String text) {
        String[] lines = text.split("\n");

        for (String line : lines) {
            String[] words = line.split(" ");
            StringBuilder currentLine = new StringBuilder();

            for (String word : words) {
                if (currentLine.length() + word.length() + 1 > maxTextLineLenght) {
                    System.out.println(
                            "│ " + currentLine.toString() + " ".repeat(maxTextLineLenght - currentLine.length())
                                    + " │");
                    currentLine = new StringBuilder();
                }
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            }

            if (currentLine.length() > 0) {
                System.out.println(
                        "│ " + currentLine.toString() + " ".repeat(maxTextLineLenght - currentLine.length()) + " │");
            }
        }
    }

    /**
     * Draw a numbered list of strings
     * 
     * @param listItems The items of the list
     */
    public void drawListNumber(String[] listItems) {
        int index = 1;
        for (String item : listItems) {
            String itemWithNumber = index + ". " + item;

            if (itemWithNumber.length() > maxTextLineLenght) {
                itemWithNumber = itemWithNumber.substring(0, maxTextLineLenght);
            }

            System.out.println("│ " + itemWithNumber + " ".repeat(maxTextLineLenght - itemWithNumber.length()) + " │");
            index++;
        }
    }

    /**
     * Draw a bullet list of strings
     * 
     * @param listItems The items of the list
     */
    public void drawListBullet(String[] listItems) {
        for (String item : listItems) {
            String itemWithBullet = "- " + item;

            if (itemWithBullet.length() > maxTextLineLenght) {
                itemWithBullet = itemWithBullet.substring(0, maxTextLineLenght);
            }

            System.out.println("│ " + itemWithBullet + " ".repeat(maxTextLineLenght - itemWithBullet.length()) + " │");
        }
    }

    public void drawTable(String[][] tableItems) {
        int[] columnWidths = new int[tableItems[0].length];

        for (String[] row : tableItems) {
            for (int col = 0; col < row.length; col++) {
                if (row[col].length() > columnWidths[col]) {
                    columnWidths[col] = Math.min(row[col].length(), maxTextLineLenght / tableItems[0].length);
                }
            }
        }

        for (String[] row : tableItems) {
            System.out.print("│");
            for (int col = 0; col < row.length; col++) {
                String cell = row[col];
                if (cell.length() > columnWidths[col]) {
                    cell = cell.substring(0, columnWidths[col]);
                }
                System.out.print(" " + cell + " ".repeat(columnWidths[col] - cell.length()));
            }
            System.out.println();
        }
    }

    /**
     * Ask the user to insert a number inside a determined range
     * 
     * @param min       the minumum value to be accepted
     * @param max       the maximum value to be accepted
     * @param listIndex determines if the returned value is expected to be from a
     *                  numbered list. if true, the returned value is x-1
     * @return the number inserted by de user, according to listIndex
     */
    public int getNumber(int min, int max, boolean listIndex, String prompt) {
        drawInputTop();

        if(!prompt.isBlank()){
            System.out.println("  " + prompt);
        }

        int num = max + 1;

        while (num < min || num > max) {
            System.out.printf("  123: ");
            try {
                num = scan.nextInt();
            } catch (Exception ex) { }
            scan.nextLine();
        }

        if (listIndex)
            num--;

        drawInputBottom();

        return num;
    }

    /**
     * Ask the user to insert a string
     */
    public String getText() {
        drawInputTop();

        String txt = "";

        while (txt.isEmpty()) {
            System.out.printf("  abc: ");
            txt = scan.nextLine();
        }

        drawInputBottom();

        return txt;
    }

    public void drawMsg(String message) {
        drawBoxTop();
        drawText(message);
        drawBoxBottom();
    }

    /**
     * Draw a tiny message box with a size adjusted to the message.
     * 
     * @param message The message to be displayed inside the tiny box.
     */
    public void drawTinyMsg(String message) {
        if (message.length() > maxTextLineLenght) {
            message = message.substring(0, maxTextLineLenght);
        }

        int boxLength = message.length();
        System.out.println("╭" + "─".repeat(boxLength+2) + "╮");

        System.out.println("│ " + message + " │");

        System.out.println("╰" + "─".repeat(boxLength+2) + "╯");
    }

    /**
     * Tries clearing the terminal
     */
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
        }
    }

    public boolean drawConfirm(String message) {
        drawMsg(message);
        drawInputTop();

        String ans = "";

        while (!ans.equals("Y") || !ans.equals("N")) {
            System.out.printf("  Y/N: ");
            ans = scan.nextLine().toUpperCase();
        }

        drawBoxBottom();

        return ans.equals("Y");
    }

    public void drawMoney() {
        int currMoney = Session.getMoney();
        int initMoney = Session.getInitialMoney();

        if (currMoney < initMoney) 
            System.out.println(fgRed); 
        else 
            System.out.println(fgGrn);

        drawTinyMsg(currMoney + Misc.getCurrency());
        
        System.out.println(colorReset);
    }
}
