package backend.Ecommercewebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category createCategory(Category category,Long parent_id){
        if(parent_id!=null) {
            Category parent= categoryRepository.findById(parent_id).orElse(null);
            if (parent != null) {
                category.setParent(parent);
                parent.getChildren().add(category);
            }
        }
        return categoryRepository.save(category);
    }
    public Category getCategoryById(Long category_id){
        return categoryRepository.findById(category_id).orElse(null);
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public void deleteCategory(Long category_id){
        Category category= categoryRepository.findById(category_id).orElse(null);
        if(category!=null){
            Category parent=category.getParent();
            if(parent!=null){
                parent.removeChild(category);
            }
            categoryRepository.deleteById(category_id);
        }
    }
    public Category updateCategory(Long category_id,Category category){
        Category category1= categoryRepository.findById(category_id).orElse(null);
        category1.setName(category.getName());

        return categoryRepository.save(category1);
    }
}
