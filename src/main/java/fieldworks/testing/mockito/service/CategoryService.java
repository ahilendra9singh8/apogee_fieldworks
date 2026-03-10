package fieldworks.testing.mockito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fieldworks.testing.mockito.entity.Category;
import fieldworks.testing.mockito.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category createCategory(Category category) {
		Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
		if (optionalCategory.isPresent()) {
			throw new IllegalStateException("Category already present" + category.getId());
		}
		Category savedCategory = categoryRepository.save(category);
		return savedCategory;
	}

	public List<Category> getAllCategories() {
		List<Category> findAllCategory = categoryRepository.findAll();
		return findAllCategory;
	}

	public Category getCategoryById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.get();
	}

	public String deleteCategory(Long id) {
		categoryRepository.deleteById(id);
		return "Category" + id + "has been deleted";
	}
}

