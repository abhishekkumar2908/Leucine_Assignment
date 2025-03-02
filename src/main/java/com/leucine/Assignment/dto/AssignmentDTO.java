package com.leucine.Assignment.dto;

import com.leucine.Assignment.enums.ClassName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignmentDTO {
    private String title;
    private String description;
    private String dueDate;
    private MultipartFile file;
    private Long createdBy;
    private ClassName className;


}