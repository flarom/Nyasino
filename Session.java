import java.time.LocalDateTime;

public class Session {
    private static int initialMoney;                    // initial amount of money
    private static int money;                           // current amount of money
    private Game[] games;                               // games loaded in
    private static int gamesPlayed = 0;                 // amount of rounds played
    private static int gamesWon = 0;                    // amount of rounds the user won
    private static int gamesLost = 0;                   // amount of rounds the user lost
    private static int highestMoneyAmount = 0;          // the highest amount of money the user managed to make in this session
    private static int gameWithHighestMoneyAmount = 0;  // the last game the user updated its highestMoneyAmount
    private static LocalDateTime sessionStart;          // date and time when the session started

    private GUI gui = new GUI();
    
    /**
     * get users current amount of money
     * @return users current amount of money
     */
    public static int getMoney(){
        return money;
    }

    /**
     * defines users current amount of money
     * @param m new value
     */
    public static void setMoney(int m){
        money = m;
    }

    /**
     * get users initial amount of money
     * @return users initial amount of money
     */
    public static int getInitialMoney(){
        return initialMoney;
    }
    
    /**
     * increments the amount of games played on this session
     */
    public static void incrementGamesPlayed(){
        gamesPlayed++;
    }

    /**
     * get the amount of games played on this session
     * @return the amount of games played on this session
     */
    public static int getGamesPlayed(){
        return gamesPlayed;
    }

    /**
     * Increments the amount of games won
     */
    public static void incrementGamesWon(){
        gamesWon++;
    }

    /**
     * Gets the current amount of games won
     * @return current amount of games won
     */
    public static int getGamesWon(){
        return gamesWon;
    }

    /**
     * Increments the amount of games lost
     */
    public static void incrementGamesLost(){
        gamesLost++;
    }

    /**
     * Gets the current amount of games lost
     * @return current amount of games lost
     */
    public static int getGamesLost(){
        return gamesLost;
    }

    /**
     * checks if the current amount of money is higher than the current highest money amount.
     * if true, the highest money amount and the game with the highest money amount is updated
     */
    public static void checkHighestMoneyAmount(){
        if(money > highestMoneyAmount){ 
            highestMoneyAmount = money;
            gameWithHighestMoneyAmount = gamesPlayed;
        }
    }

    /**
     * Gets the highest money amount
     * @return the highest money amount
     */
    public static int gethighestMoneyAmount(){
        return highestMoneyAmount;
    }

    /**
     * Gets the game with highest money amount
     * @return the game with highest money amount
     */
    public static int getGameWithHighestMoneyAmount(){
        return gameWithHighestMoneyAmount;
    }

    /**
     * Gets the date and time when the session started
     * @return the date and time when the session started
     */
    public static LocalDateTime getSessionStart(){
        return sessionStart;
    }
    
    public Session(int _money, Game[] _games){
        money = _money;
        initialMoney = _money;
        highestMoneyAmount = _money;
        this.games = _games;

        sessionStart = LocalDateTime.now();
    }

    /**
     * Draws a menu containing all available games.
     * The user can choos wich game to boot
     */
    public void showGamesMenu() {
        gui.drawMoney();

        gui.drawBoxTop();
        gui.drawTitle("Games");
        gui.drawBoxSeparator();
    
        String[] gameNames = new String[games.length];
        for (int i = 0; i < games.length; i++) {
            gameNames[i] = games[i].icon + " " + games[i].name;
        }
        gui.drawListNumber(gameNames);
    
        gui.drawBoxBottom();
    
        int selection = gui.getNumber(1, games.length, true, "");

        gui.clearScreen();

        games[selection].Menu();

        games[selection].CheckGameOver();

        showGamesMenu();
    }

    /**
     * Draws a cool title!
     */
    public void drawTitle(){
        gui.clearScreen();
        System.out.println(Misc.getLogo());
        System.out.println(Misc.getSplashText());
    }
}
