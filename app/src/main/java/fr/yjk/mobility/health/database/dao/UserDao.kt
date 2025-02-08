package fr.yjk.mobility.health.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.yjk.mobility.health.database.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("UPDATE users SET text = :txt WHERE id = :id")
    suspend fun update(txt: String, id: Long)
    @Query("UPDATE users SET title = :txt WHERE id = :id")
    suspend fun updateTitle(txt: String, id: Long)
    @Delete
    suspend fun delete(user: User)

}