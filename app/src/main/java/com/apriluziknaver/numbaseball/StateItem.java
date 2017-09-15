package com.apriluziknaver.numbaseball;

/**
 * Created by mapri on 2017-09-09.
 */

public class StateItem {

    String number;
    String strike;
    String ball;
    String ballAndStr;

    public StateItem(String number, String strike, String ball) {
        this.number = number;
        this.strike = strike;
        this.ball = ball;
        ballAndStr= ball+strike;
    }

}
