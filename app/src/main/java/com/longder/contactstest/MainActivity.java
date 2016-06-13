package com.longder.contactstest;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    //数组适配器
    private ArrayAdapter<String> adapter;
    //存储联系人姓名的集合
    private List<String> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        listView.setAdapter(adapter);
        readContacts();
    }

    /**
     * 读取联系人数据
     */
    private void readContacts() {
        Cursor cursor = null;
        try {
            /**
             * 使用getContentResolver获取对象后调用query方法进行数据查询
             * query()方法第一个参数是Uri类型，这里使用的是系统常量，标识要访问联系人列表的数据
             * 后几个参数是查询条件等，类似SQL查询
             * 返回结果还是一个游标对象
             */
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            assert cursor != null;
            //遍历游标
            while (cursor.moveToNext()) {
                /**
                 * 从cursor中获取数据，仍然使用getXXX方法，其中传递列的index
                 * 列的index可以通过getColumnIndex方法获取，传递的也是系统提供的常量
                 *标识的是哪一列（比如：联系人姓名还是联系人电话）
                 */
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(name + "  " + phone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
