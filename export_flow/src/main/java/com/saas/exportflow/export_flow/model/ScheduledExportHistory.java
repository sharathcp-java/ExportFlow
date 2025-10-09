package com.saas.exportflow.export_flow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SCHEDULED_EXPORT_HISTORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledExportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private ScheduledExportJob job;

    private String runStatus;     // SUCCESS / FAILED
    private String filePath;
    private LocalDateTime runStart;
    private LocalDateTime runEnd;
    private String errorMessage;
    private int retryCount;
}
