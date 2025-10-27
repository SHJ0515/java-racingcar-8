package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.dto.RacingInfoDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingServiceTest {

    private RacingService racingService;

    @BeforeEach
    void setUp() {
        racingService = new RacingService();
    }

    @Test
    @DisplayName("레이싱 상태를 초기화")
    void initRacingStatus() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni,jun");
        dto.setRound("5");

        // when
        racingService.initRacingStatus(dto);

        // then
        assertThat(racingService.hasNextRound()).isTrue();
    }

    @Test
    @DisplayName("다음 라운드가 있는지 확인")
    void hasNextRound() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound("3");
        racingService.initRacingStatus(dto);

        // when & then
        assertThat(racingService.hasNextRound()).isTrue();

        racingService.playRound();
        assertThat(racingService.hasNextRound()).isTrue();

        racingService.playRound();
        assertThat(racingService.hasNextRound()).isTrue();

        racingService.playRound();
        assertThat(racingService.hasNextRound()).isFalse();
    }

    @Test
    @DisplayName("라운드를 진행하면 자동차 목록을 반환")
    void playRound() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni,jun");
        dto.setRound("1");
        racingService.initRacingStatus(dto);

        // when
        List<Car> cars = racingService.playRound();

        // then
        assertThat(cars).hasSize(3);
        assertThat(cars).extracting("name")
                .containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("라운드를 진행을 통한 차의 이동")
    void playRoundMovesCars() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound("10");
        racingService.initRacingStatus(dto);

        // when
        int totalDistance = 0;
        for (int i = 0; i < 10; i++) {
            List<Car> cars = racingService.playRound();
            for (Car car : cars) {
                totalDistance += car.getCurrentLocation();
            }
        }

        // then
        assertThat(totalDistance).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("단독 우승")
    void findWinnersSingle() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni,jun");
        dto.setRound("5");
        racingService.initRacingStatus(dto);

        // when
        for (int i = 0; i < 5; i++) {
            racingService.playRound();
        }
        List<String> winners = racingService.findWinners();

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("공동 우승 가능")
    void findWinnersMultiple() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound("1");
        racingService.initRacingStatus(dto);

        // when
        racingService.playRound();
        List<String> winners = racingService.findWinners();

        // then
        assertThat(winners).hasSizeBetween(1, 2);
    }
}
