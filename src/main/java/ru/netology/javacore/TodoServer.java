package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    private Todos todos;
   private String type;
   private String task;
    private int port;

    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    String word = in.readLine();
                    JsonObject jsonObject = new Gson().fromJson(word, JsonObject.class);
                    type = jsonObject.get("type").getAsString();
                    task = jsonObject.get("task").getAsString();
                    if (type.equals("ADD")) {
                        todos.addTask(task);
                    } else if (type.equals("REMOVE")) {
                        todos.removeTask(task);
                    }
                    System.out.println(jsonObject);
                    out.write(todos.getAllTasks());
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }
}
