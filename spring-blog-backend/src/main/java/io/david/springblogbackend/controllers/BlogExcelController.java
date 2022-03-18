package io.david.springblogbackend.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.david.springblogbackend.services.BlogExcelService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/export/blog")
@Controller
public class BlogExcelController {

    BlogExcelService blogExcelService;

    public BlogExcelController(BlogExcelService blogExcelService) {
        this.blogExcelService = blogExcelService;
    }

    @GetMapping(value = { "", "/" })
    public ResponseEntity<Resource> getBlogSheet() {
        String filename = "bloglist.xlsx";

        InputStreamResource file = new InputStreamResource(blogExcelService.load());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}
