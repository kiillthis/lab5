package com.example.demo.view;

import com.example.demo.domen.Worker;
import com.example.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private final WorkerService workerService;

    @Autowired
    public View(WorkerService workerService) {
        this.workerService = workerService;
    }

    public void doWork() {
        System.out.println("Добрый день. Вы работаете в консольной программе управления персоналом ");
        while (true) {
            System.out.println("Меню");
            System.out.println("Выберите действие");
            System.out.println("1 - выполнить мягкое удаление");
            System.out.println("2 - удалить работников на букву");
            System.out.println("3 - вывести всех работников");

            Scanner in = new Scanner(System.in);
            int n = in.nextInt();

            switch (n) {
                case 1:
                    System.out.println("Введите массив идентификаторов. 0 - конец");

                    ArrayList<Integer> ids = new ArrayList<>();

                    int id;
                    do {
                        id = in.nextInt();

                        if(id != 0) {
                            ids.add(id);
                        }

                    } while (id != 0);

                    makeSoftRemove(ids);

                    break;
                case 2:
                    System.out.println("Введите букву");
                    String s = in.next();
                    removeAWorkerByName(s);
                    break;
                case 3:
                    printAllWorkers(workerService.getAllWorker());
                    break;
            }

        }
    }

    private void printAllWorkers(List<Worker> workers){
        for (Worker w: workers) {
            System.out.println("ID: " + w.getId()
                    + "имя: " + w.getName()
                    + ", фамилия: " + w.getSurname()
                    + ", зарплата " + w.getSalary()
                    + ", должность: " + w.getPosition()
                    + ", активность:" + w.isActive() );
        }
    }

    private void removeAWorkerByName(String s) {
        workerService.removeStartingWith(s);
        System.out.println("Проведено удаление работников, с именем начинающихся на " + s);
    }

    private void makeSoftRemove(ArrayList<Integer> id) {
        workerService.makeSoftRemovingAWorker(id);
        System.out.println("Работникам выставлен статус неактивен");
    }
}
