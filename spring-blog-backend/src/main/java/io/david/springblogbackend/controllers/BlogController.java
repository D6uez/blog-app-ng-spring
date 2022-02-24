package io.david.springblogbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.david.springblogbackend.models.Blog;
import io.david.springblogbackend.services.BlogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/blog")
@RestController
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value = { "", "/" })
    List<Blog> getBlogList() {
        return blogService.getBlogList();
    }

    @GetMapping("/{id}")
    Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PostMapping("/newBlog")
    Blog newBlog(@RequestBody Blog newBlog) {
        return blogService.createBlog(newBlog);
    }

    @PutMapping("/{id}")
    Blog updateBlog(@RequestBody Blog newBlog, @PathVariable Long id) {
        return blogService.updateOrCreateBlog(newBlog, id);
    }

    @DeleteMapping("/{id}")
    void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }

}
