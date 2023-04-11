package com.codurance.training.tasks;


import com.codurance.training.tasks.model.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskListMain {

    public TaskListMain(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        new AppRunner(in,out,tasks).run();
    }

    public static void main(String[] args) {
        Map<String, List<Task>> tasks = new LinkedHashMap<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskListMain(tasks,in, out);
    }
}
