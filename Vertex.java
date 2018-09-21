package A6_Dijkstra;

import java.util.HashMap;

public class Vertex {

  public boolean               known;
  public int                   dv;                                         // current
                                                                           // shortest
                                                                           // path
                                                                           // length
  public Vertex                prevNode;
  public int                   inDegree;
  public long                  idNumber;
  public String                nodeName;
  public HashMap<String, Edge> incomingEdges = new HashMap<String, Edge>();
  public HashMap<String, Edge> outgoingEdges = new HashMap<String, Edge>();

  public Vertex(long idNumber, String nodeName) {
    this.idNumber = idNumber;
    this.nodeName = nodeName;
    inDegree = 0;
    known = false;
  }

  public void addIncomingEdge(Edge newEdge) {
    if (!incomingEdges.containsValue(newEdge)) {
      incomingEdges.put(newEdge.sourceNode.nodeName, newEdge);
      inDegree++;
    } // else, the edge is already there
  }

  public void addOutgoingEdge(Edge newEdge) {
    if (!outgoingEdges.containsValue(newEdge)) {
      outgoingEdges.put(newEdge.destinationNode.nodeName, newEdge);
    } // else, the edge is already there
  }

}
