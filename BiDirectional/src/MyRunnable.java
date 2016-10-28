
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arshad
 */
class MyRunnable implements Runnable {
    static boolean flag=false;
    private int st;
    private int en;
    static ArrayList[] al;
    private String name;
    static int[] prev;
    static String[] colour;
    static int[] df; 
    static int[] db; 
    static int m,n;
    public MyRunnable(String name,int s, int e) {
        st = s;
        en = e;
        this.name=name;
        prev = new int[al.length];
        colour = new String[al.length];
        df = new int[al.length];
        db = new int[al.length];
    }
    
    public void start (){
        Thread t = new Thread (this, name);
        t.start ();
   }

    @Override
    public void run() {
        bfs();
    }
    
    public synchronized void bfs(){
        for (int u = 0; u < al.length; u++) {
            colour[u] = "W";
            prev[u] = -1;
            df[u]=-1;
            db[u] = -1;
        }
        if (Thread.currentThread().getName().equals("Forward")) {
            colour[st] = "for";
            df[st] = 0;
        }else{
            colour[st] = "back";
            db[st] = 0;
        }
        prev[st] = -1;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(st);
        
        bfs:
        while (!q.isEmpty()) {
            int u = q.poll();
            int v = 0;
            for (int s = 0; s < ((ArrayList<Integer>) al[u]).size(); s++) {
                v = ((ArrayList<Integer>) al[u]).get(s);
                if (colour[v].equals("W")) {
                     if (Thread.currentThread().getName().equals("Forward")) {
                        colour[v] = "for";
                        df[v] = df[u] + 1;
                    }else{
                        colour[v] = "back";
                        db[v] = db[u] + 1;
                    }
                    prev[v] = u;
                    q.add(v);
                }else{
                    if (Thread.currentThread().getName().equals("Forward")) {
                        if (colour[v].equals("back")) {
                            m=u;
                            n=v;
                            if (flag==false) {
                                flag=true;
                                System.out.println("Direction: "+Thread.currentThread().getName()+" City: "+city[v]+" #Roads: "+ (db[v]+1));
                            }
                            Thread.currentThread().stop();
                        }
                    }else{
                        if (colour[v].equals("for")) {
                            m=u;
                            n=v;
                            if (flag==false) {
                                flag=true;
                                System.out.println("Direction: "+Thread.currentThread().getName()+" City: "+city[v]+" #Roads: "+ (df[v]+1));
                            }
                            Thread.currentThread().stop();
                        }
                    }
                }
                if (v==en) {
                    break bfs;
                }
            }
        }
    }
    
    static int startCity;
    static int endCity;
     static String [] city;
     static int cno=0;
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner rex = new Scanner(new File("bidir.txt"));

        int ver = rex.nextInt();
        int edg = rex.nextInt();
        
        String start = rex.next();
        String end = rex.next();
        
        al = new ArrayList[ver];
        city = new String[ver];
        for (int i = 0; i < ver; i++) {
            al[i] = new ArrayList();
        }
        String a, b;
        int w=0,x=0;
        for (int c = edg; c > 1; c--) {
            a = rex.next();
            b = rex.next();
            int co=0;
            for (int i = 0; i < city.length; i++) {
                if (a.equals(city[i])) {
                    w=i;
                    co+=1;
            }else if (b.equals(city[i])) {
                 x=i;
                 co+=2;
                }
            }
            if (co==1) {
                city[cno]=b;
                x=cno;
                cno++;
            }else if (co==2) {
                city[cno]=a;
                w=cno;
                cno++;
            }else if (co==0) {
                city[cno]=a;
                w=cno;
                cno++;
                city[cno]=b;
                x=cno;
                cno++;
            }
            (al[w]).add(x);
            (al[x]).add(w);
        }

        for (int i = 0; i < al.length; i++) {
            if (city[i].equals(start)) {
                startCity=i;
            }else if (city[i].equals(end)) {
                endCity=i;
            }
        }
        
        MyRunnable forward = new MyRunnable("Forward",startCity,endCity);
        forward.start();
        
        MyRunnable backward = new MyRunnable("Backward",endCity,startCity);
        backward.start();
        while(true){
            if (flag) {
                prev[startCity] = -1;
                prev[endCity] = -1;
                int cc=0;// for length
                String ss;// for path
                ss=""+city[m];
                cc++;
                while(prev[m]!=-1){
                    ss=city[prev[m]]+"->"+ss;
                    cc++;
                    m=prev[m];
                }
                ss+="->"+city[n];
                cc++;
                while(prev[n]!=-1){
                    ss+="->"+city[prev[n]];
                    cc++;
                    n=prev[n];
                }
                System.out.println(ss);
                System.out.println("Length: "+cc);
                
                
                break;
            }
        }
        
        
    
}
    
}