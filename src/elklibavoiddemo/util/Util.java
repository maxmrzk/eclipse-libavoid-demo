package elklibavoiddemo.util;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.alg.libavoid.LibavoidLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;

public class Util {
	
	public static double relativePortLocation(double port, double node) {
		return port + node;
	}
	
	public static void layoutGraph(ElkNode elkGraph, AbstractLayoutProvider engine) {
		if (engine == null) {
			engine = new LibavoidLayoutProvider();
		}
		
		engine.layout(elkGraph, new BasicProgressMonitor());
		
	}

	public static void drawGraph(ElkNode elkGraph, LightweightSystem lws) {    
        try {
        		
        	RectangleFigure rootFigure = new RectangleFigure();
        	
        	for (ElkPort port : elkGraph.getPorts()) {
            	RectangleFigure portFigure = new RectangleFigure();
            	
            	double x = Util.relativePortLocation(port.getX(), elkGraph.getX());
            	double y = Util.relativePortLocation(port.getY(), elkGraph.getY());

            	portFigure.setBackgroundColor(ColorConstants.black);
            	portFigure.setBounds(new Rectangle((int) x, (int) y,
                                                (int) port.getWidth(), (int) port.getHeight()));
                rootFigure.add(portFigure);
            }
        	
        	List<ElkNode> children = elkGraph.getChildren(); 
        	for (ElkNode node : children) {
        		
        		
                RectangleFigure figure = new RectangleFigure();
                figure.setBackgroundColor(ColorConstants.lightBlue);
                figure.setBounds(new Rectangle((int) node.getX(), (int) node.getY(),
                                                (int) node.getWidth(), (int) node.getHeight()));
                rootFigure.add(figure);
                
                List<ElkPort> ports = node.getPorts();
                for (ElkPort port : ports) {
                	RectangleFigure portFigure = new RectangleFigure();
                	
                	double x = Util.relativePortLocation(port.getX(), node.getX());
                	double y = Util.relativePortLocation(port.getY(), node.getY());

                	portFigure.setBackgroundColor(ColorConstants.black);
                	portFigure.setBounds(new Rectangle((int) x, (int) y,
                                                    (int) port.getWidth(), (int) port.getHeight()));
                    rootFigure.add(portFigure);
                }
            }
           
            
            List<ElkEdge> edges = elkGraph.getContainedEdges();
            for (ElkEdge edge : edges) {
            	Polyline connection = new Polyline();

            	final var sections = edge.getSections();
            	if (!sections.isEmpty()) {
                	final var section = edge.getSections().get(0);
                	
                	connection.addPoint(new Point((int) section.getStartX(), (int) section.getStartY()));
                	for (final var bp : section.getBendPoints()) {
                		connection.addPoint(new Point((int) bp.getX(), (int) bp.getY()));
                	}
                	connection.addPoint(new Point((int) section.getEndX(), (int) section.getEndY()));
            	} else {
            		connection.addPoint(new Point((int) edge.getSources().get(0).getX(), (int) edge.getSources().get(0).getY()));
            		connection.addPoint(new Point((int) edge.getTargets().get(0).getX(), (int) edge.getTargets().get(0).getY()));

            	}
            	               

            	PolygonDecoration arrow = new PolygonDecoration();
                arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
                arrow.setLocation(new Point((int) edge.getTargets().get(0).getX(),(int) edge.getTargets().get(0).getY()));
                rootFigure.add(connection);
            	
            }

            lws.setContents(rootFigure);
        } catch (Exception e) {
            e.printStackTrace();    
        }
	}
	
}
