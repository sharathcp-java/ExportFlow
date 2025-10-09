package com.saas.exportflow.export_flow.repository;

import com.saas.exportflow.export_flow.model.ExportRequest;
import com.saas.exportflow.export_flow.model.ScheduledExportJob;
import com.saas.exportflow.export_flow.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledExportJobRepository extends JpaRepository<ScheduledExportJob, Long> {
    List<ScheduledExportJob> findByUser(UserInfo user);
}
