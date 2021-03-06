package com.crm.enums;

public enum transsalestotalStateEnum implements NameValueEnum{
	type_ENABL(0,"删除"),
	type_ENABLE(1,"正常"),
	type_ENAB(2,"退货取消"),
	type_ENA(3,"积分补录"),
	;
    /**
     * 数据库对应的值
     */
    private Integer value;
	/**
	 * 页面显示的值
	 */
	private String displayName;
	
	public static String getDisplayName(Integer value){
		for (transsalestotalStateEnum enums : transsalestotalStateEnum.values()) {
			if(enums.value == value){
				return enums.displayName;
			}
		}
		return "";
	}
	
	private transsalestotalStateEnum(Integer value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
	
	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return value;
	}



}
