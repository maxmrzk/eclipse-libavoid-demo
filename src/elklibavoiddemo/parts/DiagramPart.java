package elklibavoiddemo.parts;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.alg.libavoid.LibavoidLayoutProvider;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import elklibavoiddemo.handlers.ComboSelectionListener;
import elklibavoiddemo.handlers.MouseSelectionListener;
import elklibavoiddemo.model.Diagram;
import elklibavoiddemo.util.Util;

public class DiagramPart {
	
	private SashForm sash;
	private ElkNode graph;
	private static final int DEFAULT_DIAGRAM_WEIGHT = 4;
    private static final int DEFAULT_TREE_WEIGHT = 1;
    private static final int TOTAL_DEFAULT_WEIGHT = DEFAULT_DIAGRAM_WEIGHT + DEFAULT_TREE_WEIGHT;
	private Diagram diagram;
	private LightweightSystem lws;
	
	@Inject
	private MPart part;
	
	@PostConstruct
    public void createComposite(Composite parent) {
        parent.setLayout(new FillLayout());
        
        this.sash = new SashForm(parent, SWT.HORIZONTAL | SWT.NO_SCROLL);
        
        createDiagramView(this.sash);
        createTreeView(this.sash);
        
        
        this.sash.setSashWidth(TOTAL_DEFAULT_WEIGHT);
        this.sash.setWeights(DEFAULT_DIAGRAM_WEIGHT, DEFAULT_TREE_WEIGHT);

       
    }
	
	public void createDiagramView(Composite parent) {
		final Canvas canvasContainer = new Canvas(parent, 
				SWT.NONE | SWT.NO_SCROLL);
        canvasContainer.setLayout(new FillLayout());

       	//Create a demo ELKGraph
        final LibavoidLayoutProvider engine = new LibavoidLayoutProvider();
        this.diagram = new Diagram(engine);
    	this.graph = diagram.graph();
        
    	// Draw the Graph, only for visualization purposes
	    final FigureCanvas figureCanvas = new FigureCanvas(canvasContainer);
	    final LightweightSystem lws = new LightweightSystem(figureCanvas);
        this.lws = lws;
        
	    final var mouseListener = 
	    		new MouseSelectionListener(this.graph, lws);
        figureCanvas.addMouseListener(mouseListener);
        figureCanvas.addMouseMoveListener(mouseListener);
    	
    	Util.drawGraph(this.graph, lws);
	}
	
	public void createTreeView(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		
		final Label label = new Label(composite, SWT.BORDER);
		label.setText("Amount Nodes:");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setItems(new String [] {"4","8","12","16","20"});
		combo.select(0);
		
		combo.addSelectionListener(
				new ComboSelectionListener(this.diagram, this.lws));
		
	}
	
	@Focus
	public void setFocus() {
	}

	@Persist
	public void save() {
		part.setDirty(false);
	}

}