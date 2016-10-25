package com.company.units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * CoordiList is to hold a list of possible moves.
 * Each move tells row, col position and if it is an attacking move.
 */

public class CoordiList {
    private CoordiNode head;
    private CoordiNode tail;
    private int listSize;

    private class CoordiNode{
        private int row;
        private int col;
        private boolean attacking;
        public CoordiNode next;

        public CoordiNode(int row, int col){
            this.row = row;
            this.col = col;
            this.attacking = false;
        }
        public CoordiNode(int row, int col, boolean attacking){
            this.row = row;
            this.col = col;
            this.attacking = attacking;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
        public boolean isAttacking() { return attacking; }
    }

    public CoordiList(){
        head = null;
        tail = null;
        listSize = 0;
    }

    //print the whole list.
    public void printList(){
        if(head == null){
            System.out.println("No possible move.");
        }
        CoordiNode curr = head;
        while(curr != null){
            System.out.println("(" + curr.getRow() + ", " + curr.getCol() + ")");
            curr = curr.next;
        }

    }

    /**
     *
     * @param index
     * @return int[2] result;
     * result[0] = row position.
     * result[1] = col position.
     * result[2] = 0 if the move is not an attacking move. 1  if the move is an attacking move.
     */
    public int[] pickNode(int index){
        if(index < 0 || index > listSize)
            return null;

        int[] result = new int[3];
        result[0] = 0;
        result[1] = 0;
        result[2] = 0;
        if(index < 0 || index >= listSize){
            return result;
        }

        CoordiNode curr = head;
        for(int i = 0; i < listSize; i++){
            if(i == index){
                result[0] = curr.getRow();
                result[1] = curr.getCol();
                if(curr.isAttacking())
                    result[2] = 1;
                break;
            }
            curr = curr.next;
        }
        return result;
    }

    //add an element to the list. (at tail)
    public boolean add(int row, int col){
        //return false if (row, col) is invalid.
        if(row < 0 || row > 7 || col < 0 || col > 7)
            return false;

        CoordiNode node = new CoordiNode(row, col);
        //if there is no node.
        if(tail == null){
            head = node;
            tail = node;
        }
        else{
            tail.next = node;
            tail = tail.next;
        }
        listSize++;
        return true;
    }

    public boolean add(int row, int col, boolean attacking){
        //return false if (row, col) is invalid.
        if(row < 0 || row > 7 || col < 0 || col > 7)
            return false;

        CoordiNode node = new CoordiNode(row, col, attacking);
        //if there is no node.
        if(tail == null){
            head = node;
            tail = node;

        }
        else{
            tail.next = node;
            tail = tail.next;
        }
        listSize++;
        return true;
    }

    public boolean isFound(final int row, final int col){
        //return false if (row, col) is invalid.
        if(row < 0 || row > 7 || col < 0 || col > 7)
            return false;

        if(head == null)
            return false;

        CoordiNode curr = head;
        for(int i = 0; i < listSize; i++){
            if(curr.getRow() == row && curr.getCol() == col)
                return true;
            curr = curr.next;
        }
        return false;
    }

    public int getListSize(){ return listSize; }
}
