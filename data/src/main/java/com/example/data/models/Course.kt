package com.example.data.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Course {
    //id курса не изменяется
    private val id: Int;
    //заголовок курса
    private var title: String;
    //описание курса
    private var text: String;
    //цена курса
    private var price: String;
    //рейтинг курса
    private var rate: Double;
    //дата начала курса
    private var startDate : LocalDate;
    //признак, добавлен ли курс в избранное
    private var hasLike: Boolean;
    //дата публикации курса
    private val publishDate: LocalDate;

    constructor(id_: Int, title_ : String, text_:String, price_ : String, rate_ : Double,
                start : LocalDate, like : Boolean, publish: LocalDate){
        id = id_;
        title = title_;
        text = text_;
        price = price_;
        rate = rate_;
        startDate = start;
        hasLike = like;
        publishDate = publish;
    }
    fun getData():String{
        return "Id: ${id}, Title: ${title}";
    }

    fun getId(): Int{
        return id
    }
    fun getTitle():String{
        return title
    }
    fun getText():String{
        return text
    }
    fun getRateToStr(): String{
        return rate.toString()
    }
    fun getRate(): Double{
        return rate;
    }
    @Suppress("NewApi")
    fun getStartData(format : String): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        return startDate.format(formatter)
    }
    @Suppress("NewApi")
    fun getPublishData(format : String): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        return publishDate.format(formatter)
    }
    fun getPrice():String{
        return price+" ₽"
    }
    fun getLike(): Boolean{
        return hasLike
    }
    fun setLike(value : Boolean){
        hasLike = value;
    }
}