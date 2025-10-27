package racingcar.service;

import racingcar.domain.Car;
import racingcar.dto.RacingInfoDto;

import java.util.ArrayList;
import java.util.List;

public class RacingService {

    private List<Car> cars;
    private int totalRound;
    private int currentRound;

    public void initRacingStatus(RacingInfoDto racingInfoDto) {
        this.cars = parseCar(racingInfoDto.getCars());
        this.totalRound = Integer.parseInt(racingInfoDto.getRound());
        this.currentRound = 0;
    }

    public boolean hasNextRound() {
        return currentRound < totalRound;
    }

    public List<Car> playRound() {
        for (Car car : cars) {
            car.move();
        }
        currentRound++;
        return cars;
    }

    public List<String> findWinners() {
        int winnerLocation = findWinnerLocation();
        List<String> winners = new ArrayList<>();

        for (Car car : cars) {
            if (car.getCurrentLocation() == winnerLocation){
                winners.add(car.getName());
            }
        }
        return winners;
    }

    private int findWinnerLocation() {
        int max = 0;
        for (Car car : cars) {
            if (car.getCurrentLocation() > max) {
                max = car.getCurrentLocation();
            }
        }
        return max;
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
