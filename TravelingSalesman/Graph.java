/* Eleanor Chen
 * 
 * This class implements the nearest neighbor algorithm and brute force algorithm to
 * solve travelling salesman problem.
 */ 

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Graph {

  // Keep a fast index to nodes in the map
  private Map<Integer, Vertex> vertexNames;

  // Keep a fast index of the distance of each edge for brutal fore lookup 

  private double[][] edgeDistance = null;
  private double shortestDistance = Double.MAX_VALUE;
  private Vertex[] shortestPath = null;
  
  /**
   * Construct an empty Graph with a map. The map's key is the name of a vertex
   * and the map's value is the vertex object.
   */
  public Graph() {
    vertexNames = new HashMap<>();
  }

  /**
   * Adds a vertex to the graph. Throws IllegalArgumentException if two vertices
   * with the same name are added.
   * 
   * @param v
   *          (Vertex) vertex to be added to the graph
   */
  public void addVertex(Vertex v) {
    if (vertexNames.containsKey(v.name))
      throw new IllegalArgumentException("Cannot create new vertex with existing name.");
    vertexNames.put(v.name, v);
  }

  /**
   * Gets a collection of all the vertices in the graph
   * 
   * @return (Collection<Vertex>) collection of all the vertices in the graph
   */
  public Collection<Vertex> getVertices() {
    return vertexNames.values();
  }

  /**
   * Gets the vertex object with the given name
   * 
   * @param name
   *          (String) name of the vertex object requested
   * @return (Vertex) vertex object associated with the name
   */
  public Vertex getVertex(String name) {
    return vertexNames.get(name);
  }

  /**
   * Adds a directed edge from vertex u to vertex v
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addEdge(int nameU, int nameV, Double cost) {
    if (!vertexNames.containsKey(nameU))
      throw new IllegalArgumentException(nameU + " does not exist. Cannot create edge.");
    if (!vertexNames.containsKey(nameV))
      throw new IllegalArgumentException(nameV + " does not exist. Cannot create edge.");
    Vertex sourceVertex = vertexNames.get(nameU);
    Vertex targetVertex = vertexNames.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Adds an undirected edge between vertex u and vertex v by adding a directed
   * edge from u to v, then a directed edge from v to u
   * 
   * @param name
   *          (String) name of vertex u
   * @param name2
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addUndirectedEdge(int name, int name2, double cost) {
    addEdge(name, name2, cost);
    addEdge(name2, name, cost);
  }


  /**
   * Computes the euclidean distance between two points as described by their
   * coordinates
   * 
   * @param ux
   *          (double) x coordinate of point u
   * @param uy
   *          (double) y coordinate of point u
   * @param vx
   *          (double) x coordinate of point v
   * @param vy
   *          (double) y coordinate of point v
   * @return (double) distance between the two points
   */
  public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {
    return Math.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));
  }

  /**
   * Computes euclidean distance between two vertices as described by their
   * coordinates
   * 
   * @param u
   *          (Vertex) vertex u
   * @param v
   *          (Vertex) vertex v
   * @return (double) distance between two vertices
   */
  public double computeEuclideanDistance(Vertex u, Vertex v) {
    return computeEuclideanDistance(u.x, u.y, v.x, v.y);
  }

  /**
   * Calculates the euclidean distance for all edges in the map using the
   * computeEuclideanCost method.
   */
  public void computeAllEuclideanDistances() {
    for (Vertex u : getVertices())
      for (Edge uv : u.adjacentEdges) {
        Vertex v = uv.target;
        uv.distance = computeEuclideanDistance(u.x, u.y, v.x, v.y);
      }
  }



  // STUDENT CODE STARTS HERE

  public void generateRandomVertices(int n) {
    vertexNames = new HashMap<>(); // reset the vertex hashmap
    
    // Your code here...
    Random r = new Random();
    for(int i=0; i < n; i++)
    {    	
    	Vertex v = new Vertex(i,r.nextInt(101), r.nextInt(101));
    	for(Vertex s: getVertices()  )
    	{
    		Edge e1 = new Edge(s, v, Double.MAX_VALUE);
    		s.addEdge(e1);
    		Edge e2 = new Edge(v,s, Double.MAX_VALUE);
    		v.addEdge(e2);
    	}
    	vertexNames.put(i, v);    	
    }
    
    computeAllEuclideanDistances(); // compute distances
  }

  public List<Edge> nearestNeighborTsp() {
	  LinkedList<Edge> l = new LinkedList<Edge>();	  
	  if(this.vertexNames == null || this.vertexNames.values() == null || this.vertexNames.values().size() <= 1)
		  return l;
	  computeAllEuclideanDistances();
	  for(Vertex v: this.vertexNames.values() )
	  {		
		  v.known = false;
		  v.prev = null;
	  }	  	  
	  //choose one vertex
	  Vertex current = this.vertexNames.values().iterator().next();
	  Vertex first = current;
	  current.known = true;	  
	  while(hasUnknownVertex())
	  {
		  double d = Double.MAX_VALUE; // distance
		  Edge shortest = new Edge(null,null,0);
		  for(Edge e: current.adjacentEdges)
		  {
			  if(!e.target.known)
			  {
				  if(e.distance  < d)
				  {
					  d = e.distance;
					  shortest = e;
				  }
			  }
		  }
		  l.addFirst(shortest);
		  current = shortest.target;
		  current.prev = shortest.source;
		  current.known = true;		  
	  }
	  
	  for(Edge e: current.adjacentEdges)
	  {
		  if(e.target.equals(first))
		  {
			  l.addFirst(e);
			  break;
		  }
	  }
	  
    return l; 
  }

  private boolean hasUnknownVertex()
  {
	  for(Vertex v: this.vertexNames.values() )
	  {
		  if(!v.known)
			  return true;

	  }	  	  	 
	  return false;

  }
  
  private double getPathDistance(Vertex[] a)
  {
	  double d = this.edgeDistance[Integer.valueOf(a[0].name)][Integer.valueOf(a[1].name)];	  	  
	  // start with 1 !!!
	  for(int i = 1; i < a.length - 1 ; i++)
	  {	
		  int start = Integer.valueOf(a[i].name);
		  int end = Integer.valueOf(a[i+1].name);
		  d = d + this.edgeDistance[start][end];
	  }
	  d = d + this.edgeDistance[Integer.valueOf(a[a.length-1].name)][Integer.valueOf(a[0].name)];	
	  return d;
  }
  // Permutation all vertex
  private void permute(Vertex[] a, int l, int r)
  {
	  
      if (l == r)
      {
    	double d = getPathDistance(a);
    	if( d < this.shortestDistance)
    	{
    		this.shortestDistance = d;
    		// next 3 lines copy the shortest path to a new array, cannot use shortestPath=a directly because a is continue permute
    		this.shortestPath = new Vertex[a.length]; 
    		for(int i = 0; i < a.length; i++)
    		{
    			this.shortestPath[i] = a[i];
    		}
    	}
      }          
      else
      {
          for (int i = l; i <= r; i++)
          {
              swap(a,l,i);
              permute(a, l+1, r);
              swap(a,l,i);
          }
      }
  }

  
  private void swap(Vertex[] a, int i, int j )
  {
	  if(i==j) return;
	  Vertex tmp = a[i];
	  a[i] = a[j];
	  a[j] = tmp;
  }
  
  //brute force algorithm
  public List<Edge> bruteForceTsp() {
	  this.shortestDistance = Double.MAX_VALUE;
	  List<Edge> l = new LinkedList<Edge>();
	  if(this.getVertices().size() <=1)
		  return l;
	  fillinAllEuclideanDistance();
	  Vertex[] tmpArray = new Vertex[this.getVertices().size()];	  
	  int k = 0;
	  for(Vertex v: this.getVertices())
	  {
		  tmpArray[k] = v;
		  k++;
	  }
	  
	  // Because the path is cyclic path, we don't need permutation the first vertex, starting permutes second one and the rest   
	  this.permute(tmpArray, 1, tmpArray.length -1);
	  int N = this.shortestPath.length;
	  if(this.shortestPath != null)
	  {
		  for(int i=0; i < N -1; i++)
		  {
			  int indx1 = Integer.valueOf(shortestPath[i].name);
			  int indx2 = Integer.valueOf(shortestPath[i+1].name);
			  Edge e = new Edge(shortestPath[i],shortestPath[i+1], this.edgeDistance[indx1][indx2]);
			  l.add(e);
		  }
		  Edge e = new Edge(shortestPath[N-1],shortestPath[0], this.edgeDistance[Integer.valueOf(shortestPath[N-1].name)][Integer.valueOf(shortestPath[0].name)]);
		  l.add(e);
	  }	  
	  return l;			  
  }

  // To increase the speed, pre-calculate the distance of all edges and save the result to a two dimensional array
  private void fillinAllEuclideanDistance()
  {
	  int n = vertexNames.size();
	  if(n == 0)
		  return;
	  
	  this.edgeDistance = new double[n][n];	  
	  
	    for (Vertex u : getVertices())
	        for (Edge uv : u.adjacentEdges) 
	        {
	          Vertex v = uv.target;
	          int i = Integer.valueOf(u.name);
	          int j = Integer.valueOf(v.name);
	          edgeDistance[i][j] = computeEuclideanDistance(u.x, u.y, v.x, v.y);
	         }	  
	    
  }

  
  // STUDENT CODE ENDS HERE


  /**
   * Prints out the adjacency list of the graph for debugging
   */
  public void printAdjacencyList() {
    for (int u : vertexNames.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertexNames.get(u).adjacentEdges) {
        sb.append(e.target.name);
        sb.append("(");
        sb.append(e.distance);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }
}