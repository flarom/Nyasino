import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Game {
    public String author;       // your name/repo name
    public String name;         // name of the game
    public String icon;         // emoji/character to be used as the icon
    public String description;  // brief description of the game, can be anything
    public String tutorial;     // how to play the game

    public GUI gui = new GUI();

    public abstract void Menu();    // REQUIRED: function to display a game menu, use drawGenericMenu for the default game menu
    public abstract void Play();    // REQUIRED: main function to play the game


    /**
     * Returns a value selected by the user (between 1 and the users money) and subtract it from the users money
     */
    public int GetBet(){
        int bet = gui.getNumber(1, Session.getMoney(), false, "Select amount to bet (1-" + Session.getMoney() + "):");

        Session.setMoney(Session.getMoney() - bet);

        return bet;
    }

    /**
     * Checks if the users currently has no money, if true, displays the Game Over Screen
     */
    public void CheckGameOver() {
        if (Session.getMoney() <= 0) {
            LocalDateTime sessionEnd = LocalDateTime.now();

            System.out.println(Misc.getEndLogo());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String sessionStartFormatted = Session.getSessionStart().format(formatter);
            String sessionEndFormatted = sessionEnd.format(formatter);

            Duration sessionDuration = Duration.between(Session.getSessionStart(), sessionEnd);
            long hours = sessionDuration.toHours();
            long minutes = sessionDuration.toMinutesPart();
            long seconds = sessionDuration.toSecondsPart();
            String timePlayed = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            gui.drawBoxTop();
            gui.drawTitle("Statistics");
            gui.drawBoxSeparator();
            gui.drawText("Games played : " + Session.getGamesPlayed());
            gui.drawText("Games won : " + Session.getGamesWon());
            gui.drawText("Games lost : " + Session.getGamesLost());

            gui.drawBoxEmptyLine();

            gui.drawText("Initial amount of money : " + Session.getInitialMoney() + Misc.getCurrency());
            gui.drawText("Highest amount of money : " + Session.gethighestMoneyAmount() + Misc.getCurrency());
            gui.drawText("Game with highest amount of money : " + Session.getGameWithHighestMoneyAmount());

            gui.drawBoxEmptyLine();

            gui.drawText("Start of this session : " + sessionStartFormatted);
            gui.drawText("End of this session : " + sessionEndFormatted);
            gui.drawText("Time played : " + timePlayed);

            gui.drawBoxEmptyLine();

            gui.drawTitle("Thanks for playing!");
            gui.drawBoxBottom();
            System.exit(0);
        }
    }

    /**
     * Draws an generic menu for use in games
     */
    public void drawGenericMenu(){
        CheckGameOver();

        gui.drawMoney();

        gui.drawBoxTop();
        gui.drawTitle(name);
        gui.drawBoxSeparator();
        gui.drawListNumber(new String[]{"Play", "Describe", "How to play", "Quit " + name});

        if(!author.isEmpty()){
            gui.drawBoxSeparator();
            gui.drawText("Made by: " + author);
        }

        gui.drawBoxBottom();

        int selection = gui.getNumber(1, 4, true, "");

        switch(selection){
            case 0: // play
               Play(); 
               break;
            case 1: // describe
                gui.clearScreen();
                gui.drawBoxTop();
                gui.drawText(description);
                gui.drawBoxBottom();
                drawGenericMenu();
                break;
            case 2: // how to play
                gui.clearScreen();
                gui.drawBoxTop();
                gui.drawText(tutorial);
                gui.drawBoxBottom();
                drawGenericMenu();
                break;
            case 3: // exit
                gui.clearScreen();
                return;
        }
    }
}