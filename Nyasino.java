public class Nyasino {
    public static void main(String[] args) {
        Game[] games = new Game[] // stores an instance of every game
        {
            new Slots(),
            new Roulette(),
        };

        if (args.length <= 0){
            displaySintax();
            return;
        }
        
        int money;

        try {
            money = Integer.parseInt(args[0]);
        } catch (Exception ex){
            displaySintax();
            return;
        }

        Session session = new Session(money, games);

        session.drawTitle();
        session.showGamesMenu();
    }

    private static void displaySintax(){
        System.out.println("Usage: java Nyasino <initial-money>");
    }
}