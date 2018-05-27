package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Contains all the composites (TextFields, Lables, Buttons) by which user can
 * change parameters of simulation
 */
public class ControlPanel extends GridPane {
    Button btn1, btn2;
    TextField xField, yField, kField, dField;

    /**
     * Constructs ControlPanel with all composites so when called instantly sets
     * everything to live
     */
    public ControlPanel() {
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        btn1 = new Button("Start simulation");
        btn2 = new Button("Stop");
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        Label xLabel = new Label("Set columns to ");
        Label yLabel = new Label("Set rows to ");
        Label kLabel = new Label("Set possibility to ");
        Label dLabel = new Label("Set delay to ");
        xField = new TextField();
        yField = new TextField();
        kField = new TextField();
        dField = new TextField();

        xLabel.setMinWidth(80);
        yLabel.setMinWidth(83);
        dLabel.setMinWidth(82);

        xField.setMaxWidth(60);
        yField.setMaxWidth(60);
        kField.setMaxWidth(60);
        dField.setMaxWidth(60);

        btn1.setPrefSize(120,20);
        btn2.setPrefSize(80,20);

        hBox1.setSpacing(17);
        hBox1.getChildren().addAll(xLabel, xField);

        hBox2.setSpacing(17);
        hBox2.getChildren().addAll(yLabel, yField);

        hBox3.setSpacing(9);
        hBox3.getChildren().addAll(kLabel, kField);

        hBox4.setSpacing(17);
        hBox4.getChildren().addAll(dLabel, dField);

        vBox1.getChildren().addAll(hBox1, hBox2, hBox3, hBox4);
        vBox1.setSpacing(15);
        vBox1.setPadding(new Insets(10));

        vBox2.getChildren().addAll(btn1, btn2);
        vBox2.setSpacing(10);
        vBox2.setPadding(new Insets(10));
        vBox2.setAlignment(Pos.CENTER);

        this.add(new Text(""), 0,0);
        this.add(vBox1, 0, 1);
        this.add(new Text(""), 0,2);
        this.add(vBox2, 0, 3);
        this.setAlignment(Pos.TOP_CENTER);

        /**
         * Sets minimum height for rows in GridPane so it looks better
         */
        for (int i = 0; i < 4; i++) {
            RowConstraints constraints = new RowConstraints();
            constraints.setPrefHeight(90);
            this.getRowConstraints().add(constraints);
        }

        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
