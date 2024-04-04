package com.svi.dao;

import java.util.List;

import com.svi.model.SaveRequest;

public interface RecordsDAO {
	
    public void saveRecords(SaveRequest request);
    public List<String> getGameRecords(String key);
    public List<String> getGameDetails(String key);
}
