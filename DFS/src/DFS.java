
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class DFS {

    /**
     * @param args the command line arguments
     */
    
     static ArrayList[] al;
     static String [] city;
     static String[] brR;
     static int cno=0;
     static int startCity=0,endCity=0;
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner rex = new Scanner(new File("dfs.txt"));

        int ver = rex.nextInt();
        int edg = rex.nextInt();
        
        String start = rex.next();
        String end = rex.next();
        
//        System.out.println("Start="+start+" & End="+end);
        String br=rex.next();
        int brC = Integer.parseInt(br);
        br= rex.nextLine(); //for nextLine Skipping
        brR = new String[brC];
        for (int i = 0; i < brR.length; i++) {
            brR[i]=rex.nextLine();
        }
//        System.out.println(brR[0]+"+"+brR[1]);
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
//        for (int i = 0; i < city.length; i++) {
//            System.out.println(city[i]+"="+i);
//        }

        for (int i = 0; i < al.length; i++) {
            if (city[i].equals(start)) {
                startCity=i;
            }else if (city[i].equals(end)) {
                endCity=i;
            }
        }
        
//        for (int i = 0; i < al.length; i++) {
//            System.out.println(i+"-> "+al[i]);
//        }
        DFS(al);
        
        for (int i = 0; i < sb.size(); i++) {
            if ((sb.get(i)).contains("Safe")) {
                System.out.println(sb.get(i));
            }
        }
        for (int i = 0; i < sb.size(); i++) {
            if ((sb.get(i)).contains("Broken")) {
                System.out.println(sb.get(i));
            }
        }
    }
    
    

    static ArrayList<Integer> path =new ArrayList<>();
    static int pathc;
    static ArrayList<String> sb= new ArrayList<>();
    public static void DFS(Object[] G) {

        path.add(startCity);
        pathc=path.size()-1;
        DFS_Visit(startCity);
    }

    public static void DFS_Visit(int u) {
        
        int v;
        for (int s = 0; s < ((ArrayList<Integer>) al[u]).size(); s++) {
            v = ((ArrayList<Integer>) al[u]).get(s);
                if (v!=endCity) {
                    if ( !path.contains(v)) {
//                        System.out.println(u+"->"+v);
                        path.add(v);
                        pathc=path.size()-1;
                        DFS_Visit(v);
                    }
                }else {
                    if (v==endCity) {
                        if (!path.contains(v) ) {
                            path.add(v);
                            pathc=path.size()-1;
//                            System.out.println(u+"e->"+v);
//                            System.out.println(path);
                            String ways="";
                            for (int i = 0; i < path.size(); i++) {
                                for (int j = 0; j < city.length; j++) {
                                    if (path.get(i)==j) {
                                        ways+=city[j]+" ";
                                    }
                                }
                            }
                            if (ways.contains(brR[0]) || ways.contains(brR[1])) {
                                ways="Broken: "+ways;
//                                System.out.println();
                            }else{
                                ways="Safe: "+ways;
//                                System.out.println();
                            }
                            sb.add(ways);
                           
//                            System.out.println("");
                            path.remove(pathc);
                            pathc=path.size()-1;
                            break;
                        }
                    }
                }
        }
//        if (path.contains(endCity)) {
//            System.out.println(path);
//        }
//        System.out.println("-"+ path.get(pathc));
        path.remove(pathc);
        pathc--;
//        System.out.println(pathc);
//        System.out.println(path);
        
    }
    
}
