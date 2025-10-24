package com.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.entity.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Page<Client> findByPartnerId(Long partnerId, Pageable pageable);

	Page<Client> findByPartnerIdAndClientNameContainingIgnoreCaseOrSpocNameContainingIgnoreCaseOrDivisionContainingIgnoreCase(
	        Long partnerId, String clientName, String spocName, String division, Pageable pageable
	);

}
