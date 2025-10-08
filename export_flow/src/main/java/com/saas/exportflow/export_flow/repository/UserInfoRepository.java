package com.saas.exportflow.export_flow.repository;
import com.saas.exportflow.export_flow.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String name);
}