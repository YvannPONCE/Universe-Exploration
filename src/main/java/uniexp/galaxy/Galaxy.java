package uniexp.galaxy;

import uniexp.galaxy.graph.AbstractGraph;
import uniexp.galaxy.graph.Edge;
import uniexp.galaxy.graph.Vertex;

import java.util.Map;

public class Galaxy extends AbstractGraph {

    /**
     * builds a galaxy with no planets
     */
    public Galaxy() {
        super();
    }

    @Override
    public void addEdge(Vertex u, Vertex v, double weight) {
        checkVertex(u);
        checkVertex(v);
        if (add(u, v, weight)) {
            storeEdge(u, v, weight);
            add(v, u, weight);
            nbEdges++;
        }
    }

    @Override
    public void removeEdge(Vertex u, Vertex v) {
        checkVertex(u);
        checkVertex(v);
        if (remove(u, v)) {
            remove(v, u);
            nbEdges--;
        }
    }

    @Override
    public int degree(Vertex u) {
        checkVertex(u);
        return adjacencyList.get(u).size();
    }

    @Override
    public int inDegree(Vertex u) {
        return degree(u);
    }

    @Override
    public int outDegree(Vertex u) {
        return degree(u);
    }

    @Override
    public String toString() {
        return "Undirected Graph\n" + super.toString();
    }

    @Override
    public Edge findEdge(Vertex u, Vertex v) {
        Map<Vertex, Edge> map = edges.get(u);
        if (map == null)
            return edges.get(v).get(u);
        Edge e = map.get(v);
        if (e == null)
            return edges.get(v).get(u);
        return e;
    }
}
