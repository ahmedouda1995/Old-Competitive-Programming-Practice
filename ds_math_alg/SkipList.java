package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class SkipList {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n, v;
        char c;
        n = sc.nextInt();

        SkipList list = new SkipList();
        while(n-- > 0)
        {
            c = sc.nextChar();
            switch (c) {
                case 'i':
                    v = sc.nextInt();
                    if(!list.find(v))
                        list.insert(v);
                    break;
                case 'd':
                    list.delete(sc.nextInt());
                    break;
                case 's':
                    out.println(list.find(sc.nextInt())?"YES":"NO");
                    break;
            }
            //list.print();
        }
        out.flush();
    }

    static final Random rand = new Random();
    Node head;
    int levels;

    public SkipList() {
        levels = 0;
        head = new Node(null, 0, null);
        head.down = head;
    }

    class Node
    {
        Integer val, level;
        Node next, down;

        public Node(Integer val, Integer level, Node down)
        {
            this.val = val;
            this.level = level;
            this.down = down;
            this.next = null;
        }
    }

    void print()
    {
        Node cur = head;
        int curLevel = cur.level;
        Node start = cur;

        while(curLevel-- >= 0)
        {
            System.out.println("======================");
            while(cur != null)
            {
                System.out.print("(" + cur.val + "," + cur.level + ") ");
                cur = cur.next;
            }
            System.out.println();
            System.out.println("======================");
            start = cur = start.down;
        }
    }

    void insert(Integer X)
    {
        int putInLevels = getLevels();

        Node cur = head;
        for(int i = cur.level + 1; i <= putInLevels; ++i)
            cur = new Node(null, i, cur);

        Node prevUp = null;
        head = cur;

        int curLevel = cur.level;

        while(curLevel >= 0)
        {
            while(cur.next != null && (int)cur.next.val < (int)X)
                cur = cur.next;
            if(curLevel <=  putInLevels)
            {
                Node newNode = new Node(X, curLevel, null);
                newNode.down = newNode;
                if(prevUp != null) prevUp.down = newNode;
                newNode.next = cur.next;
                prevUp = cur.next = newNode;
            }
            cur = cur.down;
            curLevel--;
        }
    }

    void delete(Integer X)
    {
        Node cur = head;
        int curLevel = cur.level;

        while(curLevel-- >= 0)
        {
            while(cur.next != null && (int)cur.next.val < (int)X)
                cur = cur.next;
            if(cur.next != null && (int)cur.next.val == (int)X)
                cur.next = cur.next.next;
            cur = cur.down;
        }
    }

    boolean find(Integer  X)
    {
        Node cur = head;
        int curLevel = cur.level;

        while(curLevel-- >= 0)
        {
            while(cur.next != null && (int)cur.next.val <= (int)X)
                cur = cur.next;
            cur = cur.down;
        }
        return cur != null && cur.val != null && (int)cur.val == (int)X;
    }

    int getLevels()
    {
        int lev = 0;
        while(lev <= levels && rand.nextInt(2) == 0)
            lev++;
        levels = Math.max(levels, lev);
        return lev;
    }

    static class Scanner
    {
        StringTokenizer st; BufferedReader br;
        Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
        Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken(); }
        String nextLine()throws IOException{return br.readLine();}
        int nextInt() throws IOException {return Integer.parseInt(next());}
        double nextDouble() throws IOException {return Double.parseDouble(next());}
        char nextChar()throws IOException{return next().charAt(0);}
        Long nextLong()throws IOException{return Long.parseLong(next());}
        boolean ready() throws IOException{return br.ready();}
    }
}