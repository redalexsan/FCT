package es.example.ale.fct.data.local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import es.example.ale.fct.data.model.Visita;

@Dao
public interface VisitaDao {

    @Query("SELECT * FROM Visita")
    LiveData<List<Visita>> queryAllVisitas();

    @Query("SELECT * FROM Visita WHERE id = :visitaID")
    LiveData<Visita> queryVisita(int visitaID);

    @Query("SELECT * FROM Visita WHERE nombreAlumno = :nombre ORDER BY dia DESC LIMIT 1")
    LiveData<Visita> queryUltimaVisita(String nombre);

    @Insert
    void insertVisita(Visita visita);

    @Delete
    void deleteVisita(Visita visita);

    @Update
    void updateVisita(Visita visita);
}
