package com.example.assessment.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller class for managing the timer functionality.
 * This class handles user interactions with the timer UI and manages the timer state.
 */
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

    /**
     * Initializes the TimerController.
     * Sets up initial timer values and updates the UI to reflect these values.
     */
    public void initialize() {
        updateTimerDisplay(currentTime);
        sessionLength.setText(String.valueOf(sessionTime / 60));
        breakLength.setText(String.valueOf(breakTime / 60));
    }

    /**
     * Starts the timer. If the timer was paused, it resumes from where it left off.
     */
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

    /**
     * Schedules the timer to run at a fixed rate, updating every second.
     */
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

    /**
     * Pauses the timer.
     */
    @FXML
    private void pauseTimer() {
        if (isTimerRunning) {
            stopTimer();
            isPaused = true;
            toggleButtons(false);
        }
    }

    /**
     * Resets the timer to the initial session time and updates the UI.
     */
    @FXML
    private void resetTimer() {
        stopTimer();
        isSession = true;
        currentTime = sessionTime;
        updateUIForNewCycle();
        toggleButtons(false);
    }

    /**
     * Increases the session length by one minute.
     */
    @FXML
    private void increaseSessionLength() {
        alterLength(true, true);
    }

    /**
     * Decreases the session length by one minute.
     */
    @FXML
    private void decreaseSessionLength() {
        alterLength(true, false);
    }

    /**
     * Increases the break length by one minute.
     */
    @FXML
    private void increaseBreakLength() {
        alterLength(false, true);
    }

    /**
     * Decreases the break length by one minute.
     */
    @FXML
    private void decreaseBreakLength() {
        alterLength(false, false);
    }

    /**
     * Stops the timer and shuts down the executor service.
     */
    private void stopTimer() {
        if (executorService != null) {
            executorService.shutdownNow();
        }
        isTimerRunning = false;
    }

    /**
     * Switches the timer mode between session and break.
     */
    private void switchMode() {
        isSession = !isSession;
        currentTime = isSession ? sessionTime : breakTime;
        updateUIForNewCycle();
    }

    /**
     * Updates the timer display with the current time.
     *
     * @param seconds the current time in seconds.
     */
    private void updateTimerDisplay(final int seconds) {
        Platform.runLater(() -> timeLabel.setText(formatTime(seconds)));
    }

    /**
     * Toggles the visibility and manageability of the start, pause, and reset buttons.
     *
     * @param isStart indicates whether the timer is starting or pausing.
     */
    private void toggleButtons(boolean isStart) {
        startButton.setVisible(!isStart);
        pauseButton.setVisible(isStart);
        resetButton.setVisible(isStart);

        startButton.setManaged(!isStart);
        pauseButton.setManaged(isStart);
        resetButton.setManaged(isStart);
    }

    /**
     * Updates the UI for a new cycle (either a new session or break).
     */
    private void updateUIForNewCycle() {
        sessionLabel.setText(isSession ? "SESSION" : "BREAK");
        updateTimerDisplay(currentTime);
        toggleButtons(false);
    }

    /**
     * Formats the time in minutes and seconds.
     *
     * @param totalSeconds the total time in seconds.
     * @return the formatted time as a string.
     */
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    /**
     * Alters the length of either the session or break time.
     *
     * @param isSessionLength indicates whether to alter the session length.
     * @param isIncrease indicates whether to increase or decrease the length.
     */
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

    /**
     * Alters the time by either increasing or decreasing it by one minute.
     *
     * @param time the current time.
     * @param isIncrease indicates whether to increase or decrease the time.
     * @return the altered time.
     */
    private int alterTime(int time, boolean isIncrease) {
        if (isIncrease || time > 60) {
            return time + (isIncrease ? 60 : -60);
        }
        return time;
    }

    /**
     * Returns the VBox container for the timer.
     *
     * @return the VBox container.
     */
    public VBox getTimerPane() {
        return timerPane;
    }

}
