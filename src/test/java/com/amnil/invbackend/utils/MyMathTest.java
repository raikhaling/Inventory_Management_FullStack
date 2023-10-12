package com.amnil.invbackend.utils;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class MyMathTest {

    MyMath underTest = new MyMath();
    @Test
    void isShouldAddNumberFromArray(){
        //given
        int[] numbers = {1,2,3,4,5};

        //when
        int results = underTest.calculateSum(numbers);

        //then
        int expected = 15;
        assertThat(results).isEqualTo(expected);

    }

}