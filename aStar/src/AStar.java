
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
public class AStar {

    /**
     * @param args the command line arguments
     */
    static ArrayList[] al;
    static int ver=0;
    static int edg=0;
    static String path="";
    static int s=0,en=0;
    static String[] location;
    static double [] h;
    static double[] h1;
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner rex = new Scanner(new File("book.txt"));

        ver = rex.nextInt();
        edg = rex.nextInt();
        al = new ArrayList[ver];
        
        for (int i = 0; i < ver; i++) {
            al[i] = new ArrayList();
        }
        String start = rex.next();
        String end = rex.next();
        
        String a, b;
        Double m;
        
        location = new String[ver];
        h = new double[ver];
        int cno=0;
        double [][] wei = new double[ver][ver];
        int w=0,x=0;
        for (int c = edg; c > 0; c--) {
            a = rex.next();
            b = rex.next();
            int co=0;
            for (int i = 0; i < location.length; i++) {
                if (a.equals(location[i])) {
                    w=i;
                    co+=1;
            }else if (b.equals(location[i])) {
                 x=i;
                 co+=2;
                }
            }
            if (co==1) {
                location[cno]=b;
                x=cno;
                cno++;
            }else if (co==2) {
                location[cno]=a;
                w=cno;
                cno++;
            }else if (co==0) {
                location[cno]=a;
                w=cno;
                cno++;
                location[cno]=b;
                x=cno;
                cno++;
            }
            m = Double.parseDouble(rex.next());
            (al[w]).add(x);
            wei[w][x] = m;
        }
        while(rex.hasNext()){
            a = rex.next();
//            System.out.println(a+"=");
            double e= Double.parseDouble(rex.next());
//            System.out.println(e);
            for (int i = 0; i < location.length; i++) {
                if (location[i].equals(a)) {
                    h[i]=e;
//                    System.out.println("dd"+h[i]);
                }
            }
        }
//        for (int i = 0; i < h.length; i++) {
//            System.out.println("f"+h[i]);
//        }
        h1=new double[h.length];
        for (int i = 0; i < h1.length; i++) {
            h1[i]=Double.MAX_VALUE;
        }
        
        
        for (int i = 0; i < location.length; i++) {
            if (start.equals(location[i])) {
                s=i;
            }else if(end.equals(location[i])){
                en=i;
            }
        }
//        System.out.println("end "+en);
        
        dijkstra(al, wei, 0);
        
//        for (int i = 0; i < location.length; i++) {
//            System.out.println(location[i]+" "+d[en]);
//        }
//        System.out.println(h[en]);
ArrayList<String> out= new ArrayList<String>();
out.add(location[en]);

int des=en;
//System.out.print(location[en]);
for (int i = 0; i < prev.length; i++) {
            if (prev[des]!=-1) {
//        System.out.print(location[prev[des]]);
out.add(location[prev[des]]);
            des=prev[des];
    }
        }
        for (int i = out.size()-1; i >= 0; i--) {
            System.out.print(out.get(i) + "->");
        }
        System.out.println("end");

        double hu=d[en]+h[en];
        System.out.println(" f(n)= "+ hu);
        System.out.println("");
//        for (int i = 0; i < location.length; i++) {
//            System.out.print(location[i]+" ");
//        }
//        System.out.println("");
//        for (int i = 0; i < h1.length; i++) {
//            System.out.print(h1[i]+" ");
//        }
    }
    
    static double[] d;
    static int[] prev;
    public static boolean dijkstra(ArrayList[] G, double[][] w, int s) {
        d = new double[G.length];
        prev = new int[G.length];
        for (int i = 0; i < G.length; i++) {
            d[i] = Double.MAX_VALUE;
            prev[i] = -1;
        }
        ArrayList<Integer> q = new ArrayList();
        for (int i = 0; i < G.length; i++) {
            q.add(i);
        }
        d[s] = 0;
        
        while (!q.isEmpty()) {
            int u= mindistance(d,q);
//            System.out.print(location[u]+"->");
            if (u!=en) {
                q.remove(q.indexOf(u));
//                System.out.println(q);
                for (int j = 0; j < G[u].size(); j++) {
                    int v = (int) G[u].get(j);
//                    System.out.println("d"+v);
                    Relax(u, v, w);
                }
            }else{
//                System.out.print("end");
                q.remove(q.indexOf(u));
                break;
            }
            
        }
//        System.out.println("");
        
        return true;
    }
    
    public static void Relax(int u, int v, double[][] w) {
        if (d[v] > d[u] + w[u][v]) {
            d[v] = d[u] + w[u][v];
            h1[v] = d[u] + w[u][v] + h[v];
            prev[v] = u;
        }
    
    }
    
    public static int mindistance(double [] d,ArrayList q){
            int u=0;
            double min=Double.MAX_VALUE;
//            for (int i = 0; i < h1.length; i++) {
//                System.out.println(h1[i]);
//        }System.out.println(q);
            for (int i = 0; i < h1.length; i++) {
                
                if (q.contains(i) && h1[i] < min) {
                    u=i;
                    min=h1[i];
//                    System.out.println(i);
                }
            }
//            System.out.println("small"+u+" "+h1[u]);
            return u;
    }
}
