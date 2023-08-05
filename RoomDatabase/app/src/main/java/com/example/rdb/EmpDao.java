package com.example.rdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmpDao {

    @Query("select * from empdetails")
    public List<EmpEntity> getdetails();

    @Insert
    public void insertdetails(EmpEntity empEntity);

    @Update
    public void updatedetails(EmpEntity empEntity);

    @Delete
    public void deletedetails(EmpEntity empEntity);

    @Query("SELECT EXISTS (select * from empdetails where username =:username and empid=:password)")
    public boolean login(String username, String password);

    @Query("DELETE FROM empdetails where username=:username1")
    public void namedelete(String username1);


}
