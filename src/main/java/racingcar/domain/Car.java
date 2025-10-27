package racingcar.domain;

public class Car {
    String name;
    int currentLocation;

    public Car(String name) {
        this.name = name;
        this.currentLocation = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }
}
