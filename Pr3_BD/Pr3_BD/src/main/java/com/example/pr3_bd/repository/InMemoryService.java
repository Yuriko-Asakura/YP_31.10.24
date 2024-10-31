package com.example.pr3_bd.repository;

import com.example.pr3_bd.enity.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryService {
    private final List<Service> SERVICE = new ArrayList<>();
    public List<Service> findAllService(Long id) {
        return SERVICE;
    }

    public Service findService(Long id) {
        return SERVICE
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public Service createService(Service service) {
        SERVICE.add(service);
        return service;
    }

    public Service updateService(Service service) {
        var serIndex = IntStream.range(0, SERVICE.size())
                .filter(index -> SERVICE.get(index).getId().equals(service.getId()))
                .findFirst()
                .orElse(-1);
        if (serIndex == -1) {
            return null;
        }
        SERVICE.set(serIndex, service);
        return service;
    }

    public void deleteService(Long id) {
        var service = findAllService(id);
        if (service != null) {
            SERVICE.remove(service);
        }
    }
}
