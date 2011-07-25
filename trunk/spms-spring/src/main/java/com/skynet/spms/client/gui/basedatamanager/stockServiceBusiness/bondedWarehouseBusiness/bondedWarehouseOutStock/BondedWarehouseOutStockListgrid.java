/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseOutStock;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class BondedWarehouseOutStockListgrid extends ListGrid {

	private Logger log = Logger.getLogger("BondedWarehouseInStockListgrid");

	public void drawCredentialsRecordListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 保税库出库编号
		ListGridField credentialsCodeFiled = new ListGridField("bondedOutStockNumber");
		// 出仓日期
		ListGridField stackPositionCodeFiled = new ListGridField("outStockDate");
		stackPositionCodeFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 合同编号
		ListGridField credentialsFiled = new ListGridField("contratNumber");
		// 发送给
		ListGridField contratNumberFiled = new ListGridField("sendTo");
		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		// 关键字
		ListGridField partSerialNumberFiled = new ListGridField("keyword");
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		// 计量单位 
		ListGridField unitFiled = new ListGridField("unitOfMeasure");
		// 仓位号
		ListGridField providerNameFiled = new ListGridField("position");
        
		credentialsCodeFiled.setCanFilter(true);
		stackPositionCodeFiled.setCanFilter(true);
		credentialsFiled.setCanFilter(true);
		contratNumberFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		unitFiled.setCanFilter(true);
		providerNameFiled.setCanFilter(true);
		
		setFields(credentialsCodeFiled
				,stackPositionCodeFiled
				,credentialsFiled
				,contratNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,quantityFiled
				,unitFiled
				,providerNameFiled
				);
		setCellHeight(22);
	}

}
