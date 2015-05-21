package id.co.technomotion.booklogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adi Prasetyo on 5/21/2015.
 */
public class ListBukuAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    protected ArrayList<Buku> listBuku;

    public  ListBukuAdapter(Context context, ArrayList<Buku> listBuku){
        this.listBuku = listBuku;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBuku.size();
    }

    @Override
    public Buku getItem(int position) {
        return listBuku.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_list_view,
                    parent, false);

            holder.txtJudul = (TextView) convertView
                    .findViewById(R.id.judul);
            holder.txtPengarang = (TextView) convertView
                    .findViewById(R.id.pengarang);
            holder.txtHalaman = (TextView) convertView
                    .findViewById(R.id.halaman);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Buku car = listBuku.get(position);
        holder.txtJudul.setText(car.getJudulBuku());
        holder.txtPengarang.setText("Nama Pengarang : " + car.getNamaPengarang());
        holder.txtHalaman.setText("Jumlah Halaman : " + car.getJumlahHalaman());

        return convertView;
    }


    private class ViewHolder {
        TextView txtJudul;
        TextView txtPengarang;
        TextView txtHalaman;
    }
}
