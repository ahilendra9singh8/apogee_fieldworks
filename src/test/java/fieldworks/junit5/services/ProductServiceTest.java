package fieldworks.junit5.services;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fieldworks.testing.junit5.services.ProductService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceTest {

	ProductService productService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Before All Tests");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("After All Tests");
	}

	@BeforeEach
	void setUp() throws Exception {
		productService = new ProductService();
		System.out.println("Before Every Tests");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("After Every Tests");
	}

	@Test
	@Order(1)
	@DisplayName("✅ Price calculation with tax")
	void testCalculatePriceWithTax() {
		System.out.println("testCalculatePriceWithTax");
		double actual = productService.calculatePriceWithTax(100.0);
		Assertions.assertEquals(118.0, actual, 0.01); // delta = 0.01 for double
	}

	@Test
	@Order(2)
	@DisplayName("🚫 Negative price throws exception")
	void testNegativePriceThrowsException() {
		System.out.println("testNegativePriceThrowsException");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			productService.calculatePriceWithTax(-50);
		});
	}

	@Test
	@Order(3)
	@DisplayName("💲 Check if price is expensive")
	void testIsExpensive() {
		System.out.println("testIsExpensive");
		Assertions.assertTrue(productService.isExpensive(600));
		Assertions.assertFalse(productService.isExpensive(400));
	}

//	@Test
//	@Order(4)
//	@DisplayName("🔎 Test if logger creates log in logs folder")
//	void testLogFilePath() throws Exception {
//	    File logsFolder = new File("logs");
//	    File file = new File(logsFolder, "test-log.txt");
//
//	    // Ensure logs folder exists
//	    if (!logsFolder.exists()) {
//	        boolean created = logsFolder.mkdirs();
//	        System.out.println("📁 Created logs folder: " + created);
//	    }
//
//	    System.out.println("📍 Expected log path: " + file.getAbsolutePath());
//
//	    // Delete the file if it exists
//	    if (file.exists()) {
//	        System.out.println("❌ Log file already exists, deleting it...");
//	        boolean deleted = file.delete();
//	        System.out.println("✅ File deleted: " + deleted);
//	    }
//
//	    // Try to create the file again
//	    boolean created = file.createNewFile();
//	    System.out.println("✅ File created: " + created);
//
//	    // Assert that the file now exists
//	    Assertions.assertTrue(file.exists(), "Log file should exist.");
//	}


	@Nested
	@DisplayName("📦 Nested Tests: Special Cases")
	class SpecialCaseTests {
		@Test
		@DisplayName("🧪 Zero price returns zero")
		void testZeroPrice() {
			System.out.println("testZeroPrice");
			Assertions.assertEquals(0.0, productService.calculatePriceWithTax(0.0), 0.01);
		}
	}

	@Test
	@DisplayName("🚧 Not implemented yet")
	void testFutureFeature() {
		System.out.println("testFutureFeature");
		// future logic
	}

}