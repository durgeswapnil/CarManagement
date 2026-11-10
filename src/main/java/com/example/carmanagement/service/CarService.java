package com.example.carmanagement.service;

import com.example.carmanagement.model.Car;
import com.example.carmanagement.repository.CarRepository;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final Path uploadDirectory = Paths.get("uploads", "cars").toAbsolutePath().normalize();

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public long count() {
        return carRepository.count();
    }

    public Car save(Car car, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String original = image.getOriginalFilename() == null ? "car-image" : image.getOriginalFilename();
            String extension = "";
            int dot = original.lastIndexOf('.');
            if (dot >= 0) extension = original.substring(dot).replaceAll("[^a-zA-Z0-9.]", "");
            String fileName = UUID.randomUUID() + extension;
            Files.createDirectories(uploadDirectory);
            Path destination = uploadDirectory.resolve(fileName).normalize();
            if (!destination.startsWith(uploadDirectory)) {
                throw new IOException("Invalid image path");
            }
            image.transferTo(destination);
            car.setImageName(fileName);
        }
        return carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
