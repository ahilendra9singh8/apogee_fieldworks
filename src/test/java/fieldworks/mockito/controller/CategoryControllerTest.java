//Mockito + MockMvc

package fieldworks.mockito.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fieldworks.testing.mockito.controller.CategoryController;
import fieldworks.testing.mockito.entity.Category;
import fieldworks.testing.mockito.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

	@InjectMocks
	private CategoryController categoryController;

	@Mock
	private CategoryService categoryService;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testGetAllCategories() throws Exception {
		Category category = new Category();
		category.setId(1L);
		category.setName("test");
		List<Category> categoryList = List.of(category);
		when(categoryService.getAllCategories()).thenReturn(categoryList);
		mockMvc.perform(get("/category/getAllCategories")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(categoryList.size()))
				.andExpect(jsonPath("$[0].name").value(category.getName()));
	}

	@Test
	void testCreateCategory() throws Exception {
		Category category = new Category();
		category.setId(1L);
		category.setName("test");

		when(categoryService.createCategory(category)).thenReturn(category);
		mockMvc.perform(post("/category/createCategory").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": 1, \"name\": \"test\"}")).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value(category.getName()));
	}

}

////@WebMvcTest
//package fieldworks.mockito.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import fieldworks.testing.mockito.controller.CategoryController;
//import fieldworks.testing.mockito.entity.Category;
//import fieldworks.testing.mockito.service.CategoryService;
//
//@WebMvcTest(CategoryController.class)
//class CategoryControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    @Test
//    void testGetAllCategories() throws Exception {
//        Category category = new Category();
//        category.setId(1L);
//        category.setName("test");
//
//        when(categoryService.getAllCategories())
//                .thenReturn(List.of(category));
//
//        mockMvc.perform(get("/category/getAllCategories"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(1))
//                .andExpect(jsonPath("$[0].name").value("test"));
//    }
//
//    @Test
//    void testCreateCategory() throws Exception {
//        Category category = new Category();
//        category.setId(1L);
//        category.setName("test");
//
//        when(categoryService.createCategory(any(Category.class)))
//                .thenReturn(category);
//
//        mockMvc.perform(post("/category/createCategory")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"id\":1,\"name\":\"test\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("test"));
//    }
//}