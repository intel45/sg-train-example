package com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.ui;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.add.GuaranteeAddWin;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.biz.GuaranteeBiz;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.model.GuaranteeModelLocator;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.update.GuaranteeUpdateWin;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.view.GuaranteeViewWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 担保索赔申请单操作按钮
 * 
 * @author fl
 * 
 */
public class GuaranteeToolStrip extends BaseButtonToolStript {

	private GuaranteeGrid mainGrid;
	public GuaranteeModelLocator modelLocator = GuaranteeModelLocator
			.getInstance();

	public GuaranteeToolStrip(GuaranteeGrid mainGrid) {
		super(DSKey.C_GUARANTEESHEET);
		setWidth100();
		this.mainGrid = mainGrid;
	}

	GuaranteeBiz biz = new GuaranteeBiz();

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			GuaranteeAddWin ppa = new GuaranteeAddWin("新建担保索赔申请单", true, null,
					null, null);
			modelLocator.openedWindow = ppa;
			ShowWindowTools.showWondow(button.getPageRect(), ppa, 200);
		} else if ("MODIFY".equals(buttonName)) {
			if (biz.canModifiedSheet(modelLocator.mainSheetGrid)) {
				ListGridRecord record = modelLocator.mainSheetGrid
						.getSelectedRecord();
				modelLocator.sheetID = record.getAttribute("id");
				GuaranteeUpdateWin updateWin = new GuaranteeUpdateWin(
						"修改担保申请单", true, button.getPageRect(), null, null);
				modelLocator.openedWindow = updateWin;
				ShowWindowTools.showWindow(button.getPageRect(), updateWin, 1);
			}
		} else if ("DELETE".equals(buttonName)) {
			biz.deleteSheet(modelLocator.mainSheetGrid);
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			biz.publishSheet(mainGrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			biz.cancelPublishSheet(mainGrid);
		} else if ("VIEW".equals(buttonName)) {
			ListGridRecord record = modelLocator.mainSheetGrid
					.getSelectedRecord();
			if (record == null) {
				SC.say("请选择要查看的申请单！");
				return;
			}
			modelLocator.sheetID = record.getAttribute("id");
			GuaranteeViewWin win = new GuaranteeViewWin("查看担保索赔申请单", true,
					button.getPageRect(), null, null);
			modelLocator.openedWindow = win;
			ShowWindowTools.showWindow(button.getPageRect(), win, 1);
		} else if ("REFRESH".equals(buttonName)) {
			modelLocator.mainSheetGrid.invalidateCache();
			modelLocator.mainSheetGrid.fetchData();
		}
	}
}
