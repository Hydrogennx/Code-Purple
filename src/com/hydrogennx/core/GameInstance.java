package com.hydrogennx.core;

import com.hydrogennx.controller.ActionPhase;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.javafx.ScreenFramework;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class. This holds all relevant information about the current game.
 * This class is never used directly -- instead, a LocalPracticeInstance, ClientManager or HostManager should be created.
 */
public abstract class GameInstance {

    GameManager gameManager;
    GameState gameState;

    /**
     * The current turn all players are on.
     */
    MediaPlayer calmMusic;
    MediaPlayer actionMusic;

    /**
     * The ratio of the calm version of the song to the action version of the song.
     */
    private static final double MUSIC_SPEED_RATIO = 120.0 / 110.0;

    boolean calm = true;
    double calmVolume = 1.0;

    int turn;

    /**
     * List of all players, in no particular order.
     */
    List<Player> allPlayers = new ArrayList<>();

    public GameInstance(GameManager gameManager) {

        this.gameManager = gameManager;
        playMusic();

    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void updateScreen() {
        switch (gameState) {
            case TURN:
                gameManager.setScreen(ScreenFramework.TURN_PHASE_ID);
                break;
            case ACTION:
                gameManager.setScreen(ScreenFramework.ACTION_PHASE_ID);
                break;
            case GAME_OVER:
                gameManager.setScreen(ScreenFramework.GAME_OVER_ID);
                break;
            default:
                return; //should not happen
        }
    }

    public void playMusic() {

        if (!gameManager.getSettings().getMusicEnabled()) return;

        String calmMusicFile = "src/com/hydrogennx/core/resource/calm.mp3";
        Media calm = new Media(new File(calmMusicFile).toURI().toString());

        calmMusic = new MediaPlayer(calm);
        calmMusic.play();
        calmMusic.setCycleCount(MediaPlayer.INDEFINITE);

        String actionMusicFile = "src/com/hydrogennx/core/resource/action.mp3";
        Media action = new Media(new File(actionMusicFile).toURI().toString());

        actionMusic = new MediaPlayer(action);
        actionMusic.play();

    }

    public void setMusicIsCalm(boolean calm) {

        if (gameManager.getSettings().getMusicEnabled()) {

            this.calm = calm;

            if (calm) {
                double currentTime = actionMusic.getCurrentTime().toMillis() * MUSIC_SPEED_RATIO;
                calmMusic.seek(new Duration(currentTime));
            } else {
                double currentTime = calmMusic.getCurrentTime().toMillis() / MUSIC_SPEED_RATIO;
                actionMusic.seek(new Duration(currentTime));
            }

        }

    }

    protected void changeGameState(GameState gameState) {
        this.gameState = gameState;
        gameManager.updateScreen();

        if (gameState == GameState.ACTION) {
            setMusicIsCalm(false);
        } else {
            setMusicIsCalm(true);
        }

    }

    public void update(double time) {

        if (gameState == GameState.ACTION) {

            ActionPhase actionPhase = (ActionPhase) gameManager.getWindowController(ScreenFramework.ACTION_PHASE_ID);

            actionPhase.update(time);

        }

        if (gameManager.getSettings().getMusicEnabled()) {

            if (calm) {
                calmVolume += 1.0 / 72.0;
                if (calmVolume > 1) calmVolume = 1;
            } else {
                calmVolume -= 1.0 / 72.0;
                if (calmVolume < 0) calmVolume = 0;
            }

            calmMusic.setVolume(calmVolume);
            actionMusic.setVolume(1 - calmVolume);

        }

    }

    /**
     * Returns how much mana players get at the end of the next turn.
     * @return How much mana players get at the end of the next turn.
     */
    public int getManaReturn() {

        return turn + 2;

    }

    /**
     * Returns how much mana is spent at the end of every turn regardless of the player's actual amount spent.
     * @return How much mana is spent at the end of every turn regardless of the player's actual amount spent.
     */
    public int getManaWasted() {

        return turn;

    }

    //TODO create a ClientManager and a HostManager when we are ready to begin netcode.

    public abstract void queueAttack(Player attacker, List<AttackSequence> attackSequences);

    public abstract void recallAttack(Player attacker);

    public abstract void endAttack();
    public abstract void registerDefeat();

    public void endGame() {

        if (gameManager.getSettings().getMusicEnabled()) {
            calmMusic.stop();
            actionMusic.stop();
        }

    }

    public abstract Player getCurrentPlayer();

    public abstract void addPlayer(Player player);

}
