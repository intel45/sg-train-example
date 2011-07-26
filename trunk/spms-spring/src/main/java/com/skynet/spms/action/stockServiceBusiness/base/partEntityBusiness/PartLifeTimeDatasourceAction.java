package com.skynet.spms.action.stockServiceBusiness.base.partEntityBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness.PartLifeTimeManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity.PartLifeTime;

/**
 * 描述：备件时寿相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PartLifeTimeDatasourceAction implements DataSourceAction<PartLifeTime>{
	@Autowired
	private PartLifeTimeManager partLifeTimeManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"partLifeTime_dataSource"};
	}
	
	/**
	 * 新增备件时寿相关信息
	 * @param partLifetimeInformation
	 */
	@Override
	public void insert(PartLifeTime partLifetimeInformation) {
		partLifeTimeManager.insertEntity(partLifetimeInformation);
	}
	
	/**
	 * 更新备件时寿相关信息
	 * @param newValues
	 * @param number
	 * @return 备件时寿相关信息
	 */
	@Override
	public PartLifeTime update(Map newValues, String number) {
		return (PartLifeTime) partLifeTimeManager.updateEntity(newValues, number, PartLifeTime.class);
	}
	
	/**
	 * 删除备件时寿相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		partLifeTimeManager.deleteEntity(number, PartLifeTime.class);
	}
	
	/**
	 * 查询相关备件时寿信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件时寿相关信息
	 */
	@Override
	public List<PartLifeTime> doQuery(Map values, int startRow, int endRow) {
		return partLifeTimeManager.getPartLifeTime(values, 0, -1);
	}
	
	/**
	 * 获取所有备件时寿信息
	 * @param startRow
	 * @param endRow
	 * @return 备件时寿信息
	 */
	@Override
	public List<PartLifeTime> getList(int startRow, int endRow) {
		return partLifeTimeManager.getPartLifeTime(null, 0, -1);
	}
}