package racingcar.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.dto.RacingInfoDto;

import static org.assertj.core.api.Assertions.*;

class InputValidationTest {

    private InputValidation inputValidation;

    @BeforeEach
    void setUp() {
        inputValidation = new InputValidation();
    }

    @Test
    @DisplayName("맞는 입력값 입력 시 검증")
    void validateSuccess() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni,jun");
        dto.setRound("5");

        // when & then
        assertThatCode(() -> inputValidation.validate(dto))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 이름이 null인 경우")
    void validateCarNameNull() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars(null);
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 필수 입니다.");
    }

    @Test
    @DisplayName("자동차 이름이 빈 문자열인 경우")
    void validateCarNameEmpty() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 필수 입니다.");
    }

    @Test
    @DisplayName("자동차 이름이 공백만 있는 경우")
    void validateCarNameBlank() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("   ");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 필수 입니다.");
    }

    @Test
    @DisplayName("자동차 이름이 쉼표로 시작하는 경우")
    void validateCarNameStartsWithComma() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars(",pobi,woni");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 구분자로 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름이 쉼표로 끝나는 경우")
    void validateCarNameEndsWithComma() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni,");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 구분자로 끝날 수 없습니다.");
    }

    @Test
    @DisplayName("연속된 쉼표가 있는 경우")
    void validateConsecutiveCommas() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,,woni");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자를 연속으로 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("중복된 자동차 이름이 있으면 예외가 발생한다")
    void validateDuplicateNames() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni,pobi");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복일 수 없습니다.");
    }

    @Test
    @DisplayName("쉼표로 이름 분리 후 자동차 이름에 공백만 있는 경우")
    void validateEachNameBlank() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi, ,woni");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 공백만으로 구성될 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과할 경우")
    void validateNameLengthExceeds() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,javaji");
        dto.setRound("5");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 최대 5자 입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "woni", "jun", "12345", "a"})
    @DisplayName("자동차 이름이 5자 이하로 검증 성공")
    void validateNameLengthValid(String name) {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars(name);
        dto.setRound("5");

        // when & then
        assertThatCode(() -> inputValidation.validate(dto))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아닌 경우")
    void validateRoundNotNumber() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound("abc");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 숫자만 입력 가능합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 0인 경우")
    void validateRoundZero() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound("0");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 음수인 경우")
    void validateRoundNegative() {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound("-1");

        // when & then
        assertThatThrownBy(() -> inputValidation.validate(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "10", "100"})
    @DisplayName("시도 횟수가 1 이상으로 검증 성공")
    void validateRoundValid(String round) {
        // given
        RacingInfoDto dto = new RacingInfoDto();
        dto.setCars("pobi,woni");
        dto.setRound(round);

        // when & then
        assertThatCode(() -> inputValidation.validate(dto))
                .doesNotThrowAnyException();
    }
}
