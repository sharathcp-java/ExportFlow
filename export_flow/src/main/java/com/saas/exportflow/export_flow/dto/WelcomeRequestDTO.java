package com.saas.exportflow.export_flow.dto;

import com.saas.exportflow.export_flow.model.ExportRequest;
import com.saas.exportflow.export_flow.model.ScheduledExportJob;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WelcomeRequestDTO {

    private List<ExportRequest> allExportRequest;

    private List<ScheduledExportJob> allScheduledExportJobs;

}
