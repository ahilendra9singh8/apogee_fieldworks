package fieldworks.testing.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fieldworks.testing.mockito.entity.Category;
import fieldworks.testing.mockito.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAllCategories")
	public List<Category> getAllCategories() {
		List<Category> allCategories = categoryService.getAllCategories();
		return allCategories;
	}

	@PostMapping("createCategory")
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.createCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}
}
