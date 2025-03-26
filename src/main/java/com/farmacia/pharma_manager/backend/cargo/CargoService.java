package com.farmacia.pharma_manager.backend.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling the business logic of managing cargos.
 */
@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    /**
     * Creates or updates a cargo.
     *
     * @param cargo The cargo object to save or update.
     * @return The saved or updated cargo object.
     */
    public Cargo saveCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    /**
     * Retrieves a cargo by its ID.
     *
     * @param id The ID of the cargo to retrieve.
     * @return The cargo object, or null if not found.
     */
    public Cargo getCargoById(Integer id) {
        return cargoRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a cargo by its ID.
     *
     * @param id The ID of the cargo to delete.
     */
    public void deleteCargo(Integer id) {
        cargoRepository.deleteById(id);
    }

    /**
     * Retrieves all cargos.
     *
     * @return A list of all cargos.
     */
    public List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    /**
     * Updates an existing cargo.
     *
     * @param id The ID of the cargo to update.
     * @param cargo The cargo object with updated values.
     * @return The updated cargo object, or null if not found.
     */
    public Cargo updateCargo(Integer id, Cargo cargo) {
        if (cargoRepository.existsById(id)) {
            cargo.setIdCargo(id); // Ensure the ID remains the same for update
            return cargoRepository.save(cargo);
        }
        return null;
    }
}
