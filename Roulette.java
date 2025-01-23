public class Roulette extends Game {
    public Roulette() {
        this.author = "flarom";
        this.name = "Roulette";
        this.icon = "🎲";
        this.description = "A game of chance where you bet on a number or a color, and spin the wheel!";;
        this.tutorial = "How to Play:\n" +
                        "1. Choose a bet type: Number (0-36) or Color (Red/Black).\n" +
                        "2. Place your bet by selecting a number or color.\n" +
                        "3. Spin the wheel and see if you win!\n" +
                        "4. If the number or color you chose matches the wheel spin, you win!";
    }

    @Override
    public void Menu() {
        drawGenericMenu();
    }

    @Override
    public void Play() {
        CheckGameOver();

        gui.drawBoxTop();
        gui.drawListNumber(new String[] { "Bet on a Number", "Bet on a Color", "Return to menu" });
        gui.drawBoxBottom();

        int selection = gui.getNumber(1, 3, true, "");

        switch (selection) {
            case 0:
                BetOnNumber();
                break;
            case 1:
                BetOnColor();
                break;
            case 2:
                gui.clearScreen();
                Menu();
                return;
        }
    }

    private void BetOnNumber() {
        int betAmount = GetBet();
        int numberBet = gui.getNumber(0, 36, false, "Pick a number between 0 and 36:");

        int winningNumber = (int) (Math.random() * 37); 
        gui.drawMsg("The wheel spins... And the winning number is: " + winningNumber);

        if (numberBet == winningNumber) {
            int winnings = betAmount * 35; // Winning amount is 35x the bet
            Session.setMoney(Session.getMoney() + winnings);
            Session.incrementGamesWon();
            gui.drawTinyMsg("Congratulations! You won " + winnings + Misc.getCurrency() + "!");
        } else {
            Session.incrementGamesLost();
            gui.drawTinyMsg("Sorry, you lost. Better luck next time!");
        }

        gui.drawMoney();

        Session.incrementGamesPlayed();
        Session.checkHighestMoneyAmount();

        Play();
    }

    private void BetOnColor() {
        int betAmount = GetBet();
        int colorBet = gui.getNumber(1, 2, false, "Pick a color: 1 for Red, 2 for Black:");

        int winningNumber = (int) (Math.random() * 37);
        String winningColor = getColorForNumber(winningNumber);
        gui.drawMsg("The wheel spins... And the winning number is: " + winningNumber + " (" + winningColor + ")");

        if ((colorBet == 1 && winningColor.equals("Red")) || (colorBet == 2 && winningColor.equals("Black"))) {
            int winnings = betAmount * 2; // Winning amount is 2x the bet
            Session.setMoney(Session.getMoney() + winnings);
            Session.incrementGamesWon();
            gui.drawMsg("Congratulations! You won " + winnings + Misc.getCurrency() + "!");
        } else {
            Session.incrementGamesLost();
            gui.drawMsg("Sorry, you lost. Better luck next time!");
        }

        gui.drawMoney();

        Session.incrementGamesPlayed();
        Session.checkHighestMoneyAmount();

        Play();
    }

    private String getColorForNumber(int number) {
        if (number == 0) return "Green"; // 0 is green
        if (number % 2 == 0) return "Black"; // Even numbers are black
        return "Red"; // Odd numbers are red
    }
}
