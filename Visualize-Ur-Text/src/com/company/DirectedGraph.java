package com.company;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DirectedGraph<T> implements DirectedGraphInterface<T>,java.io.Serializable
{
    /*
        data field;
     */
    private Map<T,VertexInterface<T>> VerticesMap_ObjToIter;
    private int edgeCount;

    public DirectedGraph()
    {
        VerticesMap_ObjToIter = new LinkedHashMap<>();
        edgeCount = 0;
    }

    @Override
    public void addVertex(T vertexLabel)
    {
        VerticesMap_ObjToIter.put(vertexLabel,new Vertex<T>(vertexLabel));
    }

    @Override
    public void addEdge(T begin,T end,double edgeWeight)
    {
        VertexInterface<T> beginVertex = VerticesMap_ObjToIter.get(begin);
        VertexInterface<T> endVertex = VerticesMap_ObjToIter.get(end);

        if (beginVertex != null && endVertex != null)
        {
            beginVertex.addEdge(endVertex,edgeWeight);
        }
        edgeCount ++;
    }

    @Override
    public void addEdge(T begin,T end)
    {
        addEdge(begin,end,0);
    }

    @Override
    public boolean hasEdge(T begin,T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = VerticesMap_ObjToIter.get(begin);
        VertexInterface<T> endVertex = VerticesMap_ObjToIter.get(end);

        if (beginVertex == null || endVertex == null || !beginVertex.hasNeighbor())
        {
            return found;
        }
        else
        {
            Iterator<Vertex<T>.Edge> nextEdgeIterator = beginVertex.getNeighborIterator();
            while (!found && nextEdgeIterator.hasNext())
            {
                Vertex<T>.Edge nextEdge = nextEdgeIterator.next();
                if (endVertex.equals(nextEdge.getEndVertex()))
                {
                    found = true;
                }
            }
        }
        return found;
    }

    @Override
    public boolean isEmpty()
    {
        return VerticesMap_ObjToIter.isEmpty();
    }

    @Override
    public int getNumberOfVertices()
    {
        return VerticesMap_ObjToIter.size();
    }

    @Override
    public int getNumberOfEdges()
    {
        return edgeCount;
    }

    @Override
    public void clear()
    {
        VerticesMap_ObjToIter.clear();
    }
}
