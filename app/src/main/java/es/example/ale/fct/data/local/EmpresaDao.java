package es.example.ale.fct.data.local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import es.example.ale.fct.data.model.Empresa;

@Dao
public interface EmpresaDao {
    
    @Query("SELECT * FROM Empresa")
    LiveData<List<Empresa>> queryAllEmpresas();

    @Query("SELECT * FROM Empresa WHERE id = :empresaID")
    LiveData<Empresa> queryEmpresa(long empresaID);

    @Insert
    void insertEmpresa(Empresa empresa);

    @Delete
    void deleteEmpresa(Empresa empresa);

    @Update
    void updateEmpresa(Empresa empresa);
}
