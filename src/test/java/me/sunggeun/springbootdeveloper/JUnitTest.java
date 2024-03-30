package me.sunggeun.springbootdeveloper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    @Test
    @DisplayName("1 + 2는 3이다")
    void junitTest() {
        // given // when
        int a = 1;
        int b = 2;
        int sum = 3;

        //then
        Assertions.assertEquals(sum, a + b);
    }

    @Test
    void junitQuiz1() {
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길은";

        org.assertj.core.api.Assertions.assertThat(name1).isNotNull();
        org.assertj.core.api.Assertions.assertThat(name2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(name3).isNotNull();

        org.assertj.core.api.Assertions.assertThat(name1).isEqualTo(name2);
        org.assertj.core.api.Assertions.assertThat(name1).isNotEqualTo(name3);
    }

}
