package com.example.user.unmannedpostbox;

class state {
    private int flag=0; // 0: open, 1:close
    private static String ip="http://192.168.1.79/";

    public void setIP(String i){
        ip=i;
    }

    public int getFlag() {
        return flag;
    }

    public void switchState(){
        if(flag==1) flag=0;
        else flag=1;
    }
}
