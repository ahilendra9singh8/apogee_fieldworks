////With h2 database
//package fieldworks.mockito.repository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import fieldworks.testing.mockito.entity.Category;
//import fieldworks.testing.mockito.repository.CategoryRepository;
//
//@DataJpaTest
//class CategoryRepositoryTest {
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	private Category category;
//
//	@BeforeEach
//	void setUp() {
////		Insert row in category table
//		category = new Category();
//		category.setName("Test");
//		categoryRepository.save(category);
//	}
//
//	@AfterEach
//	void tearDown() {
////		Delete row from category table
//		categoryRepository.delete(category);
//	}
//
//	@Test
//	void testFindByName() {
//		Category foundCategory = categoryRepository.findByName("Test").orElse(null);
//		assertNotNull(foundCategory);
//		assertEquals(category.getName(), foundCategory.getName());
//	}
//
//	@Test
//	void testDeleteByName() {
//		categoryRepository.deleteByName("Test");
//		Category foundCategory = categoryRepository.findByName("Test").orElse(null);
//		assertNull(foundCategory);
//	}
//
//}

// //yh direct mockito use kr rha h (uper bala code h2 database use kr rha h .properties file ke dwara use kr rha h)
package fieldworks.mockito.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fieldworks.testing.mockito.entity.Category;
import fieldworks.testing.mockito.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class) // Mockito ko JUnit 5 ke saath integrate karne ke liye extension use kiya
									// gaya.(Mockito extension ko enable karna)
class CategoryRepositoryTest {

	@Mock // Iska use categoryRepository ko mock karne ke liye kiya gaya.
	private CategoryRepository categoryRepository; // CategoryRepository ko mock kar rahe hain

	private Category category;

	@BeforeEach
	void setUp() {
		category = new Category();
		category.setName("Test");
	}

	@Test
	void testFindByName() {
		// Mockito ke through findByName ko mock karna
		when(categoryRepository.findByName("Test")).thenReturn(Optional.of(category));

		Category foundCategory = categoryRepository.findByName("Test").orElse(null);

		assertNotNull(foundCategory);
		assertEquals(category.getName(), foundCategory.getName());

		// Verify ki mock method ko call kiya gaya
		verify(categoryRepository).findByName("Test");
	}

	@Test
	void testDeleteByName() {
		// Mockito ke through deleteByName ko mock karna
		doNothing().when(categoryRepository).deleteByName("Test");

		categoryRepository.deleteByName("Test");

		// Verify ki deleteByName method ko call kiya gaya
		verify(categoryRepository).deleteByName("Test");

		// Ab findByName ko call karenge aur null expect karenge
		when(categoryRepository.findByName("Test")).thenReturn(Optional.empty());
		Category foundCategory = categoryRepository.findByName("Test").orElse(null);

		assertNull(foundCategory);
	}
}
