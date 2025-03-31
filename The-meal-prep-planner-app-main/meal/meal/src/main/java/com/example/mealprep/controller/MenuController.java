package com.example.mealprep.controller;

import com.example.mealprep.entity.Menu;
import com.example.mealprep.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @PostMapping
    public Menu createMenuItem(@RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    @GetMapping
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuItemById(@PathVariable Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable Long id, @RequestBody Menu menuDetails) {
        return menuRepository.findById(id).map(menu -> {
            menu.setItemName(menuDetails.getItemName());
            menu.setPrice(menuDetails.getPrice());
            return ResponseEntity.ok(menuRepository.save(menu));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        if (!menuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menuRepository.deleteById(id);
        return ResponseEntity.ok("Menu item deleted");
    }
}
