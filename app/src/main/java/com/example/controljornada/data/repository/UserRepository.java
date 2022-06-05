package com.example.controljornada.data.repository;

import android.util.Log;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.UserDao;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.listadohoras.ListadoManageContract;
import com.example.controljornada.ui.listadohoras.ListadoNumeroHorasContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class UserRepository implements ListadoNumeroHorasContract.Repository, ListadoManageContract.Repository {


    private static UserRepository instance;
    private ArrayList<User> list;

    private UserDao userDao;

    private String result;
    private UserRepository(){
        list = new ArrayList<>();
        userDao = ControlJornadaDatabase.getDatabase().userDao();
    }


    public static UserRepository getInstance(){
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }
    private void sendData(String data) {
        result = data;
    }

    @Override
    public void getList(OnRepositoryListCallback callback) {
        Thread thread = new Thread(new Runnable() {
            public String data = "";

            @Override
            public void run() {

                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/controlJornada.php");
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; utf-8");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.connect();

                    int code = connection.getResponseCode();
                    switch (code) {
                        case 200:
                        case 201:
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                            String line;
                            while ((line = br.readLine()) != null) {
                                data += line;
                            }
                            sendData(data);


                            br.close();
                    }
                    connection.disconnect();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            public String getData() {
                return data;
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        JSONArray array = null;
        int id = 0;
        String name = null;
        String surname = null;
        String email = null;
        String admin = null;
        String genero = null;
        String telefono = null;
        String numeroHorasMensuales = null;
        String empresa = null;
        String edad = null;

        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                name = object.getString("name");
                surname = object.getString("surname");
                email = object.getString("email");
                admin = object.getString("admin");
                genero = object.getString("genero");
                telefono = object.getString("telefono");
                numeroHorasMensuales = object.getString("numeroHorasMensuales");
                empresa = object.getString("empresa");
                edad = object.getString("edad");

                User user = new User(id, email,name,Integer.parseInt(admin),numeroHorasMensuales);
                user.setApellidos(surname);
                user.setGenero(genero);
                user.setTelefono(telefono);
                user.setEmpresa(empresa);
                user.setEdad(0);

                list.add(user);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("LISTUSEr", list.toString());
        callback.onSuccess(list);
        this.list.clear();
    }

    @Override
    public void delete(User user, OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.delete(user));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/deleteUser.php?id=" + user.getId());
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getErrorStream();
                    } else {
                        InputStream err = connection.getErrorStream();
                    }

                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
        thread.start();


        callback.onDeleteSucces("Se ha eliminado el usuario" +user.getNombre());
    }

    @Override
    public void undo(User user, OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.insert(user));

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarUser.php?email="+user.getEmail()+"&name="+user.getNombre()+"&surname="+user.getApellidos()+"&numeroHorasMensuales="+user.getNumeroHorasMensuales());
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if( connection.getResponseCode() == HttpURLConnection.HTTP_OK ){
                        InputStream is = connection.getErrorStream();
                    }else{
                        InputStream err = connection.getErrorStream();
                    }

                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        callback.onUndoSuccess("Se ha vuelto ha añadir este usuario");

    }

    @Override
    public void add(User user, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.insert(user));

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarUser.php?email="+user.getEmail()+"&name="+user.getNombre()+"&surname="+user.getApellidos()+"&numeroHorasMensuales="+user.getNumeroHorasMensuales());
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if( connection.getResponseCode() == HttpURLConnection.HTTP_OK ){
                        InputStream is = connection.getErrorStream();
                    }else{
                        InputStream err = connection.getErrorStream();
                    }

                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        callback.onSuccess("Se ha añadido correctamente");
    }

    @Override
    public void edit(User user, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.update(user));

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/updateUser.php?email="+user.getEmail()+"&name="+user.getNombre()+"&surname="+user.getApellidos()+"&numeroHorasMensuales="+user.getNumeroHorasMensuales()+"&empresa="+user.getEmpresa()+"&genero="+user.getGenero()+"&telefono="+user.getTelefono()+"&edad="+user.getEdad());
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if( connection.getResponseCode() == HttpURLConnection.HTTP_OK ){
                        InputStream is = connection.getErrorStream();
                    }else{
                        InputStream err = connection.getErrorStream();
                    }

                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        callback.onSuccess(user.toString());
    }
}
