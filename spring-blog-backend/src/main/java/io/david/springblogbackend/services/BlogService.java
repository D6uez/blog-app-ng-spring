package io.david.springblogbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.david.springblogbackend.models.Blog;
import io.david.springblogbackend.repositories.BlogRepository;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlogList() {

        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog Not Found"));
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateOrCreateBlog(Blog newBlog, Long id) {
        return blogRepository.findById(id)
                .map(blog -> {
                    blog.setTitle(newBlog.getTitle());
                    blog.setBody(newBlog.getBody());
                    blog.setTopic(newBlog.getTopic());

                    return blogRepository.save(blog);
                })
                .orElseGet(() -> {
                    newBlog.setId(id);

                    return blogRepository.save(newBlog);
                });
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

}
