package data_structure.DirectedGraph;

import data_structure.unionfind.UnionFindSet;

import java.util.*;

public class MinimumSpanningTree {
    public int minimumSpanningTree(List<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return -1;
        }

        List<Connection> connections = new ArrayList<>();
        UnionFindSet<DirectedGraphNode> ufs = new UnionFindSet<>();

        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors.keySet()) {
                connections.add(new Connection(node, neighbor, node.neighbors.get(neighbor)));
            }
            ufs.add(node);
        }

        Collections.sort(connections, Comparator.comparingInt(connection -> connection.cost));

        List<Connection> cost = new ArrayList<>();

        for (Connection connection : connections) {
            if (!ufs.isUnioned(connection.from, connection.to)) {
                ufs.union(connection.from, connection.to);
                cost.add(connection);
            }
        }

        if (cost.size() < nodes.size() - 1) {
            return -1;
        } else {
            return cost.stream().mapToInt(connection -> connection.cost).sum();
        }
    }

    public class Connection {
        public DirectedGraphNode from, to;
        public int cost;

        public Connection(DirectedGraphNode from, DirectedGraphNode to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }


    public static void main(String[] args) {
        DirectedGraphNode a = new DirectedGraphNode(0);
        DirectedGraphNode b = new DirectedGraphNode(1);
        DirectedGraphNode c = new DirectedGraphNode(2);
        DirectedGraphNode d = new DirectedGraphNode(3);
        DirectedGraphNode e = new DirectedGraphNode(4);
        DirectedGraphNode f = new DirectedGraphNode(5);
        DirectedGraphNode g = new DirectedGraphNode(6);
        DirectedGraphNode h = new DirectedGraphNode(7);


        a.neighbors.put(b, 4);
        a.neighbors.put(c, 3);
        a.neighbors.put(e, 7);

        b.neighbors.put(a, 4);
        b.neighbors.put(c, 6);
        b.neighbors.put(d, 5);

        c.neighbors.put(a, 3);
        c.neighbors.put(b, 6);
        c.neighbors.put(d, 11);
        c.neighbors.put(e, 8);

        d.neighbors.put(b, 5);
        d.neighbors.put(c, 11);
        d.neighbors.put(e, 2);
        d.neighbors.put(f, 2);
        d.neighbors.put(g, 10);

        e.neighbors.put(a, 7);
        e.neighbors.put(c, 8);
        e.neighbors.put(d, 2);
        e.neighbors.put(g, 5);

        f.neighbors.put(d, 2);
        f.neighbors.put(g, 3);

        g.neighbors.put(d, 10);
        g.neighbors.put(e, 5);
        g.neighbors.put(f, 3);

        List<DirectedGraphNode> nodes = Arrays.asList(a, b, c, d, e, f, g);
        System.out.println(new MinimumSpanningTree().minimumSpanningTree(nodes));
    }
}
