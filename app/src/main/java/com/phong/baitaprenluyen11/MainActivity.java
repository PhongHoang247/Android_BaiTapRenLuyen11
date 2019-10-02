package com.phong.baitaprenluyen11;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.phong.model.SanPham;

public class MainActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayAdapter<SanPham> sanPhamArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SanPham sp = sanPhamArrayAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this,ChiTietActivity.class);
                intent.putExtra("SANPHAM",sp);
                startActivityForResult(intent,113);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==113 && resultCode == 115)
        {
            boolean isXoa = data.getBooleanExtra("XacNhanXoa",false);
            SanPham spXoa = (SanPham) data.getSerializableExtra("SANPHAM");
            for (int i = 0; i < sanPhamArrayAdapter.getCount(); i++)
            {
                SanPham sp = sanPhamArrayAdapter.getItem(i);
                if (sp.getMa().equals(spXoa.getMa()))
                {
                    sanPhamArrayAdapter.remove(sp);
                    break;
                }
            }
        }
    }

    private void addControls() {
        lvSanPham = (ListView) findViewById(R.id.lvSanPham);
        sanPhamArrayAdapter = new ArrayAdapter<SanPham>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(sanPhamArrayAdapter);
        //Giả lập sản phẩm để đẩy vào ListView:
        sanPhamArrayAdapter.add(new SanPham("sp1","Cocacola",15000));
        sanPhamArrayAdapter.add(new SanPham("sp2","Pepsi",20000));
        sanPhamArrayAdapter.add(new SanPham("sp3","Redbull",10000));
        sanPhamArrayAdapter.add(new SanPham("sp4","Yellowcow",18000));
        sanPhamArrayAdapter.add(new SanPham("sp5","Milk",10000));
    }
}
