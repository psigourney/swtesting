package pset5;
import java.util.*;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

public class CFG {
    Set<Node> nodes = new HashSet<Node>();
    Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();

    public static class Node{
        int position;
        Method method;
        JavaClass clazz;

        Node(int p, Method m, JavaClass c){
            position = p;
            method = m;
            clazz = c;
        }

        public Method getMethod(){
            return method;
        }

        public JavaClass getClazz(){
            return clazz;
        }

        public boolean equals(Object o){
            if(!(o instanceof Node)) return false;
            Node n = (Node)o;
            return (position == n.position) && method.equals(n.method) && clazz.equals(n.clazz);
        }

        public int hashCode(){
            return position + method.hashCode() + clazz.hashCode();
        }

        public String toString(){
            return clazz.getClassName() + '.' + method.getName() + method.getSignature() + ": " + position;
        }
    }

    public void addNode(int p, Method m, JavaClass c){
        addNode(new Node(p, m, c));
    }

    private void addNode(Node n){
        nodes.add(n);
        Set<Node> nbrs = edges.get(n);
        if(nbrs == null){
            nbrs = new HashSet<Node>();
            edges.put(n, nbrs);
        }
    }

    public void addEdge(int p1, Method m1, JavaClass c1, int p2, Method m2, JavaClass c2){
        Node n1 = new Node(p1, m1, c1);
        Node n2 = new Node(p2, m2, c2);
        addNode(n1);
        addNode(n2);
        Set<Node> nbrs = edges.get(n1);
        nbrs.add(n2);
        edges.put(n1, nbrs);
    }

    public void addEdge(int p1, int p2, Method m, JavaClass c){
        addEdge(p1, m, c, p2, m, c);
    }

    public String toString(){
        return nodes.size() + " nodes\n" +"nodes: " + nodes + '\n' + "edges: " + edges;
    }

    //Determine whether a method invokes another method, either directly or indirectly.
    public boolean isReachable(String methodFrom, String clazzFrom, String methodTo, String clazzTo) {
        LinkedList<Method> visitedMethods = new LinkedList<>();
        for (Map.Entry<CFG.Node, Set<CFG.Node>> entry : edges.entrySet()) {
            //If we find the FROM method in the edges set:
            if (entry.getKey().getMethod().getName().equals(methodFrom) && entry.getKey().getClazz().getClassName().equals(clazzFrom)) {
                return recursiveCheck(entry.getKey().getMethod(), entry.getKey().getClazz(), methodTo, clazzTo, visitedMethods);
            }
        }
        //methodFrom doesn't exist in our CFG, bail out immediately
        return false;
    }

    private boolean recursiveCheck(Method methodFrom, JavaClass clazzFrom, String methodTo, String clazzTo, LinkedList<Method> visitedMethods) {
        if (methodFrom.getName().equals(methodTo) && clazzFrom.getClassName().equals(clazzTo)) { return true; }
        visitedMethods.add(methodFrom);
        //For each matching node in startingMethod....
        for (Map.Entry<CFG.Node, Set<CFG.Node>> entry : edges.entrySet()) {
            if (entry.getKey().getMethod().equals(methodFrom) && entry.getKey().getClazz().equals(clazzFrom)) {
                //....recursively check all its destination nodes for the target method
                for (Node destNode : entry.getValue()) {
                    if(!(visitedMethods.contains(destNode.getMethod()))) {
                        if (recursiveCheck(destNode.method, destNode.clazz, methodTo, clazzTo, visitedMethods)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
