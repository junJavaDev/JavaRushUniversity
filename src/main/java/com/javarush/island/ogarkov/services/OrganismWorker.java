package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import javafx.application.Platform;

import java.util.Set;

import static com.javarush.island.ogarkov.settings.Setting.ISLAND_COLS;
import static com.javarush.island.ogarkov.settings.Setting.ISLAND_ROWS;

public class OrganismWorker implements Runnable{

    private final Island island;
    private final Controller controller = Controller.control;
    public OrganismWorker(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                Territory currentTerritory = island.getTerritories()[row][col];
                Cell[][] cells = currentTerritory.getOldCells();

                for (int cellRow = 0; cellRow < cells.length; cellRow++) {
                    for (int cellCol = 0; cellCol < cells.length; cellCol++) {

                        Cell cell = cells[cellRow][cellCol];
                        Set<Organism> organisms = cell.getPopulation();
                        for (Organism organism : organisms) {
                            if (organism.getItem().is(Items.ANIMAL)) {
                                Animal animal = (Animal) organism;
                                Territory destination = animal.move(cell);
//                                if (currentTerritory != destination) {
//                                    iterator.remove();
//                                }
                            }
//                            if (organism.getItem().is(Items.LANDFORM)) {
//                                cell.getPopulation().clear();
//                                Plant plant = new PlantFactory().createItem();
//                                cell.getPopulation().add(new PlantFactory().createItem());
//                                cell.setResident(plant);
//                            }
//                            if (cell.getPopulation().isEmpty()) {
//                                Landform landform = new LandformFactory().createItem();
//                                cell.getPopulation().add(new LandformFactory().createItem());
//                                cell.setResident(landform);
//                            }
                        }

//                        Iterator<Organism> iterator = organisms.iterator();
//                        while (iterator.hasNext()) {
//                            Organism organism = iterator.next();
//                            if (organism.getItem().is(Items.ANIMAL)) {
//                                Animal animal = (Animal) organism;
//                                Territory destination = animal.move(cell);
//                                if (currentTerritory != destination) {
//                                    iterator.remove();
//                                }
//                            }
//                            if (organism.getItem().is(Items.LANDFORM)) {
//                                cell.getPopulation().clear();
//                                Plant plant = new PlantFactory().createItem();
//                                cell.getPopulation().add(new PlantFactory().createItem());
//                                cell.setResident(plant);
//                            }
//                            if (cell.getPopulation().isEmpty()) {
//                                Landform landform = new LandformFactory().createItem();
//                                cell.getPopulation().add(new LandformFactory().createItem());
//                                cell.setResident(landform);
//                            }
//                        }

                    }
                }
                currentTerritory.addMouseClickedAction();
//                currentTerritory.updateTerritoryView();
            }
        }
        Platform.runLater(new IslandUpdateWorker(island, controller));

    }

    public void resetIslandColor() {
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                island.getTerritories()[row][col].getLeader().setIslandCellColor();
            }
        }
    }


}
