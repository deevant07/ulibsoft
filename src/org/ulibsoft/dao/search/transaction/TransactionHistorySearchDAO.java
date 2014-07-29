package org.ulibsoft.dao.search.transaction;

import java.util.List;

import org.ulibsoft.model.TransMemberModel;

public interface TransactionHistorySearchDAO {

	public List<TransMemberModel> listPerStudent(String adno);
	public List<TransMemberModel> listPerStaff(String lid);
}
