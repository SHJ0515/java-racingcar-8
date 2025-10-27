package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

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

    public void move() {
        int randomNum = Randoms.pickNumberInRange(0, 9);
        if (randomNum >= 4) {
            currentLocation++;
        }
    }
}
