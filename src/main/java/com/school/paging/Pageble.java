package com.school.paging;

import com.school.sort.Sorter;

public interface Pageble {

	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
