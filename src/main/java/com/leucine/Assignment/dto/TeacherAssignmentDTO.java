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
public class TeacherAssignmentDTO {
    private String assignmentTitle;
    private ClassName className;
    private LocalDateTime dueDate;
}