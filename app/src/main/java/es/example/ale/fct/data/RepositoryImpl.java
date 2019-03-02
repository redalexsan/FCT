package es.example.ale.fct.data;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import es.example.ale.fct.data.local.AlumnoDao;
import es.example.ale.fct.data.local.EmpresaDao;
import es.example.ale.fct.data.local.VisitaDao;
import es.example.ale.fct.data.model.Alumno;
import es.example.ale.fct.data.model.Empresa;
import es.example.ale.fct.data.model.Visita;

public class RepositoryImpl implements Repository{

    private AlumnoDao alumnoDao;
    private EmpresaDao empresaDao;
    private VisitaDao visitaDao;

    public RepositoryImpl(AlumnoDao alumnoDao) {
        this.alumnoDao = alumnoDao;
    }
    public RepositoryImpl(EmpresaDao empresaDao) { this.empresaDao = empresaDao;}
    public RepositoryImpl(VisitaDao visitaDao){ this.visitaDao = visitaDao; }
    public RepositoryImpl(AlumnoDao alumnoDao,VisitaDao visitaDao){
        this.alumnoDao = alumnoDao;
        this.visitaDao = visitaDao;
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

    @Override
    public LiveData<List<Empresa>> queryAllEmpresas() {
        return empresaDao.queryAllEmpresas();
    }

    @Override
    public LiveData<Empresa> queryEmpresa(long empresaID) {
        return empresaDao.queryEmpresa(empresaID);
    }

    @Override
    public void insertEmpresa(Empresa empresa) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> empresaDao.insertEmpresa(empresa));
    }

    @Override
    public void deleteEmpresa(Empresa empresa) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> empresaDao.deleteEmpresa(empresa));
    }

    @Override
    public void updateEmpresa(Empresa empresa) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> empresaDao.updateEmpresa(empresa));
    }

    @Override
    public LiveData<List<Visita>> queryAllVisitas() {
        return visitaDao.queryAllVisitas();
    }

    @Override
    public LiveData<Visita> queryVisita(int visitaID) {
        return visitaDao.queryVisita(visitaID);
    }

    @Override
    public void insertVisita(Visita visita) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitaDao.insertVisita(visita));
    }

    @Override
    public void deleteVisita(Visita visita) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitaDao.deleteVisita(visita));
    }

    @Override
    public void updateVisita(Visita visita) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitaDao.updateVisita(visita));
    }

    @Override
    public LiveData<Visita> queryUltimaVisita(String nombre) {
        return visitaDao.queryUltimaVisita(nombre);
    }
}
