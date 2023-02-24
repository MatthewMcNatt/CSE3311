//Matthew McNatt
//Created: 2/24/2023
package com.example.minutemarker;

public class Note {
    private String body;
    private String title;
    //TODO: add field for for holding the notifications

    //TODO: Parse out appropriate title based on note
    public void setString(String s){
        body = s;
        if (s == null)
            title = "EMPTY";
        else{
            title = s;
        }


    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }
}
