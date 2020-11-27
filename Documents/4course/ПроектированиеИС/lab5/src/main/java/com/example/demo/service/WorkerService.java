package com.example.demo.service;

import com.example.demo.domen.Worker;

import java.util.ArrayList;
import java.util.List;

public interface WorkerService {

    void makeSoftRemovingAWorker(ArrayList<Integer> id);

    void removeStartingWith(String s);

    List<Worker> getAllWorker();
}
