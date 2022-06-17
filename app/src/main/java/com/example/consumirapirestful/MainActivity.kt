package com.example.consumirapirestful

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cosumirApi(view: View) {
        val respuestaObtenida = findViewById<TextView>(R.id.txtRespuestaJSON);
        //val respuesta:String;
        //Línea que nos permitirá hacer scroll para poder ver todos los registros que traemos de la API
        respuestaObtenida.movementMethod = ScrollingMovementMethod();

        val queue = Volley.newRequestQueue(this)
        val url = "http://www.google.com"

        // Solicitamos la respuesta
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, "https://gorest.co.in/public/v1/users", null,
            { response ->
                var listaUsers = "";
                val listaUsersJSON = JSONArray(response.getString("data"));
                for(i in 0 until listaUsersJSON.length()){
                    val user: JSONObject = listaUsersJSON.getJSONObject(i);
                    listaUsers = listaUsers + " {\n    id: " + user.getString("id") +
                            "; \n    name: " + user.getString("name")+
                            "; \n    email: " + user.getString("email")+
                            "; \n    gender: " + user.getString("gender")+
                            "; \n    status: " + user.getString("status")+"\n },\n"
                }
                respuestaObtenida.text=listaUsers;
            },
            {
                respuestaObtenida.text="Ha ocurrido un error inesperado\n y no se pudo consumir la API :(";
            })
        queue.add(stringRequest)
    }
}