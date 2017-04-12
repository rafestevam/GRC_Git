package com.idsscheer.webapps.arcm.common.constants.metadata.attribute;

import com.idsscheer.webapps.arcm.config.metadata.objecttypes.BooleanAttributeType;
import com.idsscheer.webapps.arcm.config.metadata.objecttypes.IDateAttributeType;
import com.idsscheer.webapps.arcm.config.metadata.objecttypes.IEnumAttributeType;
import com.idsscheer.webapps.arcm.config.metadata.objecttypes.IStringAttributeType;
import com.idsscheer.webapps.arcm.config.metadata.objecttypes.StringAttributeType;

public interface IIssueAttributeTypeCustom extends IIssueAttributeType {
	
	public static final String STR_REPLANNED="replanned";
	public static final BooleanAttributeType ATTR_REPLANNED=(BooleanAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_REPLANNED);
	
	public static final String STR_ISSUE_YEAR="issue_year";
	public static final IStringAttributeType ATTR_ISSUE_YEAR=(IStringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_ISSUE_YEAR);
	
	public static final String STR_RA_RESULT="ra_result";
	public static final StringAttributeType  ATTR_RA_RESULT= (StringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESULT);
	
	public static final String STR_RA_RESIDUALFINAL="ra_residualfinal";
	public static final StringAttributeType  ATTR_RA_RESIDUALFINAL= (StringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RA_RESIDUALFINAL);
	
	public static final String STR_RSK_NAME="rsk_name";
	public static final StringAttributeType  ATTR_RSK_NAME= (StringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_RSK_NAME);
	
	public static final String STR_CST_MODELNAME="cst_modelname";	
	public static final StringAttributeType  ATTR_CST_MODELNAME= (StringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_CST_MODELNAME);
		
	public static final String STR_CST_PROCESS="cst_process";	
	public static final StringAttributeType  ATTR_CST_PROCESS= (StringAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_CST_PROCESS);
	
	public static final String STR_ACTIONTYPE = "action_type";
	public static final IEnumAttributeType ATTR_ACTIONTYPE = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_ACTIONTYPE);
	
	public static final String STR_IS_CREATOR_STATUS = "custom_is_creator_status";
	public static final IEnumAttributeType ATTR_IS_CREATOR_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_IS_CREATOR_STATUS);
	
	public static final String STR_IS_OWNER_STATUS = "custom_is_owner_status";
	public static final IEnumAttributeType ATTR_IS_OWNER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_IS_OWNER_STATUS);
	
	public static final String STR_IS_REVIEWER_STATUS = "custom_is_reviewer_status";
	public static final IEnumAttributeType ATTR_IS_REVIEWER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_IS_REVIEWER_STATUS);
	
	public static final String STR_AP_CREATOR_STATUS = "custom_ap_creator_status";
	public static final IEnumAttributeType ATTR_AP_CREATOR_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_AP_CREATOR_STATUS);

	public static final String STR_AP_OWNER_STATUS = "custom_ap_owner_status";
	public static final IEnumAttributeType ATTR_AP_OWNER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_AP_OWNER_STATUS);
	
	public static final String STR_AP_REVIEWER_STATUS = "custom_ap_reviewer_status";
	public static final IEnumAttributeType ATTR_AP_REVIEWER_STATUS = (IEnumAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_AP_REVIEWER_STATUS);
 
	public static final String STR_ISSUE_DATE="issue_date";
	public static final IDateAttributeType   ATTR_ISSUE_DATE=(IDateAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_ISSUE_DATE);
	
	public static final String STR_ENDDATEACTIONPLAN="enddateactionplan";
	public static final IDateAttributeType ATTR_ENDDATEACTIONPLAN=(IDateAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_ENDDATEACTIONPLAN);
	
	public static final String STR_ENDDATEISSUE="enddateissue";
	public static final IDateAttributeType ATTR_ENDDATEISSUE=(IDateAttributeType)MetadataConstantsUtil.create(OBJECT_TYPE, STR_ENDDATEISSUE);
	
}
