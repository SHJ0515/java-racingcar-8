package racingcar.service;

import racingcar.domain.Car;
import racingcar.dto.RacingInfoDto;

import java.util.ArrayList;
import java.util.List;

public class RacingService {

    private List<Car> cars;
    private int totalRound;
    private int currentRound;

    public void initRacing(RacingInfoDto racingInfoDto) {
        this.cars = parseCar(racingInfoDto.getCars());
        this.totalRound = Integer.parseInt(racingInfoDto.getRound());
        this.currentRound = 0;
    }

    private List<Car> parseCar(String carNames) {

        String[] names = carNames.split(",");
        List<Car> carList = new ArrayList<>();

        for (String name : names) {
            carList.add(new Car(name));
        }

        return carList;
    }


}
