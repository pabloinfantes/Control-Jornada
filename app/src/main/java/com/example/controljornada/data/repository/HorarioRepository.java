package com.example.controljornada.data.repository;

import android.util.Log;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.HorarioDao;
import com.example.controljornada.data.dao.UserDao;
import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.base.ReadFromObras;
import com.example.controljornada.ui.base.ReadFromRoomCallback;
import com.example.controljornada.ui.calendario.CalendarioListContract;
import com.example.controljornada.ui.horario.HorarioContract;

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
import java.util.concurrent.ExecutionException;

public class HorarioRepository implements HorarioContract.Repository, CalendarioListContract.Repository {

    private static HorarioRepository instance;

    private ArrayList<Horario> listHorario;
    private ArrayList<User> listUser;
    private ArrayList<String> obras;

    private String result;
    private String resultObra;



    private HorarioRepository() {
        listHorario = new ArrayList<>();
        listUser = new ArrayList<>();
        obras = new ArrayList<>();
        //horarioDao = ControlJornadaDatabase.getDatabase().horarioDao();
        //userDao = ControlJornadaDatabase.getDatabase().userDao();
    }

    public static HorarioRepository getInstance() {
        if (instance == null) {
            instance = new HorarioRepository();
        }
        return instance;
    }


    private void sendData(String data) {
        result = data;
    }
    private void sendDataObra(String data) {
        resultObra = data;
    }

    @Override
    public void add(Horario horario, OnRepositoryCallback callback) {
        //ControlJornadaDatabase.databaseWriteExecutor.submit(() -> horarioDao.insert(horario));

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url2 = new URL("http://158.101.203.234/add/controlJornada/insertarHorario.php?id=" + horario.getId() + "&idUser=" + horario.getIduser() + "&emailUser=" + horario.getEmailUser() + "&morningWork=" + horario.getLugarTrabajoMñn()+ "&afternoonWork=" + horario.getLugarTrabajoTarde()+ "&actualDate=" + horario.getFechaDelDiaDeTrabajo()+ "&horarioEntradaManana=" + horario.getHorarioEntradaMñn()+ "&horarioSalidaManana=" + horario.getHorarioSalidaMñn()+ "&horarioEntradaTarde=" + horario.getHorarioEntradaTarde()+ "&horarioSalidaTarde=" + horario.getHorarioSalidaTarde()+ "&numeroHoras=" + horario.getNumeroHoras()+ "&motivoAusencia=" + horario.getMotivoAusencia());
                    Log.d("url", String.valueOf(url2));
                    HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getErrorStream();
                    } else {
                        InputStream err = connection.getErrorStream();
                    }
                    Log.d("ENTROOO", String.valueOf(url2));
                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        callback.onSuccess("añadido nuevo horario");
    }

    @Override
    public void leer(Horario horario, ReadFromRoomCallback callback) {

        Thread thread = new Thread(new Runnable() {
            public String data = "";

            @Override
            public void run() {

                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/leerHorario.php");
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
        int idUser = 0;
        String emailUser = null;
        String morningWork = null;
        String afternoonWork = null;
        String actualDate = null;
        String horarioEntradaManana = null;
        String horarioSalidaManana = null;
        String horarioEntradaTarde = null;
        String horarioSalidaTarde = null;
        String numeroHoras = null;
        String motivoAusencia = null;

        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                idUser = object.getInt("idUser");
                emailUser = object.getString("emailUser");
                morningWork = object.getString("morningWork");
                afternoonWork = object.getString("afternoonWork");
                actualDate = object.getString("actualDate");
                horarioEntradaManana = object.getString("horarioEntradaManana");
                horarioSalidaManana = object.getString("horarioSalidaManana");
                horarioEntradaTarde = object.getString("horarioEntradaTarde");
                horarioSalidaTarde = object.getString("horarioSalidaTarde");
                numeroHoras = object.getString("numeroHoras");
                motivoAusencia = object.getString("motivoAusencia");

                Horario horario1 = new Horario(idUser, emailUser, morningWork, afternoonWork, actualDate, horarioEntradaManana, horarioSalidaManana, horarioEntradaTarde, horarioSalidaTarde, Integer.parseInt(numeroHoras), motivoAusencia);
                listHorario.add(horario1);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.OnSuccessReadHorario(String.valueOf(listHorario.size()));
    }


    @Override
    public void leer(User user, ReadFromRoomCallback callback) {


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

                User user1 = new User(id, email, name, Integer.parseInt(admin), numeroHorasMensuales);
                user.setApellidos(surname);
                user.setGenero(genero);
                user.setTelefono(telefono);
                user.setEmpresa(empresa);
                user.setEdad(0);

                listUser.add(user1);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("LIST USER",listUser.toString());
        Log.d("USER ADDED",user.toString());
        for (User user2: listUser) {

            if (user.getId() == user2.getId()){
                Log.d("USER ADDED 2",user.toString());
                callback.OnSuccessReadUser(user2);
            }else {
                Log.d("USER ADDED 22",user.toString());
                user.setId(0);
                callback.OnSuccessReadUser(user2);
            }
        }

    }

    @Override
    public void leerObra(ReadFromObras callback) {


        Thread thread = new Thread(new Runnable() {
            public String dataObra = "";

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
                                dataObra += line;
                            }
                            sendDataObra(dataObra);


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
                return dataObra;
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        JSONArray arrayObra = null;
        int id = 0;
        String name = null;
        String shortname = null;
        String description = null;

        try {
            arrayObra = new JSONArray(resultObra);
            for (int i = 0; i < arrayObra.length(); i++) {
                JSONObject object = arrayObra.getJSONObject(i);
                id = object.getInt("id");
                name = object.getString("name");
                //shortname = object.getString("shortname");
                //description = object.getString("description");
                //Obra obra = new Obra(id, name, shortname, description);
                obras.add(name);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("LIISOBRA", obras.toString());
        callback.OnSuccessReadObra(obras);
        this.obras.clear();


    }

    @Override
    public void edit(User user, OnRepositoryCallback callback) {
//        Integer numHorasAlMes = null;
//        try {
//            numHorasAlMes = (Integer) ControlJornadaDatabase.databaseWriteExecutor.submit(() -> horarioDao.obtenerNumHorasAlMes(user.getId())).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        user.setNumeroHorasMensuales(String.valueOf(numHorasAlMes));

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/updateUser.php?email=" + user.getEmail() + "&name=" + user.getNombre() + "&surname=" + user.getApellidos() + "&numeroHorasMensuales=" + user.getNumeroHorasMensuales() + "&empresa=" + user.getEmpresa() + "&genero=" + user.getGenero() + "&telefono=" + user.getTelefono() + "&edad=" + user.getEdad());
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

        //ControlJornadaDatabase.databaseWriteExecutor.submit(() -> userDao.update(user));
        callback.onSuccess("edit");
    }

    @Override
    public void add(User user, OnRepositoryCallback callback) throws ExecutionException, InterruptedException {
        //ControlJornadaDatabase.databaseWriteExecutor.submit(() -> userDao.insert(user));


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

        callback.onSuccess(user.toString());
    }

    @Override
    public void selectAdminUser(String fechaDelDiaDeTrabajo, OnRepositoryListCallback callback) {


        Thread thread = new Thread(new Runnable() {
            public String data = "";

            @Override
            public void run() {

                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/leerHorarioAdmin.php?actualDate="+fechaDelDiaDeTrabajo);
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
        int idUser = 0;
        String emailUser = null;
        String morningWork = null;
        String afternoonWork = null;
        String actualDate = null;
        String horarioEntradaManana = null;
        String horarioSalidaManana = null;
        String horarioEntradaTarde = null;
        String horarioSalidaTarde = null;
        String numeroHoras = null;
        String motivoAusencia = null;

        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                idUser = object.getInt("idUser");
                emailUser = object.getString("emailUser");
                morningWork = object.getString("morningWork");
                afternoonWork = object.getString("afternoonWork");
                actualDate = object.getString("actualDate");
                horarioEntradaManana = object.getString("horarioEntradaManana");
                horarioSalidaManana = object.getString("horarioSalidaManana");
                horarioEntradaTarde = object.getString("horarioEntradaTarde");
                horarioSalidaTarde = object.getString("horarioSalidaTarde");
                numeroHoras = object.getString("numeroHoras");
                motivoAusencia = object.getString("motivoAusencia");

                Horario horario1 = new Horario(idUser, emailUser, morningWork, afternoonWork, actualDate, horarioEntradaManana, horarioSalidaManana, horarioEntradaTarde, horarioSalidaTarde, Integer.parseInt(numeroHoras), motivoAusencia);
                listHorario.add(horario1);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        callback.onSuccess(listHorario);
        this.listHorario.clear();
    }

    @Override
    public void selectNormalUser(String emailUser, String fechaDelDiaDeTrabajo, OnRepositoryListCallback callback) {

        Thread thread = new Thread(new Runnable() {
            public String data = "";

            @Override
            public void run() {

                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/leerHorarioAdmin.php?actualDate="+fechaDelDiaDeTrabajo+"&emailUser="+emailUser);
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
        int idUser = 0;
        String emailUser2 = null;
        String morningWork = null;
        String afternoonWork = null;
        String actualDate = null;
        String horarioEntradaManana = null;
        String horarioSalidaManana = null;
        String horarioEntradaTarde = null;
        String horarioSalidaTarde = null;
        String numeroHoras = null;
        String motivoAusencia = null;

        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                idUser = object.getInt("idUser");
                emailUser2 = object.getString("emailUser");
                morningWork = object.getString("morningWork");
                afternoonWork = object.getString("afternoonWork");
                actualDate = object.getString("actualDate");
                horarioEntradaManana = object.getString("horarioEntradaManana");
                horarioSalidaManana = object.getString("horarioSalidaManana");
                horarioEntradaTarde = object.getString("horarioEntradaTarde");
                horarioSalidaTarde = object.getString("horarioSalidaTarde");
                numeroHoras = object.getString("numeroHoras");
                motivoAusencia = object.getString("motivoAusencia");

                Horario horario1 = new Horario(idUser, emailUser2, morningWork, afternoonWork, actualDate, horarioEntradaManana, horarioSalidaManana, horarioEntradaTarde, horarioSalidaTarde, Integer.parseInt(numeroHoras), motivoAusencia);
                listHorario.add(horario1);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.onSuccess(listHorario);

        this.listHorario.clear();
    }
}
