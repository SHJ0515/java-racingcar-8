package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.RacingInfoDto;

public class InputView {
    private static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_ROUND = "시도할 횟수는 몇 회인가요?";
    private static final String RACING_RESULT = "\n실행 결과";

    public RacingInfoDto input(){
        RacingInfoDto racingInfoDto = new RacingInfoDto();

        System.out.println(INPUT_CAR_NAME);
        racingInfoDto.setCars(Console.readLine());

        System.out.println(INPUT_ROUND);
        racingInfoDto.setRound(Console.readLine());

        System.out.println(RACING_RESULT);
        return racingInfoDto;
    }
}
