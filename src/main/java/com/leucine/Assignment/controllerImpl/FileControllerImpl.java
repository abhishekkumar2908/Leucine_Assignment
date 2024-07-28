package com.leucine.Assignment.controllerImpl;

import com.leucine.Assignment.controller.FileController;
import com.leucine.Assignment.dao.Assignments;
import com.leucine.Assignment.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/api/assignments")
@RestController
public class FileControllerImpl implements FileController {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Value("${file.base-directory}")
    private String baseDirectory;

    @Value("${server.address}")
    private String serverAddress;

    @GetMapping("/file-resource/{id}")
    @Override
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        try {
            // Fetch the assignment from the database
            Assignments assignment = assignmentsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Assignment not found"));

            // Construct the absolute file path
            Path filePath = Paths.get(baseDirectory).resolve(assignment.getFile()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                // Determine the content type based on the file extension
                String contentType = determineContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Add logging here if needed
            e.printStackTrace(); // Replace with proper logging
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/file-path/{fileName}")
    @Override
    public ResponseEntity<String> getFileUrl(String fileName) {
        return ResponseEntity.ok(serverAddress + "/api/assignments/files/" + fileName);

    }


    private String determineContentType(Path filePath) {
        try {
            return Files.probeContentType(filePath);
        } catch (IOException e) {
            return null;
        }
    }
}