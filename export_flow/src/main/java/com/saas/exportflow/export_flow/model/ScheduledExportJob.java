package com.saas.exportflow.export_flow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SCHEDULED_EXPORT_JOB")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledExportJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    private String dbType;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    @Column(columnDefinition = "TEXT")
    private String sqlQuery;

    private String outputType;
    private String deliveryType;
    private String deliveryDetails;

    private String cronExpression;
    private String status; // ACTIVE / INACTIVE

    private LocalDateTime lastRunAt;
    private LocalDateTime nextRunAt;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScheduledExportHistory> historyList;
}
