package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodosTest {
    Todos todos = new Todos();

    @Test
    void testAdd() {
        String task = "Пробежка";
        todos.addTask(task);
        Assertions.assertEquals(task,todos.getAllTasks());
    }
    @Test
    void testRemove(){
        String task="Уборка";
        todos.addTask("Пробежка");
        todos.addTask(task);
        todos.removeTask("Пробежка");
        Assertions.assertEquals(task,todos.getAllTasks());
    }

    @Test
    void getAllTagsTest() {
        String task = "Бегать";
        String task1 = "Прогулка";
        String task2 = "Уборка";
        todos.addTask(task);
        todos.addTask(task1);
        todos.addTask(task2);
        List<String> expected = new ArrayList<>();
        expected.add(task);
        expected.add(task1);
        expected.add(task2);
        Assertions.assertEquals(expected.stream()
                .sorted(String::compareTo)
                .collect(Collectors.joining(" ")), todos.getAllTasks());
    }
}
