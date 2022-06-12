package ru.javarush.ogarkov.islandsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.javarush.ogarkov.islandsimulation.settings.Setting;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private Island islandModel;
    private Location locationModel;
    private View view;
    private Controller controller;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        createSimulation();
        loadSimulationForm(stage);
    }



    private void createSimulation () {
        locationModel = new Location(Setting.LOCATION_WIDTH, Setting.LOCATION_HEIGHT, Setting.CELL_SIZE);
        islandModel = new Island(Setting.ISLAND_WIDTH, Setting.ISLAND_HEIGHT, Setting.CELL_SIZE);
        view = new View();
        controller = new Controller(this, islandModel, locationModel,  view);
    }

    private void loadSimulationForm (Stage stage) throws IOException {
        // Загрузка формы simulationForm.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/simulationForm.fxml"));
        fxmlLoader.setController(controller);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        Scene scene = new Scene(fxmlLoader.load(), Setting.ISLAND_WIDTH_FORM, Setting.ISLAND_HEIGHT_FORM);
        stage.setTitle("Island Simulation by Ogarkov");
        stage.setScene(scene);
        stage.show();
    }
}