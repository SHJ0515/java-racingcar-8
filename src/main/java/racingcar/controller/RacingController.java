package racingcar.controller;

import racingcar.domain.Car;
import racingcar.dto.RacingInfoDto;
import racingcar.service.RacingService;
import racingcar.validation.InputValidation;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingController {

    private final InputValidation inputValidation;
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingService racingService;

    public RacingController() {
        this.inputValidation = new InputValidation();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.racingService = new RacingService();
    }

    public void run() {
        try {
            RacingInfoDto racingInfoDto = inputView.input();
            inputValidation.validate(racingInfoDto);

            racingService.initRacingStatus(racingInfoDto);
            while (racingService.hasNextRound()) {
                List<Car> cars = racingService.playRound();
                outputView.printRoundResult(cars);
            }

            outputView.printWinner(racingService.findWinners());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            throw e;
        }
    }
}
