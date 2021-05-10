package hu.mzsb.booksbysubject.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class RoomBook (
        @PrimaryKey(autoGenerate = false) val id: String,
        @ColumnInfo(name = "subject") val subject: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "authorName") val authorName: String,
        @ColumnInfo(name = "authorBio") val authorBio: String,
        @ColumnInfo(name = "covers") val covers: String,
        @ColumnInfo(name = "isRead") val isRead: Boolean
)