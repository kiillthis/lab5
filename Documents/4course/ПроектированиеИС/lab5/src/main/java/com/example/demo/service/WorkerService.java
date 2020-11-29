package com.example.demo.service;

import com.example.demo.domen.Worker;

import java.util.ArrayList;
import java.util.List;

public interface WorkerService {

    List<Worker> makeSoftRemovingAWorker(ArrayList<Integer> id);

    List<Worker> removeStartingWith(String s);

    List<Worker> getAllWorker();
}
