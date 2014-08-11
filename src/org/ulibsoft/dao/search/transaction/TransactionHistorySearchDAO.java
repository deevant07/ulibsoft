package org.ulibsoft.dao.search.transaction;

import java.util.List;

import org.ulibsoft.model.BKTransMemberModel;
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
	
	/**
	 * List the fine details along with book, cd and magazine details.
	 * @param adno
	 * @return
	 */
	public List<FineMemberModel> listFinePerStaff(String lid);
	/**
	 * Fetch the list of staff and student who had taken the particular book from history
	 * @param code
	 * @return
	 */
	public List<BKTransMemberModel> listPerBook(String code);
}
