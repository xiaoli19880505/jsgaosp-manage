package com.britecloud.marketingcloud.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.model.BcDataTable;
import com.britecloud.marketingcloud.model.BcDataTableField;

public class DataSourceDef {
	List<BcDataTable> tables = new ArrayList<BcDataTable>();
	Map<String, List<BcDataTableField>> fields = new HashMap<String, List<BcDataTableField>>();

	public List<BcDataTable> getTables() {
		return tables;
	}

	public void setTables(List<BcDataTable> tables) {
		this.tables = tables;
	}

	public Map<String, List<BcDataTableField>> getFields() {
		return fields;
	}

	public void setFields(Map<String, List<BcDataTableField>> fields) {
		this.fields = fields;
	}

	public void addTable(String code, BcDataTable table) {
		tables.add(table);
	}

	public void addFields(String code, List<BcDataTableField> list) {
		fields.put(code, list);
	}

	public BcDataTable getTable(String code) {
		for (Iterator iterator = tables.iterator(); iterator.hasNext();) {
			BcDataTable bcDataTable = (BcDataTable) iterator.next();
			if (bcDataTable.getCode().equals(code)) {
				return bcDataTable;
			}
		}
		return null;
	}

	public boolean isArray(String code) {
		BcDataTable table = getTable(code);
		return "1-N".equalsIgnoreCase(table.getRelation());
	}

	public List<BcDataTable> listTables() {
		return tables;
	}

	public List<BcDataTableField> listFields(String code) {
		return fields.get(code);
	}
}
