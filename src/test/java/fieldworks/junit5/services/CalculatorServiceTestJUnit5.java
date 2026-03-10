package fieldworks.junit5.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import fieldworks.testing.junit5.services.CalculatorService;

public class CalculatorServiceTestJUnit5 {

//	execute the logic before all test cases
	@BeforeAll
	public static void init() {
		System.out.println("call Before all Test cases");
	}

//	Execute After all Test Cases
	@AfterAll
	public static void cleanup() {
		System.out.println("call After all Test cases");
	}

//	execute before each test case
	@BeforeEach
	public void beforeEachTestCase() {
		System.out.println("call beforeEachTestCase");
	}

	// execute after each test case
	@AfterEach
	public void afterEachTestCase() {
		System.out.println("call afterEachTestCase");
	}

	@Test
	@DisplayName("This is custom name")
	public void addTwoNumbersTest() {
		System.out.println("addTwoNumbersTest Test case");
		int actualResult = CalculatorService.addTwoNumbers(12, 12);
		int expectedResult = 26;
		Assertions.assertEquals(expectedResult, actualResult);

	}

	@Test
	@Disabled
	public void addAnyNumbersTest() {
		System.out.println("addAnyNumbersTest Test case");
		int actualResult = CalculatorService.sumAnyNumbers(12, 12, 12, 12);
		int expectedResult = 48;
		Assertions.assertEquals(expectedResult, actualResult);
	}

//	@Tag
//	@Nested
//	@TestFactory

}
