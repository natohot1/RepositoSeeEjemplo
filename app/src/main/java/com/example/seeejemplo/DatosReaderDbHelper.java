package com.example.seeejemplo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DatosReaderDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sentencia.sqlite";
    public static final int DATABASE_VERSION = 1;
    public Context myContext;
    public SQLiteDatabase myDataBase;
    public static final String TABLA_MEDICAMENTOS2 = "medicamentos2";
    public static final String TABLA_DOSESPECIAL = "dosespecial";
    public static final String TABLA_COMERCIAL = "comercial";
    public static final String TABLA_COMERCIAL2 = "comercial2";
    public static final String TABLA_PESO_EDAD = "peso_edad";
    public static final String TABLA_JARABES = "jarabes";
    public static final String TABLA_JARABES2 = "jarabes2";
    public static final String TABLA_ASMA = "asma";

    private static final String COLUMNA_MEDICAMENTO = "medicamento";
    private static final String COLUMNA_PESO = "peso";

    DecimalFormat df = new DecimalFormat("0.0");
    DecimalFormat d = new DecimalFormat("0");

    //Ruta por defecto de las bases de datos en el sistema Android
    private static String DB_PATH;


    public DatosReaderDbHelper(Context myContext) {
        super(myContext, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = myContext;
        this.DB_PATH = this.myContext.getDatabasePath(DATABASE_NAME).getAbsolutePath();
    }

    /**
     * Crea una base de datos vacia en el sistema y la reescribe con nuestro fichero de base de datos.
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //la base de datos existe y no hacemos nada.
        } else {
            //Llamando a este metodo se crea la base de datos vac�a en la ruta por defecto del sistema
            //de nuestra aplicaci�n por lo que podremos sobreescribirla con nuestra base de datos.
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copiando Base de Datos");
            }
        }
    }

    /**
     * Comprueba si la base de datos existe para evitar copiar siempre el fichero cada vez que se abra la aplicacion.
     *
     * @return true si existe, false si no existe
     */
    private boolean checkDataBase() {

        File dbFile = new File(DB_PATH + DATABASE_NAME);
        return dbFile.exists();

        // SQLiteDatabase checkDB = null;
        //
        // try {
        // String myPath = DB_PATH + DB_NAME;
        // checkDB = SQLiteDatabase.openDatabase(myPath, null,
        // SQLiteDatabase.OPEN_READWRITE); // changing to writable
        //
        // } catch (SQLiteException e) {
        //
        // // database does't exist yet.
        //
        // }
        //
        // if (checkDB != null) {
        //
        // checkDB.close();
        //
        // }
        //
        // return checkDB != null ? true : false;
    }


    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    /**
     * Copia nuestra base de datos desde la carpeta assets a la recien creada
     * base de datos en la carpeta de sistema, desde donde podremos acceder a ella.
     * Esto se hace con bytestream.
     */
    private void copyDataBase() throws IOException {
        //Abrimos el fichero de base de datos como entrada
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        //Ruta a la base de datos vacia recien creada
        String outFileName = DB_PATH + DATABASE_NAME;

        //Abrimos la base de datos vacìa como salida
        OutputStream myOutput = new FileOutputStream(outFileName);

        //Transferimos los bytes desde el fichero de entrada al de salida
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Liberamos los streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDB() {
        try {
            createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("DataBaseHelper", e.toString());
        }

        openDataBase();
        getWritableDatabase();
        getReadableDatabase();

    }

    public void openDataBase() throws android.database.SQLException {

        // Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE // this was readable only
                        | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

    }

    public SQLiteDatabase getInstanceOfDB() {
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE // this was readable only
                        | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase;
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public static double[] sacar_num(String valor) {
        int largo, contador;
        Boolean saltar = true;
        contador = 0;
        String resul_string[];
        resul_string = valor.split("-");
        largo = resul_string.length;
        double dosis_finales[] = new double[largo];
        while (saltar) {
            try {
                dosis_finales[contador] = Double.parseDouble(resul_string[contador]);
                contador++;
            } catch (Exception e) {
                saltar = false;
            }
        }
        return dosis_finales;
    }

    public static String[] sacar_numStrin(String valor) {
        int largo, contador;
        Boolean saltar = true;
        contador = 0;
        String resul_string[];
        resul_string = valor.split("-");
        return resul_string;
    }

    public static void dosis(double dosis[], int progre, double cada[], double var_jar, double ins_r, TextView most) {
        double primera_dosis, segun_dosis;
        DecimalFormat df = new DecimalFormat("0.0");
        DecimalFormat d = new DecimalFormat("0");
        try {
            primera_dosis = (((dosis[0] * ins_r) * progre) / (24 / cada[0])) / (var_jar / 5);
            segun_dosis = (((dosis[1] * ins_r) * progre) / (24 / cada[0])) / (var_jar / 5);
            most.setText("Jarabe  " + d.format(var_jar) + "        " + df.format(primera_dosis) + "  a " + df.format(segun_dosis) + "  cc");
        } catch (Exception e) {
            primera_dosis = (((dosis[0] * ins_r) * progre) / (24 / cada[0])) / ((var_jar / 5));
            most.setText("Jarabe   " + d.format(var_jar) + "      " + df.format(primera_dosis) + "  cc");
        }
    }


    public void modificarbandera(String value){
        openDB();
        String var_bandera = "BANDERA";
        ContentValues registro = new ContentValues();
        registro.put(Medicamento.CN_dosisAddia,value);
        myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS,registro,
                Medicamento.CN_medicamento + "=?", new String[]{var_bandera});
        close();
    }

    public void pasarDatos(String clase3, String botonAc4){
        openDB();
        String var_bandera = "BANDERA";
        ContentValues registro = new ContentValues();
        registro.put(Medicamento.CN_DosisP,clase3);
        registro.put(Medicamento.CN_DosisP1,botonAc4);
        myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS,registro,
                Medicamento.CN_medicamento + "=?", new String[]{var_bandera});
        close();
    }

    //obtener cur_medicamento por nombre
    public Cursor buscarMed(String medicina) throws SQLException {
        openDB();
        String selection = Medicamento.CN_medicamento+ " = ? ";
        String selectionArgs[] = new String[]{medicina};
        Cursor c =myDataBase.query(
                "medicamentos2",
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    //DEVULEVE EL NOMBRE GENERICO INRODUCIDO
    public String mediComer(String medicina){
        openDB();
        String devol=null;
        String selection = Comercial.CN_nobrecom+ " = ? ";
        String selectionArgs[] = new String[]{medicina};
        Cursor c =myDataBase.query(
                "comercial",
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
            devol=c.getString(1);
        }
        return devol;

    }

    //BUSCAR Y EXTRER SI HAY EN TABLA DOSIS ESPECIALES
    public Cursor dos_esp(String medicina) throws SQLException {
        openDB();
        String query = "select * FROM " + TABLA_DOSESPECIAL + " WHERE " + COLUMNA_MEDICAMENTO + " = \"" + medicina + "\"";
        Cursor ce = myDataBase.rawQuery(query,null);

        if (ce != null) {
            ce.moveToFirst();
        }
        return ce;
    }

    public Cursor nom_com(String nombrcomer) throws SQLException {
        openDB();
        String query = "select * FROM " + TABLA_COMERCIAL + " WHERE " + Medicamento.CN_nobrecom + " = \"" + nombrcomer + "\"";
        Cursor cex = myDataBase.rawQuery(query,null);

        if (cex != null) {
            cex.moveToFirst();
        }
        return cex;
    }

    public Cursor edad(String peso)throws SQLException {
        openDB();
        String query = "select * FROM " + TABLA_PESO_EDAD + " WHERE " + COLUMNA_PESO + " = \"" + peso + "\"";
        Cursor ce = myDataBase.rawQuery(query,null);

        if(ce != null){
            ce.moveToFirst();
        }
        return ce;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //  INSERTAR DATOS DE INICIO EN AMOXICILINA
    public void val_inicio() throws SQLException {
        openDB();
        String var_paraceta ="PARACETAMOL";
        String var_amoxic="AMOXICILINA";
        Cursor p=buscarMed(var_paraceta);
        Cursor a=buscarMed(var_amoxic);

        int primeraVezP=p.getInt(6);
        int paraFecha,amoFecha;

        paraFecha=p.getInt(6);
        amoFecha=a.getInt(6);

        int masdias=1;

        Date fecha = new Date();
        Date masfecha = new Date();

        masfecha=sumarRestarDiasFecha(fecha,masdias);

        String fechaString=fecha.toString();
        String fe_mas=masfecha.toString();

        if ((primeraVezP == 0) || (paraFecha<=amoFecha)) {
            primeraVezP=1;
            ContentValues registro = new ContentValues();
            ContentValues registro1 = new ContentValues();
            registro.put(Medicamento.CN_nebulizada, fechaString);
            registro.put(Medicamento.CN_DosisP2,primeraVezP);
            registro1.put(Medicamento.CN_nebulizada,fe_mas);
            myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS,registro,Medicamento.CN_medicamento + "=?", new String[]{var_paraceta});
            myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS,registro1,Medicamento.CN_medicamento + "=?", new String[]{var_amoxic});
        }
    }


    public Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public void cambioBandera(int bandera){
        int valor;
        if(bandera ==0){
            String var_amoxi="AMOXICILINA";
            valor=0;
            ContentValues registro = new ContentValues();
            registro.put(Medicamento.CN_DosisP2,valor);
            myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS, registro,
                    Medicamento.CN_medicamento + "=?", new String[]{var_amoxi});
        }
        else{
            String var_amoxi="AMOXICILINA";
            valor=1;
            ContentValues registro = new ContentValues();
            registro.put(Medicamento.CN_DosisP2,valor);
            myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS, registro,
                    Medicamento.CN_medicamento + "=?", new String[]{var_amoxi});
        }

    }

    public Boolean validarBandera() throws SQLException {
        Boolean esBoli=null;
        Cursor para=buscarMed("AMOXICILINA");
        int valorBandera=para.getInt(5);
        if (valorBandera==0){
            esBoli=true;
        }
        else {
            esBoli=false;
        }
        return esBoli;
    }

    //<editor-fold desc="SACA LOS DATOS EN array_inter[][]">
    public String[][] sacarOrdenados_en_array(String medicamen, String mediComercial, String bandera) throws java.sql.SQLException {
        String[][] array_inter = new String[20][9];
        String jarabes_por5ml = "";

        Cursor c = null;
        try {
            c = buscarMed(medicamen);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        c.moveToFirst();
        int m = 0, j = 0, d = 0;

        while (m <= 19) {
            array_inter[m][0] = c.getString(m);
            m++;
        }
        c.close();
        try {
            c = jar_NombreComerciales(medicamen, bandera);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }
        while (j <= 7) {
            array_inter[0][j] = c.getString(j);
            j++;
        }
        c.close();
        //</editor-fold>
        //SACAR JARABES SEGUN PAIS//////
        if (bandera.equals("banespana.png")) {
            jarabes_por5ml = array_inter[10][0];
        }
        if (bandera.equals("banbolivia.png")) {
            jarabes_por5ml = array_inter[9][0];
        }
        String[] arrJarpor5ml = jarabes_por5ml.split("-");
        int largoArrJar5 = arrJarpor5ml.length;

        String[] nue = new String[5];
        nue[0] = array_inter[0][2];
        nue[1] = array_inter[0][3];
        nue[2] = array_inter[0][4];
        nue[3] = array_inter[0][5];
        nue[4] = array_inter[0][6];
        //ORDENAMOS LOS VALORES
        int contador = 0;
        // int contadorEsta=0;
        while (contador < 5) {
            String interme = ponPrimero(nue[contador], mediComercial);
            nue[contador] = interme;
            contador++;
        }
        int contadorEsta = estaEnArray(nue, mediComercial);

        //cambiamos de posicion el string de medicamentos
        String interSeg = nue[0];
        String interme = nue[contadorEsta];
        nue[0] = interme;
        nue[contadorEsta] = interSeg;

        String interPrimDosis = arrJarpor5ml[0];
        String intermeDosis = arrJarpor5ml[contadorEsta];
        arrJarpor5ml[0] = intermeDosis;
        arrJarpor5ml[contadorEsta] = interPrimDosis;

        contador = 0;
        String jara5 = new String();
        while (contador < largoArrJar5) {


            jara5 = jara5 + "-" + arrJarpor5ml[contador];
            contador++;

        }
        Character guion = jara5.charAt(0);
        char mio = '-';
        if (guion == mio) {
            jara5 = jara5.substring(1, jara5.length());
        }
        String devJar5 = jara5;
        array_inter[0][2] = nue[0];
        array_inter[0][3] = nue[1];
        array_inter[0][4] = nue[2];
        array_inter[0][5] = nue[3];
        array_inter[0][6] = nue[4];
        if (bandera.equals("banespana.png")) {
            array_inter[10][0] = jara5;
        }
        if (bandera.equals("banbolivia.png")) {
            array_inter[9][0] = jara5;
        }
        int borrar = 1;
        return array_inter;
    }

    //10/05/20 FUNCION QUE DEVULVE SRING DONDE EL BUSCADO SE PONE EN PRIMER LUGAR EL BUSCADO SI ESTA
    //  NECESITA STRING SEPARADO POR "\n", STRING BUSCADO. NECESITA LA FUNCION estaEnNumero
    public String ponPrimero(String strOrigen, String strBuscado) {
        String devol = strOrigen;
        String[] arraOrigen = strOrigen.split("\n");
        int largo = arraOrigen.length;
        int posicionQueEsta = estaEnNumero(strOrigen, strBuscado);
        if (posicionQueEsta != -1) {
            devol = "";
            String interme = arraOrigen[posicionQueEsta];
            String prime = arraOrigen[0];
            //intercambiamos posiciones
            arraOrigen[0] = interme;
            arraOrigen[posicionQueEsta] = prime;
        }

        int contador = 0;
        if (posicionQueEsta == 0) {
            devol = strOrigen;
        }
        if (posicionQueEsta > 0) {
            devol = arraOrigen[0];
            contador = 1;
            while (contador < largo) {
                devol = devol + "\n" + arraOrigen[contador];
                contador++;
            }
        }


        return devol;
    }

    //FUNCION DEVULEVE -1 SI NO HAY UNA PALABRA IGUAL, SINO EL NUMERO DEL ORIGEN DE DATOS LA CUAL VENDRA SEPAARDO POR "\n"
    private int estaEnNumero(String origenDatos, String medicina) {
        //primero sacamos las palabras en array
        int devol = -1;
        String[] arrOrigen = origenDatos.split("\n");
        int largoarrOrigen = arrOrigen.length, contador = 0;
        while (contador < largoarrOrigen) {
            boolean esta = esIgual(arrOrigen[contador], medicina);
            if (esta) {
                devol = contador;
            }
            contador++;
        }
        return devol;
    }

    //deveulve el numero donde esta la cadena a buscar NECESITA estaEnBool
    private int estaEnArray(String[] origendatos, String buscado) {
        int estaEn = 0;
        boolean contiene = false;
        int largoArray = origendatos.length, contador = 0;
        while (contador < largoArray) {
            contiene = estaEnBool(origendatos[contador], buscado);
            if (contiene) {
                estaEn = contador;
                break;
            }
            contador++;
        }
        return estaEn;
    }

    //FUNCION QUE DVUELVE TRUE SI SON IGUALES NECESITA STRIN SENSILLOS
    public boolean esIgual(String aComparar, String medicina) {
        boolean devol = false;
        aComparar = aComparar.replaceAll("Ñ", "");
        medicina = medicina.replaceAll("Ñ", "");
        int largoCompara = aComparar.length(), largoMedicina = medicina.length();

        int contador = 0;
        Character[] aCompaChar = new Character[largoCompara];
        Character[] mediChar = new Character[largoMedicina];
        if (largoCompara == largoMedicina) {
            while (contador < largoCompara) {
                aCompaChar[contador] = aComparar.charAt(contador);
                contador++;
            }
            contador = 0;
            while (contador < largoMedicina) {
                mediChar[contador] = medicina.charAt(contador);
                contador++;
            }
            contador = 0;
            if (largoCompara != largoMedicina) {
                devol = false;
            } else {
                while (contador < largoMedicina) {
                    if (aCompaChar[contador] == mediChar[contador]) {
                        devol = true;
                    } else {
                        devol = false;
                        break;
                    }
                    contador++;
                }
            }
        }
        return devol;
    }

    //**************************************NUEVA FUNCION***********************
    //FUNCION DEVULEVE TRUE SI HAY UNA PALABRA IGUAL DE ORIGEN DE DATOS LA CUAL VENDRA SEPAARDO POR "\n"
    //NECESITA PARA FUNCIONAR esIgual
    public boolean estaEnBool(String origenDatos, String medicina) {
        //primero sacamos las palabras en array
        boolean devol = false;
        String[] arrOrigen = origenDatos.split("\n");
        int largoarrOrigen = arrOrigen.length, contador = 0;
        while (contador < largoarrOrigen) {
            boolean esta = esIgual(arrOrigen[contador], medicina);
            if (esta) {
                devol = true;
            }
            contador++;
        }
        return devol;
    }


    //**************************************NUEVA FUNCION***********************


    public String[][] sacar_datos_en_array(String medicamen, String bandera) throws java.sql.SQLException {
        String[][] array_inter = new String[20][9];
        Cursor c = null;
        try {
            c = buscarMed(medicamen);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        c.moveToFirst();
        int m = 0, j = 0, d = 0;

        while (m <= 19) {
            array_inter[m][0] = c.getString(m);
            m++;
        }
        c.close();
        try {
            c = jar_NombreComerciales(medicamen, bandera);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }
        while (j <= 7) {
            array_inter[0][j] = c.getString(j);
            j++;
        }
        c.close();
        return array_inter;

    }

    //DOSIS ESPECIALES
    public double[] sacar_dosis_especial (String medicamen_final) {
        Cursor dosesp=null;
        double[] resultado = new double[18];
        int cont = 0;
        try {
            //SACAMOS DOSIS ESPECIALES
            dosesp = dos_esp(medicamen_final);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        while (cont < 17) {
            resultado[cont] = dosesp.getDouble(cont);
            cont++;
        }
        return resultado;
    }

    //obtener lista de jarabes
    public Cursor jar_NombreComerciales(String medicamento, String tablaPais) throws SQLException {
        String pais=null;
        if(tablaPais.equals("banbolivia.png")){
            pais="jarabes2";}
        if(tablaPais.equals("banespana.png")){
            pais="jarabes";}
        String query = "select * FROM " + pais + " WHERE " + COLUMNA_MEDICAMENTO + " = \"" + medicamento + "\"";
        Cursor c = myDataBase.rawQuery(query,null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Vector jarabes_No_Comer(String medicamento, String comer, String jarabes[],String pais) throws SQLException {
        String query=null;
        if (pais.equals("banespana.png")) {
            query = "select * FROM " + TABLA_JARABES + " WHERE " + COLUMNA_MEDICAMENTO + " = \"" + medicamento + "\"";
        }
        if (pais.equals("banbolivia.png")) {
            query = "select * FROM " + TABLA_JARABES2 + " WHERE " + COLUMNA_MEDICAMENTO + " = \"" + medicamento + "\"";
        }
        Cursor c = myDataBase.rawQuery(query,null);
        if (c != null) {
            c.moveToFirst();
        }
        String[]nue=new String[5];
        nue[0]=c.getString(2);
        nue[1]=c.getString(3);
        nue[2]=c.getString(4);
        nue[3]=c.getString(5);
        nue[4]=c.getString(6);
        int cont=0,con2=0,con3=0;
        while (cont<5) {
            int valor=nue[cont].length();
            if (valor>2){
                con2=cont;
            }
            cont++;
        }
        String mios[]=new String[con2+1];
        while (con3<=(con2)){
            mios[con3]=nue[con3];
            con3++;
        }
        int largo=mios.length;
        int [][]dato_var1={{0,1,2,3,4},{1,0,2,3,4},{2,0,1,3,4},{3,0,1,2,4},{4,0,1,2,3}};


        int con1=0,variable=0;

        while(con1<largo){
            int res1=mios[con1].indexOf(comer);
            if(res1!=-1){
                variable=con1;
                break;            }
            con1++;       }
        String[] jarabes_comercial3 = new String[largo];
        String[] jaraDosis=new String[largo];

        int contaArra=0;
        while(contaArra<(largo)){
            jarabes_comercial3[contaArra]=mios[dato_var1[variable][contaArra]];
            jaraDosis[contaArra]=jarabes[dato_var1[variable][contaArra]];

            contaArra++;
        }
        Vector nuevo = new Vector(2);
        nuevo.add(jarabes_comercial3);
        nuevo.add(jaraDosis);
        return nuevo;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String dosis_kilo1(int res_espiner, double resSeeb, double[] dosis_intDou, double[] cada_double, String[] array_jarabes,String medicamen_final, int cada_int) {
        double resultado, resultado1;
        String res;
        double minimoPeso=3; //ESTABLECE PESO MINIMO EN KILOGRAMOS
        double seekBar_minimo= nuevores(resSeeb,minimoPeso);
        double jara_int = Double.parseDouble(array_jarabes[res_espiner]);
        try {
            resultado = ((dosis_intDou[0] * seekBar_minimo) / (24 / cada_double[0])) / ((jara_int) / 5);
            resultado1 = ((dosis_intDou[1] * seekBar_minimo) / (24 / cada_double[0])) / ((jara_int) / 5);
            res=si_gotas2(resultado,resultado1, medicamen_final,cada_int);
            //  txCantidad.setText(res);
        } catch (Exception e) {
            resultado = ((dosis_intDou[0] * seekBar_minimo) / (24 / cada_double[0])) / ((jara_int) / 5);
            resultado1=0;
            res=si_gotas2(resultado,resultado1, medicamen_final,cada_int);
            if(resultado==0){
                res="";
                //   txCantidad.setText("");
            }else {
                //   txCantidad.setText(res);
            }
        }
        return res;
    }

    private double nuevores ( double resSeeb, double minimoPeso){
        double resul;
        if (resSeeb <= minimoPeso) {
            resul = 0;
        } else {
            resul = resSeeb;
        }
        return resul;
    }
    //PARA AGREGAR LOS MEDICACMENTOS QUE TIENEN PRESENTACION DE GOTAS///////////////
    public String si_gotas2 ( double resultado, double re1, String medi, int cada_int){
        boolean cont = true, gotas = false;
        String nue = "";
        int variaGotas = 20;
        double resFinalgotas = resultado, resFinalgotas2 = re1;
        String predni = "PREDNISOLONA", dexgotas = "DEXTROMETORFANO",paragotas = "PARACETAMOL",levogotas = "LEVOCETIRIZINA",
                ibugotas = "IBUPROFENO",levodrogotas = "LEVODROPROPIZINA",metalgialgotas = "METAMIZOL",cetirizinagotas ="CETIRIZINA",zamenegotas = "DEFLAZACORT";
        if (resultado < 1) {
            if (medi.equals(predni)) {
                gotas = true;
                variaGotas = 40; }
            if (medi.equals(dexgotas)) {
                gotas = true;
                variaGotas = 27; }
            if (medi.equals(paragotas) || medi.equals(levogotas )|| medi.equals(ibugotas)|| medi.equals(levodrogotas) || medi.equals(metalgialgotas) || medi.equals(cetirizinagotas)|| medi.equals(zamenegotas)) {
                gotas = true; }
        }
        if (gotas) {
            try {
                if (resultado < 1.5) {
                    resFinalgotas = resultado * variaGotas;
                } else {
                    resFinalgotas = resultado; }
                if (re1 < 2) {
                    resFinalgotas2 = re1 * variaGotas; }
                else {
                    resFinalgotas2 = re1; }
            } catch (Exception e) {
                if (resultado < 1.5) {
                    resFinalgotas = resultado * variaGotas;
                } else {
                    resFinalgotas = resultado;}
            }
        }


        if (re1 == 0) {
            if (gotas) {
                nue = (d.format(resFinalgotas) + " gotas");
            } else {
                nue = (df.format(resFinalgotas) + " cc");
            }
        } else {
            if (gotas) {
                nue = (d.format(resFinalgotas) + " a " + d.format(resFinalgotas2) + " gotas");
            } else {
                nue = (df.format(resFinalgotas) + " a " + df.format(resFinalgotas2) + " cc");
            }
        }
        if (resultado == 0) {
            nue = "";
        }
        return nue;
    }

    public double resul_dosis1 ( double[] dosis, int contador, double sekbar){
        double[] edad_int = new double[10];
        int cont3 = 0;
        double dosis_valor1, dosis_valor2, edad_valor1, edad_valor2, edad_resul, dosis_resul, r_final = 0;
        dosis_valor1 = dosis[contador];
        dosis_valor2 = dosis[contador + 1];
        edad_valor1 = dosis[contador + 2];
        edad_valor2 = dosis[contador + 3];
        dosis_resul = (dosis_valor2 - dosis_valor1) / 10;
        edad_resul = (edad_valor2 - edad_valor1) / 10;
        for (int i = 0; i < 10; i++) {
            edad_int[cont3] = (edad_resul * cont3) + edad_valor1;
            cont3++;
        }
        int cont2 = 0, resul1 = 0;
        while (cont2 < 10) {
            if ((sekbar / 12) >= edad_int[cont2] && (sekbar / 12) <= edad_int[cont2 + 1]) {
                break;
            }
            cont2++;
        }
        r_final = (dosis_resul * (cont2)) + dosis_valor1;
        return r_final;
    }

    //*****SACA DOSIS DE ACUERDO A EDAD VA CON dosis_final_edad  ALERGIA, TOS
    public double[] dosis_final_edad(double[] dosis, double valor_seekb1){
        //<editor-fold desc="SE NECEITA SEEKBARR Y VALORES DE DOSIS EDAD = ultima_dosis_edad ALERGIA, TOS">
        double[] resultado = new double[1];
        double valor_seekb;
        valor_seekb = valor_seekb1 / 12;

        int contador = 0;
        if (dosis[5] <= valor_seekb && dosis[6] >= valor_seekb) {
            contador = 3;
            resultado[0] = resul_dosis1(dosis, contador, valor_seekb1);
        }
        if (dosis[10] <= valor_seekb && dosis[11] >= valor_seekb) {
            contador = 8;
            resultado[0] = resul_dosis1(dosis, contador, valor_seekb1);
        }
        if (dosis[15] <= valor_seekb && dosis[16] >= valor_seekb) {
            contador = 13;
            resultado[0] = resul_dosis1(dosis, contador, valor_seekb1);
        }
        if (valor_seekb < dosis[5]) {
        }
        return resultado;
        //</editor-fold>(ULTIM

    }










    //DEVUELVE LA TABLA OH STRING DONDE ESTA, SI ES "0" NO ESTA, NECESITA 5 VALORES
    public int posicion(String valores,String valores2,String valores3,String valores4,String valores5,String aencontrar){
        int dev=0;
        String[]arrayMa={valores,valores2,valores3,valores4,valores5};
        int contaFinal=0;
        int contaDevol=0;
        while (contaFinal<5) {
            String[] nue = arrayMa[contaFinal].split("\n");
            int largo = nue.length;
            int contador = 0, contInter = 0;
            while (contador < largo) {
                if (nue[contador].equals(aencontrar)) {
                    dev = contador;
                    contaDevol=contaFinal+1;
                    break;
                }
                contador++;
            }
            contaFinal++;
        }
        return contaDevol;
    }

    private int contiene(String trata,String busca2){
        //SE CAMBIARA SPLIT SEGUN ESTEN SEPARADOS LOS REGISTROS
        String[] prim=trata.split("\n");
        int largo=prim.length,contador=0,ret=0;
        while (contador<largo){
            String vari=prim[contador];
            if (vari.equals(busca2)){
                ret=contador+1;
            }
            contador++;
        }
        return ret;
    }

    //NECSITA STRING SEPARDO POR SALTO DE LINEA, DEVUELVE STRING SEPARADO POR SALTO DE LINEA
    private String ponPrimDefi(String origenDatos, String buscado) {
        String devol = new String();
        String[] array = origenDatos.split("\n");
        int largo = array.length, conta = 0, enQuePosicion = 0;
        while (conta < largo) {
            if (buscado.equals(array[conta])) {
                enQuePosicion = conta;
            }
            conta++;
        }
        if (enQuePosicion == 0) {
            devol = origenDatos;
        } else {
            String inter = array[enQuePosicion];
            String prime = array[0];
            //cambiamos la posicion
            array[0] = inter;
            array[enQuePosicion] = prime;
        }
        conta = 0;

        int contaInt = largo;
        while (conta < largo) {
            devol = devol + "\n" + array[conta];
            // contaInt=contaInt-1;
            conta++;
        }
        int larDevol = devol.length();

        String revision = devol.substring(larDevol - 1, larDevol);
        // if(revision=="\n") {
        //     devol = devol.substring(0, larDevol - 1);
        // }


        return devol;
    }

    //DEVULVE STRING CON SALTO DE LINEA PONE EN PRIMER
    private String ponerPrimero(String origenDatos, String[] devuel) {
        String[] pasado = origenDatos.split("\n");
        String dev1 = "";
        int posi = Integer.parseInt(devuel[0]);
        int largo = pasado.length;
        int contador = 0;
        String sacado = pasado[posi - 1], prime = pasado[0];
        pasado[0] = sacado;
        pasado[posi - 1] = prime;
        while (contador < largo) {
            dev1 = dev1 + pasado[contador] + "\n";
            contador++;
        }
        return dev1;
    }

    private String ponerPrimero2(String pasadoIni, String[] devuel) {
        String []pasado=pasadoIni.split("\n");
        String dev1="";
        int posi=Integer.parseInt(devuel[0]),largo=pasado.length,contador=0;
        String sacado = pasado[posi - 1];
        String prime = pasado[0];
        pasado[0]=sacado;
        pasado[posi-1]=prime;
        while (contador<largo) {
            dev1 = dev1+"\n" + pasado[contador];
            contador++;
        }
        return dev1;
    }


    //</editor-fold>

    //*************DESECHOS
    //<editor-fold desc="FUNCION QUE DEVUELVE ARRAY STRING DE 2 VALORES LA POSICION Y LUEGO LA CADENA STRING, STRING ORDENADO. NECESITA 5 STRING
    //  SEPARADOS POR ALGO COMO COMAS, SALTOS DE LINEA. SON NECESARIAS  FUNCIONES   "esta"  y "contiene", ademas array de dosis ordenados>
    public String[] esta(String mioA, String mio2, String mio3, String mio4, String mio5, String busca, String[] arrJar) {
        String[] prim = mioA.split("\n");
        String[] prim2 = mio2.split("\n");
        String[] prim3 = mio3.split("\n");
        String[] prim4 = mio4.split("\n");
        String[] prim5 = mio5.split("\n");
        String[] nue = new String[5];
        nue[0] = mioA;
        nue[1] = mio2;
        nue[2] = mio3;
        nue[3] = mio4;
        nue[4] = mio5;
        //LA POSICION DE A TABLA STRING
        int striPosi = 0;

        int tamano = prim.length, tamano2 = prim2.length, tamano3 = prim3.length, tamano4 = prim4.length, tamano5 = prim5.length;
        int estaen;
        //************************NUEVA FUNCION******************
        //DETERMINAMOS SI ESTA Y EN QUE POSICION SE ENCUENTRA strPosi2
        int striPosi2 = 0, contPosi = 0;
        while (contPosi < 5) {
            boolean buscados = estaEnBool(nue[contPosi], busca);
            if (buscados) {
                striPosi2 = contPosi;
                break;
            }
            contPosi++;
        }


        int lar2 = nue[1].length();
        int lar3 = nue[2].length();
        int lar4 = nue[3].length();
        int lar5 = nue[4].length();

        int largoDatos = 1;
        int contUltimo = 0;
        int contadorInt2 = 0;


        //<editor-fold desc="DETERMINA CUANTOS LINEAS DE JARABES HAY">
        if (lar2 > 1) {
            largoDatos = 2;
        }
        if (lar3 > 1) {
            largoDatos = 3;
        }
        if (lar4 > 1) {
            largoDatos = 4;
        }
        if (lar5 > 1) {
            largoDatos = 5;
        }
        //</editor-fold>

        String devuelve2[] = new String[largoDatos];

        //COPIA EN INTERMEDIO EL NUMERO DE STRING QUE CONTIENE EL MEDICAMENTO BUSCADO
        String intermedio = nue[striPosi2];
        intermedio = ponPrimDefi(intermedio, busca);
        String intermedioDosis = arrJar[striPosi2];

        String primero = nue[0];
        String primeroDosis = arrJar[0];

        nue[0] = intermedio;
        arrJar[0] = intermedioDosis;

        nue[striPosi2] = primero;
        arrJar[striPosi2] = primeroDosis;


        while (contUltimo < largoDatos) {
            //SE DEVUELVE SOLO LOS DATOS****************
            devuelve2[contUltimo] = arrJar[contadorInt2] + "\n" + nue[contadorInt2];
            contadorInt2++;
            contUltimo++;
        }

        return devuelve2;
    }



}
