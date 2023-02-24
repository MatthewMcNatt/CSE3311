package com.example.minutemarker;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notes_Container implements Serializable {

    //fields
    private ArrayList<Note> _notes;
    private int notes_Num;
    private static final long serialVersionUID = 1;

    public int getNotesNum(){return notes_Num;}

    //constructors
    public Notes_Container(){
        _notes = new ArrayList<>();
        notes_Num = 0;
    }

    /*
        Load Data takes a filename which it will
    try to load _notes from. The file needs no extension.
    The generics cast is
    functional and tested, but always generates warnings
    */
    @SuppressWarnings("unchecked")
    public void loadData(String fileName) {
        if(fileName == null){throw new IllegalArgumentException("Null String Entered");}
        if(fileName.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        try{
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            _notes = (ArrayList<Note>) oi.readObject();
            fi.close();
            oi.close();
            notes_Num = _notes.size();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(Exception e){
            System.out.println("ERROR LOADING DATA");
        }


    }

    /*
        Save Data takes a filename which it will
    save _notes to. The file needs no extension
    and automatically saves in the directory.
    */
    public void saveData(String fileName){
        if(fileName == null){throw new IllegalArgumentException("Null String Entered");}
        if(fileName.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        try{
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(_notes);
            o.flush();
            o.close();
            f.flush();
            f.close();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (Exception e) {
            System.out.println("ERROR SAVING DATA");
        }

    }

    //get by title
    public Note getNote(String title){
        if(title == null){throw new IllegalArgumentException("Null String Entered");}
        if(title.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        for(Note u : _notes){
            if (u.getTitle().equals(title)) return u;
        }
        return null;
    }

    /*standard get by index*/
    public Note getNote(int i){
        if(i < 0 || i > notes_Num){throw new IllegalArgumentException("Out of range index");}
        return _notes.get(i);
    }

    /*standard add and increment*/
    public void addUser(Note note){
        _notes.add(note);
        notes_Num++;
    }

    /*standard remove and decrement*/
    public void removeNote(int key){
        if(key < 0 || key > notes_Num){throw new IllegalArgumentException("Out of range index");}
        _notes.remove(key);
        notes_Num--;
    }

    /*replaces user at i with User u */
    public void editUser(int i, Note n){
        if(i < 0 || i > notes_Num){throw new IllegalArgumentException("Out of range index");}
        _notes.set(i, n);
    }
    public List<String> getNames(){
        List<String> names = new ArrayList<>();
        for(Note s :_notes){
            names.add(s.getTitle());
        }
        return names;
    }



}
