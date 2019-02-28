package es.example.ale.fct.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.data.model.Empresa;
import es.example.ale.fct.data.model.Visita;

@Database(entities = {Alumno.class, Empresa.class, Visita.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "fct.db";
    public abstract AlumnoDao alumnoDao();
    private static  volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).build();
        }
        return instance;
    }

}
