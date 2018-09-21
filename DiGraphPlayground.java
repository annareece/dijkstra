package A6_Dijkstra;

public class DiGraphPlayground {

  public static void main(String[] args) {

    // thorough testing is your responsibility
    //
    // you may wish to create methods like
    // -- print
    // -- sort
    // -- random fill
    // -- etc.
    // in order to convince yourself your code is producing
    // the correct behavior
    practice();
  }

  public static void practice() {
    DiGraph d = new DiGraph();
    d.addNode(0, "A");
    d.addNode(1, "B");
    d.addNode(2, "C");
    d.addNode(3, "D");
    d.addNode(4, "E");
    d.addNode(5, "F");
    d.addNode(6, "G");
    d.addEdge(0, "A", "B", 4, null);
    d.addEdge(1, "A", "C", 2, null);
    d.addEdge(2, "A", "G", 3, null);
    d.addEdge(3, "B", "D", 3, null);
    d.addEdge(4, "B", "F", 4, null);
    d.addEdge(5, "B", "G", 2, null);
    d.addEdge(6, "C", "B", 1, null);
    d.addEdge(7, "C", "G", 1, null);
    d.addEdge(8, "D", "E", 2, null);
    d.addEdge(9, "D", "F", 1, null);
    d.addEdge(10, "F", "E", 2, null);
    d.addEdge(11, "G", "D", 1, null);
    d.addEdge(12, "G", "F", 2, null);

    ShortestPathInfo[] array1 = d.shortestPath("A");
    for (int i = 0; i < array1.length; i++) {
      System.out
          .println("(" + array1[i].getDest() + ", " + array1[i].getTotalWeight() + ")");
    }

    printTOPO(d.topoSort());

  }

  public static void myPath2() {
    // tests both delNode and delEdge, and then calls shortestPath
    DiGraph d = new DiGraph();
    d.addNode(0, "0");
    d.addNode(1, "1");
    d.addNode(2, "2");
    d.addNode(3, "3");
    d.addNode(4, "4");
    d.addNode(5, "5");
    d.addNode(6, "6");
    d.addEdge(0, "4", "5", 2, null);
    d.addEdge(1, "0", "5", 3, null);
    d.addEdge(2, "3", "2", 6, null);
    d.addEdge(3, "6", "1", 4, null);
    d.addEdge(4, "4", "0", 1, null);

    d.addEdge(6, "5", "3", 2, null);
    d.addEdge(7, "5", "2", 2, null);

    ShortestPathInfo[] array = d.shortestPath("0");
    for (int i = 0; i < array.length; i++) {
      System.out
          .println("(" + array[i].getDest() + ", " + array[i].getTotalWeight() + ")");
    }

    System.out.println();
    System.out.println();
    d.delEdge("5", "2");
    ShortestPathInfo[] array1 = d.shortestPath("0");
    for (int i = 0; i < array1.length; i++) {
      System.out
          .println("(" + array1[i].getDest() + ", " + array1[i].getTotalWeight() + ")");
    }

    System.out.println();
    System.out.println();
    d.delNode("5");
    ShortestPathInfo[] array2 = d.shortestPath("0");
    for (int i = 0; i < array2.length; i++) {
      System.out
          .println("(" + array2[i].getDest() + ", " + array2[i].getTotalWeight() + ")");
    }
  }

  public static void myPath1() {
    // tests shortestPath after doing a delNode
    DiGraph d = new DiGraph();
    d.addNode(0, "0");
    d.addNode(1, "1");
    d.addNode(2, "2");
    d.addNode(3, "3");
    d.addNode(4, "4");
    d.addNode(5, "5");
    d.addNode(6, "6");
    d.addEdge(0, "4", "5", 2, null);
    d.addEdge(1, "0", "5", 3, null);
    d.addEdge(2, "3", "2", 6, null);
    d.addEdge(3, "6", "1", 4, null);
    d.addEdge(4, "4", "0", 1, null);

    d.addEdge(6, "5", "2", 2, null);

    ShortestPathInfo[] array = d.shortestPath("0");
    for (int i = 0; i < array.length; i++) {
      System.out
          .println("(" + array[i].getDest() + ", " + array[i].getTotalWeight() + ")");
    }

    d.delNode("5");
    ShortestPathInfo[] array1 = d.shortestPath("0");
    System.out.println();
    for (int i = 0; i < array1.length; i++) {
      System.out
          .println("(" + array1[i].getDest() + ", " + array1[i].getTotalWeight() + ")");
    }
  }

  public static void myPath0() {
    // tests shortestPath after doing a delEdge
    DiGraph d = new DiGraph();
    d.addNode(0, "0");
    d.addNode(1, "1");
    d.addNode(2, "2");
    d.addNode(3, "3");
    d.addNode(4, "4");
    d.addNode(5, "5");
    d.addNode(6, "6");
    d.addEdge(0, "4", "5", 2, null);
    d.addEdge(1, "0", "5", 3, null);
    d.addEdge(2, "3", "2", 6, null);
    d.addEdge(3, "6", "1", 4, null);
    d.addEdge(4, "4", "0", 1, null);

    d.addEdge(5, "0", "3", 7, null);
    d.addEdge(6, "5", "2", 2, null);
    d.delEdge("0", "3");
    d.addEdge(5, "2", "6", 3, null);
    ShortestPathInfo[] array = d.shortestPath("0");
    for (int i = 0; i < array.length; i++) {
      System.out
          .println("(" + array[i].getDest() + ", " + array[i].getTotalWeight() + ")");
    }
  }

  public static void shortestPath4() {
    // I get a null pointer somewhere.
    // Update: it was because I wasn't updating the count variable..
    // Case 4(Islands):

    DiGraph d = new DiGraph();
    d.addNode(0, "0");
    d.addNode(1, "1");
    d.addNode(2, "2");
    d.addNode(3, "3");
    d.addNode(4, "4");
    d.addNode(5, "5");
    d.addNode(6, "6");
    d.addEdge(0, "4", "5", 2, null);
    d.addEdge(1, "0", "5", 3, null);
    d.addEdge(2, "3", "2", 6, null);
    d.addEdge(3, "6", "1", 4, null);
    d.addEdge(4, "4", "0", 1, null);
    ShortestPathInfo[] array = d.shortestPath("0");
    System.out.println(array[0]); // array[2] is null.
    System.out.println(array[1]); // array[2] is null.

    for (int i = 0; i < array.length; i++) {
      System.out
          .println("(" + array[i].getDest() + ", " + array[i].getTotalWeight() + ")");
    }
    // List: (0,0) (1, -1) (2, -1) (3, -1) (4, -1) (5, 3) (6, -1)
  }

  public static void shortestPath0() {
    // Case 0(Euclidean's Bridge):

    DiGraph d = new DiGraph();
    d.addNode(0, "a");
    d.addNode(1, "b");
    d.addNode(2, "c");
    d.addEdge(0, "a", "b", 3, null);
    d.addEdge(1, "b", "c", 4, null);
    d.addEdge(2, "a", "c", 5, null);
    ShortestPathInfo[] array = d.shortestPath("a");
    System.out.println(array[2]); // array[2] is null.
    // System.out.println(array[2].getDest() + array[2].getTotalWeight());
    for (int i = 0; i < array.length; i++) {
      System.out
          .println("(" + array[i].getDest() + ", " + array[i].getTotalWeight() + ")");
    }
    // shortest Path List:
    // (a, 0) (b, 3) (c, 5)
  }

  public static void delNode3() {
    DiGraph d = new DiGraph();

    d.addNode(1, "f");
    d.addNode(2, "x");
    System.out.println(d.numNodes()); // 2
    d.addEdge(0, "f", "x", 0, null);
    System.out.println(d.numEdges()); // 1
    d.delNode("x");
    System.out.println(d.numNodes()); // 1
    System.out.println(d.numEdges()); // 0
    System.out.println(d.addNode(2, "x")); // true
    System.out.println(d.addEdge(0, "f", "x", 0, null)); // true

  }

  public static void delNode2() {
    DiGraph d = new DiGraph();
    d.addNode(1, "f");
    d.addNode(3, "s");
    System.out.println(d.delNode("f")); // true

    DiGraph e = new DiGraph();
    e.addNode(1, "f");
    e.addNode(3, "s");
    System.out.println(e.delNode("x")); // false

    DiGraph f = new DiGraph();
    System.out.println(f.delNode("f")); // false
    f.addNode(1, "f");
    System.out.println(f.delNode("f")); // true
    System.out.println(f.addNode(3, "f")); // true
    System.out.println(f.delNode("f")); // true

  }

  public static void topoTest() {

    DiGraph d = new DiGraph();
    d.addNode(1, "p");
    d.addNode(4, "a");
    d.addNode(3, "g");
    d.addNode(2, "e");
    d.addEdge(0, "p", "a", 0, null);
    d.addEdge(1, "a", "g", 0, null);
    d.addEdge(2, "g", "e", 0, null);
    d.addEdge(3, "e", "p", 0, null);
    String[] topo = d.topoSort();
    System.out.println(topo[0]);
    printTOPO(d.topoSort()); // should be null

  }

  public static void topoTest2() {

    DiGraph d = new DiGraph();
    d.addNode(1, "p");
    d.addNode(4, "a");
    d.addNode(3, "g");
    d.addNode(2, "e");
    d.addEdge(0, "p", "a", 0, null);
    d.addEdge(1, "a", "g", 0, null);
    d.addEdge(2, "g", "e", 0, null);
    d.addEdge(3, "e", "p", 0, null);
    d.addEdge(4, "p", "g", 0, null);
    d.addEdge(5, "a", "e", 0, null);

    String[] topo = d.topoSort();
    System.out.println(topo[0]);
    printTOPO(d.topoSort()); // should be null

  }

  public static void delEdgeTest() {
    // DELEDGE TEST:

    // Case0: (Simple True)
    DiGraph d = new DiGraph();
    d.addNode(1, "f");
    d.addNode(3, "s");
    d.addEdge(0, "f", "s", 0, null);
    System.out.println(d.delEdge("f", "s")); // should be true

    // Case1: (Simple False)
    System.out.println("Case 1: ");
    DiGraph a = new DiGraph();
    a.addNode(1, "f");
    a.addNode(3, "s");
    a.addEdge(0, "f", "s", 0, null);
    // addNode(1,f)
    // addNode(3, s)
    // addEdge(0, f, s, 0, null)
    System.out.println(a.delEdge("f", "x"));
    // delEdge(f,x)==false
    System.out.println(a.delEdge("x", "f"));
    // delEdge(x,f)==false

    // Case2: (Add After Del)
    System.out.println("Case 2: ");
    DiGraph b = new DiGraph();
    System.out.println(b.delEdge("f", "s")); // false
    // delEdge(f, s)==false
    b.addNode(1, "f");
    b.addNode(3, "s");
    b.addEdge(0, "f", "s", 0, null);
    // addNode(1,f)
    // addNode(3, s)
    // addEdge(0, f, s, 0, null)
    System.out.println(b.delEdge("f", "s")); // true
    // delEdge(f,s)==true
    System.out.println(b.delEdge("f", "s")); // false
    // delEdge(f,s)==false
    System.out.println(b.addEdge(0, "f", "s", 0, null)); // true
    // addEdge(0, f, s, 0, null)==true
    System.out.println(b.delEdge("f", "s")); // true
    // delEdge(f,s)==true
  }

  public static void exTest() {
    DiGraph d = new DiGraph();
    d.addNode(1, "f");
    d.addNode(3, "s");
    d.addNode(7, "t");
    d.addNode(0, "fo");
    d.addNode(4, "fi");
    d.addNode(6, "si");
    d.addEdge(0, "f", "s", 0, null); // the null pointer is here
    d.addEdge(1, "f", "si", 0, null);
    d.addEdge(2, "s", "t", 0, null);
    d.addEdge(3, "fo", "fi", 0, null);
    d.addEdge(4, "fi", "si", 0, null);
    System.out.println("numEdges: " + d.numEdges());
    System.out.println("numNodes: " + d.numNodes());
    printTOPO(d.topoSort());

  }

  public static void printTOPO(String[] toPrint) {
    System.out.print("TOPO Sort: ");
    for (String string : toPrint) {
      System.out.print(string + " ");
    }
    System.out.println();
  }

}