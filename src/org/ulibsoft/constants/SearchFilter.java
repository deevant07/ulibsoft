package org.ulibsoft.constants;

public interface SearchFilter {

	static enum PendingCriteria{
		PNDNG_DATE("ReceiveDate"),
		PNDNG_BRANCH("Branch"),
		PNDNG_YEAR("Year");
		
		private final String key;
		private PendingCriteria(final String val)
		{
			key = val;
		}
		public String toString()
		{
			return key;
		}
		
	}
	
	
	
}
