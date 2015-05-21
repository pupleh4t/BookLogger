package id.co.technomotion.booklogger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.*;

import static android.R.layout.*;

public class MainActivity extends ActionBarActivity {
    Button btnSimpan;
    ListView listViewBook;
    EditText editTextInput, editPengarang, editHalaman;

    //menginisiasi arraylist yang akan digunakan untuk menyimpan daftar judul buku
    //ArrayList<String> listOfBook=new ArrayList<>();
    ArrayList<Buku> listOfBook = new ArrayList<>();

    //mendeklarasikan arrayadapter
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewBook= (ListView) findViewById(R.id.listView_output);
        editTextInput= (EditText) findViewById(R.id.inputJudul);
        editPengarang = (EditText) findViewById(R.id.inputPengarang);
        editHalaman = (EditText) findViewById(R.id.inputHalaman);
        btnSimpan= (Button) findViewById(R.id.btn_simpan);

        //menyiapkan data
        listOfBook.add(new Buku("Laskar Pelangi", "Andrea Hirata", "15"));
        listOfBook.add(new Buku("Naruto", "Masashi", "200"));
        /*listOfBook.add("5 cm");
        listOfBook.add("Ayat ayat cinta");
        listOfBook.add("Lima Menara");
        listOfBook.add("Tutorial Pemrograman Android");*/

        //meng-inisiasi arrayadapter
        final ListBukuAdapter adapterX = new ListBukuAdapter(this, listOfBook);
        listViewBook.setAdapter(adapterX);

        //mengaktifkan fungsi onItemClickListener dan onItemLongClickListener
        listViewBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //something happen
                Buku clickedItem= (Buku) parent.getAdapter().getItem(position);
                //Log.d("booklogger",clickedItem);
            }
        });

        listViewBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //something happen
                Buku longClickedItem= (Buku) parent.getAdapter().getItem(position);
                //Log.d("booklogger",longClickedItem);
                showDeleteDialog(longClickedItem, adapterX);
                return false;
            }
        });

        // mengaktifkan fungsi button simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //something happen if user click this button
                String title = editTextInput.getText().toString();
                String author = editPengarang.getText().toString();
                String page = editHalaman.getText().toString();
                // dilakukan check untuk memastikan bahwa user telah menulis judul buku
                if(!title.isEmpty()){
                    // menambahkan judul buku kedalam listOfBook
                    listOfBook.add(new Buku(title,author,page));
                    // meng-update listview
                    adapterX.notifyDataSetChanged();
                    // clear edittext
                    editTextInput.setText("");
                    editPengarang.setText("");
                    editHalaman.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"judul buku waji diisi",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // mmbuat alert dialog untuk fungsi hapus buku
    private void showDeleteDialog(final Buku bookTitle, final ListBukuAdapter adapterXX){
        AlertDialog.Builder deleteDialog=new AlertDialog.Builder(this);
        deleteDialog.setMessage("Anda yakin untuk menghapus \n"+bookTitle.getJudulBuku()+"?");
        deleteDialog.setPositiveButton("Ya",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                listOfBook.remove(bookTitle);
                // setelah menghapus, kita perlu meng-update listview
                adapterXX.notifyDataSetChanged();
            }
        });
        deleteDialog.setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        deleteDialog.show();
    }
}
