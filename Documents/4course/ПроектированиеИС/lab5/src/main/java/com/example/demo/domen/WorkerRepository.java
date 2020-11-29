package com.example.demo.domen;

import com.example.demo.domen.Worker;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public interface WorkerRepository extends CrudRepository<Worker, Integer> {

    @Modifying
    @Query("update worker set is_active = false where id in (:id)")
    List<Worker> makeSoftRemoving(@Param("id") ArrayList<Integer> id);

    @Modifying
    @Query("delete from worker where name like concat(:s, '%') ")
    List<Worker> deleteAllByName(@Param("s") String s);

    List<Worker> findAll();

}
