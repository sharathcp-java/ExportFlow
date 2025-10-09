package com.saas.exportflow.export_flow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EXPORT_REQUEST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    private String dbType;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword; // Encrypted

    @Column(columnDefinition = "TEXT")
    private String sqlQuery;

    private String outputType;       // CSV / XLSX
    private String deliveryType;     // DOWNLOAD / EMAIL / SFTP
    private String deliveryDetails;  // Email address or SFTP path

    private String status;           // SUCCESS / FAILED / RUNNING
    private String resultFilePath;

    private LocalDateTime createdAt;
    private LocalDateTime executedAt;
}
