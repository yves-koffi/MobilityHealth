package fr.yjk.mobility.health.database.repository


import androidx.lifecycle.LiveData
import fr.yjk.mobility.health.database.dao.UserDao
import fr.yjk.mobility.health.database.entity.User
import java.util.Date

class UserRepository(private val userDao: UserDao) {

    fun imageTexts(): LiveData<List<User>> {
        return userDao.getAll()
    }

    suspend fun save(user: User) = userDao.insert(user.apply {
        createdAt = Date()
    })

    suspend fun update(user: User) =
        userDao.update(id = user.id, txt = user.text)

    suspend fun updateTitle(user: User) =
        userDao.updateTitle(id = user.id, txt = user.title)
}