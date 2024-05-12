package com.example.taskmate.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmate.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("""SELECT * FROM Task ORDER BY
        CASE WHEN :isAsc = 1 THEN taskTitle END ASC, 
        CASE WHEN :isAsc = 0 THEN taskTitle END DESC""")
    fun getTaskListSortByTaskTitle(isAsc: Boolean) : Flow<List<Task>>

    @Query("""SELECT * FROM Task ORDER BY
        CASE WHEN :isAsc = 1 THEN date END ASC, 
        CASE WHEN :isAsc = 0 THEN date END DESC""")
    fun getTaskListSortByTaskDate(isAsc: Boolean) : Flow<List<Task>>


    @Query("""SELECT * FROM Task ORDER BY 
    CASE WHEN :isAsc
        THEN 
            CASE 
                WHEN priority LIKE 'L%' THEN 1 
                WHEN priority LIKE 'M%' THEN 2 
                WHEN priority LIKE 'H%' THEN 3 
            END 
        ELSE 
            CASE 
                WHEN priority LIKE 'H%' THEN 1 
                WHEN priority LIKE 'M%' THEN 2 
                WHEN priority LIKE 'L%' THEN 3
            END 
    END""")
    fun sortByPriority(isAsc: Boolean): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long


    // First way
    @Delete
    suspend fun deleteTask(task: Task) : Int


    // Second Way
    @Query("DELETE FROM Task WHERE taskId == :taskId")
    suspend fun deleteTaskUsingId(taskId: String) : Int


    @Update
    suspend fun updateTask(task: Task): Int


    @Query("UPDATE Task SET taskTitle=:title, description = :description, priority = :priority WHERE taskId = :taskId")
    suspend fun updateTaskPaticularField(taskId:String,title:String,description:String,priority:String): Int


    @Query("SELECT * FROM Task WHERE taskTitle LIKE :query ORDER BY date DESC")
    fun searchTaskList(query: String) : Flow<List<Task>>





}