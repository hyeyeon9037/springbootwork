package com.study.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Menu;
import com.study.springboot.domain.Taste;
import com.study.springboot.domain.Type;
import com.study.springboot.repository.MenuRepository;

@Service
public class MenuService {
   
   @Autowired
   MenuRepository menuRepository;

   public List<Menu> menuAllList() {
      return menuRepository.findAll();
   }

   public List<Menu> findByType(Type type) {
      return menuRepository.findByType(type);
   }

   public List<Menu> findByTypeAndTaste(Type type, Taste taste) {
      return menuRepository.findByTypeAndTaste(type, taste);
   }

   public Optional<Menu> findById(Long id) {
      return menuRepository.findById(id);
   }

public Menu insertMenu(Menu menu) {
	return menuRepository.save(menu);
}

public Menu updateMenu(Menu menu) {
	Menu reMenu = menuRepository.findById(menu.getId()).get(); // 검색해서 가져와야 (따라서 findById를 쓰는 것!) 업데이트가 가능한 것
	return menuRepository.save(menu); // 사용자가 넣은 것을 가져와야함
}

public void deleteMenu(Long id) {
	menuRepository.deleteById(id);
	
}

}