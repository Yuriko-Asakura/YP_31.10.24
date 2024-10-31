package com.example.pr3_bd.service;

import com.example.pr3_bd.enity.Service;
import com.example.pr3_bd.repository.CustomerRepository;
import com.example.pr3_bd.repository.ServiceRepository;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{
    private final ServiceRepository repository;

    public ServiceServiceImpl(ServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Service> findAllService() {
        return (List<Service>) repository.findAll();
    }

    @Override
    public Service createService(Service service) {
        return repository.save(service);
    }

    @Override
    public Service updateService(Service service) {
        return repository.save(service);
    }

    @Override
    public Service findServicebyId(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public void deleteService(Long id) {
        repository.deleteById(id);
    }


}
