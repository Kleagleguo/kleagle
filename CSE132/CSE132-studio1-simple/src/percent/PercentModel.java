package percent;

import javax.swing.DefaultBoundedRangeModel;

public class PercentModel extends DefaultBoundedRangeModel {
	public PercentModel() {
		super(100, 0, 0, 100);
	}
	public int computePercentOf(int n){
		if (n==0){
			return 0;
		} 
		else {
			return (int) ((n*1.0)/100*this.getValue());
		}
	}
}
