package fr.yjk.mobility.health.database.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(defaultValue = "") var title: String = "",
    @ColumnInfo(defaultValue = "") var text: String = "",
    @ColumnInfo(defaultValue = "") var lang: String = "",
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: Bitmap? = null,
    @ColumnInfo(name = "created_at") var createdAt: Date? = null,
    @ColumnInfo(name = "updated_at") var updatedAt: Date? = null
)
