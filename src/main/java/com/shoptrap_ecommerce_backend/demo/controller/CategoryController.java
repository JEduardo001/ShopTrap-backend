package com.shoptrap_ecommerce_backend.demo.controller;

import com.shoptrap_ecommerce_backend.demo.dto.DtoApiResponse;
import com.shoptrap_ecommerce_backend.demo.dto.dtoCreate.DtoCreateCategory;
import com.shoptrap_ecommerce_backend.demo.dto.dtoEntity.DtoCategory;
import com.shoptrap_ecommerce_backend.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DtoApiResponse> getall(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoApiResponse(
                HttpStatus.CREATED.value(),"Catgorias obtenidas", categoryService.getAll(page,size)
        ));
    }

    @PostMapping("/create")
    public ResponseEntity<DtoApiResponse> create(@Valid @RequestBody DtoCreateCategory newCategory){
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoApiResponse(
                HttpStatus.CREATED.value(),"Categoria Creada", categoryService.create(newCategory)
        ));
    }

    @PostMapping("/update")
    public ResponseEntity<DtoApiResponse> update(@Valid @RequestBody DtoCategory newCategory){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoApiResponse(
                HttpStatus.OK.value(),"Categoria actualizada",categoryService.update(newCategory)
        ));
    }

    @DeleteMapping("/delete/{idCategory}")
    public ResponseEntity<DtoApiResponse> delete(@PathVariable Long idCategory){
        categoryService.delete(idCategory);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoApiResponse(
                HttpStatus.OK.value(),"Categoria eliminada"
        ));
    }

}
