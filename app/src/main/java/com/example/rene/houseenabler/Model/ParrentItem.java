package com.example.rene.houseenabler.Model;

/**
 * Created by Rene on 26-10-2015.
 */
public class ParrentItem
{

    private  String _idparrent;
    private  String parrentname;

    public ParrentItem()
    {

    }

    public  ParrentItem(String idparrent, String parrentname)
    {
        this._idparrent = idparrent;
        this.parrentname = parrentname;

    }

    public String getParrentname() {
        return parrentname;
    }

    public void setParrentname(String parrentname) {
        this.parrentname = parrentname;
    }

    public String get_idparrent() {
        return _idparrent;
    }

    public void set_idparrent(String _idparrent) {
        this._idparrent = _idparrent;
    }
}
