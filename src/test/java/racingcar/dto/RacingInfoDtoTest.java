package racingcar.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RacingInfoDtoTest {

    @Test
    @DisplayName("자동차 이름을 설정후 조회")
    void setCarsAndGetCars() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        String carNames = "pobi,woni,jun";

        // when
        dto.setCars(carNames);

        // then
        assertThat(dto.getCars()).isEqualTo(carNames);
    }

    @Test
    @DisplayName("시도 횟수를 설정후 조회")
    void setRoundAndGetRound() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        String round = "5";

        // when
        dto.setRound(round);

        // then
        assertThat(dto.getRound()).isEqualTo(round);
    }
}
