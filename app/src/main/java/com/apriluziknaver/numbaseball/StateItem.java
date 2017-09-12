package com.apriluziknaver.numbaseball;

/**
 * Created by mapri on 2017-09-09.
 */

public class StateItem {

    String number;
    String strike;
    String ball;
    String result;

    public StateItem(String number, String strike, String ball) {
        this.number = number;
        this.strike = strike;
        this.ball = ball;
    }

    public StateItem(String result) {
        this.result = result;
    }
}
