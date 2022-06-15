package ru.javarush.ogarkov.island.settings;

import javafx.scene.image.Image;
import ru.javarush.ogarkov.island.factory.*;
import ru.javarush.ogarkov.island.factory.carnivore.*;
import ru.javarush.ogarkov.island.factory.herbivore.*;
import ru.javarush.ogarkov.island.factory.landform.PlainFactory;
import ru.javarush.ogarkov.island.factory.plant.*;
import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.javarush.ogarkov.island.settings.EatingProbability.*;

public enum Items {
    CARNIVORE(new CarnivoreFactory()),
        BEAR("Медведь",CARNIVORE, 500, 5, 2, 80, BEAR_FOOD, new BearFactory(), getImage("/carnivore/bear.png")),
        BOA("Удав",CARNIVORE, 15, 30, 1, 3, BOA_FOOD, new BoaFactory(), getImage("/carnivore/boa.png")),
        EAGLE("Орёл",CARNIVORE, 6, 20, 3, 1, EAGLE_FOOD, new EagleFactory(), getImage("/carnivore/eagle.png")),
        FOX("Лиса",CARNIVORE,8, 30, 2, 2, FOX_FOOD, new FoxFactory(), getImage("/carnivore/fox.png")),
        WOLF("Волк",CARNIVORE,50, 30, 3, 8, WOLF_FOOD, new WolfFactory(), getImage("/carnivore/wolf.png")),
    HERBIVORE(new HerbivoreFactory()),
        BOAR("Кабан",HERBIVORE,400, 50, 2, 50, BOAR_FOOD, new BoarFactory(), getImage("/herbivore/boar.png")),
        BUFFALO("Буйвол",HERBIVORE,700, 10, 3, 100, HERBIVORE_FOOD, new BuffaloFactory(), getImage("/herbivore/buffalo.png")),
        CATERPILLAR("Гусеница",HERBIVORE,0.01, 1000, 0, 0, HERBIVORE_FOOD, new CaterpillarFactory(), getImage("/herbivore/caterpillar.png")),
        DEER("Олень",HERBIVORE,300, 20, 4, 50, HERBIVORE_FOOD, new DeerFactory(), getImage("/herbivore/deer.png")),
        DUCK("Утка",HERBIVORE,1, 200, 4, 0.15, HERBIVORE_FOOD, new DuckFactory(), getImage("/herbivore/duck.png")),
        GOAT("Коза",HERBIVORE,60, 140, 3, 10, HERBIVORE_FOOD, new GoatFactory(), getImage("/herbivore/goat.png")),
        HORSE("Лошадь",HERBIVORE,400, 20, 4, 60, HERBIVORE_FOOD, new HorseFactory(), getImage("/herbivore/horse.png")),
        MOUSE("Мышь",HERBIVORE,0.05, 500, 1, 0.01, HERBIVORE_FOOD, new MouseFactory(), getImage("/herbivore/mouse.png")),
        RABBIT("Кролик",HERBIVORE,2, 150, 2, 0.45, HERBIVORE_FOOD, new RabbitFactory(), getImage("/herbivore/rabbit.png")),
        SHEEP("Овца",HERBIVORE,70, 140, 3, 15, HERBIVORE_FOOD, new SheepFactory(), getImage("/herbivore/sheep.png")),
    PLANT(new PlantFactory()),
        BUSH("Куст",PLANT,1, 200, new BushFactory(), getImage("/plant/bush.png")),
        DANDELION("Одуванчик",PLANT,1, 200, new DandelionFactory(), getImage("/plant/dandelion.png")),
        FLOWER("Цветок",PLANT,1, 200, new FlowerFactory(), getImage("/plant/flower.png")),
        GRASS("Трава",PLANT,1, 200, new GrassFactory(), getImage("/plant/grass.png")),
        SPROUT("Росток",PLANT,1, 200, new SproutFactory(), getImage("/plant/sprout.png")),
        TREE("Дерево",PLANT,1, 200, new TreeFactory(), getImage("/plant/tree.png")),
    LANDFORM(new LandformFactory()),
        PLAIN("Равнина",LANDFORM,1, 200, new PlainFactory(), getImage("/landform/plain.png"));


    static {
        EatingProbability.init();
    }

    private final Factory factory;
    private Items parent;
    private Image icon;
    private double weight;
    private int maxPerLocation;
    private int maxSpeed;
    private double foodPerSatiation;
    private String name;
    private Map<Items, Integer> eatingProbability;
    private final List<Items> children = new ArrayList<>();

    Items(Factory factory) {
        this.factory = factory;
    }

    Items(String name, Items parent, double weight, int maxPerLocation, Factory factory, Image icon) {
        this(factory);
        this.name = name;
        this.parent = parent;
        parent.children.add(this);
        this.weight = weight;
        this.maxPerLocation = maxPerLocation;
        this.icon = icon;
    }

    Items(String name, Items parent, double weight, int maxPerLocation, int maxSpeed, double foodPerSatiation, Map<Items, Integer> eatingProbability, Factory factory, Image icon) {
        this(name, parent, weight, maxPerLocation, factory, icon);
        parent.children.add(this);
        this.maxSpeed = maxSpeed;
        this.foodPerSatiation = foodPerSatiation;
        this.eatingProbability = eatingProbability;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerLocation() {
        return maxPerLocation;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getFoodPerSatiation() {
        return foodPerSatiation;
    }

    public Map<Items, Integer> getEatingProbability() {
        return eatingProbability;
    }

    private static Image getImage(String path) {
        return new Image(String.valueOf(Items.class.getResource(path)));
    }

    public Image getIcon() {
        return icon;
    }

    public List<Items> getChildren() {
        return children;
    }
    public BasicItem createItem() {
        return factory.createItem(this);
    }

    public long getCreatedItemsCount(){
        return factory.getCreatedItemsCount();
    }

    public boolean is(Items other) {
        return this == other || this.parent == other;
    }

    public boolean isNot(Items other) {
        return !this.is(other);
    }
}
