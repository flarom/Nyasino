public class Slots extends Game {
    public Slots() {
        this.author = "flarom";
        this.name = "Slots";
        this.icon = "🎰";
        this.description = "A slot machine game where you bet money and spin the reels. " +
                           "Match three symbols to win big!";
        this.tutorial = "How to Play:\n" +
                        "1. Choose the amount of money you want to bet (minimum: 1).\n" +
                        "2. Spin the reels by selecting 'Play'.\n" +
                        "3. If all three reels match, you win 10x your bet!\n" +
                        "4. If they don't match, better luck next time.\n" +
                        "5. You can quit by selecting 'Quit'.\n" +
                        "Good luck!";
    }

    @Override
    public void Menu() {
        drawGenericMenu();
    }

    @Override
    public void Play() {
        int bet = GetBet();

        String[] symbols = { "🍒", "🍋", "🔔", "⭐", "7️⃣" };

        String[] result = new String[3];
        for (int i = 0; i < 3; i++) {
            result[i] = symbols[(int) (Math.random() * symbols.length)];
        }

        gui.drawBoxTop();
        gui.drawTitle("Slot Machine Result");
        gui.drawListBullet(result);
        gui.drawBoxBottom();

        if (result[0].equals(result[1]) && result[1].equals(result[2])) {
            int winnings = bet * 10;
            Session.setMoney(Session.getMoney() + winnings);
            Session.incrementGamesWon();
            gui.drawTinyMsg("JACKPOT! You won " + winnings + Misc.getCurrency() + "!");
        } else {
            Session.incrementGamesLost();
            gui.drawTinyMsg("Better luck next time!");
        }

        Session.incrementGamesPlayed();
        Session.checkHighestMoneyAmount();

        Menu();
    }
}
