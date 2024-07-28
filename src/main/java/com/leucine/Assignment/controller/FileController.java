package com.leucine.Assignment.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface FileController {

    public ResponseEntity<Resource> getFile( @NotNull @PathVariable Long id);
}