package com.leucine.Assignment.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface FileController {

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id);
}