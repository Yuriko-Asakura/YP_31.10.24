package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Service;

import java.util.List;

public interface ServiceService {
    List<Service> findAllService();
    Service createService (Service service);
    Service updateService (Service service);
    Service findServicebyId (Long id);
    void deleteService (Long id);
}
