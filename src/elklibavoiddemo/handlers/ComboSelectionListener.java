package elklibavoiddemo.handlers;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

import elklibavoiddemo.model.Diagram;
import elklibavoiddemo.util.Util;

public class ComboSelectionListener implements SelectionListener {
	
	private static int[] nodeCount = {4,8,12,14,20};
	private Diagram diagram;
	private LightweightSystem lws;
	
	public ComboSelectionListener(Diagram diagram, LightweightSystem lws) {
		this.diagram = diagram;
		this.lws = lws;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		var source = e.getSource();
		if (source instanceof Combo combo) {
			int index = combo.getSelectionIndex();
			ElkNode graph = this.diagram.initGraph(nodeCount[index]);
	        Util.drawGraph(graph, this.lws);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
