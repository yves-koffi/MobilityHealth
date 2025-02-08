package fr.yjk.mobility.health.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.yjk.mobility.health.database.converters.BitmapConverter
import fr.yjk.mobility.health.database.converters.DateTimeConverter
import fr.yjk.mobility.health.database.converters.LocalDateTimeConverter
import fr.yjk.mobility.health.database.dao.UserDao
import fr.yjk.mobility.health.database.entity.User
import java.util.concurrent.Executors

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [DateTimeConverter::class, BitmapConverter::class, LocalDateTimeConverter::class])
abstract class RoomDatabaseEntry : RoomDatabase() {

    abstract val userDao: UserDao
    companion object {
        @JvmStatic
        @Volatile
        private var INSTANCE: RoomDatabaseEntry? = null

        @JvmStatic
        fun getInstance(context: Context): RoomDatabaseEntry {
            if (INSTANCE == null) {
                synchronized(RoomDatabaseEntry::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, RoomDatabaseEntry::class.java, "wiikea.db"
                    ).addCallback(prepopulateDatabase()).build()
                }
            }
            return INSTANCE!!
        }

        @JvmStatic
        fun prepopulateDatabase(): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    Executors.newSingleThreadExecutor().execute {
                        //INSTANCE.dao.fun
                    }
                }
            }
        }

    }


}