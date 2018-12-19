package com.gracenote.logic;

import com.gracenote.bean.ResponseBean;

public interface WriteInterface {
	public boolean writeToFile(ResponseBean responseBean, String path);
}
