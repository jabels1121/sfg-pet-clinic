package com.jaybe.sfgpetclinic.services.map;

import com.jaybe.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private final Long ownerId = 1L;
    private final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner byId = ownerMapService.findById(ownerId);

        assertEquals(ownerId, byId.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner build = Owner.builder().id(id).build();

        Owner saved = ownerMapService.save(build);
        assertEquals(id, saved.getId());
    }

    @Test
    void saveNoId() {
        Owner saved = ownerMapService.save(Owner.builder().build());

        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void delete() {
        Owner byId = ownerMapService.findById(ownerId);
        ownerMapService.delete(byId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        Owner byId = ownerMapService.findById(ownerId);
        ownerMapService.deleteById(byId.getId());
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner byLastName = ownerMapService.findByLastName(lastName);

        assertNotNull(byLastName);

        assertEquals(lastName, byLastName.getLastName());
        assertEquals(ownerId, byLastName.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner dummy = ownerMapService.findByLastName("Dummy");

        assertNull(dummy);
    }

    @Test
    void findAllSortedById() {
        Owner build = Owner.builder().id(2L).build();
        Owner build1 = Owner.builder().id(4L).build();
        Owner build2 = Owner.builder().id(3L).build();
        Owner save = ownerMapService.save(build);
        Owner save1 = ownerMapService.save(build1);
        Owner save2 = ownerMapService.save(build2);

        List<Owner> ownerList = Arrays.asList(Owner.builder().id(ownerId).build(),
                build, build1, build2);
        ownerList.sort(Comparator.comparing(Owner::getId));
        List<Long> collect = ownerList.stream().map(Owner::getId).collect(Collectors.toList());

        List<Long> all = ownerMapService.findAll().stream().map(Owner::getId).collect(Collectors.toList());
        // todo: assert that set of owners was sorted by Id property
        assertEquals(all, collect);
    }
}