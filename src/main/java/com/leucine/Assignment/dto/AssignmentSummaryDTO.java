package com.leucine.Assignment.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class AssignmentSummaryDTO {
    private int totalAssignments;
    private int totalSubmittedAssignments;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime upcomingDueDate;
}