package com.example.mealprep.service;

import com.example.mealprep.entity.Menu;
import com.example.mealprep.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}
