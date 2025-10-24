package com.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.backend.entity.Client;
import com.backend.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repo;

    public Page<Client> getClientsByPartner(Long partnerId, String search, int page, int size, String sortBy, String direction) {
        Sort sort = "asc".equalsIgnoreCase(direction) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (search != null && !search.isBlank()) {
            return repo.findByPartnerIdAndClientNameContainingIgnoreCaseOrSpocNameContainingIgnoreCaseOrDivisionContainingIgnoreCase(
                    partnerId, search, search, search, pageable
            );
        }
        return repo.findByPartnerId(partnerId, pageable);
    }
}
