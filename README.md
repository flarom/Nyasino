# Nyasino
Terminal gambling framework

## Downloading, Compiling and Running
### Download
Click here to download the [latest compiled version](https://github.com/flarom/Nyasino/releases/latest), or use `git clone https://github.com/flarom/Nyasino` to download the repository.
### Compiling
To compile Nyasino, simply open the `/nyasino/` folder on the terminal and type `javac *.java`, you may need to instal Java to do so - `sudo <preferred-package-manager> install openjdk-21-jdk`. 

If you downloaded the already compiled version, you can skip this step.

### Running
To run Nyasino, open the `/nyasino/` folder on the terminal and type `java Nyasino <initial-money-amount>`, you may also need to instal Java to do so.

## Editing simple game messages
Some strings used on the game, such as the title art and currency symbol, are located on the /nyasino/misc/ folder as .txt files, and can be easly modified using any text editor;
```
cd nyasino
cd misc
<preferred-text-editor> currency.txt
```

## Creating a game
### Template
You can create your own games that can be executed in-game. To do so, follow this simple template:
```java
public class CustomGame extends Game{
    public CustomGame(){
        this.author      = "your name here";
        this.name        = "game name here";
        this.description = "brief description about the game";
        this.tutorial    = "tutorial on how to play";
    }

    @Override
    public void Menu(){
        // MENU HERE

        drawGenericMenu(); // displays a basic menu with all basic game functions

        // you can opt to not use drawGenericMenu();, if you want to make a custom menu or use custom options
    }

    @Override
    public void Play(){
        // GAME LOGIC HERE

          // use `GetBet()` to ask the user to bet a value, this will automatcally remove money from the user
          // use `Session.getMoney()` and `Session.setMoney()` to manage users money
          // use `CheckGameOver()` whenever necessary to check if the users money is equals to 0, so the game can end
          // use `Session.incrementGamesPlayed()` at the end of the round, to increment the rounds played.
          // use `Session.incrementGamesWon()` if the user won, to increment the count.
          // use `Session.incrementGamesLost()` if the user lost, to increment the count.
          // use `Session.checkHighestMoneyAmount()` at the end of the round to update more statistics

          // use Menu() at the end of the round, so the user isn't kicked out of the game
    }

    // expand your logic if necessary
}
```
### Game logic methods
| Method                    | Description                                                                                                                                                   |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `drawGenericMenu();`      | Draws a simple game menu, with a title, the options "Play, Describe, How to play and Quit" and the authors name.                                              |
| `GetBet()`                | Asks the user to bet a value between 1 and all money the user has, this value will automatcally be removed from the users pocket, so you don't need to do so. |
| `Session.getMoney()`      | Returns the amount of money the user currently have.                                                                                                          |
| `Session.setMoney(int x)` | Changes the current amount of money to x.                                                                                                                     |
| `CheckGameOver()`         | Checks if the user has no more money. Displays the game over screen.                                                                                          |
| `gui.drawMoney()`         | Draws users current amount of money.                                                                                                                          |
| `Misc.getCurrency()`      | Returns a string of the currency used in game.                                                                                                                |

### Statistics methods
| Method                                    | Description                                                               |
|-------------------------------------------|---------------------------------------------------------------------------|
| `Session.getInitialMoney()`               | Gets the initial amount of money the user had.                            |
| `Session.incrementGamesPlayed()`          | Increments the amount of rounds played by 1.                              |
| `Session.getGamesPlayed()`                | Returns the amount of rounds played on this session.                      |
| `Session.incrementGamesWon()`             | Increments the amount of rounds won by 1.                                 |
| `Session.getGamesWon()`                   | Returns the amount of rounds won on this session.                         |
| `Session.incrementGamesLost()`            | Increments the amount of rounds lost by 1.                                |
| `Session.getGamesLost()`                  | Returns the amount of rounds lost on this session.                        |
| `Session.checkHighestMoneyAmount()`       | Checks if the current amount of money the user has is the biggest amount. |
| `Session.gethighestMoneyAmount()`         | Returns the biggest amount of money the user had on this session.         |
| `Session.getGameWithHighestMoneyAmount()` | Returns the game where the user achieved its biggest amount of money.     |
| `Session.getSessionStart()`               | Returns the moment in time where the session started.                     |

### GUI methods
#### Colored text
| Variable         | Color                  |
|------------------|------------------------|
| `GUI.colorReset` | Default terminal color |
| `GUI.fgBlk`      | Black text             |
| `GUI.fgRed`      | Red text               |
| `GUI.fgGrn`      | Green text             |
| `GUI.fgYlw`      | Yellow text            |
| `GUI.fgBlu`      | Blue text              |
| `GUI.fgPrp`      | Purple text            |
| `GUI.fgCyn`      | Cyan text              |
| `GUI.fgWit`      | White text             |
| `GUI.fgGry`      | Gray text              |
| `GUI.bgBlk`      | Black background       |
| `GUI.bgRed`      | Red background         |
| `GUI.bgGrn`      | Green background       |
| `GUI.bgYlw`      | Yellow background      |
| `GUI.bgBlu`      | Blue background        |
| `GUI.bgPrp`      | Purple background      |
| `GUI.bgCyn`      | Cyan background        |
| `GUI.bgWit`      | White background       |
| `GUI.bgGry`      | Gray background        |

#### Box drawing
| Method                               | Description                                            |
|--------------------------------------|--------------------------------------------------------|
| `drawSeparator()`                    | Draw a horizontal separator, for use outside of boxes. |
| `drawBoxTop()`                       | Draw the top part of a box.                            |
| `drawBoxSeparator()`                 | Draw a horizontal separator, for use inside of boxes.  |
| `drawBoxEmptyLine()`                 | Draw a empty line inside of a box.                     |
| `drawBoxBottom()`                    | Draw the bottom part of a box.                         |
| `drawInputTop()`                     | Draw the top part of a input field.                    |
| `drawInputBottom()`                  | Draw the bottom part of a input field.                 |
| `drawTitle(String title)`            | Draw a box line with a title in the center.            |
| `drawText(String text)`              | Draw multiple lines of text inside of a box.           |
| `drawListNumber(String[] listItems)` | Draw a numbered list of strings.                       |
| `drawListBullet(String[] listItems)` | Draw a bullet list of strings.                         |
| `drawTable(String[][] tableItems)`   | Draw a 2D Array of Strings as a table.                 |
#### Interactions
| Type    | Method                                                          | Description                                                  |
|---------|-----------------------------------------------------------------|--------------------------------------------------------------|
| int     | `getNumber(int min, int max, boolean listIndex, String prompt)` | Asks the user to insert a number inside a determined range.  |
| String  | `getText()`                                                     | Ask the user to insert a string.                             |
| void    | `drawMsg(String message)`                                       | Draw a message box.                                          |
| void    | `drawTinyMsg(String message)`                                   | Draw a tiny message box with a size adjusted to the message. |
| boolean | `drawConfirm(String message)`                                   | Draw a confirm dialog.                                       |
| void    | `clearScreen()`                                                 | Clears the screen.                                           |
| void    | `drawMoney()`                                                   | Draws users current amount of money.                         |
