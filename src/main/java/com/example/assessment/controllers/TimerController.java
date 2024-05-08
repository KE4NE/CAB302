package com.example.assessment.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerController {

    @FXML
    private Label timeLabel, sessionLabel, breakLength, sessionLength;
    @FXML
    private Button startButton, pauseButton, resetButton;
    @FXML
    private VBox timerPane;

    private ScheduledExecutorService executorService;
    private boolean isTimerRunning = false;
    private boolean isPaused = false;
    private boolean isSession = true;
    private int sessionTime = 25 * 60;
    private int breakTime = 5 * 60;
    private int currentTime = sessionTime;


    public void initialize() {
        updateTimerDisplay(currentTime);
        sessionLength.setText(String.valueOf(sessionTime / 60));
        breakLength.setText(String.valueOf(breakTime / 60));
    }

    @FXML
    private void startTimer() {
        if (!isTimerRunning && !isPaused) {
            currentTime = isSession ? sessionTime : breakTime;
            scheduleTimer();
        } else if (isPaused) {
            scheduleTimer();
        }
        toggleButtons(true);
    }

    private void scheduleTimer() {
        if (executorService != null && !executorService.isShutdown()) {
            return;
        }
        executorService = Executors.newSingleThreadScheduledExecutor();
        isTimerRunning = true;
        isPaused = false;

        long delayUntilNextSecond = 1000 - (System.currentTimeMillis() % 1000);

        executorService.scheduleAtFixedRate(() -> {
            if (currentTime > 0) {
                currentTime--;
                updateTimerDisplay(currentTime);
            } else {
                Platform.runLater(() -> {
                    stopTimer();
                    switchMode();
                });
            }
        }, delayUntilNextSecond, 1000, TimeUnit.MILLISECONDS);
    }


    @FXML
    private void pauseTimer() {
        if (isTimerRunning) {
            stopTimer();
            isPaused = true;
            toggleButtons(false);
        }
    }

    @FXML
    private void resetTimer() {
        stopTimer();
        isSession = true;
        currentTime = sessionTime;
        updateUIForNewCycle();
        toggleButtons(false);
    }

    @FXML
    private void increaseSessionLength() {
        alterLength(true, true);
    }

    @FXML
    private void decreaseSessionLength() {
        alterLength(true, false);
    }

    @FXML
    private void increaseBreakLength() {
        alterLength(false, true);
    }

    @FXML
    private void decreaseBreakLength() {
        alterLength(false, false);
    }

    private void stopTimer() {
        if (executorService != null) {
            executorService.shutdownNow();
        }
        isTimerRunning = false;
    }

    private void switchMode() {
        isSession = !isSession;
        currentTime = isSession ? sessionTime : breakTime;
        updateUIForNewCycle();
    }

    private void updateTimerDisplay(final int seconds) {
        Platform.runLater(() -> timeLabel.setText(formatTime(seconds)));
    }

    private void toggleButtons(boolean isStart) {
        startButton.setVisible(!isStart);
        pauseButton.setVisible(isStart);
        resetButton.setVisible(isStart);

        startButton.setManaged(!isStart);
        pauseButton.setManaged(isStart);
        resetButton.setManaged(isStart);
    }

    private void updateUIForNewCycle() {
        sessionLabel.setText(isSession ? "SESSION" : "BREAK");
        updateTimerDisplay(currentTime);
        toggleButtons(false);
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    private void alterLength(boolean isSessionLength, boolean isIncrease) {
        if (isSessionLength) {
            sessionTime = alterTime(sessionTime, isIncrease);
            sessionLength.setText(String.valueOf(sessionTime / 60));
        } else {
            breakTime = alterTime(breakTime, isIncrease);
            breakLength.setText(String.valueOf(breakTime / 60));
        }
        if (!isTimerRunning) {
            resetTimer();
        }
    }

    private int alterTime(int time, boolean isIncrease) {
        if (isIncrease || time > 60) {
            return time + (isIncrease ? 60 : -60);
        }
        return time;
    }

    public VBox getTimerPane() {
        return timerPane;
    }

}
