package com.hydrogennx.controller;

import com.hydrogennx.core.*;
import com.hydrogennx.core.attack.AttackSequence;
import com.hydrogennx.core.attack.AxisAttack;
import com.hydrogennx.core.attack.HealDefense;
import com.hydrogennx.core.attack.RainAttack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TurnPhase extends WindowController implements Initializable {

    private GameInstance gameInstance;

    private final static Image PURPLE_BONUS = new Image(TurnPhase.class.getResource("/purple-bonus.png").toString());
    private final static Image PURPLE_BONUS_EMPTY = new Image(TurnPhase.class.getResource("/purple-bonus-empty.png").toString());
    private final static Image PURPLE_BONUS_NEXT = new Image(TurnPhase.class.getResource("/purple-bonus-next.png").toString());

    private final static Image PURPLE_READY = new Image(TurnPhase.class.getResource("/purple-ready.png").toString());
    private final static Image PURPLE_NEXT = new Image(TurnPhase.class.getResource("/purple-nearly-ready.png").toString());
    private final static Image PURPLE_LOCKED = new Image(TurnPhase.class.getResource("/purple-locked-2.png").toString());

    @FXML
    private ImageView instructions = new ImageView();

    @FXML
    private ProgressBar mainHealthBar;

    @FXML
    private Label mainName;

    @FXML
    private ImageView mainColor;

    @FXML
    private ProgressBar otherHealthBar;

    @FXML
    private Label otherName;

    @FXML
    private ImageView otherColor;

    @FXML
    private ProgressIndicator waitingCircle;

    @FXML
    private Label manaLabel;

    @FXML
    private ImageView firstMainMana;

    @FXML
    private ImageView firstBonusMana;

    @FXML
    private Pane manaPane;

    boolean manaBarsCreated = false;

    List<ImageView> mainMana = new ArrayList<>();
    List<ImageView> bonusMana = new ArrayList<>();

    private boolean visible = false;

    public TurnPhase() throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void funAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new RainAttack(Direction.DOWN));

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void boringAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AxisAttack());

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void mixedAttack() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new HealDefense());
        attacks.add(new RainAttack(Direction.UP));
        attacks.add(new RainAttack(Direction.DOWN));

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void allAtOnce() {

        List<AttackSequence> attacks = new ArrayList<>();
        attacks.add(new AxisAttack());
        attacks.add(new RainAttack(Direction.LEFT));
        attacks.add(new RainAttack(Direction.RIGHT));

        gameInstance.queueAttack(gameInstance.getCurrentPlayer(), attacks);

    }

    @FXML
    public void helpButtonPressed() {

        if (!visible) {
            instructions.setVisible(true);
            visible = true;
        } else if (visible) {
            instructions.setVisible(false);
            visible = false;
        }
    }

    public void backButton() {
        gameInstance.endGame();
    }

    /**
     * Updates the state of things in the TurnPhase.
     * As TurnPhase does not include any live action, this does not have to be called 60 times a second,
     * and is instead run whenever the game switches to it, or something changes.
     */
    public void updateState() {

        mainHealthBar.setProgress(gameInstance.getCurrentPlayer().getHealth());

        manaLabel.setText("Mana: " + (gameInstance.getCurrentPlayer().getStoredMana() + gameInstance.getFreeMana()) + "  Turn: " + gameInstance.getFreeMana());

        if (gameInstance instanceof NetworkGameInstance) {

            NetworkGameInstance networkGameInstance = (NetworkGameInstance) gameInstance;

            otherHealthBar.setProgress(networkGameInstance.getOtherPlayer().getHealth());

            otherName.setText(networkGameInstance.getOtherPlayer().getName());
            mainName.setText(networkGameInstance.getCurrentPlayer().getName());

        }

        if (!manaBarsCreated) {
            manaBarsCreated = true;
            createManaBars();
        }

        updateManaBars();

    }

    public void updateManaBars() {

        int storedMana = gameInstance.getCurrentPlayer().getStoredMana();
        int freeMana = gameInstance.getFreeMana();

        for (int i = 0; i < mainMana.size(); i++) {
            if (freeMana > i + 1) {
                mainMana.get(i).setImage(PURPLE_READY);
            } else if (freeMana == i + 1) {
                mainMana.get(i).setImage(PURPLE_NEXT);
            } else {
                mainMana.get(i).setImage(PURPLE_LOCKED);
            }
        }

        for (int i = 0; i < bonusMana.size(); i++) {
            if (storedMana > i + 1) {
                bonusMana.get(i).setImage(PURPLE_BONUS);
            } else if (storedMana + gameInstance.getManaReturn() > i + 1) {
                bonusMana.get(i).setImage(PURPLE_BONUS_NEXT);
            } else {
                bonusMana.get(i).setImage(PURPLE_BONUS_EMPTY);
            }
        }

    }

    public void createManaBars() {

        for (int i = 1; i < 8; i++) {
            ImageView manaImage = new ImageView(firstMainMana.getImage());
            manaImage.setX(firstMainMana.getLayoutX() + (i * manaImage.getImage().getWidth()));
            manaImage.setY(firstMainMana.getLayoutY());
            mainMana.add(manaImage);
        }

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 8; x++) {
                if (x == 0 && y == 0) continue;
                ImageView manaImage = new ImageView(firstBonusMana.getImage());
                manaImage.setLayoutX(firstBonusMana.getLayoutX() + (x * manaImage.getImage().getWidth()));
                manaImage.setLayoutY(firstBonusMana.getLayoutY() + (y * manaImage.getImage().getHeight()));
                bonusMana.add(manaImage);
            }
        }

        manaPane.getChildren().addAll(mainMana);
        manaPane.getChildren().addAll(bonusMana);

    }

    public void setGameInstance(GameInstance gameInstance) {
        if (this.gameInstance == null) {
            this.gameInstance = gameInstance;

            if (!(gameInstance instanceof NetworkGameInstance)) {

                otherHealthBar.setVisible(false);
                otherName.setVisible(false);
                otherColor.setVisible(false);
                waitingCircle.setVisible(false);

            }

        }
    }

    public void setOtherPlayerDecision(boolean decided) {

        if (decided) {
            waitingCircle.setProgress(1);
        } else {
            waitingCircle.setProgress(-1);
        }


    }

}
