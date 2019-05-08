package graficos.curso.ejercicios.a08_acceso_servicios_contacto_resuelto;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import beans.Contacto;
import modelo.GestionContactos;

public class ListadoActivity extends Activity {
    ListView lstContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        lstContactos=this.findViewById(R.id.lstContactos);
        AccesoContactos ac=new AccesoContactos();
        ac.execute();
    }

    private class AccesoContactos extends AsyncTask<Void,Void,ArrayList<Contacto>>{
        @Override
        protected void onPostExecute(ArrayList<Contacto> contactos) {
            super.onPostExecute(contactos);
            ArrayAdapter<Contacto> adp=new ArrayAdapter<Contacto>(ListadoActivity.this,android.R.layout.simple_list_item_1,contactos);
            lstContactos.setAdapter(adp);
        }

        @Override
        protected ArrayList<Contacto> doInBackground(Void... voids) {
            GestionContactos gcontactos=new GestionContactos();
            return gcontactos.recuperarContactos("http://169.254.13.49:8080/04_servicio_contactos_modelo_spring/rest/contactos");
        }
    }

    public void salir(View v){
        this.finish();
    }

}
