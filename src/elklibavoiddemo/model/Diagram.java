package elklibavoiddemo.model;

import java.util.List;

import org.eclipse.elk.alg.libavoid.options.LibavoidOptions;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkGraphFactory;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.elk.graph.util.ElkReflect;

public class Diagram {
	
	private AbstractLayoutProvider engine;
	
	private ElkNode graph;
	
	public Diagram(AbstractLayoutProvider layoutEngine) {
		this.engine = layoutEngine;
	}
	
	public ElkNode graph() {
        final ElkGraphFactory factory = ElkGraphFactory.eINSTANCE;
        final ElkNode graph = factory.createElkNode();
        
        ElkReflect.registerClone(ElkMargin.class, new ElkMarginCloneFunction());
        ElkReflect.registerClone(ElkPadding.class, new ElkPaddingCloneFunction());

        final ElkNode vertex1 = factory.createElkNode();
        graph.getChildren()
                .add(vertex1);
        vertex1.setLocation(50, 40); vertex1.setDimensions(80, 100);
        
        final ElkNode vertex2 = factory.createElkNode();
        graph.getChildren()
                .add(vertex2);
        vertex2.setLocation(150, 50); vertex2.setDimensions(100, 100);
        
        final ElkNode vertex3 = factory.createElkNode();
        graph.getChildren()
                .add(vertex3);
        vertex3.setLocation(300, 75); vertex3.setDimensions(100, 100);
        
        final ElkEdge edge = factory.createElkEdge();
        graph.getContainedEdges().add(edge);
        edge.getSources().add(vertex1);
        edge.getTargets().add(vertex3);
        
        this.engine.layout(graph, new BasicProgressMonitor());

        return graph;
	}

	
	public ElkNode initGraph(int numberOfNodes) {
        final ElkGraphFactory factory = ElkGraphFactory.eINSTANCE;
        this.graph = factory.createElkNode();
        final ElkMargin margin = new ElkMargin();
        
        this.graph.setProperty(CoreOptions.ALGORITHM, LibavoidOptions.ALGORITHM_ID);
        
        ElkReflect.registerClone(ElkMargin.class, new ElkMarginCloneFunction());
        ElkReflect.registerClone(ElkPadding.class, new ElkPaddingCloneFunction());

        graph.setProperty(CoreOptions.NODE_LABELS_PADDING, new ElkPadding());
        graph.setProperty(CoreOptions.SPACING_LABEL_NODE, 0.0);
        graph.setProperty(CoreOptions.SPACING_LABEL_LABEL, 0.0);
        graph.setProperty(CoreOptions.SPACING_PORT_PORT, 0.0);
        graph.setProperty(CoreOptions.SPACING_LABEL_PORT_HORIZONTAL, 0.0);
        graph.setProperty(CoreOptions.SPACING_LABEL_PORT_VERTICAL, 0.0);
        graph.setProperty(CoreOptions.MARGINS, margin);
        graph.setProperty(CoreOptions.SPACING_PORTS_SURROUNDING, margin);
        graph.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.insideCenter());
        
        /*
        double x = 10; 
        double y = 10;
        int count = 0;
        
        for (int i = 0; i < numberOfNodes; i++) {
        	count++;
        	int dimension = (int) (Math.random() * 15);
        	int xFactor = (int) (Math.random() * 30 + 40);
        	int yFactor = (int) (Math.round(Math.random())) * 50;
        	
        	var node = factory.createElkNode();
        	graph.getChildren().add(node);
        	node.setLocation(xFactor + x, yFactor + y);
        	x += xFactor*2;
        	y += yFactor*2;
        	node.setDimensions(dimension + 20, -dimension + 35);
        	node.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
        
        	var port = factory.createElkPort();
        	node.getPorts().add(port);
        	port.setLocation(dimension + 20, (-dimension + 35)/2);
        	port.setDimensions(5,5);
        	port.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
        	
        	if (count % 2 == 0) {
        		var edge = factory.createElkEdge();
        		graph.getContainedEdges().add(edge);
        		var child1 = graph.getChildren().get(count-2);
        		var child2 = graph.getChildren().get(count-1);
        		
        		var p1 = child1.getPorts().get(0);
        		var p2 = child2.getPorts().get(0);

        		edge.getSources().add(p1);
        		edge.getTargets().add(p2);
        		
        		var port2 = factory.createElkPort();
            	node.getPorts().add(port2);
            	port2.setLocation(-5, (-dimension + 35)/2);
            	port2.setDimensions(5,5);
            	port2.setProperty(CoreOptions.PORT_SIDE, PortSide.WEST);

        	}
        	
        	if ((count % 2 != 0) && (count >= 3)) {       		
        		var port3 = factory.createElkPort();
            	node.getPorts().add(port3);
            	port3.setLocation((dimension + 20)/2, 0);
            	port3.setDimensions(5,5);
            	port3.setProperty(CoreOptions.PORT_SIDE, PortSide.SOUTH);
            	
            	var edge = factory.createElkEdge();
        		graph.getContainedEdges().add(edge);
        		var child1 = graph.getChildren().get(count-1);
        		var child2 = graph.getChildren().get(count-2);
        		
        		var p1 = child1.getPorts().get(1);
        		var p2 = child2.getPorts().get(0);
        		
        		edge.getSources().add(p1);
        		edge.getTargets().add(p2);
        		
        	}
        	
        }
		*/
        var n1 = factory.createElkNode();
		graph.getChildren().add(n1);
		n1.setLocation(20, 20);
		n1.setDimensions(30, 40);
		n1.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		n1.setProperty(CoreOptions.HYPERNODE, true);
		
		var n321 = factory.createElkNode();
		graph.getChildren().add(n321);
		n321.setLocation(20, 20);
		n321.setDimensions(30, 40);
		n321.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		n321.setProperty(CoreOptions.HYPERNODE, true);
		
		var p1 = factory.createElkPort();
		n1.getPorts().add(p1);
		p1.setLocation(30, 10);
		p1.setDimensions(5, 5);
		p1.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
		
		var p2 = factory.createElkPort();
		n321.getPorts().add(p2);
		p2.setLocation(30, 20);
		p2.setDimensions(5, 5);
		p2.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
		
		var p22 = factory.createElkPort();
		n1.getPorts().add(p22);
		p22.setLocation(30, 30);
		p22.setDimensions(5, 5);
		p22.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
		
		var n2 = factory.createElkNode();
		graph.getChildren().add(n2);
		n2.setLocation(80, 20);
		n2.setDimensions(30, 30);
		n2.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		
		var p3 = factory.createElkPort();
		n2.getPorts().add(p3);
		p3.setLocation(-5, 12);
		p3.setDimensions(5, 5);
		p3.setProperty(CoreOptions.PORT_SIDE, PortSide.WEST);
		
		var n3 = factory.createElkNode();
		graph.getChildren().add(n3);
		n3.setLocation(150, 30);
		n3.setDimensions(30, 30);
		n3.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		
		var p4 = factory.createElkPort();
		n3.getPorts().add(p4);
		p4.setLocation(-5, 12);
		p4.setDimensions(5, 5);
		p4.setProperty(CoreOptions.PORT_SIDE, PortSide.WEST);
		
		var e1 = factory.createElkEdge();
		graph.getContainedEdges().add(e1);
		e1.getSources().add(p1);
		e1.getTargets().add(p3);
		
		
		var n4 = factory.createElkNode();
		graph.getChildren().add(n4);
		n4.setLocation(200, 30);
		n4.setDimensions(100, 50);
		n4.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		n4.setProperty(CoreOptions.HIERARCHY_HANDLING, 
				HierarchyHandling.INCLUDE_CHILDREN);
		
		var p5 = factory.createElkPort();
		n4.getPorts().add(p5);
		p5.setLocation(-5, 12);
		p5.setDimensions(5, 5);
		p5.setProperty(CoreOptions.PORT_SIDE, PortSide.WEST);
		
		var p6 = factory.createElkPort();
		n2.getPorts().add(p6);
		p6.setLocation(-5, 12);
		p6.setDimensions(5, 5);
		p6.setProperty(CoreOptions.PORT_SIDE, PortSide.EAST);
		
		var e3 = factory.createElkEdge();
		graph.getContainedEdges().add(e3);
		e3.getSources().add(p6);
		e3.getTargets().add(p5);
		
		var e4 = factory.createElkEdge();
		graph.getContainedEdges().add(e4);
		e4.getSources().add(p2);
		e4.getTargets().add(p4);
		
		var n5 = factory.createElkNode();
		n4.getChildren().add(n5);
		n5.setLocation(210, 35);
		n5.setDimensions(20, 30);
		n5.setProperty(CoreOptions.PORT_CONSTRAINTS, 
				PortConstraints.FIXED_POS);
		
		var n6 = factory.createElkNode();
		n4.getChildren().add(n6);
		n6.setLocation(250, 40);
		n6.setDimensions(25, 20);
		n6.setProperty(CoreOptions.PORT_CONSTRAINTS, 
				PortConstraints.FIXED_POS);
		
        this.engine.layout(graph, new BasicProgressMonitor());
			
        return graph;
	}
	
	public void deleteGraph() {
		
	}
	
	public void setAbstractLayoutProvider(AbstractLayoutProvider p) {
		this.engine = p;
	}
	
	public ElkNode removeNode(ElkNode node) {
		this.graph.getChildren().remove(node);
		return this.graph;
	}

}
