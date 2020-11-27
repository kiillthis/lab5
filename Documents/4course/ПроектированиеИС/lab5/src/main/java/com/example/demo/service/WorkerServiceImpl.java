package com.example.demo.service;

import com.example.demo.domen.Worker;
import com.example.demo.domen.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class WorkerServiceImpl implements  WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Transactional
    @Override
    public void makeSoftRemovingAWorker(ArrayList<Integer> id) {
        workerRepository.makeSoftRemoving(id);
    }

    @Transactional
    @Override
    public void removeStartingWith(String s) {
        workerRepository.deleteAllByName(s);
    }

    @Transactional
    @Override
    public List<Worker> getAllWorker() {
        return workerRepository.findAll();
    }
}
