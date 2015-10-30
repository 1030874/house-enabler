package com.example.rene.houseenabler.Model;

/**
 * Created by Rene on 26-10-2015.
 */
public class ParrentItem
{

    private  int _idparrent;
    private  String _parrentname;

    public ParrentItem()
    {

    }

    public ParrentItem(String parrentname)
    {

        this._parrentname = parrentname;

    }

    public String get_Parrentname() {
        return _parrentname;
    }

    public void set_Parrentname(String parrentname) {
        this._parrentname = parrentname;
    }

    public int get_idparrent() {
        return _idparrent;
    }

    public void set_idparrent(int _idparrent) {
        this._idparrent = _idparrent;
    }
}
