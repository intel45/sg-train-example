package com.skynet.spms.client.gui.customerService.leaseService.leasecontract.update;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author tqc
 * 
 */
public class ContractBaseForm extends VLayout {
	private MainModelLocator modellocator = MainModelLocator.getInstance();
	private static final String uploadUrl = "spms/upload.do";

	public final static LayoutDynamicForm form = new LayoutDynamicForm();
	private LeaseRequisitionSheetBusiness business = new LeaseRequisitionSheetBusiness();
	/** 运代商联系人 **/
	TextItem carrierLinkManItem;
	/** 运代商联系方式 **/
	TextItem carrierLinkWayItem;
	/** 金额总计 **/
	TextItem extendedValueTotalAmountItem;

	/** 项数总计 **/
	TextItem quantityItem;

	public ContractBaseForm(final ListGrid lg) {
		this.setAutoHeight();

		form.setDataSource(modellocator.leaseContractListGrid.getDataSource());
		form.reset();
		form.editSelectedData(modellocator.leaseContractListGrid);

		form.setNumCols(6);
		form.setCellPadding(2);

		TextItem rqid = new TextItem();
		rqid.setName("rqId");
		rqid.setValue(modellocator.leaseContractListGrid.getSelectedRecord()
				.getAttribute("id"));
		rqid.setVisible(false);

		TextItem contratNumberItem = new TextItem();
		contratNumberItem.setName("contractNumber");
		contratNumberItem.setTitle("合同编号");
		contratNumberItem.setDisabled(true);

		// 租赁申请号
		TextItem codeItem = new TextItem();
		codeItem.setName("leaseRequisitionNumber");
		codeItem.setDisabled(true);
		codeItem.setTitle("租赁申请号");

		// 优先级
		DicSelectItem priorityItem = new DicSelectItem();
		priorityItem.setTitle("优先级");
		priorityItem.setName("m_Priority");
		// 客户
		SelectItem supplierItem = new SelectItem();
		supplierItem.setTitle("客户");
		supplierItem.setName("buyer.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
				supplierItem);
		// 联系人
		TextItem linkManItem = new TextItem();
		linkManItem.setName("linkman");
		linkManItem.setTitle("联系人");

		// 飞机机尾号
		DicSelectItem planeLastNumberItem = new DicSelectItem();
		planeLastNumberItem.setName("aiNumber");
		planeLastNumberItem.setTitle("飞机机尾号");

		// 联系方式
		TextItem linkWayItem = new TextItem();
		linkWayItem.setName("contactInformation");
		linkWayItem.setColSpan(3);
		linkWayItem.setTitle("联系方式");

		// 交货方式
		DicSelectItem deliveryTypeItem = new DicSelectItem();
		deliveryTypeItem.setName("m_DeliveryType");
		deliveryTypeItem.setTitle("交货方式");

		// 由供应商指定运代
		CheckboxItem isBuyerFreightAgentItem = new CheckboxItem();
		isBuyerFreightAgentItem.setName("buyerFreightAgent");
		isBuyerFreightAgentItem.setColSpan(3);
		isBuyerFreightAgentItem.setTitle("由供应商指定运代");

		// 运输方式
		DicSelectItem transportationCodeItem = new DicSelectItem();
		transportationCodeItem.setName("m_TransportationCode");
		transportationCodeItem.setTitle("运输方式");

		// 运代商
		final SelectItem carrierNameItem = new SelectItem();
		carrierNameItem.setName("carrierName.id");
		carrierNameItem.setTitle("运代商");
		carrierNameItem.setDisabled(true);
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAMECODE, carrierNameItem);

		carrierNameItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				String id = carrierNameItem.getValueAsString();
				CodeRPCTool.bindCarrierData(id, carrierLinkManItem,
						carrierLinkWayItem);
			}
		});

		// 运代商联系人
		carrierLinkManItem = new TextItem();
		carrierLinkManItem.setName("appointForwarderLinkman");
		carrierLinkManItem.setTitle("联系人");
		carrierLinkManItem.setDisabled(true);

		// 贸易方式
		SelectItem tradeMethodItem = new SelectItem();
		tradeMethodItem.setName("m_TradeMethods");
		tradeMethodItem.setTitle("贸易方式");

		// 运代商联系方式
		carrierLinkWayItem = new TextItem();
		carrierLinkWayItem.setName("appointForwarderContact");
		carrierLinkWayItem.setTitle("联系方式");
		carrierLinkWayItem.setDisabled(true);
		carrierLinkWayItem.setColSpan(3);
		// 判断是否选中复选框
		isBuyerFreightAgentItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				carrierNameItem.setDisabled(!((Boolean) event.getValue()));
				carrierLinkManItem.setDisabled(!((Boolean) event.getValue()));
				carrierLinkWayItem.setDisabled(!((Boolean) event.getValue()));
			}
		});

		// GTA协议
		DicSelectItem enterpriseGTAItem = new DicSelectItem();
		enterpriseGTAItem.setName("enterpriseIds");
		enterpriseGTAItem.setMultiple(true);
		enterpriseGTAItem.setTitle("GTA协议");

		// 支付方式
		DicSelectItem paymentItem = new DicSelectItem();
		paymentItem.setName("m_Payment");
		paymentItem.setTitle("支付方式");

		// 支付说明
		TextItem paymentExplainItem = new TextItem();
		paymentExplainItem.setName("paymentExplain");
		paymentExplainItem.setColSpan(3);
		paymentExplainItem.setTitle("支付说明");

		// 特殊条款
		TextAreaItem extraProvisionItem = new TextAreaItem();
		extraProvisionItem.setName("extraProvision");
		extraProvisionItem.setColSpan(3);
		extraProvisionItem.setTitle("特殊条款");
		extraProvisionItem.setTitleVAlign(VerticalAlignment.TOP);
		extraProvisionItem.setHeight(50);

		// 备注
		TextAreaItem remarkTextItem = new TextAreaItem();
		remarkTextItem.setName("remarkText");
		remarkTextItem.setTitle("备注");
		remarkTextItem.setTitleVAlign(VerticalAlignment.TOP);
		remarkTextItem.setHeight(50);

		// 数量总计
		TextItem quantityItem = new TextItem();
		quantityItem.setName("quantity");
		quantityItem.setTitle("项数总计");
		quantityItem.setDisabled(true);

		// 金额总计
		TextItem extendedValueTotalAmountItem = new TextItem();
		extendedValueTotalAmountItem.setName("extendedValueTotalAmount");
		extendedValueTotalAmountItem.setTitle("金额总计");
		extendedValueTotalAmountItem.setDisabled(true);

		// 制定人
		TextItem makeByItem = new TextItem();
		makeByItem.setName("makeBy");
		makeByItem.setTitle("制定人");

		form.setFields(rqid, contratNumberItem, codeItem, priorityItem,
				supplierItem, linkManItem, planeLastNumberItem, linkWayItem,
				deliveryTypeItem, isBuyerFreightAgentItem,
				transportationCodeItem, carrierNameItem, carrierLinkManItem,
				tradeMethodItem, carrierLinkWayItem, enterpriseGTAItem,
				paymentItem, paymentExplainItem, extraProvisionItem,
				remarkTextItem, makeByItem, quantityItem,
				extendedValueTotalAmountItem);

		String id = modellocator.leaseContractListGrid.getSelectedRecord()
				.getAttribute("buyer.id");
		CodeRPCTool.bindCustomerData(id, linkManItem, linkWayItem,
				enterpriseGTAItem);

		Timer timer = Utils.startAmountTimer(lg, quantityItem,
				extendedValueTotalAmountItem, "price");
		timer.scheduleRepeating(500);

		this.addMember(form);

		/** 操作按钮 * */
		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		btnGroup.setLayoutLeftMargin(10);

		IButton btnSave = new IButton("保存");
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("保存成功");
						String leaseContractId = response.getData()[0]
								.getAttribute("id");
						modellocator.LeaseContractId = leaseContractId;
						modellocator.defaultUploader.setVisible(true);
						modellocator.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + modellocator.LeaseContractId);
						business
								.refeshListGrid(modellocator.leaseContractListGrid);
					}
				});
			}
		});
		btnGroup.addMember(btnSave);
		this.addMember(btnGroup);
	}
}
