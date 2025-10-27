package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @Test
    @DisplayName("자동차 생성")
    void createCar() {
        // given
        String carName = "pobi";

        // when
        Car car = new Car(carName);

        // then
        assertThat(car.getName()).isEqualTo(carName);
        assertThat(car.getCurrentLocation()).isZero();
    }

    @Test
    @DisplayName("자동차 이름을 조회")
    void getName() {
        // given
        Car car = new Car("pobi");

        // when
        String name = car.getName();

        // then
        assertThat(name).isEqualTo("pobi");
    }

    @Test
    @DisplayName("자동차의 현재 위치를 조회")
    void getCurrentLocation() {
        // given
        Car car = new Car("pobi");

        // when
        int location = car.getCurrentLocation();

        // then
        assertThat(location).isZero();
    }

    @Test
    @DisplayName("move 메서드 호출을 통한 위치 변경")
    void move() {
        // given
        Car car = new Car("pobi");
        int initialLocation = car.getCurrentLocation();

        // when
        for (int i = 0; i < 10; i++) {
            car.move();
        }

        // then
        assertThat(car.getCurrentLocation()).isGreaterThanOrEqualTo(initialLocation);
    }
}
