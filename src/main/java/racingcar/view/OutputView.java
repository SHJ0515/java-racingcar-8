package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {

    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            String hyphens = "-".repeat(car.getCurrentLocation());
            System.out.println(car.getName() + " : " + hyphens);
        }
        System.out.println();
    }

    public void printWinner(List<String> winnerList) {
        String winners = String.join(",", winnerList);
        System.out.println("최종 우승자 : " + winners);
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
