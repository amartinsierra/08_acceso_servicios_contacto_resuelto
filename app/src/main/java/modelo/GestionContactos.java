package modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import beans.Contacto;

/**
 * Created by antonio on 25/09/2017.
 */

public class GestionContactos {
    public ArrayList<Contacto> recuperarContactos(String direccion){
        ArrayList<Contacto> contactos=new ArrayList<>();
        String cad = "", aux;
        //conectamos con el servicio google
        BufferedReader bf=null;
        try {
            URL url = new URL(direccion);
            URLConnection con =  url.openConnection();
            HttpURLConnection http=(HttpURLConnection)con;
            System.out.println(direccion);
            http.setDoInput(true);
            http.setRequestProperty("Accept","application/json");
           // http.setRequestProperty("Content-Length",String.valueOf(ob.toString().getBytes("UTF-8").length));

           // http.setDoInput(true);

            //obtener Stream de entrada para leer los datos
            //enviados desde la URL
            InputStream is = http.getInputStream();
            bf = new BufferedReader(new InputStreamReader(is));
            //vamos leyendo las líneas enviadas por el servicio y las unimos
            //en la variable cad
            while ((aux = bf.readLine()) != null) {
                cad += aux + "\n";
            }
            //parseamos la cadena JSON

            JSONArray jsonArray=new JSONArray(cad);
            //recorremos el array de json y cada objeto lo convertimos en un Contacto
            //para añadirlo al arraylist de contactos que se entregará como resultado
            for(int i=0;i<jsonArray.length();i++){
                JSONObject ob=jsonArray.getJSONObject(i);
                contactos.add(new Contacto(ob.get("nombre").toString(),ob.get("email").toString()));
            }






           // http.connect();


        }
        catch(IOException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(bf!=null){
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactos;
    }

    public void guarda(String direccion){
//en caso de que se requiera autenticación:
        /*
        Authenticator.setDefault(new Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
       return new PasswordAuthentication(username, password.toCharArray());
     }
   });
         */
        try {
            JSONObject ob=new JSONObject();
            ob.put("nombre","android33");
            ob.put("email","aaa@gmail.com");
            ob.put("idContacto",22);
            ob.put("edad",10);
            URL url = new URL(direccion);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            System.out.println(direccion);
            http.setDoOutput(true);
            //http.setDoInput(false);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json");

            OutputStream salida=http.getOutputStream();
            System.out.println(ob.toString());
            salida.write(ob.toString().getBytes("UTF-8"));
            InputStream is = http.getInputStream();
            salida.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void elimina(String direccion,int id){
//en caso de que se requiera autenticación:
        /*
        Authenticator.setDefault(new Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
       return new PasswordAuthentication(username, password.toCharArray());
     }
   });
         */
        try {
            direccion+="/"+id;
            URL url = new URL(direccion);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            System.out.println(direccion);
            //http.setDoOutput(true);
            //http.setDoInput(false);
            http.setRequestMethod("DELETE");
            //http.setRequestProperty("Content-Type", "application/json");

            InputStream is = http.getInputStream();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
