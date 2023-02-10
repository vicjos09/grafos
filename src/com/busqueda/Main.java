package com.busqueda;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int vertices=4;
    static List<Integer>[] adjacencyList;
    public static void main(String[] args) {
        List<Integer> nodos=new ArrayList<>();
        List<Integer> edges=new ArrayList<>();
        List<Integer> l=new ArrayList<>();
        List<Integer> r=new ArrayList<>();

        int vertices=0;

        int flag=0;
        try {
            File file = new File("C:\\Users\\Joseph\\Documents\\IntelliJ\\BusquedaAProfundidadConLector\\Grafo_no_conexo.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                if (flag==1){
                    nodos=regresarNodos(data);
                    flag=0;
                }

                if (flag==2){
                    edges=regresarPuntos(data);
                    l.add(edges.get(0));
                    r.add(edges.get(1));

                }
                if(data.equals("Nodes:")) flag=1;
                if(data.equals("Edges:")) flag=2;
            }
            myReader.close();
            int numVertices=nodos.size();
            List<Integer>[] listaAdja=new ArrayList[numVertices];
            for (int i=0;i<numVertices;i++){
                listaAdja[i] = new ArrayList<>();
            }
            boolean[] visited = new boolean[numVertices];
            for (int i=0;i<l.size();i++){
                listaAdja[l.get(i)].add(r.get(i));
            }
            DFSUtil(0, visited,listaAdja);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



    }
    static List<Integer> regresarPuntos(String datos){
        int size=datos.length();
        int contador=0;
        String subirDato="";
        List<Integer> edges= new ArrayList<Integer>();
        int i=0;

        while (i<size){
            contador=i;
            subirDato="";
            if ((datos.charAt(i)==' ' && (i==4 || i==5) ) || datos.charAt(i)=='('){

                while (datos.charAt(contador)!=',' && datos.charAt(contador)!=')' && contador<size){

                    if(datos.charAt(contador)!='(') {
                        subirDato=subirDato+datos.charAt(contador);

                    }

                    contador++;
                }
                edges.add(Integer.valueOf(subirDato.trim()));
            }
            i++;

        }

        return edges;
    }
    static  List<Integer> regresarNodos(String datos){
        //Funcion que regresa nodos
        int len=datos.length();
        int i=0;
        int contador=0;
        String subirdato="";
        List<Integer> nodos=new ArrayList<>();

        while(i<len){

            contador=i;
            subirdato="";
            if(datos.charAt(i)==' ' || datos.charAt(i)=='(' ){

                while (datos.charAt(contador)!=',' && contador<len-1){

                         if(datos.charAt(contador)!='(') subirdato=subirdato+datos.charAt(contador);
                         contador++;
                }
                nodos.add(Integer.valueOf(subirdato.trim()));
            }
            i++;
        }
        return nodos;
    }
    static void DFSUtil(int vertex, boolean[] visited, List<Integer>[] lista) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for(int neighbor : lista[vertex]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited,lista);
            }
        }
    }
}