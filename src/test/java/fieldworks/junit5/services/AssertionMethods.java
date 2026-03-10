package fieldworks.junit5.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertionMethods {

//	Assertions -> validating actual result with expected result

	// Assertions class

	@Test
	public void test1() {
		System.out.println("Testing Some Assertion methods");
		int actual = 12;
		int expexted = 12;

		// overloaded methods
		Assertions.assertEquals(expexted, actual);

		// Array
		int[] actualIntArray = { 1, 2, 3, 4, 5, 6 };
		int[] expectedIntArray = { 1, 2, 3, 4, 5, 6 };

		Assertions.assertArrayEquals(expectedIntArray, actualIntArray);

		// Assertions.assertSame
		// Assertions.assertNull
		// Assertions.assertNotNull
		// Assertions.assertTrue

	}

}