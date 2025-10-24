package com.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.entity.Client;
import com.backend.entity.Partner;
import com.backend.repository.ClientRepository;
import com.backend.repository.PartnerRepository;
import com.backend.Service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepo;
    private final PartnerRepository partnerRepo;
    private final ClientService clientService;

    public ClientController(ClientRepository clientRepo, PartnerRepository partnerRepo, ClientService clientService) {
        this.clientRepo = clientRepo;
        this.partnerRepo = partnerRepo;
        this.clientService = clientService;
    }

    @GetMapping("/partner/{partnerId}")
    public Page<Client> getClientsByPartner(
            @PathVariable Long partnerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) String search
    ) {
        return clientService.getClientsByPartner(partnerId, search, page, size, sortBy, direction);
    }

    @PostMapping("/partner/{partnerId}")
    public Client addClient(@PathVariable Long partnerId, @RequestBody Client client) {
        Partner partner = partnerRepo.findById(partnerId).orElseThrow(() -> new RuntimeException("Partner not found"));
        client.setPartner(partner);
        return clientRepo.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client existing = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));

     
        existing.setClientName(clientDetails.getClientName());
        existing.setSpocName(clientDetails.getSpocName());
        existing.setDivision(clientDetails.getDivision());
        existing.setLocation(clientDetails.getLocation());
        existing.setRequirement(clientDetails.getRequirement());
        existing.setStatus(clientDetails.getStatus());
        existing.setNextSteps(clientDetails.getNextSteps());
   

        return clientRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepo.deleteById(id);
    }

    @GetMapping("/divisions")
    public List<String> getDivisions() {
        return List.of("Recruitment", "Payroll","Recruitment with Payroll", "Others" );
    }

    @GetMapping("/statuses")
    public List<String> getStatuses() {
        return List.of("Pending","On Hold","On Process","Finalising","Selected","Not Selected");
    }
    
    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

}
