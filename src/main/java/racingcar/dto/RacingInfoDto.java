package racingcar.dto;

public class RacingInfoDto {

    private String cars;  // 자동차 이름 입력값
    private String round;  //시행 횟수

    public String getCars() {
        return cars;
    }

    public void setCars(String cars) {
        this.cars = cars;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
