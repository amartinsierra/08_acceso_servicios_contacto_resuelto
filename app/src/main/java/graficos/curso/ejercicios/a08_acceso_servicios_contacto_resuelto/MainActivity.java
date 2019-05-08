package graficos.curso.ejercicios.a08_acceso_servicios_contacto_resuelto;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import beans.Contacto;
import modelo.GestionContactos;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mostrar(View v){
        Intent in=new Intent(this,ListadoActivity.class);
        this.startActivity(in);
    }
    public void agregar(View v){
        AccesoContactos ac=new AccesoContactos();
        ac.execute();
    }
    public void eliminar(View v){
        EliminarContactos ac=new EliminarContactos();
        ac.execute();
    }
    private class AccesoContactos extends AsyncTask<Void,Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            GestionContactos gcontactos=new GestionContactos();
            gcontactos.guarda("http://169.254.13.49:8080/04_servicio_contactos_modelo_spring/rest/contactos");
            return null;
        }


    }
    private class EliminarContactos extends AsyncTask<Void,Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            GestionContactos gcontactos=new GestionContactos();
            gcontactos.elimina("http://169.254.13.49:8080/04_servicio_contactos_modelo_spring/rest/contactos",497);
            return null;
        }


    }
}
