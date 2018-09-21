package A6_Dijkstra;

public class Edge {

  /*
   * We will serial number an edge so that each has a unique integer id. Make
   * sure the id number is 0 or greater. Since node labels are unique we will
   * specify an edge by naming the source and destination nodes. The label for
   * the edge is optional (meaning the user may pass the null string; use null
   * label as default. Edge labels also do not have to be unique. Edge weight is
   * an integer (it might be negative); use 1 as the default weight. We are only
   * allowing one edge from any node M to node N; however since edges are
   * directed, we might have one edge from M to N, and another edge from N to M.
   * To further complicate things, we also may have an edge from M to M
   * (itself).
   */

  protected long   weight;
  protected String edgeName;
  public long      edgeID;         // must be unique. 0 or greater
  public Vertex    sourceNode;
  public Vertex    destinationNode;

  public Edge() { // this is just so I can create a temp edge
    edgeID = 1728574;
    sourceNode = null;
    destinationNode = null;
    weight = 1;
    edgeName = null;
  }

  public Edge(long edgeID, Vertex sourceNode, Vertex destinationNode) {
    this.edgeID = edgeID;
    this.sourceNode = sourceNode;
    this.destinationNode = destinationNode;

    this.weight = 1;
    this.edgeName = null;
  }

  public Edge(long idNum, Vertex sNode, Vertex dNode, long weight, String eLabel) { // totally
                                                                                    // customized
    this.edgeID = idNum;
    this.sourceNode = sNode;
    this.destinationNode = dNode;
    this.weight = weight;
    this.edgeName = eLabel;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public void setEdgeName(String name) {
    this.edgeName = name;
  }
}
