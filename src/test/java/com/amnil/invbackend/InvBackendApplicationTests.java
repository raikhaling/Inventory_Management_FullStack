package com.amnil.invbackend;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class InvBackendApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumebrs() {
		//given
		int numberOne = 20;
		int numberTwo = 30;

		//when
		int results = underTest.add(20, 30);

		//then
		int expected = 50;
		assertThat(results).isEqualTo(expected);
	}
	class Calculator{
		int add(int a, int b){return a+b;}
	}

}
