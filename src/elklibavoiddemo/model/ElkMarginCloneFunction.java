package elklibavoiddemo.model;

import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.graph.util.ElkReflect.CloneFunction;

public class ElkMarginCloneFunction implements CloneFunction {

	@Override
	public Object clone(Object o) {
		if (o instanceof ElkMargin margin) {
			return new ElkMargin(margin);
		} else {
			return null;
		}
	}

}
