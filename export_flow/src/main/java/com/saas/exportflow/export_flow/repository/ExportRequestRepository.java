package com.saas.exportflow.export_flow.repository;

import com.saas.exportflow.export_flow.model.ExportRequest;
import com.saas.exportflow.export_flow.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExportRequestRepository extends JpaRepository<ExportRequest, Long> {

    List<ExportRequest> findByUser(UserInfo user);


}
