package com.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.backend.entity.Partner;
import com.backend.repository.PartnerRepository;
import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    private final PartnerRepository repo;

    public PartnerController(PartnerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Partner> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Partner create(@RequestBody Partner partner) {
        return repo.save(partner);
    }

    @PutMapping("/{id}")
    public Partner updatePartner(@PathVariable Long id, @RequestBody Partner partnerDetails) {
        Partner existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Partner not found"));
        existing.setName(partnerDetails.getName());
        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
