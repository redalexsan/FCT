package es.example.ale.fct.data;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.model.Alumno;

public class RepositoryImpl implements Repository{

    private final AlumnoDao alumnoDao;

    public RepositoryImpl(AlumnoDao alumnoDao) {
        this.alumnoDao = alumnoDao;
    }

    @Override
    public LiveData<List<Alumno>> queryAllAlumnos() {
        return alumnoDao.queryAllAlumnos();
    }

    @Override
    public LiveData<Alumno> queryAlumno(long alumnoID) {
        return alumnoDao.queryAlumno(alumnoID);
    }

    @Override
    public void insertAlumno(final Alumno alumno) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> alumnoDao.insertAlumno(alumno));
    }

    @Override
    public void deleteAlumno(Alumno alumno) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> alumnoDao.deleteAlumno(alumno));
    }

    @Override
    public void updateAlumno(Alumno alumno) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> alumnoDao.updateAlumno(alumno));
    }
}
