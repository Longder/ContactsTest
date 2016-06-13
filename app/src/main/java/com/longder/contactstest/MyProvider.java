package com.longder.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * 自定义的内容提供器
 * Created by Longder on 2016/6/13.
 */
public class MyProvider extends ContentProvider {
    /**
     * 声明一组常量用来匹配不同的uri
     */
    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    public static UriMatcher uriMatcher;

    static {
        /**
         * 指定匹配的规则
         */
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.longder.app.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.longder.app.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.longder.app.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.longder.app.provider", "table2/#", TABLE2_ITEM);
    }

    /**
     * 初始化ContentProvider时调用，返回true代表初始化成功，返回false表示失败。
     * 只有当存在ContentResolver尝试访问程序中的数据时，ContentProvider才会被初始化。
     *
     * @return
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 从ContentProvider中查询数据。
     *
     * @param uri           用来标识查询哪张表
     * @param projection    定义结果集的列
     * @param selection     查询条件
     * @param selectionArgs 查询条件参数
     * @param sortOrder     排序
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        /**
         * 查询前先使用match方法进行匹配，返回值是之前配置好的常量。
         * 进而可以进行分支判断操作。
         */
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                break;
            case TABLE1_ITEM:
                break;
            case TABLE2_DIR:
                break;
            case TABLE2_ITEM:
                break;
        }
        return null;
    }

    /**
     * 根据传入的uri返回相应的MIME类型
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.longder.app.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.longder.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.longder.app.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.longder.app.provider.table2";
            default:
                return null;
        }
    }

    /**
     * 向ContentProvider中添加一条数据
     *
     * @param uri    确定添加到哪张表
     * @param values 待添加的数据
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    /**
     * 从ContentProvider中删除数据
     *
     * @param uri           确定删除哪张表
     * @param selection     删除条件
     * @param selectionArgs 删除条件参数
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新ContentProvider中已有的数据
     *
     * @param uri           确定更新哪张表
     * @param values        待更新的数据
     * @param selection     更新条件
     * @param selectionArgs 更新条件参数
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
