package fieldworks.mockito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fieldworks.testing.mockito.entity.Category;
import fieldworks.testing.mockito.repository.CategoryRepository;
import fieldworks.testing.mockito.service.CategoryService;

@ExtendWith(MockitoExtension.class) // Mockito ko JUnit 5 ke saath integrate karne ke liye extension use kiya
									// gaya.(Mockito extension ko enable karna)
class CategoryServiceTest {

	// @Mock fake (dummy) object banata hai.
	// CategoryRepository ka real code nahi chalega
	// Sirf ek fake object milega
	// Iska use categoryRepository ko mock karne ke liye kiya gaya.

	@Mock
	private CategoryRepository categoryRepository;

	// @InjectMocks real object banata hai
	// aur uske andar jo dependencies hain, unme @Mock wale objects inject kar deta
	// hai.
	// Mocked dependencies ko real class me inject karta hai
	@InjectMocks
	private CategoryService categoryService;

	private Category category;

	@BeforeEach
	void setUp() {
		category = new Category();
		category.setId(1L);
		category.setName("Test");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testCreateCategoty_categoryShouldbeCreated() {
		when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
		when(categoryRepository.save(category)).thenReturn(category);
		Category savedCategory = categoryService.createCategory(category);
		assertNotNull(savedCategory);
		assertEquals(category.getName(), savedCategory.getName());
	}

	@Test
	void testCreateCategoty_ShouldThrowException_WhenCategoryAlreadyExists() {
		when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
		assertThrows(IllegalStateException.class, () -> categoryService.createCategory(category));
		verify(categoryRepository, times(0)).save(category);  //“Test ke dauran repository ka save() method kabhi call nahi hona chahiye”(kyuki yah fake h)
	}

//	@Test
//	void testGetAllCategories() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetCategoryById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteCategory() {
//		fail("Not yet implemented");
//	}

}
