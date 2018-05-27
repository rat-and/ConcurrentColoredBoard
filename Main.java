package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This is the main function that displays main scene (the Stage) which main
 * element is a BorderPanel containing two other Panels: ControlPanel and ActionPanel.
 * In addition it contains an AlertDialog instance displayed when inappropriate parameters
 * would be put and EventHandler's for buttons in ControlPanel, by which user can manage the
 * computing process
 * @see ActionPanel
 * @see ControlPanel
 * @see Alert
 * @see EventHandler
 */
public class Main extends Application {

    /**
     * Displays an ErrorDialog when invalid parameters were set
     * @see Alert
     */
    public void errorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Parameters Error");
        alert.setHeaderText("Invalid parameter type or range");
        alert.setContentText("It is temporary text");
        alert.showAndWait();
    }

    /**
     * A class that extends Application (JavaFX) must override start() method
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        /**
         * An instance of ControlPanel
         */
        ControlPanel cPane = new ControlPanel();

        /**
         * An instance of ActionPanel
         */
        ActionPanel aPane = new ActionPanel();

        /**
         * Handles the "Start simulation" button. Fist it checks if the provided parameters
         * are valid, then sends those parameters to ActionPanel object and calls drawGrid() method
         * which starts the simulation
         * @see drawGrid();
         */
        cPane.btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (aPane.halt == false) {
                    aPane.setHalt(true);
                    aPane.shutDownThreads();
                }

                if (cPane.xField.getText().trim().equals("") || cPane.yField.getText().trim().equals("") || cPane.kField.getText().trim().equals("") || cPane.dField.getText().trim().equals("")) {
                    errorDialog();
                } else {
                    int nTemp = 0, mTemp = 0, dTemp = 0;
                    double kTemp = 0;
                    try {
                        nTemp = Integer.parseInt(cPane.xField.getText());
                        mTemp = Integer.parseInt(cPane.yField.getText());
                        kTemp = Double.parseDouble(cPane.kField.getText());
                        dTemp = Integer.parseInt(cPane.dField.getText());
                    } catch (NumberFormatException e) {
                        errorDialog();
                    }

                    if (nTemp <= 0 || mTemp <= 0 || kTemp <= 0 || kTemp > 100 || dTemp <= 0) {
                        errorDialog();
                    } else {
                        aPane.setColumns(nTemp);
                        aPane.setRows(mTemp);
                        aPane.setPoblty(kTemp);
                        aPane.setDelay(dTemp);
                        aPane.setCurrnetWidth(aPane.getWidth());
                        aPane.setCurrnetHeight(aPane.getHeight());
                        aPane.drawGrid();
                    }
                }
            }
        });

        /**
         * Handles the "Stop" button. First sets the boolean parameter halt to false so all the
         * infinitive loops in ActionPanel are being stopped, then clears the HashMap object containing
         * active rectangles and at the end kills all working threads.
         * @see drawGrid();
         */
        cPane.btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aPane.setHalt(false);
                aPane.map.clear();
                aPane.shutDownThreads();
            }
        });

        root.setRight(cPane);
        root.setCenter(aPane);

        Scene scene = new Scene(root,800, 600);

        //performed when scenes width is being changed
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                if (aPane.halt) {
                    aPane.setHalt(false);
                    aPane.map.clear();
                    aPane.shutDownThreads();
                    aPane.setCurrnetWidth(aPane.getWidth());
                    aPane.setHalt(true);
                    aPane.drawGrid();

                } else {
                    aPane.setCurrnetWidth(aPane.getWidth());
                }
            }
        });
        //performed when scenes height is being changed
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                if (aPane.halt) {
                    aPane.setHalt(false);
                    aPane.map.clear();
                    aPane.shutDownThreads();
                    aPane.setCurrnetHeight(aPane.getHeight());
                    aPane.setHalt(true);
                    aPane.drawGrid();

                } else {
                    aPane.setCurrnetHeight(aPane.getHeight());
                }
            }
        });

        primaryStage.setTitle("Concurrent Colored Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
