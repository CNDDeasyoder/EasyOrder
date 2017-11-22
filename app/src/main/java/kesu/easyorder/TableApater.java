package kesu.easyorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Tonqt Thonq on 11/3/2017.
 */

public class TableApater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BanAn> List;
    public TableApater(Context context, int layout, List<BanAn> list) {
        this.context = context;
        this.layout = layout;
        this.List = list;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        Button btn = (Button)view.findViewById(R.id.btn_table);
        final BanAn tb = List.get(i);
        btn.setText(""+String.valueOf(tb.getBanSo()));
        if(tb.getState()==0) {
            btn.setBackgroundResource(R.drawable.ban_xanh);
        } else if (tb.getState()==1) btn.setBackgroundResource(R.drawable.ban_do);
        else if (tb.getState()==2) btn.setBackgroundResource(R.drawable.ban_vang);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
                if (tb.getState()==0){ //Bàn vẫn còn trống
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Chọn bàn");
                    builder.setMessage("Bạn có chắc chắn muốn chọn bàn này không?");
                    builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProgressDialog dialog1 = new ProgressDialog(context);
                            dialog1.setMessage("Đang chọn bàn");
                            dialog1.setCancelable(false);
                            tb.setState(1);
                            mDatabaseReference.child("danhSachBanAn").child("ban"+tb.getBanSo()).child("state").setValue(1);
                            mDatabaseReference.child("danhSachBanAn").child("ban"+tb.getBanSo())
                                    .child("khachHang").child("tenKhachHang").setValue(tb.getKhachHang().getTenKhachHang());
                            SetInforActivity.banSo=tb.getBanSo();
                            dialog1.dismiss();
                            Intent intent = new Intent(context,SelectionActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();

                } else{
                    Toast.makeText(context, "Bàn đã có người ngồi, vui lòng chọn bàn khác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
