package A6_Dijkstra;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class DiGraph implements DiGraph_Interface {

  // in here go all your data and methods for the graph
  // and the topo sort operation

  // Keep list of vertices/nodes. Each vertex has a list of edges
  public ArrayList<Vertex>       vertexList  = new ArrayList<>();
  public HashMap<String, Vertex> map         = new HashMap<>();
  public HashSet<Long>           vertexIdSet = new HashSet<>();
  public HashSet<Long>           edgeIdSet   = new HashSet<>();

  public DiGraph() { // default constructor
  }

  @Override
  public boolean addNode(long idNum, String label) {
    if (label.equals(null)) {
      return false;
    }
    if (map.containsKey(label) || vertexIdSet.contains(idNum)) {
      return false;
    }
    if (idNum < 0) {
      return false;
    }

    // else
    Vertex newNode = new Vertex(idNum, label);
    vertexList.add(newNode);
    map.put(label, newNode);
    vertexIdSet.add(idNum);
    return true;
    // You might want to generate the unique number automatically,
    // but this operation allows you to specify any integer.
    // returns false if node number is not unique, or less than 0
    // returns false if label is not unique (or is null)
    // returns true if node is successfully added
  }

  @Override
  public boolean addEdge(long idNum, String sLabel, String dLabel, long weight,
      String eLabel) {
    if (idNum < 0) {
      return false;
    }
    if (edgeIdSet.contains(idNum)) {
      return false;
    }
    Vertex source = map.get(sLabel);
    Vertex destination = map.get(dLabel);

    if (source == null || destination == null) {
      return false;
    }
    if (!source.outgoingEdges.isEmpty()) {
      if (source.outgoingEdges.containsKey(dLabel)) { // checks if there's
                                                      // already
                                                      // an edge between these 2
        return false;
      }
    }
    // Otherwise, add the edge.
    // I update the in-degree within the vertex class
    Edge newEdge = new Edge(idNum, source, destination, weight, eLabel);
    source.addOutgoingEdge(newEdge);
    destination.addIncomingEdge(newEdge);
    edgeIdSet.add(idNum);
    return true;
  }

  @Override
  public boolean delNode(String label) {
    Vertex delNode = map.get(label);
    if (delNode == null) {
      return false;
    }

    Collection<Edge> idk = delNode.incomingEdges.values();
    Object[] inEdges = idk.toArray();
    Collection<Edge> idk2 = delNode.outgoingEdges.values();
    Object[] outEdges = idk2.toArray();

    for (int i = 0; i < inEdges.length; i++) {
      Edge eachEdge = (Edge) inEdges[i];
      delEdge(eachEdge.sourceNode.nodeName, eachEdge.destinationNode.nodeName);
    }
    for (int i = 0; i < outEdges.length; i++) {
      Edge eachEdge = (Edge) outEdges[i];
      delEdge(eachEdge.sourceNode.nodeName, eachEdge.destinationNode.nodeName);
    }
    /*
     * In delNode I made two temp arrays for in Edges and out Edges from the
     * Hashmaps of the Vertex I want to delete. Then I use for loops to iterate
     * through those arrays and call delEdge. In the for loop for my inEdge
     * array, I use the label of the current Node[i] in the array as the source
     * of delEdge and I use the Vertex I'm deleting as the destination.
     */
    map.remove(label);
    vertexIdSet.remove(delNode.idNumber);
    vertexList.remove(delNode);
    return true;
  }

  @Override
  public boolean delEdge(String sLabel, String dLabel) {
    Edge desiredEdge = new Edge();
    int count = 0;
    // Go through the vertex array, and search for sLabel OR dLabel
    for (int i = 0; i < vertexList.size(); i++) {
      Vertex node = vertexList.get(i);
      if (node.nodeName.equals(sLabel) || node.nodeName.equals(dLabel)) {
        if (node.nodeName.equals(sLabel)) {
          desiredEdge = node.outgoingEdges.get(dLabel);
          node.outgoingEdges.remove(dLabel);
          count++;
        }
        if (node.nodeName.equals(dLabel)) {
          desiredEdge = node.incomingEdges.get(sLabel);
          node.incomingEdges.remove(sLabel);
          node.inDegree--;
          count++;
        }
        if (count >= 2) {
          break;
        }
      }
    }
    if (count < 2 || desiredEdge == null) {
      return false;
    }
    edgeIdSet.remove(desiredEdge.edgeID);
    return true;
  }

  @Override
  public long numNodes() {
    return vertexIdSet.size();
  }

  @Override
  public long numEdges() {
    return edgeIdSet.size();
  }

  @Override
  public String[] topoSort() {
    String[] nodeLabels = new String[vertexIdSet.size()];
    int currentIndex = 0;
    ArrayDeque<Vertex> queue = new ArrayDeque<Vertex>();
    for (int i = 0; i < vertexList.size(); i++) {
      if (vertexList.get(i).inDegree == 0) {
        queue.add(vertexList.get(i));
      }
    }

    while (!queue.isEmpty()) {
      Vertex currentNode = queue.remove();
      nodeLabels[currentIndex] = currentNode.nodeName;
      currentIndex++;
      Vertex[] adjacentNodes = new Vertex[currentNode.outgoingEdges.size()];
      int index = 0;
      for (int i = 0; i < vertexList.size(); i++) {
        Vertex node = vertexList.get(i);
        if (currentNode.outgoingEdges.get(node.nodeName) != null) {
          // then we've found an edge
          adjacentNodes[index] = node;
          index++;
          delEdge(currentNode.nodeName, node.nodeName);
        }
      }
      for (int k = 0; k < adjacentNodes.length; k++) {
        if (adjacentNodes[k].inDegree == 0) {
          // enque that node
          queue.add(adjacentNodes[k]);
        }
      }
    }
    if (nodeLabels.length != vertexIdSet.size()) {
      // there was a cycle
      nodeLabels = null;
    }
    /*
     * Decrement in-degree of any node W where (V,W) is an element of E.
     * (essentially remove V and its out-edges from the graph).
     */
    // When you decrement in-degree of each adjacent node, if one drops to 0,
    // enque it.

    // if there is no topo sort (a cycle), return null for the array
    /*
     * if there is a topo sort, return an array containing the node labels in
     * order
     */
    /*
     * A topological sort is a list of the nodes in a graph in a specific order.
     * Therefore, this operation is returning an array, and the elements of the
     * array are node labels. The order will be first node in the sort in
     * element 0, and then on up sequentially through the subscripts.
     */
    return nodeLabels;
  }

  @Override
  public ShortestPathInfo[] shortestPath(String label) {

    /*
     * shortestPath method: in: string label for start vertex return: array of
     * ShortestPathInfo objects (ShortestPathInfo) length of this array should
     * be numNodes (as you will put in all shortest paths including from source
     * to itself) See ShortestPathInfo class for what each field of this object
     * should contain
     */

    // length of this array should be numNodes (as you will put in all shortest
    // paths including from source to itself)

    // Dv: tells us what is the current shortest path to each node.

    Collection<Vertex> idk = map.values();
    Object[] allNodes = idk.toArray();
    for (int i = 0; i < allNodes.length; i++) {
      Vertex eachNode = (Vertex) allNodes[i];
      eachNode.known = false;
      eachNode.dv = 100000;
      eachNode.prevNode = null;
    } // makes sure all vertices are clean to start with

    ShortestPathInfo[] array = new ShortestPathInfo[allNodes.length];

    MinBinHeap pq = new MinBinHeap(); // the priority queue
    Vertex startNode = map.get(label);
    startNode.dv = 0;
    pq.insert(new EntryPair(label, 0));
    Vertex currentNode = startNode;
    int count = 0;
    while (pq.size() != 0) {

      currentNode = map.get(pq.getMin().value);
      int d = pq.getMin().priority;
      pq.delMin();
      if (currentNode.known) {
        // back to loop and get another node from PQ
      } else {
        currentNode.known = true;
        array[count] = new ShortestPathInfo(currentNode.nodeName, currentNode.dv);
        count++;

        // d. For each unknown node A adjacent to N
        // i. if a.dist > d+edge.weight then update a.dist in table and add a to
        // PQ
        Collection<Edge> idk2 = currentNode.outgoingEdges.values();
        Object[] outEdges = idk2.toArray();
        // System.out.println("Length of outEdges is: " + outEdges.length);
        for (int i = 0; i < outEdges.length; i++) {
          Edge eachEdge = (Edge) outEdges[i];
          Vertex a = eachEdge.destinationNode;
          // System.out.println("Destination node is: " + a.nodeName);
          if (!a.known) {
            if (a.dv > d + eachEdge.weight) {
              // update a.dv and add a to the queue.
              a.dv = d + (int) eachEdge.weight;
              pq.insert(new EntryPair(a.nodeName, a.dv));
            }
          }

        }
      }

    }

    if (array[array.length - 1] == null) {
      // not all nodes are reachable from the start node.
      // Here, I fill the rest of the array with the unreachable nodes.
      for (int i = 0; i < allNodes.length; i++) {
        Vertex temp = (Vertex) allNodes[i];
        if (!temp.known) {
          temp.dv = -1;
          array[count] = new ShortestPathInfo(temp.nodeName, temp.dv);
          count++;
        }
      }
    }
    // Note on totalWeight: If a path from source node to the destination node
    // does not exist, set totalWeight to -1.

    return array;
  }

}
