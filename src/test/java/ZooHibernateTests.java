import database.DatabaseUtils;
import database.hibernate.DBHibernateService;
import database.hibernate.models.Animal;
import database.hibernate.models.Places;
import database.hibernate.models.Workman;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZooHibernateTests {
    DBHibernateService dbHibernateService = new DBHibernateService();

    @BeforeAll
    static void init() {
        DatabaseUtils.createData();
    }

    /**
     * В таблице public.animal ровно 10 записей
     */
    @Test
    void countRowAnimal() {
        assertEquals(10, dbHibernateService.getCountRowAnimal());
    }

    static Stream<Animal> animalProvider() {
        List<Animal> animals = new ArrayList<>();
        for (int id = 1; id <= 10; id++) {
            Animal animal = new Animal();
            animal.setId(id);
            animal.setName("Sharik");
            animal.setAge(10);
            animal.setType(1);
            animal.setSex(1);
            animal.setPlace(1);
            animals.add(animal);
        }
        return animals.stream();
    }
    /**
     * В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
     */
    @ParameterizedTest
    @MethodSource("animalProvider")
    void insertIndexAnimal(Animal animal) {
        assertThrows(PersistenceException.class, () -> dbHibernateService.insertAnimal(animal));
    }

    /**
     * В таблицу public.workman нельзя добавить строку с name = null
     */
    @Test
    void insertNullToWorkman() {
        Workman workman = new Workman();
        workman.setId(88);
        workman.setName(null);
        workman.setAge(12);
        workman.setPosition(1);
        assertThrows(PersistenceException.class, () -> dbHibernateService.insertWorkman(workman));
    }

    /**
     * Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
     */
    @Test
    void insertPlacesCountRow() {
        int sizeBefore = dbHibernateService.getCountRowPlaces();
        Places places = new Places();
        places.setId(6);
        places.setRow(1);
        places.setPlace_num(185);
        places.setName("Загон 1");
        dbHibernateService.insertPlaces(places);
        assertEquals(sizeBefore + 1, dbHibernateService.getCountRowPlaces());
    }

    /**
     * В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
     */
    @Test
    void countRowZoo() {
        List<String> expectedNames = Arrays.asList("Центральный", "Северный", "Западный");

        int actualZooCountRow = dbHibernateService.getCountRowZoo();
        assertEquals(3, actualZooCountRow);

        List<String> actualNames = dbHibernateService.getZooNameData();
        assertThat(actualNames, containsInAnyOrder(expectedNames.toArray()));
    }

    /**
     * Добавить животное
     */
    @Test
    void addAnimal() {
        Animal animal = new Animal();
        animal.setName("New Animal");
        animal.setAge(5);
        animal.setType(1);
        animal.setSex(1);
        animal.setPlace(1);

        int countBefore = dbHibernateService.getCountRowAnimal();
        dbHibernateService.insertAnimal(animal);
        int countAfter = dbHibernateService.getCountRowAnimal();

        assertEquals(countBefore + 1, countAfter);
    }

    /**
     * Отредактировать животное
     */
    @Test
    void editAnimal() {
        Animal animal = dbHibernateService.getAnimalData(1);
        animal.setName("Edited Animal");

        dbHibernateService.updateAnimal(animal);

        Animal updatedAnimal = dbHibernateService.getAnimalData(1);
        assertEquals("Edited Animal", updatedAnimal.getName());
    }

    /**
     * Удалить животное
     */
    @Test
    void deleteAnimal() {
        int countBefore = dbHibernateService.getCountRowAnimal();
        dbHibernateService.deleteAnimal(1);
        int countAfter = dbHibernateService.getCountRowAnimal();

        assertEquals(countBefore - 1, countAfter);
    }

    /**
     * Добавить строку
     */
    @Test
    void addPlace() {
        Places place = new Places();
        place.setId(7);
        place.setRow(1);
        place.setPlace_num(185);
        place.setName("New Place");

        int countBefore = dbHibernateService.getCountRowPlaces();
        dbHibernateService.insertPlaces(place);
        int countAfter = dbHibernateService.getCountRowPlaces();

        assertEquals(countBefore + 1, countAfter);
    }

    /**
     * Отредактировать строку
     */
    @Test
    void editPlace() {
        Places place = dbHibernateService.getPlacesData(1);
        place.setName("Edited Place");

        dbHibernateService.updatePlaces(place);

        Places updatedPlace = dbHibernateService.getPlacesData(1);
        assertEquals("Edited Place", updatedPlace.getName());
    }

    /**
     * Удалить строку
     */
    @Test
    void deletePlace() {
        int countBefore = dbHibernateService.getCountRowPlaces();
        dbHibernateService.deletePlaces(1);
        int countAfter = dbHibernateService.getCountRowPlaces();

        assertEquals(countBefore - 1, countAfter);
    }
}