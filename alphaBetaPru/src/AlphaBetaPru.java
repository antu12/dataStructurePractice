
import java.io.File;
import java.io.FileNotFoundException;
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

public class AlphaBetaPru {

    /**
     * @param args the command line arguments
     */
    
    static int [] tree;
    static int branches;
    static int prunned=0;
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner rex = new Scanner(new File("abp.txt"));
        int turns = Integer.parseInt(rex.next());
        int depth = 2 * turns;
        branches =  Integer.parseInt(rex.next());;
        int nodes=(int)(Math.pow(branches, (depth+1))-1)/(branches-1); // (branches^(depth+1))-1
        int min = Integer.parseInt(rex.next());
        int max = Integer.parseInt(rex.next());
        
        tree = new int[nodes];
        int leaves = (int)Math.pow(branches, depth); // branches^depth
        
        System.out.println("Depth: "+depth);
        System.out.println("Branch: "+branches);
        System.out.println("Terminal States: "+leaves);
        
        String str = "";
        for (int i = nodes-1; i>=(nodes-leaves); i--) {
           tree[i]=min + (int)(Math.random() * ((max - min) + 1));
           str=tree[i]+" "+str;
        }
        System.out.println("leaves values: "+ str);
        
        int maxAmount=alphaBeta(0, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        if ( maxAmount != Integer.MIN_VALUE) {
            System.out.println("Maximum Amount: "+ maxAmount);
        }else{
            System.out.println("No Solution");
        }
        
        System.out.println("Comparisons: "+ leaves);
        System.out.println("Prunned: "+ prunned);
         
    }
    
    public static int alphaBeta(int node, int depth, int alpha, int beta, boolean maximizing){
        if(depth == 0){  
            return tree[node];
        }
        if(maximizing){
            int v = Integer.MIN_VALUE;
            int childs = (branches*node)+1; // getting the array index of child nodes
            int i = 0;
            while(i < branches) {
                v = (int)Math.max(v, alphaBeta(childs, depth-1, alpha , beta, false));
                alpha = (int)Math.max(alpha, v);
                
                if(beta <= alpha){
                    prunned+= branches - (i+1);
                    break;
                }
                childs++;
                i++;
            }
            return v; 
        }
         if(!maximizing){
            int v = Integer.MAX_VALUE;
            int childs = (branches*node)+1; // getting the array index of child nodes
            int i = 0;
            while(i < branches){
                v = (int)Math.min(v, alphaBeta(childs, depth-1, alpha , beta, true));
                beta = (int)Math.min(beta, v);
                
                if(beta <= alpha){
                    prunned+= branches - (i+1);
                    break;
                }
                childs++;
                i++;
            }
            return v; 
        }
         
        return Integer.MIN_VALUE;
    }

    
}
