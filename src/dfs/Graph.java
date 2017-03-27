package dfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//A Java program to print topological sorting of a graph
//using indegrees
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

//Class to represent a graph
class Graph
{
 int V;// No. of vertices
  
 //An Array of List which contains 
 //references to the Adjacency List of 
 //each vertex
 List <Integer> adj[];
 public Graph(int V)// Constructor
 {
     this.V = V;
     adj = new ArrayList[V];
     for(int i = 0; i < V; i++)
         adj[i]=new ArrayList<Integer>();
 }
  
 // function to add an edge to graph
 public void addEdge(int u,int v)
 {
     adj[u].add(v);
 }
 // prints a Topological Sort of the complete graph  
 public void topologicalSort()
 {
	 Double  t1,t2,tempoExecucao =(double) 0;

	 t1 = (double) System.currentTimeMillis();
	 Integer NUM_EXECUCOES = 0;
	 
     // Create a array to store indegrees of all
     // vertices. Initialize all indegrees as 0.
     int indegree[] = new int[V];
      
     // Traverse adjacency lists to fill indegrees of
     // vertices. This step takes O(V+E) time        
     for(int i = 0; i < V; i++)
     {
         ArrayList<Integer> temp = (ArrayList<Integer>) adj[i];
         for(int node : temp)
         {
             indegree[node]++;
         }
     }
      
     // Create a queue and enqueue all vertices with
     // indegree 0
     Queue<Integer> q = new LinkedList<Integer>();
     for(int i = 0;i < V; i++)
     {
         if(indegree[i]==0)
             q.add(i);
     }
      
     // Initialize count of visited vertices
     int cnt = 0;
      
     // Create a vector to store result (A topological
     // ordering of the vertices)
     Vector <Integer> topOrder=new Vector<Integer>();
     while(!q.isEmpty())
     {
         // Extract front of queue (or perform dequeue)
         // and add it to topological order
         int u=q.poll();
         topOrder.add(u);
          
         // Iterate through all its neighbouring nodes
         // of dequeued node u and decrease their in-degree
         // by 1
         for(int node : adj[u])
         {
             // If in-degree becomes zero, add it to queue
             if(--indegree[node] == 0)
                 q.add(node);
         }
         NUM_EXECUCOES++;
         cnt++;
     }
     
      
      
     // Check if there was a cycle       
     if(cnt != V)
     {
         System.out.println("There exists a cycle in the graph");
         return ;
     }
      
     // Print topological order          
     for(int i : topOrder)
     {
         System.out.print(i+" ");
     }
     
     t2 = (double) System.currentTimeMillis();
     
     tempoExecucao = (t2-t1)/1000;  // calcula o tempo de execucao (em segundos)

     System.out.printf("\nTempo de Execucao: %12.9f segundos\n",tempoExecucao);

 }
}
//Driver program to test above functions
class Main
{
	public static void main(String args[])
    {

		Graph g = null;
		
        //String name = "C:\\Users\\marcelo.silva\\Downloads\\largeG.txt";
        String name = "C:\\Users\\marcelo.silva\\Downloads\\mediumG.txt";
        //String name = "C:\\Users\\marcelo.silva\\Downloads\\tinyG.txt";
        
        Integer vertices;

        try {

            FileReader arq = new FileReader(name);
            BufferedReader fileReade = new BufferedReader(arq);
            String line = fileReade.readLine();
            vertices = Integer.parseInt(line);

            g = new Graph(vertices);
            line = fileReade.readLine();
            
            System.out.println("Grafo criado com " + vertices +  " Vértices");
            
            while (line != null){

                //System.out.println("line = " + line);
                
                g.addEdge(Integer.parseInt(line.split("\\s+")[0]),Integer.parseInt(line.split("\\s+")[1]));
                line = fileReade.readLine();
            }

        }catch (IOException e){
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        // Create a graph given in the above diagram


        System.out.println("Following is a Topological Sort");
        g.topologicalSort();

} 
}