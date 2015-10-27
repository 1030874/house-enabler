package com.example.rene.houseenabler.View;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.rene.houseenabler.Model.ParrentItem;
import com.example.rene.houseenabler.Model.ChildItem;
import com.example.rene.houseenabler.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Rene on 26-10-2015.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter
{
    private Context context;
    private List<ParrentItem> listParrents;
    private HashMap<ParrentItem, List<ChildItem>> listChilds;
    private LayoutInflater inflater;

    public ExpandableAdapter(Context context, List<ParrentItem> listParrentts, HashMap<ParrentItem, List<ChildItem>> listChilds)
    {
        this.context = context;
        this.listParrents = listParrentts;
        this.listChilds = listChilds;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public ExpandableAdapter()
    {
        super();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String childText = (String) getChild(groupPosition, childPosition);


        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_child, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        return convertView;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_parrent, null);

        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;

    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {

        return childPosition;

    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return this.listChilds.get(this.listParrents.get(groupPosition)).get(childPosition);

    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this.listParrents.get(groupPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return 0;
    }

    @Override
    public int getGroupCount()
    {
        return this.listParrents.size();
    }


}
