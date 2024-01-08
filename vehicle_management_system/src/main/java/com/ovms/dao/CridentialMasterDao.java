package com.ovms.dao;

import com.ovms.entity.CridentialMaster;

public interface CridentialMasterDao {

	void addCridential(CridentialMaster cridentialMaster);

	void updateCridential(CridentialMaster cridentialMaster);

	CridentialMaster getByUsername(String username);
}
