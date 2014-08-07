package org.ulibsoft.dao.search.transaction;

import java.util.List;

import org.ulibsoft.model.FineMemberModel;
import org.ulibsoft.model.TransMemberModel;

public interface TransactionHistorySearchDAO {

	public List<TransMemberModel> listPerStudent(String adno);
	public List<TransMemberModel> listPerStaff(String lid);
	/**
	 * List the fine details along with book, cd and magazine details.
	 * @param adno
	 * @return
	 */
	public List<FineMemberModel> listFinePerStudent(String adno);
}
