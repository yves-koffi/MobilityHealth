package fr.yjk.mobility.health.database.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class LocalDateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { Date(it).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() }
    }

    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime?): Long? {
        return date?.let { Date.from(it.atZone(ZoneId.systemDefault())?.toInstant()).time }
    }
}