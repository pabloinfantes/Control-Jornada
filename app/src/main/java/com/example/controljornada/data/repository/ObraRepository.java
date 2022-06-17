package com.example.controljornada.data.repository;


import android.content.Intent;
import android.util.Log;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.ObraDao;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.MainActivity;
import com.example.controljornada.ui.MainActivityNormalUser;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.login.LoginActivity;
import com.example.controljornada.ui.obra.ListadoObrasContract;
import com.example.controljornada.ui.obra.ObraManageContract;

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
/**
 * Esta clase es la encargada de comunicarse con la base de datos que esta en el servidor y proporcionar
 * una serie de datos o acciones
 * @author pablo
 *
 */
public class ObraRepository implements ListadoObrasContract.Repository, ObraManageContract.Repository {

    private static ObraRepository instance;
    private ArrayList<Obra> list;

    private ObraDao obraDao;
    private String result;

    private ObraRepository() {
        list = new ArrayList<>();
        obraDao = ControlJornadaDatabase.getDatabase().obraDao();
    }


    public static ObraRepository getInstance() {
        if (instance == null) {
            instance = new ObraRepository();
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

                    URL url = new URL("http://158.101.203.234/add/controlJornada/leerObra.php");
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
        String shortname = null;
        String description = null;
        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                name = object.getString("name");
                shortname = object.getString("shortname");
                description = object.getString("description");
                Obra obra = new Obra(id, name, shortname, description);
                list.add(obra);


            }
            Log.d("LIISOBRA", list.toString());
            callback.onSuccess(list);
            this.list.clear();
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void delete(Obra obra, OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(() -> obraDao.delete(obra));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/deleteObra.php?id=" + obra.getId());
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

        callback.onDeleteSucces("Se ha eliminado la obra" + obra.getName());
    }

    @Override
    public void undo(Obra obra, OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(() -> obraDao.insert(obra));


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarObra.php?name=" + obra.getName().toString() +"&shortname="+obra.getShortname().toString() +"&description="+obra.getDescription().toString() );
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

        callback.onUndoSuccess("Se ha vuelto ha añadir esta obra");
    }


    @Override
    public void add(Obra obra, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(() -> obraDao.insert(obra));


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarObra.php?name=" + obra.getName().toString() +"&shortname="+obra.getShortname().toString() +"&description="+obra.getDescription().toString() );
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

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess("Se ha añadido correctamente");

    }

    @Override
    public void edit(Obra obra, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(() -> obraDao.update(obra));


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/updateObra.php?&id=" + obra.getId() + "&description=" +obra.getDescription().toString());
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
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess("Se ha editado correctamente");
    }
}
