package com.nksa.tc2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
/**
Para la ejecucion del programa es recomendable usar un Nexus X5 y android 7.0
*/
public class MainActivity extends AppCompatActivity {

    /**
     * Variables utilizadas en la clase.
     *
     * largo: tamaño variable que tendrán los array que se utilizarán.
     * cont: cantidad de pulsaciones del usuario sobre el ¨botón¨ BubbleSort.
     * txtnum: Array que almacenará los valores númericos a los que se le aplicara el ordenamiento.
     * array: Array en el cual se guardarán los números que se crearán de forma aleatoria para luego
     * ser almacenados en el Array txtnum.
     * handler: controlador que se encargará de la ejecución del proceso de ordenamiento.
     * compare: variable que cuenta el número de comparaciones que se realizaron en el método de
     * ordenamiento a la hora de ordenar los datos.
     */

    public int largo = (int) (Math.random() * 20);
    private int cont = 0;
    private TextView[] txtnum;
    private int [] array;
    private Handler handler = new Handler();
    int loop = 0,n;
    private float totalTime = 0;
    private long time2;
    private long time1;
    private int minPos = 0;
    int purple = Color.parseColor("#A901DB");
    private int yellow = Color.parseColor("#F3F781");
    private int delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(largo < 10){
            if(largo < 5){
                largo += 10;
            }else {
                largo += 6;
            }
        }
        array = new int[largo];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        /**
         * Se asigna el espacio gráfico donde se "guardarán" los datos
         */
        txtnum = new TextView[42];
        txtnum[0] = (TextView)findViewById(R.id.txtnum1);
        txtnum[1] = (TextView)findViewById(R.id.txtnum2);
        txtnum[2] = (TextView)findViewById(R.id.txtnum3);
        txtnum[3] = (TextView)findViewById(R.id.txtnum4);
        txtnum[4] = (TextView)findViewById(R.id.txtnum5);
        txtnum[5] = (TextView)findViewById(R.id.txtnum6);
        txtnum[6] = (TextView)findViewById(R.id.txtnum7);
        txtnum[7] = (TextView)findViewById(R.id.txtnum8);
        txtnum[8] = (TextView)findViewById(R.id.txtnum9);
        txtnum[9] = (TextView)findViewById(R.id.txtnum10);
        txtnum[10] = (TextView)findViewById(R.id.txtnum11);
        txtnum[11] = (TextView)findViewById(R.id.txtnum12);
        txtnum[12] = (TextView)findViewById(R.id.txtnum13);
        txtnum[13] = (TextView)findViewById(R.id.txtnum14);
        txtnum[14] = (TextView)findViewById(R.id.txtnum15);
        txtnum[15] = (TextView)findViewById(R.id.txtnum16);
        txtnum[16] = (TextView)findViewById(R.id.txtnum17);
        txtnum[17] = (TextView)findViewById(R.id.txtnum18);
        txtnum[18] = (TextView)findViewById(R.id.txtnum19);
        txtnum[19] = (TextView)findViewById(R.id.txtnum20);
        txtnum[20] = (TextView)findViewById(R.id.txtnum21);
        txtnum[21] = (TextView)findViewById(R.id.txtnum22);
        txtnum[22] = (TextView)findViewById(R.id.txtnum23);
        txtnum[23] = (TextView)findViewById(R.id.txtnum24);
        txtnum[24] = (TextView)findViewById(R.id.txtnum25);
        txtnum[25] = (TextView)findViewById(R.id.txtnum26);
        txtnum[26] = (TextView)findViewById(R.id.txtnum27);
        txtnum[27] = (TextView)findViewById(R.id.txtnum28);
        txtnum[28] = (TextView)findViewById(R.id.txtnum29);
        txtnum[29] = (TextView)findViewById(R.id.txtnum30);
        txtnum[30] = (TextView)findViewById(R.id.txtnum31);
        txtnum[31] = (TextView)findViewById(R.id.txtnum32);
        txtnum[32] = (TextView)findViewById(R.id.txtnum33);
        txtnum[33] = (TextView)findViewById(R.id.txtnum34);
        txtnum[34] = (TextView)findViewById(R.id.txtnum35);
        txtnum[35] = (TextView)findViewById(R.id.txtnum36);
        txtnum[36] = (TextView)findViewById(R.id.txtnum37);
        txtnum[37] = (TextView)findViewById(R.id.txtnum38);
        txtnum[38] = (TextView)findViewById(R.id.txtnum39);
        txtnum[39] = (TextView)findViewById(R.id.txtnum40);
        txtnum[40] = (TextView)findViewById(R.id.txtnum41);
        txtnum[41] = (TextView)findViewById(R.id.txtnum42);

        /**
         * Ciclo encargado de asignar un valo aleatorio de 1 al 100 a cada posicion 
         * del array, el cual tiene un largo aleatorio entre 20 y 40 que se le asignará a
         * la constante largo.
         */
        for(int i = 0; i < largo; i++) {
            array[i] = (int)(Math.floor(Math.random()*100 +1));
        }

        /**
         * Ciclo encargado de asignar los valores del array a un nuevo array, txtnum
         * el cual se encarga de mostrar estos valores en pantalla.
         */
        for(int i = 0; i < largo; i++){
            txtnum[i].setText(String.valueOf(array[i]));
        }

        /**
         * Ciclo que se encarga de quitar los espacios sobrantes
         */
        for(int i = largo; i < 40; i++) {
            if (txtnum[i].getText().equals("u")) {
                txtnum[i].setBackgroundResource(R.drawable.dark_circle);
            }
        }


        /**
         * Método encargado de asignar una funcion al boton de Bubblesort en la pnatalla
         * donde segun la cantidad de llamados reliza una acción, (1) llama el método de ordenamiento BubbleSort
         * (2) muestra una alerta al usuario para que especifique que hacer.
         */
        ((TextView)findViewById(R.id.bsort)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cont == 0) {
                    bubblesort();
                }else{
                    alerta();
                }
            }
        });
        /**
         * Método encargado de asignar una funcion al boton de Bubblesort en la pnatalla
         * donde segun la cantidad de llamados reliza una acción, (1) llama el método de ordenamiento SelectionSort
         * (2) muestra una alerta al usuario para que especifique que hacer.
         */

        ((TextView)findViewById(R.id.ssort)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cont == 0) {
                    try {
                        selectionsort();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    alerta();
                }
            }
        });

        /**
         * Método para asignar al botón "close" en la pantalla
         * para que cierre la aplicación.
         */
        ((TextView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
    }

    /**
     * Método que genera el cuadro de alerta
     */

    public void alerta(){
        cont++;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(MainActivity.this,android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Warning")
                .setMessage("Do you wanna kill the current sorting process and initialize a new one?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cont = 0;
                        intent0();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cont++;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    /**
     * Método que reinicia los valores y la interfaz
     */
    public void intent0(){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("key", 1);
        intent.putExtras(bundle);
        MainActivity.this.finish();
        startActivity(intent);
    }


    /**
     * Método llama al método animado del ordenamiento BubbleSort.
     */
    public void bubblesort(){
        time1 = System.nanoTime();
        animationBBS(0,1);
        cont++;
    }
    /**
     * Método llama al método animado del ordenamiento SelectionSort.
     */
    public void selectionsort() throws InterruptedException {
        time1 = System.nanoTime();
        animationSLS(0,20);
        txtnum[41].setText("Min for selection sort: " + Integer.valueOf(txtnum[0].getText().toString()));
        cont++;
    }


    /**
     * Método que se encarga de realizar las animaciones que el usuario verá en pantalla al realizarse el
     * ordenamiento por medio de BubbleSort.
     * @param a
     * @param b
     */
    public void animationBBS(final int a, final int b){
        handler.postDelayed(new Runnable() {

            /**
             * Método encargado de realizar las compraciones y los cambios es el array txtnum
                 * dependiendo de si el elemento a es mayor, igual o menor al elemnto b, y de cambiar
                 * el color del fondo de los elemntos que se estan comparando para que el usuario
                 * pueda darles seguimiento, todo utilizando bubblesort.
             */
            @Override
            public void run() {
                if(Integer.valueOf(txtnum[a].getText().toString())>Integer.valueOf(txtnum[b].getText().toString())){
                    String temp = txtnum[a].getText().toString();
                    txtnum[a].setText(txtnum[b].getText().toString());
                    txtnum[b].setText(temp);
                }
                txtnum[a].setBackgroundResource(R.drawable.cyan_circle);
                txtnum[b].setBackgroundResource(R.drawable.cyan_circle);
                int j = b + 1;
                int i = a + 1;
                if(j == array.length-loop){
                    i = 0;
                    j = i + 1;
                    loop++;
                }
                if(loop < array.length-1){

                    animationBBS(i,j);
                }
            }
        },850);
        txtnum[a].setBackgroundResource(R.drawable.purple_circle);
        txtnum[b].setBackgroundResource(R.drawable.purple_circle);
        time2 = System.nanoTime();
        totalTime = ((time2-time1)/1000000000);
        txtnum[40].setText("Time:"+ totalTime + "s");
    }

    /**
     * Método que se encarga de realizar las animaciones que el usuario verá en pantalla al realizarse el
     * ordenamiento por medio de SelectionSort.
     * @param a
     * @param b
     */
    private void animationSLS(final int a, final int b) {
        if(Integer.valueOf(txtnum[a].getText().toString()) != 0){
            delay = 700;
        }

            handler.postDelayed(new Runnable() {


                /**
                 * Método encargado de realizar las compraciones y los cambios es el array txtnum
                 * dependiendo de si el elemento a es mayor, igual o menor al elemnto b, y de cambiar
                 * el color del fondo de los elemntos que se estan comparando para que el usuario
                 * pueda darles seguimiento, todo utilizando selectionsort.
                 */
                @Override
                public void run() {
                    txtnum[41].setBackgroundColor(yellow);
                    if(a < largo-1 && Integer.valueOf(txtnum[a].getText().toString()) != 0){
                        txtnum[a].setBackgroundResource(R.drawable.cyan_circle);

                        if(Integer.valueOf(txtnum[a].getText().toString()) < Integer.valueOf(txtnum[minPos].getText().toString())){
                            txtnum[41].setText("Min for selection sort: " + Integer.valueOf(txtnum[a].getText().toString()));
                            txtnum[41].setBackgroundColor(purple);
                            minPos = a;
                        }
                    }
                    int i = a + 1;
                    int j = b;

                    if(a == largo-1){
                        if(Integer.valueOf(txtnum[a].getText().toString()) != 0){
                            txtnum[a].setBackgroundResource(R.drawable.cyan_circle);
                            if(Integer.valueOf(txtnum[a].getText().toString()) < Integer.valueOf(txtnum[minPos].getText().toString())){
                                txtnum[41].setText("Min for selection sort: " + Integer.valueOf(txtnum[a].getText().toString()));
                                txtnum[41].setBackgroundColor(purple);
                                minPos = a;
                            }
                        }

                        if(b < largo + 20){
                            txtnum[b].setText(String.valueOf(txtnum[minPos].getText().toString()));
                            txtnum[b].setBackgroundResource(R.drawable.cyan_circle);
                            txtnum[minPos].setText(String.valueOf(0));
                            txtnum[minPos].setBackgroundResource(R.drawable.dark_circle);
                            minPos = remaining();
                            txtnum[41].setText("Min for selection sort: " + Integer.valueOf(txtnum[minPos].getText().toString()));
                            j++;
                            animationSLS(0, j);
                        }
                    } else if(a < largo-1){
                        animationSLS(i, j);
                    }
                }
            }, delay);
        delay = 0;
        time2 = System.nanoTime();
        totalTime = ((time2 - time1)/1000000000);
        txtnum[40].setText("Time:" + totalTime + "s");
        if(Integer.valueOf(txtnum[a].getText().toString()) != 0) {
            txtnum[a].setBackgroundResource(R.drawable.purple_circle);
        }
    }
    /**
    Metodo que analiza los elementos restantes y saca el primer elemento que encuentra
    */
    private int remaining(){
        for(int i = 0; i < largo; i++){
            if(Integer.valueOf(txtnum[i].getText().toString()) != 0){
                return i;
            }
        }
        return 0;
    }
}
