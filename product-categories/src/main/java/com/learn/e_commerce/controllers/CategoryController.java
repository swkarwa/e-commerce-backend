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

@RestController
@RequestMapping("/api")
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
                                    schema = @Schema(implementation = com.learn.e_commerce.model.Category.class)
                            )
                    )
            })
    // Swagger doc end

    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<List<com.learn.e_commerce.model.Category>> getCategories() {
        List<com.learn.e_commerce.model.Category> categories = categoryService.getAllCategories();
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

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public ResponseEntity<String> addCategory(@RequestBody com.learn.e_commerce.model.Category category) {

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
    @RequestMapping(value = "/admin/category/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {

        String message = null;
        try {
            message = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }

    }

    // swagger doc
    @Operation(
            summary = "Update a category",
            description = "Update a category by ID"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the category",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(type = "string", example = "Category updated successfully")
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

    @RequestMapping(value = "/admin/category/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
