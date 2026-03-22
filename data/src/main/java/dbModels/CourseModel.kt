package dbModels

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
class CourseModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0
    @ColumnInfo(name = "title")
    var title: String = ""
    @ColumnInfo(name = "text")
    var text: String? = null
    //цена курса
    @ColumnInfo(name = "price")
    var price: String = "";
    //рейтинг курса
    @ColumnInfo(name = "rate")
    var rate: Double=0.0;
    //дата начала курса
    @ColumnInfo(name = "startDate")
    var startDate : String = "";
    //дата публикации курса
    @ColumnInfo(name = "publishDate")
    var publishDate: String = "";

    constructor(id: Int, title: String, text: String?, price: String, rate: Double,
                startDate : String, publishDate: String) {
        this.id = id
        this.title = title
        this.text = text
        this.price = price
        this.rate = rate
        this.startDate = startDate
        this.publishDate = publishDate
    }

    /*
    fun getId(): Int{
        return id
    }
    fun getTitle():String{
        return title
    }
    fun getText(): String {
        val result = if (text.isNullOrEmpty()) "" else text.toString()
        return result
    }
    fun getPrice():String{
        return price
    }
    fun getRate(): Double{
        return rate
    }
    fun getDateStart(): String{
        return startDate;
    }
    fun getDatePablish(): String{
        return publishDate;
    }
    */
}