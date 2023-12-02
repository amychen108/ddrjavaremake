package ddr.controller;

import ddr.ScreenObserver;
import ddr.model.Game;
import ddr.model.Highscore;
import ddr.view.GameplayGUI;
import ddr.view.MainGUI;
import ddr.view.ResultGUI;

public class ScreenController implements ScreenObserver {
    MainGUI mainScreen;
    GameplayGUI gameScreen;
    ResultGUI resultScreen;
    Game gameplay;
    Highscore highscore;

    public ScreenController() {
        this.mainScreen = new MainGUI(this);
        this.gameplay = new Game();
        this.gameScreen = new GameplayGUI(this, gameplay);
        this.gameplay.set_obvs(gameScreen);
        this.gameScreen.disable();
        this.resultScreen = new ResultGUI(gameplay, this);
        this.resultScreen.disable();
        this.highscore = new Highscore();
    }

    public void transition(int mode) {
        mainScreen.disable();
        gameScreen.enable();
        gameScreen.set_start();
        gameplay.set_diff(mode);
        gameScreen.game_start();
    }

    public void press() {
        gameplay.hit();
    }

    public void miss() {
        gameplay.miss();
    }

    public void to_main() {
        gameplay.reset();
        mainScreen.enable();
        resultScreen.disable();
        mainScreen.updateHighScores();
        System.out.println("hiii");
    }

    public void retry() {
        gameplay.reset();
        gameScreen.game_start(); //idk do i need this
        gameScreen.enable();
        resultScreen.disable();
    }

    public int rank() {
        return gameplay.getRank();
    }

    public void startGame() {
        gameScreen.game_start();
        highscore.addHighscore(gameplay.getScore());
    }

    public void endGame() {
        resultScreen.enable();
        mainScreen.disable();
        gameScreen.disable();

        highscore.addHighscore(gameplay.getScore());
        highscore.saveHighScoresToFile("highscores.ser");

        resultScreen.updateScoreLabel(gameplay.getScore());
        resultScreen.updateComboLabel(gameplay.getHighestCombo());
        resultScreen.updateGoodLabel(gameplay.getHits());
        resultScreen.updateMissLabel(gameplay.getMisses());
        resultScreen.updateClearLabel(gameplay.getClearType());
        resultScreen.setRank();

    }
}
