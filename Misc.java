import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Misc {
    private static Path miscFolder = Paths.get(System.getProperty("user.dir"), "misc");

    /**
     * Get a Splash Text
     * @return a randomly selected string, with decorations
     */
    public static String getSplashText() {
        try {
            Path filePath = Paths.get(miscFolder.toString(), "splashes.txt");

            List<String> lines = Files.readAllLines(filePath);

            if (!lines.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(lines.size());
                try{
                    return GUI.fgYlw + " ".repeat(getSplashWhiteSpace() - lines.get(randomIndex).length()) + lines.get(randomIndex) + "!";
                } catch (Exception ex){
                    return GUI.fgYlw + lines.get(randomIndex) + "!";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return GUI.fgYlw + "No splash text available!";
    }

    private static int getSplashWhiteSpace(){
        try {
            Path filePath = Paths.get(miscFolder.toString(), "logo.txt");

            List<String> lines = Files.readAllLines(filePath);

            if (!lines.isEmpty()) {
                String lastLine = lines.toArray()[lines.size()-1].toString();
                return lastLine.length()-1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * Get application logo
     * @return the application logo
     */
    public static String getLogo(){
        try {
            Path filePath = Paths.get(miscFolder.toString(), "logo.txt");

            String logo = Files.readString(filePath);

            return logo;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "No logo available!";
    }

    /**
     * Get Game Over logo
     * @return the Game Over logo
     */
    public static String getEndLogo() {
        try {
            Path filePath = Paths.get(miscFolder.toString(), "endlogo.txt");

            String logo = Files.readString(filePath);

            return "\u001B[31m"+logo+"\u001B[0m";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "you lost!";
    }

    /**
     * Get currency string
     * @return the currency string
     */
    public static String getCurrency(){
        try {
            Path filePath = Paths.get(miscFolder.toString(), "currency.txt");

            String logo = Files.readString(filePath);

            return logo;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "$";
    }
}
