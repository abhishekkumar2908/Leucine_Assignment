package com.leucine.Assignment.dto;

import com.leucine.Assignment.enums.ClassName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubmissionDTO {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private String submissionText;
    private MultipartFile data;
    private String submissionDate;
    private ClassName className;
}
