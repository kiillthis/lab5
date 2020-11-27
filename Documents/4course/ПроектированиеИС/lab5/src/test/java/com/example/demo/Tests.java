package com.example.demo;

import com.example.demo.service.WorkerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

@SpringBootTest
class Tests {

    private final WorkerService service;

    @Autowired
    Tests(WorkerService service) {
        this.service = service;
    }

    /*
    перевірка функції видалення за іменем
     */
    @Rollback
    @Test
    public void checkRemoveByName() {
        int expected = 4;

        String toDelete = "A";

        service.removeStartingWith(toDelete);

        int actual = service.getAllWorker().size();

        Assert.assertEquals(expected, actual);
    }

    /*
    перевірка встановлення статусу видалення
     */
    @Rollback
    @Test
    public void checkSoftDeleting() {
        int expected = 4;

        ArrayList<Integer> array = new ArrayList<>();

        array.add(5);
        array.add(6);
        array.add(7);

        service.makeSoftRemovingAWorker(array);

        int actual = service.getAllWorker().size();

        Assert.assertEquals(expected, actual);

    }

    /*
    перевірка встановлення статусу видалення
     */
    @Rollback
    @Test
    public void checkSoftDeleting2() {
        int expected = 4;

        ArrayList<Integer> array = new ArrayList<>();

        array.add(1);
        array.add(2);
        array.add(3);
        array.add(5);
        array.add(6);
        array.add(7);

        service.makeSoftRemovingAWorker(array);

        int actual = service.getAllWorker().size();

        Assert.assertEquals(expected, actual);
    }

    /*
    перевірка функції видалення за іменем
     */
    @Rollback
    @Test
    public void checkRemoveByName2() {
        int expected = 4;

        String toDelete = "H";

        service.removeStartingWith(toDelete);

        int actual = service.getAllWorker().size();

        Assert.assertEquals(expected, actual);
    }
}