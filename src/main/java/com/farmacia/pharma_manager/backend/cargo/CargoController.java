package com.farmacia.pharma_manager.backend.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to cargos.
 */
@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    /**
     * Creates a new cargo.
     *
     * @param cargo The cargo object to create.
     * @return The created cargo object.
     */
    @PostMapping
    public Cargo createCargo(@RequestBody Cargo cargo) {
        return cargoService.saveCargo(cargo);
    }

    /**
     * Retrieves a cargo by its ID.
     *
     * @param id The ID of the cargo to retrieve.
     * @return The cargo object with the specified ID.
     */
    @GetMapping("/{id}")
    public Cargo getCargoById(@PathVariable Integer id) {
        return cargoService.getCargoById(id);
    }

    /**
     * Retrieves all cargos.
     *
     * @return A list of all cargos.
     */
    @GetMapping
    public List<Cargo> getAllCargos() {
        return cargoService.getAllCargos();
    }

    /**
     * Deletes a cargo by its ID.
     *
     * @param id The ID of the cargo to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteCargo(@PathVariable Integer id) {
        cargoService.deleteCargo(id);
    }

    /**
     * Updates an existing cargo.
     *
     * @param id The ID of the cargo to update.
     * @param cargo The cargo object with updated values.
     * @return The updated cargo object.
     */
    @PutMapping("/{id}")
    public Cargo updateCargo(@PathVariable Integer id, @RequestBody Cargo cargo) {
        return cargoService.updateCargo(id, cargo);
    }
}
