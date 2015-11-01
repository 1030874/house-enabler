package com.example.rene.houseenabler.Model;

import java.util.ArrayList;


/**
 * Created by Rene on 26-10-2015.
 */
public class ChildItem {


    private int _idchild;
    private int _category;
    private String _childname;
    private String _description;
    private ParrentItem _parrentItem;


    public ChildItem(int category, String childname, String description)
    {

        this._category = category;
        this._childname = childname;
        this._description = description;
    }

    public int get_idchild() {
        return _idchild;
    }

    public void set_idchild(int _idchild) {
        this._idchild = _idchild;
    }

    public int get_category() {
        return _category;
    }

    public void set_category(int _category) {
        this._category = _category;
    }

    public String get_childname() {
        return _childname;
    }

    public void set_childname(String _childname) {
        this._childname = _childname;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public ParrentItem get_parrentItem() {
        return _parrentItem;
    }

    public void set_parrentItem(ParrentItem _parrentItem) {
        this._parrentItem = _parrentItem;
    }
}
