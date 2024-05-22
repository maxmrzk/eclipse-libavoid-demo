package elklibavoiddemo.handlers;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import elklibavoiddemo.util.Util;

public class MouseSelectionListener implements MouseListener, MouseMoveListener {
	
    private ElkNode selectedNode;
    private Point offset;
    private ElkNode graph;
    private LightweightSystem lws;
    
    public MouseSelectionListener(ElkNode graph, LightweightSystem lws) {
    	this.graph = graph;
    	this.lws = lws;
    }

    private void handleMouseDown(MouseEvent e) {
        Point mouseLocation = new Point(e.x, e.y);
        // Check if any node is clicked
        selectedNode = getNodeAtLocation(mouseLocation);
        if (selectedNode != null) {
            offset = new Point((int) (mouseLocation.x - selectedNode.getX()),
                    (int) (mouseLocation.y - selectedNode.getY()));
        }
    }

    private void handleMouseUp() {
        selectedNode = null;
    }

    private void handleMouseMove(MouseEvent e) {
        if (selectedNode != null) {
            Point newLocation = new Point(e.x - offset.x, e.y - offset.y);
            for (final var child : selectedNode.getChildren()) {
            	Point relativeLocation = new Point ((int) (child.getX() - selectedNode.getX()), 
            			(int) (child.getY() - selectedNode.getY()));
            	Point newChildLocation = new Point (newLocation.x + relativeLocation.x, 
            			newLocation.y + relativeLocation.y);
            	child.setLocation(newChildLocation.x, newChildLocation.y);
            }
            selectedNode.setLocation(newLocation.x, newLocation.y);
            redrawCanvas();
        }
    }

    private ElkNode getNodeAtLocation(Point location) {
        for (ElkNode node : graph.getChildren()) {
        	Rectangle rectangle = new Rectangle((int) node.getX(), (int) node.getY(),
                    (int) node.getWidth(), (int) node.getHeight());
        	if (rectangle.contains(location)) {
        		return node;
        	}
        }
        return null;
    }

    private void redrawCanvas() {
    	Util.layoutGraph(graph, null);
        Util.drawGraph(graph, lws);
    }

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
        handleMouseDown(e);
	}

	@Override
	public void mouseUp(MouseEvent e) {
        handleMouseUp();
		
	}

	@Override
	public void mouseMove(MouseEvent e) {
		handleMouseMove(e);		
	}
}
