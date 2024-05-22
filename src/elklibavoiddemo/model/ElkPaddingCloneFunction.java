package elklibavoiddemo.model;

import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.graph.util.ElkReflect.CloneFunction;

public class ElkPaddingCloneFunction implements CloneFunction {

	@Override
	public Object clone(Object o) {
		if (o instanceof ElkPadding padding) {
			return new ElkPadding(padding);
		} else {
			return null;
		}
	}

}
