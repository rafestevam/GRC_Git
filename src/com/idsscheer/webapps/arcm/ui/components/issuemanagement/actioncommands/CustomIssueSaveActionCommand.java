package com.idsscheer.webapps.arcm.ui.components.issuemanagement.actioncommands;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.idsscheer.batchserver.logging.Logger;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.IAppObj;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.IAppObjFacade;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.attribute.IDateAttribute;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.attribute.IEnumAttribute;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.attribute.IListAttribute;
import com.idsscheer.webapps.arcm.common.constants.metadata.ObjectType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IIssueAttributeType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IIssueAttributeTypeCustom;
import com.idsscheer.webapps.arcm.common.notification.NotificationTypeEnum;
import com.idsscheer.webapps.arcm.common.util.ARCMCollections;
import com.idsscheer.webapps.arcm.common.util.ovid.IOVID;
import com.idsscheer.webapps.arcm.config.metadata.enumerations.IEnumerationItem;

public class CustomIssueSaveActionCommand extends IssueSaveActionCommand  {
	
	private static final com.idsscheer.batchserver.logging.Logger debuglog = new com.idsscheer.batchserver.logging.Logger();	
	private static final boolean DEBUGGER_ON = true;
	protected void afterExecute(){
			
		
		//IUIEnvironment currEnv = this.environment;
		IAppObj currIssueAppObj = this.formModel.getAppObj();
		IListAttribute iroList = currIssueAppObj.getAttribute(IIssueAttributeType.LIST_ISSUERELEVANTOBJECTS);
		List<IAppObj> iroElements = iroList.getElements(this.getUserContext());
		Iterator<IAppObj> iroIterator = iroElements.iterator();
		
		IAppObjFacade issueFacade = this.environment.getAppObjFacade(ObjectType.ISSUE);
		
		IEnumAttribute issueTypeList = currIssueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_ACTIONTYPE);
		IEnumerationItem issueType = ARCMCollections.extractSingleEntry(issueTypeList.getRawValue(), true);
		
		 if(issueType.getId().equals("actionplan")){					
			this.displayLog("Tipo dentro do if: " + issueType.getId());		
			//this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, "Data Fim: " + iroIterator.hasNext(), new String[] { getStringRepresentation(this.formModel.getAppObj()) });
			
			try{

				while(iroIterator.hasNext()){
					
					IAppObj iroAppObj = iroIterator.next();
					IOVID iroOVID = iroAppObj.getVersionData().getHeadOVID();
					IAppObj iroUpdObj = issueFacade.load(iroOVID, true);
					//currIssueAppObj.getAttribute(IIssueAttributeType.LIST_ISSUERELEVANTOBJECTS).removeElement(iroAppObj, this.getUserContext());
					//currIssueAppObj.getAttribute(IIssueAttributeType.LIST_ISSUERELEVANTOBJECTS).g(iroAppObj, this.getUserContext());
							
					if(iroAppObj.getObjectType() != ObjectType.ISSUE)
						continue;
					
					issueFacade.allocateWriteLock(iroUpdObj.getVersionData().getHeadOVID());
//					this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, "write lock - version " + String.valueOf(iroAppObj.getVersionData().getSubstituteUserOVID()) , new String[] { getStringRepresentation(this.formModel.getAppObj()) });
					
					
					IDateAttribute actplnenddate = currIssueAppObj.getAttribute(IIssueAttributeType.ATTR_PLANNEDENDDATE);
					Date actplnenddateValue = actplnenddate.getRawValue();
					
					IDateAttribute issueenddate = iroUpdObj.getAttribute(IIssueAttributeType.ATTR_PLANNEDENDDATE);
					Date issueendtateValue = issueenddate.getRawValue();
					
					
					IDateAttribute currDataFim = currIssueAppObj.getAttribute(IIssueAttributeType.ATTR_PLANNEDENDDATE);
					Date currDataFimValue = currDataFim.getRawValue();
	
					IDateAttribute dataFim = iroUpdObj.getAttribute(IIssueAttributeType.ATTR_PLANNEDENDDATE);								
					Date dataFimValue = dataFim.getRawValue();
							
					Logger.info(CustomIssueSaveActionCommand.class.getName(), "---->" +String.valueOf(actplnenddateValue));					
					
					this.displayLog("DaTa issue date : " + issueendtateValue );
					if(actplnenddateValue.after(issueendtateValue)){
					
						iroUpdObj.getAttribute(IIssueAttributeTypeCustom.ATTR_PLANNEDENDDATE).setRawValue(actplnenddateValue);
						iroUpdObj.getAttribute(IIssueAttributeTypeCustom.ATTR_REPLANNED).setRawValue("True");
						currIssueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_REPLANNED).setRawValue("True");	
					}
					
//					IBooleanAttribute replanned = iroUpdObj.getAttribute(IIssueAttributeTypeCustom.ATTR_REPLANNED);								
//					Boolean replannedValue = replanned.getRawValue();
					this.displayLog("DATa currDataFim : " + currDataFimValue );
					if(currDataFimValue.after(dataFimValue)){

						iroUpdObj.getAttribute(IIssueAttributeType.ATTR_PLANNEDENDDATE).setRawValue(currDataFimValue);
						iroUpdObj.getAttribute(IIssueAttributeTypeCustom.ATTR_REPLANNED).setRawValue("True");
						this.displayLog("DATa : " + currDataFimValue );
//						this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, "Nova Data do apontamento..: " + currDataFimValue , new String[] { getStringRepresentation(this.formModel.getAppObj()) });
					}
//					this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, "Save lock - version " + String.valueOf(iroAppObj.getVersionData().getHeadOVID()) , new String[] { getStringRepresentation(this.formModel.getAppObj()) });
					issueFacade.save(iroUpdObj, this.getDefaultTransaction(), true);
					issueFacade.releaseLock(iroUpdObj.getVersionData().getHeadOVID());
//					this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, "Passei facede Data apontamento " + currDataFimValue , new String[] { getStringRepresentation(this.formModel.getAppObj()) });
					break;
				}
			}catch(Exception e){
				this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, e.getMessage() , new String[] { getStringRepresentation(this.formModel.getAppObj()) });
			}
		}
		
	}
	
	private void displayLog(String message){
		if(DEBUGGER_ON){
			debuglog.info(this.getClass().getName(),message );
		}
	}
}
