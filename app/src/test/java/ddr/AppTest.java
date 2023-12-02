/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ddr;

import org.junit.Test;
import static org.junit.Assert.*;

import ddr.model.Highscore;
import ddr.model.Game;
import ddr.view.GameplayGUI;
import ddr.controller.ScreenController;

public class AppTest {
    @Test 
    public void newHighScore() {
        Highscore high = new Highscore();
        int big = 99; //you might want to delete the .ser file to reset the high scores cuz this is hard coded
        high.addHighscore(big);
        assertEquals(true, high.getHighscores().get(0) == big);
    }

    @Test
    public void rankBTest() { //testing for B rank
        Game game = new Game();
        ScreenController dum = new ScreenController();
        GameplayGUI dummy = new GameplayGUI(dum, game);
        game.set_obvs(dummy);
        for(int i = 0; i < 8; i++){
            game.hit();
        }
        for(int i = 0; i < 2; i++){
            game.miss();
        }
        assertEquals(true, game.getRank() == 2);
    }

    public void currentComboTest() { //testing for current combo (resets to 0 when missing)
        Game game = new Game();
        ScreenController dum = new ScreenController();
        GameplayGUI dummy = new GameplayGUI(dum, game);
        game.set_obvs(dummy);
        for(int i = 0; i < 10; i++){ 
            game.hit();
        }
        for(int i = 0; i < 1; i++){
            game.miss();
        }
        for(int i = 0; i < 3; i++){
            game.hit();
        }
        assertEquals(true, game.getCurrentCombo() == 3);
    }

    public void highestComboTest() { //testing for highest combo (does not reset to 0)
        Game game = new Game();
        ScreenController dum = new ScreenController();
        GameplayGUI dummy = new GameplayGUI(dum, game);
        game.set_obvs(dummy);
        for(int i = 0; i < 10; i++){ 
            game.hit();
        }
        for(int i = 0; i < 1; i++){
            game.miss();
        }
        for(int i = 0; i < 3; i++){
            game.hit();
        }
        assertEquals(true, game.getHighestCombo() == 10);
    }
    
}
