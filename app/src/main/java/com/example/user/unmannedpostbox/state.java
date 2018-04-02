package com.example.user.unmannedpostbox;

class state {
    private int flag=0; // 0: open, 1:close

    public int getFlag() {
        return flag;
    }

    public void switchState(){
        if(flag==1) flag=0;
        else flag=1;
    }
}
