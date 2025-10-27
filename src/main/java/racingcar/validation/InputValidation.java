package racingcar.validation;

import racingcar.dto.RacingInfoDto;

import java.util.HashSet;
import java.util.Set;

public class InputValidation {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_ROUND = 1;
    private static final String DELIMITER = ",";   //이름 구분자

    public void validate(RacingInfoDto racingInfoDto) {
        validateCarNames(racingInfoDto.getCars());
        validateRound(racingInfoDto.getRound());
    }

    private void validateCarNames(String carName) {
        validateNameIsEmpty(carName);
        validateConsecutiveCommas(carName);

        String[] names = carName.split(DELIMITER);
        validateDuplicateNames(names);
        validateStartOrEndWithDelimiter(carName);

        for (String name : names) {
            validateEachNameIsEmptyOrBlank(name);
            validateEachNameLength(name);
        }
    }

    private void validateRound(String round) {
        validateRoundIsNumber(round);
        validateRoundIsPositive(round);
    }

    private void validateNameIsEmpty(String carName) {
        if (carName == null || carName.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 필수 입니다.");
        }
    }

    private void validateStartOrEndWithDelimiter(String carName) {
        if (carName.startsWith(DELIMITER)) {
            throw new IllegalArgumentException("자동차 이름은 구분자로 시작할 수 없습니다.");
        }
        if (carName.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("자동차 이름은 구분자로 끝날 수 없습니다.");
        }
    }

    private void validateConsecutiveCommas(String carName) {
        if (carName.contains(DELIMITER + DELIMITER)) {
            throw new IllegalArgumentException("구분자를 연속으로 입력할 수 없습니다.");
        }
    }

    private void validateDuplicateNames(String[] names) {
        Set<String> nameSet = new HashSet<>();
        for (String name : names) {
            if (nameSet.contains(name)) {
                throw new IllegalArgumentException("자동차 이름은 중복일 수 없습니다.");
            }
            nameSet.add(name);
        }
    }

    private void validateEachNameIsEmptyOrBlank(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 공백만으로 구성될 수 없습니다.");
        }
    }

    private void validateEachNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 최대 5자 입니다.");
        }
    }

    private void validateRoundIsNumber(String round) {
        try {
            Integer.parseInt(round);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자만 입력 가능합니다.");
        }
    }

    private void validateRoundIsPositive(String round) {
        int roundNumber = Integer.parseInt(round);
        if (roundNumber < MIN_ROUND) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }
}
