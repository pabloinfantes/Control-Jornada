package com.example.controljornada.data.repository;

import android.util.Log;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.base.ReadFromUser;
import com.example.controljornada.ui.listadohoras.ListadoManageContract;
import com.example.controljornada.ui.listadohoras.ListadoNumeroHorasContract;
import com.example.controljornada.ui.profile.UserContract;

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

/**
 * Esta clase es la encargada de comunicarse con la base de datos que esta en el servidor y proporcionar
 * una serie de datos o acciones
 * @author pablo
 *
 */
public class UserRepository implements ListadoNumeroHorasContract.Repository, ListadoManageContract.Repository , UserContract.Repository {


    private static UserRepository instance;
    private ArrayList<User> list;
    private String resultNumeroHoras;
    private User user = null;

    private String result;

    private UserRepository() {
        list = new ArrayList<>();

    }

    private void sendDataNumeroHoras(String data) {
        resultNumeroHoras = data;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
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

                User user = new User(id, email, name, Integer.parseInt(admin), numeroHorasMensuales);
                user.setApellidos(surname);
                user.setGenero(genero);
                user.setTelefono(telefono);
                user.setEmpresa(empresa);
                user.setEdad(edad);

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

        callback.onDeleteSucces("usuario borrado");


        /*
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),user.getPassword());
        // Prompt the user to re-provide their sign-in credentials

        FirebaseUser userFire = FirebaseAuth.getInstance();
            userFire.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            userFire.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", "User account deleted.");
                                                callback.onDeleteSucces("usuario borrado");
                                            }
                                        }
                                    });
                        }
                    });


         */
    }

    @Override
    public void undo(User user, OnRepositoryListCallback callback) {


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarUser.php?email=" + user.getEmail() + "&name=" + user.getNombre() + "&surname=" + user.getApellidos() + "&numeroHorasMensuales=" + user.getNumeroHorasMensuales());
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
        callback.onUndoSuccess("Se ha vuelto ha añadir este usuario");

    }


    @Override
    public void add(User user, OnRepositoryCallback callback) {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarUser.php?&email=" + user.getEmail() + "&name=" + user.getNombre() + "&surname=" + user.getApellidos());
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
    public void edit(User user, OnRepositoryCallback callback) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/updateUser.php?admin=" + user.getAdmin() + "&email=" + user.getEmail() + "&name=" + user.getNombre() + "&surname=" + user.getApellidos() + "&numeroHorasMensuales=" + user.getNumeroHorasMensuales() + "&empresa=" + user.getEmpresa() + "&genero=" + user.getGenero() + "&telefono=" + user.getTelefono() + "&edad=" + user.getEdad());
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

        callback.onSuccess(user.toString());
    }

    @Override
    public void leer(String userEmail, ReadFromUser callback) {

        Thread thread = new Thread(new Runnable() {
            public String data = "";
            @Override
            public void run() {

                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/controlJornada.php?email="+userEmail);
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
                            sendDataNumeroHoras(data);


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
        String email = null;
        String admin = null;
        String name = null;
        String surname = null;
        String password = null;
        String numeroHorasMensuales = null;
        String genero = null;
        String telefono = null;
        String empresa = null;
        String edad = null;


        try {
            array = new JSONArray(resultNumeroHoras);
            for(int i=0; i < array.length(); i++)
            {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                email = object.getString("email");
                admin = object.getString("admin");
                name = object.getString("name");
                surname = object.getString("surname");
                password = object.getString("password");
                numeroHorasMensuales = object.getString("numeroHorasMensuales");
                genero = object.getString("genero");
                telefono = object.getString("telefono");
                empresa = object.getString("empresa");
                edad = object.getString("edad");


            }
            user = new User(id,email,name,Integer.parseInt(admin),numeroHorasMensuales);
            user.setApellidos(surname);
            user.setGenero(genero);
            user.setTelefono(telefono);
            user.setEmpresa(empresa);
            user.setEdad(edad);
            user.setPassword(password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        callback.OnSuccessReadUser(user);


    }


}
