package com.example.demo.service;

import com.example.demo.domen.Worker;
import com.example.demo.domen.WorkerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class WorkerServiceImplTest {

    /*
     * мокування об'єктів для тестування
     */
    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private WorkerServiceImpl workerService;

    /*
     * створення штучного набору даних
     * для повернення репозитарієм
     */

    private ArrayList<Worker> workersLitter;

    private ArrayList<Worker> workersRemoved;

    private ArrayList<Integer> idArray;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        /*
         * дані для перевірки роботи функції
         * з видалення по букві
         */
        workersLitter.add(new Worker(3, "George", "Miltov", 1000, "driver", true));
        workersLitter.add(new Worker(4, "Elly", "Tailor", 200, "singer", true));
        workersLitter.add(new Worker(5, "Tom", "Bennet", 900, "driver", true));

        /*
         * дані для перевірки функції
         * з м'яким видаленням по масиву ідентифікаторів
         */
        workersRemoved.add(new Worker(1, "Andrew", "Kompaniets", 100, "CEO", true));
        workersRemoved.add(new Worker(2, "Anton", "Ivanov", 700, "SEO", true));
        workersRemoved.add(new Worker(3, "George", "Miltov", 1000, "driver", true));
        workersRemoved.add(new Worker(4, "Edward", "Eloin", 500, "cook", false));
        workersRemoved.add(new Worker(5, "Neigel", "Kilp", 2000, "courier", false));

        /*
         * масив ідентифікаторів
         * для м'якого видалення
         */
        idArray.add(4);
        idArray.add(5);
    }

    /*
     * перевірка функції видалення по букві
     * по виклику методу репозитарія
     * ми повертаємо підготовлений
     * набір даних.
     * Тут ми перевіряємо виклики методів
     * репозитарія, їх кількість, максимальну та мінімальну.
     * Перевіряємо, чи збігається набір даних.
     */
    @Test
    public void checkWorkerService_Should_Delete_Worker_By_Litter() {

        when(workerRepository.deleteAllByName("A")).thenReturn(workersLitter);

        workerService.removeStartingWith("A");

        List<Worker> workersTest = workerRepository.deleteAllByName("A");

        verify(workerRepository, atLeastOnce()).deleteAllByName("A");

        verify(workerRepository, atMost(2)).deleteAllByName("A");

        Assert.assertEquals(workersLitter, workersTest);

    }

    /*
     * Тут ми перевіряємо функцію видалення
     * з боку тільки сервіса.
     * Перевіряється видалення по букві.
     * Перевіряється виклик функції лише 1 раз за максумумом в 2.
     * Також після цього більше не має місця виклики чого-небудь.
     */
    @Test
    public void checkWorkerService_Service_Interaction() {
        when(workerRepository.deleteAllByName("A")).thenReturn(workersLitter);

        workerService.removeStartingWith("A");

        verify(workerService, times(1)).removeStartingWith("A");

        verify(workerService, atLeastOnce()).removeStartingWith("A");

        verify(workerService, atMost(2)).removeStartingWith("A");

        verifyNoMoreInteractions(workerService);

    }

    /*
     * Тут перевіряється, щоб при виклику необхідних функцій,
     * не викликались інши методи, які не мають бути викликаними.
     */
    @Test()
    public void checkWorkerService_Delete_By_Litter_Never() {

        workerService.removeStartingWith("A");

        verify(workerRepository, never()).findAll();

        verify(workerRepository, never()).makeSoftRemoving(idArray);

        verify(workerService, never()).makeSoftRemovingAWorker(idArray);

        verify(workerService, never()).getAllWorker();

    }

    /*
     * Перевірка функції м'якого видалення
     * за масивом ідентифікаторів.
     * Перевіряється виклик необхідних методів репозатирію,
     * його отримує та повертає заготовлені дані,
     * перевіряється кількість викликів методів,
     * перевіряється повернення необхідного набору даних.
     *
     */
    @Test
    public void checkWorkerService_Make_Soft_Deleting() {

        when(workerRepository.makeSoftRemoving(idArray)).thenReturn(workersRemoved);

        workerService.makeSoftRemovingAWorker(idArray);

        List<Worker> workersTest = workerRepository.makeSoftRemoving(idArray);

        verify(workerRepository, atLeastOnce()).makeSoftRemoving(idArray);

        verify(workerRepository, atMost(2)).makeSoftRemoving(idArray);

        Assert.assertEquals(workersRemoved, workersTest);
    }

    /*
     * Перевірка функції м'якого видалення лише
     * з боку сервісу,
     * перевіряється кількість викликів методу сервісу,
     * після роботи він не має більше викликати інші методи.
     *
     */
    @Test
    public void checkWorkerService_Make_Soft_Deleting_Service_Interaction() {
        when(workerRepository.makeSoftRemoving(idArray)).thenReturn(workersRemoved);

        workerService.makeSoftRemovingAWorker(idArray);

        verify(workerService, times(1)).makeSoftRemovingAWorker(idArray);

        verify(workerService, atLeastOnce()).makeSoftRemovingAWorker(idArray);

        verify(workerService, atMost(2)).makeSoftRemovingAWorker(idArray);

        verifyNoMoreInteractions(workerService);
    }

    /*
     * Перевірка функції м'якого видалення,
     * перевірка методів сервісу та репозатарію
     * на факт не виклику методів, які не мають місця.
     *
     */
    @Test
    public void checkWorkerService_Make_Soft_Deleting_Never() {
        workerService.makeSoftRemovingAWorker(idArray);

        verify(workerRepository, never()).findAll();

        verify(workerRepository, never()).deleteAllByName(anyString());

        verify(workerService, never()).getAllWorker();

        verify(workerService, never()).removeStartingWith(anyString());
    }
}