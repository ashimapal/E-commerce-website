package backend.Ecommercewebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/post/{parent_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @PathVariable long parent_id){
        Category createdCategory= categoryService.createCategory(category,parent_id);
        return ResponseEntity.ok(createdCategory);
    }


    @GetMapping("/getall")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories= categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/get/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long category_id){
        Category category= categoryService.getCategoryById(category_id);
        if(category!=null){
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("delete/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long category_id){
        categoryService.deleteCategory((category_id));
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long category_id, @RequestBody Category category){
        Category category1=categoryService.updateCategory(category_id,category);
        if(category!=null){
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }
}
