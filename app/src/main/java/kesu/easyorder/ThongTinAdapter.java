package kesu.easyorder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thinhle on 9/22/17.
 */

public class ThongTinAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonAn> monAnList;

    public ThongTinAdapter(Context context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        TextView tvTen = (TextView) view.findViewById(R.id.tv_ten);
        TextView tvSoLuong = (TextView) view.findViewById(R.id.tv_so_luong);
        final TextView tvTrangThai = (TextView) view.findViewById(R.id.tv_trang_thai);
        Button btnXoa = (Button) view.findViewById(R.id.btn_xoa);

        MonAn monAn = monAnList.get(i);

        tvTen.setText(monAn.getTen());
        tvSoLuong.setText(String.valueOf(monAn.getSl()));
        if (monAn.getState() == 0)
        {
            tvTrangThai.setText("Mới gọi");
        }
        else if (monAnList.get(i).getState() == 1)
        {
            tvTrangThai.setText("Đang làm");
        }
        else if (monAnList.get(i).getState() == 2)
        {
            tvTrangThai.setText("Đang mang ra");
        }
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTrangThai.getText().equals("Mới gọi"))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xoá món ");
                    builder.setMessage("Bạn có chắc chắn muốn xoá món này?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //////do something
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
                else
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Không thể xoá");
                    builder.setMessage("Món ăn đang được chuẩn bị nên không thể xoá");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });
        return view;
    }
}
