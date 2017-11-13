package kesu.easyorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
        if(!tb.getState()) {
            btn.setBackgroundResource(R.drawable.ban_xanh);
        } else btn.setBackgroundResource(R.drawable.ban_do);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
                if (!tb.getState()){ //Bàn vẫn còn trống
                    ProgressDialog dialog = new ProgressDialog(context);
                    dialog.setMessage("Đang chọn bàn");
                    dialog.setCancelable(false);
                    tb.setState(true);
                    tb.setYeuCauThanhToan(YeuCauThanhToan.CHUA_YEU_CAU);
                    mDatabaseReference.child("danhSachBanAn").child("ban"+tb.getBanSo()).setValue(tb);
                    SetInforActivity.banSo=tb.getBanSo();
                    dialog.dismiss();
                    Intent intent = new Intent(context,SelectionActivity.class);
                    context.startActivity(intent);
                } else{
                    Toast.makeText(context, "Bàn đã có người ngồi, vui lòng chọn bàn khác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
