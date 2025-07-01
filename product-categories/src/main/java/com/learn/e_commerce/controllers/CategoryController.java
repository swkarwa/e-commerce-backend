package com.learn.e_commerce.controllers;

import com.learn.e_commerce.model.Category;
import com.learn.e_commerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Swagger doc
    @Operation(
            summary = "Get all categories",
            description = "Returns a list of all available product categories."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval of categories",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Category.class)
                            )
                    )
            })
    // Swagger doc end

    @GetMapping(value = "/api/public/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Swagger doc
    @Operation(
            summary = "Add a category",
            description = "Adds a category"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully adds a category",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE

                            )

                    )
            }
    )
    // Swagger doc end
    @PostMapping("/api/admin/category")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {

        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created", HttpStatus.CREATED);

    }

    // Swagger doc
    @Operation(
            summary = "Delete a category",
            description = "Deletes a category by ID"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted the category",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(type = "string", example = "Category deleted successfully")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(type = "string", example = "Category not found")
                            )
                    )
            }
    )
    // Swagger doc end
    @DeleteMapping("/api/admin/category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {

        String message = null;
        try {
            message = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }

    }
}
