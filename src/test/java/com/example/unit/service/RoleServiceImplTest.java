package com.example.unit.service;

import com.example.dao.entity.Role;
import com.example.dao.repository.RoleRepository;
import com.example.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    //    Roles save(Roles roles); +
//    void delete(Roles roles); +
//    void deleteById(Long id); +
//    Roles updateRoles(Roles roles); +
//    List<Roles> findAll(); +
//    Roles findById(Long id); +
//    Optional<Roles> findByName(String name);

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testFindAll() {
        final List<Role> roleList = Collections.singletonList(new Role());
        when(roleRepository.findAll()).thenReturn(roleList);
        assertEquals(roleService.findAll(), roleList);
    }

    @Test
    public void testFindById() {
        final Role role = new Role();
        when(roleRepository.findById(any(Long.class))).thenReturn(Optional.of(role));
        assertEquals(roleService.findById(1L), role);
    }

    @Test
    public void testSave() {
        final Role role = new Role();
        when(roleRepository.saveAndFlush(role)).thenReturn(role);
        assertEquals(roleService.save(role), role);
    }

    @Test
    public void testUpdate() {
        final Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleRepository.saveAndFlush(role)).thenReturn(role);
        assertEquals(roleService.updateRole(role), role);
    }

    @Test
    public void testDelete() {
        final Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        doNothing().when(roleRepository).delete(role);
        assertDoesNotThrow(() -> roleService.delete(role));
    }

    @Test
    public void testDeleteById() {
        final Role role = new Role();
        role.setId(1L);
        doNothing().when(roleRepository).deleteById(any(Long.class));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        assertDoesNotThrow(() -> roleService.deleteById(1L));
    }

    @Test
    public void testFindByName(){
        final Role role = new Role();
        when(roleRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        assertEquals(roleService.findByName("admin"), role);
    }
}

