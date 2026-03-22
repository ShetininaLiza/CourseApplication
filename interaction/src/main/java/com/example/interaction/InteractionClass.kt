package com.example.interaction

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.util.Objects
import androidx.core.net.toUri

//класс для взаимодейстаия с View
class InteractionClass {
    fun openURL(urlStr : String, context : Context){
        val toast: Toast = Toast.makeText(context, urlStr, Toast.LENGTH_LONG)
        toast.show();
        val url = urlStr.toUri();
        val browserIntent = Intent(Intent.ACTION_VIEW, url);
        context.startActivity(browserIntent);
    }
    fun openActivity(context: Context, cls: Class<*>){
        val secondActivityIntent = Intent(context, cls);
        context.startActivity(secondActivityIntent);
    }

    //метод для сохранения избоанного курса в изьбранное
}