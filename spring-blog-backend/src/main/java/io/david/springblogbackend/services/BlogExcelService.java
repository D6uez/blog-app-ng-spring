package io.david.springblogbackend.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import io.david.springblogbackend.helper.BlogExcelHelper;
import io.david.springblogbackend.models.Blog;
import io.david.springblogbackend.repositories.BlogRepository;

@Service
public class BlogExcelService {

    BlogRepository blogRepository;

    public BlogExcelService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public ByteArrayInputStream load() {
        List<Blog> blogs = blogRepository.findAll();
        ByteArrayInputStream in = BlogExcelHelper.blogsToExcel(blogs);

        return in;
    }

}
