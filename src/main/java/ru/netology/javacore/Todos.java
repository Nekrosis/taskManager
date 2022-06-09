package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private List<String> list;

    public Todos() {
        this.list = new ArrayList<>();
    }

    public void addTask(String task) {
        list.add(task);
    }

    public void removeTask(String task) {
        list.remove(task);
    }

    public String getAllTasks() {
        return list.stream()
                .sorted(String::compareTo)
                .collect(Collectors.joining(" "));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todos todos = (Todos) o;
        return Objects.equals(list, todos.list);
    }
}
