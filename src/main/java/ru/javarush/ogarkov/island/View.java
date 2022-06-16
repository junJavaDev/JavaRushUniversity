package ru.javarush.ogarkov.island;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.*;
import ru.javarush.ogarkov.island.location.Island;
import ru.javarush.ogarkov.island.location.Location;
import ru.javarush.ogarkov.island.settings.Setting;

import java.util.List;

import static ru.javarush.ogarkov.island.settings.Setting.*;

public class View {

    @FXML
    private AnchorPane islandField;

    @FXML
    private AnchorPane locationField;

    @FXML
    private TextFlow staticticField;

    protected void initIslandField(Island model) {
        islandField.getChildren().clear();
        islandField.setPrefWidth(ISLAND_WIDTH * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE));
        islandField.setPrefHeight(ISLAND_HEIGHT * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE));
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                islandField.getChildren().add(model.getLocations()[x][y].getLeader());
            }
        }
    }

    protected void initLocationField(Location model) {
        locationField.getChildren().clear();
        for (int x = 0; x < Setting.LOCATION_WIDTH; x++) {
            for (int y = 0; y < Setting.LOCATION_HEIGHT; y++) {
                locationField.getChildren().add(model.getTerritories()[x][y]);
            }
        }
    }

    protected void initStatistic(List<Text> texts) {
        staticticField.getChildren().clear();
        for (Text text : texts) {
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 11));
            staticticField.getChildren().add(text);
        }
    }
}