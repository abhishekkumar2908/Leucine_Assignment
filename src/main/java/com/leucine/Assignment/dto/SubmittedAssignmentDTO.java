package com.leucine.Assignment.dto;

import com.leucine.Assignment.enums.ClassName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmittedAssignmentDTO {
    private String assignmentTitle;
    private String studentName;
    private LocalDateTime submissionDate;
    private ClassName className;

}