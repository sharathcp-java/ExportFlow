package com.saas.exportflow.export_flow.service;

import com.saas.exportflow.export_flow.dto.WelcomeRequestDTO;
import com.saas.exportflow.export_flow.model.ExportRequest;

import java.util.List;

public interface ExportflowService {
    WelcomeRequestDTO getallExportRequestByUser(String username);
}
