package fr.yjk.mobility.health.database.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.Date

class BitmapConverter {
    @TypeConverter
    fun fromByteArray(value: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(value,0,value.size)
    }

    @TypeConverter
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray? {
        val byteArrayOutputStream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }
}