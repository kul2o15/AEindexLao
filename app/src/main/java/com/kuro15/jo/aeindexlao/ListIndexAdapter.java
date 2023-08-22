package com.kuro15.jo.aeindexlao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jo on 06-05-17.
 */

public class ListIndexAdapter extends BaseAdapter {
    private Context mcoContext;
    private List<LaoIndexModel> mLaoIndexModelList;

    public ListIndexAdapter(Context mcoContext, List<LaoIndexModel> mLaoIndexModelList) {
        this.mcoContext = mcoContext;
        this.mLaoIndexModelList = mLaoIndexModelList;
    }

    @Override
    public int getCount() {
        return mLaoIndexModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mLaoIndexModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mLaoIndexModelList.get(position).getIndexID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mcoContext,R.layout.item_listview, null);
        TextView IDae = (TextView) v.findViewById(R.id.ae_id);
        TextView Nameae = (TextView) v.findViewById(R.id.ae_lao);
        TextView Engae = (TextView) v.findViewById(R.id.ae_eng);
        IDae.setText(String.valueOf(mLaoIndexModelList.get(position).getIndexID()));
        Nameae.setText(mLaoIndexModelList.get(position).getIndexName());
        Engae.setText(mLaoIndexModelList.get(position).getIndexEng());
        return v;
    }
}
