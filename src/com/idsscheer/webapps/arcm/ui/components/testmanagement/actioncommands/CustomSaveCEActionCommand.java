package com.idsscheer.webapps.arcm.ui.components.testmanagement.actioncommands;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.idsscheer.webapps.arcm.bl.models.objectmodel.IAppObj;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.IAppObjFacade;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.attribute.IEnumAttribute;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.query.IAppObjIterator;
import com.idsscheer.webapps.arcm.bl.models.objectmodel.query.IAppObjQuery;
import com.idsscheer.webapps.arcm.common.constants.metadata.ObjectType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IControlAttributeType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IControlexecutionAttributeType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IControlexecutionAttributeTypeCustom;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IRiskAttributeType;
import com.idsscheer.webapps.arcm.common.constants.metadata.attribute.IRiskAttributeTypeCustom;
import com.idsscheer.webapps.arcm.common.util.ARCMCollections;
import com.idsscheer.webapps.arcm.common.util.ovid.IOVID;
import com.idsscheer.webapps.arcm.config.metadata.enumerations.IEnumerationItem;
import com.idsscheer.webapps.arcm.ui.framework.actioncommands.object.BaseSaveActionCommand;

public class CustomSaveCEActionCommand extends BaseSaveActionCommand {
	
/*	private String viewName = "customcontrol2risk";
	private String view_column_obj_id = "risk_obj_id";
	private String view_column_version_number = "risk_version_number";
	private String view_ce = "customCET2CtrlExec";
	private String view_ce_obj_id = "obj_id";
	private String view_ce_version_number = "version_number";*/
	private String riscoPotencial = "";
	final Logger log = Logger.getLogger(CustomSaveCEActionCommand.class.getName());

	protected void afterExecute(){
		
		IAppObj currAppObj = this.formModel.getAppObj();
		IAppObj currParentCtrlObj = this.parentControl(currAppObj);
		
		try{
		
			IAppObj riskParentObj = this.getRiskFromControl(currParentCtrlObj);
			log.info("risk parent obj: " + riskParentObj.toString());
			this.riscoPotencial = riskParentObj.getAttribute(IRiskAttributeTypeCustom.ATTR_RA_RESULT).getRawValue();
			this.affectResidualRisk(riskParentObj);
			
			
		}catch(Exception e){
			//this.environment.getDialogManager().createSilentForwardDialog("ERRO", e.getMessage());
			this.environment.getDialogManager().getNotificationDialog().addInfo(e.getMessage());
		}		
		
	}
	
	private IAppObj parentControl(IAppObj childAppObj){
		
		IAppObj parentControlObj = null;
		
		List<IAppObj> controlList = childAppObj.getAttribute(IControlexecutionAttributeType.LIST_CONTROL).getElements(this.getUserContext());
		for(IAppObj controlObj : controlList){
			parentControlObj = controlObj;
			//parentControlName = controlObj.getAttribute(IControlAttributeType.ATTR_NAME).getRawValue();
		}
		
		return parentControlObj;
		
	}
	
	private IAppObj getRiskFromControl(IAppObj controlObj) throws Exception{
		
		IAppObjFacade riskFacade = this.environment.getAppObjFacade(ObjectType.RISK);
		IAppObjQuery riskQuery = riskFacade.createQuery();
		IAppObjIterator riskIterator = riskQuery.getResultIterator();
		IAppObj riskAppObj = null;
		log.info("infra para obten��o de risco pronta");
		
		while(riskIterator.hasNext()){
			
			IAppObj riskObj = riskIterator.next();
			log.info("risco lido" + riskObj.getAttribute(IRiskAttributeType.ATTR_NAME).getRawValue());
			
			if(!(riskAppObj == null))
				break;
			
			List<IAppObj> ctrlList = riskObj.getAttribute(IRiskAttributeType.LIST_CONTROLS).getElements(this.getUserContext());
			for(IAppObj ctrlObj : ctrlList){
				if(ctrlObj.getGuid().equals(controlObj.getGuid())){
					log.info("risco do controle" + riskObj.getAttribute(IRiskAttributeType.ATTR_NAME).getRawValue());
					riskAppObj = riskObj;
					break;
				}
			}
			
		}
		riskQuery.release();
		
		return riskAppObj;
		
		
		/*Map filterMap = new HashMap();
		//filterMap.put("control_obj_id", controlObj.getAttribute(IControlAttributeType.ATTR_CONTROL_ID).getRawValue());
		log.info("RFC filtermap declarado");
		log.info("RFC Control ID: " + controlObj.getAttribute(IControlAttributeType.ATTR_CONTROL_ID).getRawValue());
				
		IAppObj riskReturn = null;
		IAppObjFacade riskFacade = this.environment.getAppObjFacade(ObjectType.RISK);
		log.info("RFC risk facade declarado");
		log.info("RFC " + riskFacade.toString());
		
		log.info(this.viewName);
		IViewQuery query = QueryFactory.createQuery(this.getUserContext(), this.viewName, filterMap, null,
				true, this.getDefaultTransaction());
		
		log.info(query.getSize());
		
		try{
			
			log.info("RFC query executada");
			
			Iterator it = query.getResultIterator();
			while(it.hasNext()){
				
				log.info("RFC iterator");
				
				IViewObj viewObj = (IViewObj) it.next();
				Long obj_id = (Long) viewObj.getRawValue(view_column_obj_id);
				log.info("RFC obj_id: " + String.valueOf(obj_id));
				Long version_number = (Long) viewObj.getRawValue(view_column_version_number);
				log.info("RFC version_number: " + String.valueOf(version_number));
				
				IOVID riskOVID = OVIDFactory.getOVID(obj_id.longValue(), version_number.longValue());
				log.info("RFC riskOVID: " + riskOVID.toString());
				riskReturn = riskFacade.load(riskOVID, true);
				log.info("RFC riskOBJ carregado: " + riskReturn.toString());
				
			}
			query.release();
			return riskReturn;
			
		}catch(RightException re){
			log.info("RFC Exce��o Direitos: " + re.getMessage());
			throw (Exception)re;
		}catch(ObjectLockException ole){
			log.info("RFC Exce��o Objeto Lockado: " + ole.getMessage());
//			throw (Exception)ole;
		}catch(ValidationException ve){
			log.info("RFC Exce��o Validation: " + ve.getMessage());
//			throw ve;
		}catch(ObjectNotUniqueException onue){
			log.info("RFC Exce��o Obj Nao Unico: " + onue.getMessage());
//			throw onue;
		}catch(ObjectAccessException oae){
			log.info("RFC Exce��o Obj Sem Acesso: " + oae.getMessage());
//			throw oae;
		}catch(Exception e){
			log.info("RFC Exce��o Query: " + e.getMessage());
			throw e;
		}*/
		
	}
	
	private void affectResidualRisk(IAppObj riskObj) throws Exception{
		
		double countTotal = 0;
		double count1line = 0;
		
		try{
			
			IAppObjFacade riskFacade = this.environment.getAppObjFacade(ObjectType.RISK);
			IOVID riskOVID = riskObj.getVersionData().getHeadOVID();
			IAppObj riskUpdObj = riskFacade.load(riskOVID, true);
			riskFacade.allocateWriteLock(riskOVID);
		
			List<IAppObj> controlList = riskObj.getAttribute(IRiskAttributeType.LIST_CONTROLS).getElements(this.getUserContext());
			for(IAppObj controlObj : controlList){
				
				List<IAppObj> cetList = controlObj.getAttribute(IControlAttributeType.LIST_CONTROLEXECUTIONTASKS).getElements(this.getUserContext());
				for(IAppObj cetObj : cetList){
					
					List<IAppObj> ceList = this.getCtrlExecFromCET(cetObj);
					for(IAppObj ceObj : ceList){
						countTotal += 1;
						IEnumAttribute statusAttr = ceObj.getAttribute(IControlexecutionAttributeTypeCustom.ATTR_CUSTOMCTRLEXECSTATUS);
						IEnumerationItem statusItem = ARCMCollections.extractSingleEntry(statusAttr.getRawValue(), true);
						if(statusItem.getId().equals("ineffective")){
							count1line += 1;
						}	
					}
					
				}
				
			}
			
			double risk1line = ( count1line / countTotal );
			String riskClass1line = this.riskClassification(risk1line);
			riskUpdObj.getAttribute(IRiskAttributeTypeCustom.ATTR_RA_CONTROL1LINE).setRawValue(riskClass1line);
			
			String riskClass2line = riskUpdObj.getAttribute(IRiskAttributeTypeCustom.ATTR_RA_CONTROL2LINE).getRawValue();
			if(riskClass2line == null)
				riskClass2line = "";
				
			String riskClass3line = riskUpdObj.getAttribute(IRiskAttributeTypeCustom.ATTR_RA_CONTROL3LINE).getRawValue();
			if(riskClass3line == null)
				riskClass3line = "";
			
			String riskClassFinal = this.riskFinalClassification(riskClass1line, riskClass2line, riskClass3line);
			
			String riskResidualFinal = this.riskResidualFinal(this.riscoPotencial, riskClassFinal);
			riskUpdObj.getAttribute(IRiskAttributeTypeCustom.ATTR_RA_RESIDUALFINAL).setRawValue(riskResidualFinal);
			
			riskFacade.save(riskUpdObj, this.getDefaultTransaction(), true);
			riskFacade.releaseLock(riskOVID);
		
		}
		catch(Exception e){
			throw e;
		}
		
	}
	
	private List<IAppObj> getCtrlExecFromCET(IAppObj cetObj) throws Exception{
		
		IAppObjFacade ceFacade = this.environment.getAppObjFacade(ObjectType.CONTROLEXECUTION);
		IAppObjQuery ceQuery = ceFacade.createQuery();
		IAppObjIterator ceIterator = ceQuery.getResultIterator();
		List<IAppObj> ceListReturn = new ArrayList<IAppObj>();
		log.info("Infra para obten��o de Control Execution");
		
		while(ceIterator.hasNext()){
			
			IAppObj ceObj = ceIterator.next();
			log.info("CExec Lido: " + ceObj.getAttribute(IControlexecutionAttributeType.ATTR_NAME).getRawValue());
			
			List<IAppObj> ctList = ceObj.getAttribute(IControlexecutionAttributeType.LIST_CONTROLEXECUTIONTASK).getElements(this.getUserContext());
			for(IAppObj ctObj : ctList){
				if(ctObj.getGuid().equals(cetObj.getGuid())){
					log.info("CExec Adicionado: " + ceObj.getAttribute(IControlexecutionAttributeType.ATTR_NAME).getRawValue());
					ceListReturn.add(ceObj);
				}
			}
			
		}
		ceQuery.release();
		
		return (List<IAppObj>)ceListReturn;
		
		/*Map filterMap = new HashMap();
		List<IAppObj> ceReturn = new ArrayList<IAppObj>();
		IAppObjFacade ceFacade = this.environment.getAppObjFacade(ObjectType.CONTROLEXECUTION);
		log.info("CEFCET - Facade Criado: " + ceFacade.toString());
		
		IViewQuery query = QueryFactory.createQuery(this.getUserContext(), this.view_ce, filterMap, null,
				true, this.getDefaultTransaction());
		
		try{
			
			log.info("CEFCET - Query Executada");
			
			Iterator it = query.getResultIterator();
			while(it.hasNext()){
				
				IViewObj viewObj = (IViewObj) it.next();
				Long obj_id = (Long) viewObj.getRawValue(this.view_ce_obj_id);
				log.info("CEFCET - CE Obj ID: " + String.valueOf(obj_id));
				Long version_number = (Long) viewObj.getRawValue(this.view_ce_version_number);
				log.info("CEFCET - CE Vers Number: " + String.valueOf(version_number));
				
				IOVID ceOVID = OVIDFactory.getOVID(obj_id.longValue(), version_number.longValue());
				log.info("CEFCET ceOVID: " + ceOVID.toString());
				ceReturn.add(ceFacade.load(ceOVID, true));
				log.info("CEFCET ceOBJ Carregado: " + ceOVID.toString());
				
			}
			query.release();
			return (List<IAppObj>)ceReturn;
			
		}catch(Exception e){
			log.info("CEFCET Exception: " + e.getMessage());
			throw e;
		}*/

	}
	
	private String riskClassification(double riskVuln){
		
		String riskClassif = "";
		
		if( (riskVuln >= 0.00) && (riskVuln <= 0.19) ){
			riskClassif = "Baixo";
		}
		
		if( (riskVuln >= 0.20) && (riskVuln <= 0.49) ){
			riskClassif = "M�dio";
		}
		
		if( (riskVuln >= 0.50) && (riskVuln <= 0.69) ){
			riskClassif = "Alto";
		}
		
		if( (riskVuln >= 0.70) && (riskVuln <= 1.00) ){
			riskClassif = "Muito Alto";
		}
		
		return riskClassif;
		
	}		
	
	private String riskFinalClassification(String risk1line, String risk2line, String risk3line){
		
		int height_1line = 0;
		int height_2line = 0;
		int height_3line = 0;
		String riskClassFinal = "";
		
		//Classifica��o - Amb. Controles 1a Linha
		if(risk1line.equalsIgnoreCase("Muito Alto"))
			height_1line = 4;
		if(risk1line.equalsIgnoreCase("Alto"))
			height_1line = 3;
		if(risk1line.equalsIgnoreCase("M�dio"))
			height_1line = 2;
		if(risk1line.equalsIgnoreCase("Baixo"))
			height_1line = 1;
		
		//Classifica��o - Amb. Controles 2a Linha
		if(risk2line.equalsIgnoreCase("Muito Alto"))
			height_2line = 4;
		if(risk2line.equalsIgnoreCase("Alto"))
			height_2line = 3;
		if(risk2line.equalsIgnoreCase("M�dio"))
			height_2line = 2;
		if(risk2line.equalsIgnoreCase("Baixo"))
			height_2line = 1;
		
		//Classifica��o - Amb. Controles 3a Linha
		if(risk3line.equalsIgnoreCase("Muito Alto"))
			height_3line = 4;
		if(risk3line.equalsIgnoreCase("Alto"))
			height_3line = 3;
		if(risk3line.equalsIgnoreCase("M�dio"))
			height_3line = 2;
		if(risk3line.equalsIgnoreCase("Baixo"))
			height_3line = 1;		
		
		int maxHeightCtrl = Math.max(height_1line, Math.max(height_2line, height_3line));
		switch(maxHeightCtrl){
		case 4:
			riskClassFinal = "Muito Alto";
		case 3:
			riskClassFinal = "Alto";
		case 2:
			riskClassFinal = "M�dio";
		case 1:
			riskClassFinal = "Baixo";
		}
		
		return riskClassFinal;
		
	}
	
	private String riskResidualFinal(String riskPotencial, String riskControlFinal){
		
		String riskResidualReturn = "";
		
		if(riskPotencial.equals("Muito Alto") && riskControlFinal.equals("Muito Alto"))
			riskResidualReturn = "Muito Alto";
		
		if(riskPotencial.equals("Muito Alto") && riskControlFinal.equals("Alto"))
			riskResidualReturn = "Muito Alto";
		
		if(riskPotencial.equals("Muito Alto") && riskControlFinal.equals("M�dio"))
			riskResidualReturn = "Alto";
		
		if(riskPotencial.equals("Muito Alto") && riskControlFinal.equals("Baixo"))
			riskResidualReturn = "M�dio";
		
		if(riskPotencial.equals("Alto") && riskControlFinal.equals("Muito Alto"))
			riskResidualReturn = "Alto";
		
		if(riskPotencial.equals("Alto") && riskControlFinal.equals("Alto"))
			riskResidualReturn = "Alto";
		
		if(riskPotencial.equals("Alto") && riskControlFinal.equals("M�dio"))
			riskResidualReturn = "M�dio";
		
		if(riskPotencial.equals("Alto") && riskControlFinal.equals("Baixo"))
			riskResidualReturn = "M�dio";
		
		if(riskPotencial.equals("M�dio") && riskControlFinal.equals("Muito Alto"))
			riskResidualReturn = "M�dio";
		
		if(riskPotencial.equals("M�dio") && riskControlFinal.equals("Alto"))
			riskResidualReturn = "M�dio";
		
		if(riskPotencial.equals("M�dio") && riskControlFinal.equals("M�dio"))
			riskResidualReturn = "M�dio";
		
		if(riskPotencial.equals("M�dio") && riskControlFinal.equals("Baixo"))
			riskResidualReturn = "Baixo";
		
		return riskResidualReturn;
		
	}	
	
/*	
		//this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, "saveCET", new String[] { getStringRepresentation(this.formModel.getAppObj()) });
		IAppObj currAppObj = this.formModel.getAppObj();
		IUIEnvironment currEnv = this.environment;
		String currCtrlParentName = this.parentControl(currAppObj);
		
		IEnumAttribute ra_control1lineattr = currAppObj.getAttribute(IControlexecutionAttributeTypeCustom.ATTR_CUSTOMCTRLEXECSTATUS);
		IEnumerationItem ra_control1lineitem = ARCMCollections.extractSingleEntry(ra_control1lineattr.getRawValue(), true);
		// = ra_control1lineitem.getParameter(IControlexecutionAttributeTypeCustom.STR_CUSTOMCTRLEXECSTATUS);
		String ra_control1line = ra_control1lineitem.getId();
		//log.info("ra_control1line: " + ra_control1line);
		
		try{
		
			IAppObjFacade ceFacade = currEnv.getAppObjFacade(ObjectType.CONTROLEXECUTION);
			IAppObjQuery ceQuery = ceFacade.createQuery();
			IAppObjIterator ceIterator = ceQuery.getResultIterator();
			
			while(ceIterator.hasNext()){
				
				IAppObj ceAppObj = ceIterator.next();
				IAppObj ceUpdAppObj = ceFacade.load(ceAppObj.getVersionData().getHeadOVID(), true);
				
				if(ceUpdAppObj.getGuid().equals(currAppObj.getGuid()))
					continue;
				
				String ceCtrlParentName = this.parentControl(ceUpdAppObj);
				
				if(!(currCtrlParentName.equals(ceCtrlParentName)))
					continue;
				
				this.copyEditableAttr(currAppObj, ceUpdAppObj);
				ceFacade.save(ceUpdAppObj, this.getDefaultTransaction(), true);
				ceFacade.releaseLock(ceUpdAppObj.getVersionData().getOVID());
				
				List<IAppObj> ceCtrlList = ceUpdAppObj.getAttribute(IControlexecutionAttributeType.LIST_CONTROL).getElements(this.getUserContext());
				for(IAppObj ceCtrlObj : ceCtrlList){
					this.affectResidualRisk(currEnv, ceCtrlObj, ra_control1line);
				}
				
			}
			ceQuery.release();
			
			List<IAppObj> currCtrlList = currAppObj.getAttribute(IControlexecutionAttributeType.LIST_CONTROL).getElements(this.getUserContext());
			for(IAppObj currCtrlObj : currCtrlList){
				this.affectResidualRisk(currEnv, currCtrlObj, ra_control1line);
			}
		
		}catch(Exception e){
			this.formModel.addControlInfoMessage(NotificationTypeEnum.INFO, e.getMessage(), new String[] { getStringRepresentation(this.formModel.getAppObj()) });
		}
		
	}
	
	private String parentControl(IAppObj childAppObj){
		
		String parentControlName = "";
		
		List<IAppObj> controlList = childAppObj.getAttribute(IControlexecutionAttributeType.LIST_CONTROL).getElements(this.getUserContext());
		for(IAppObj controlObj : controlList){
			parentControlName = controlObj.getAttribute(IControlAttributeType.ATTR_NAME).getRawValue();
		}
		
		return parentControlName;
		
	}
	
	private void copyEditableAttr(IAppObj sourceObj, IAppObj targetObj){
		
		List<IAttribute> attrList = sourceObj.getEditableAttributes(this.getUserContext());
		for(IAttribute attr : attrList){
			AppObjUtility.copyAttributeValue(sourceObj, targetObj, attr.getAttributeType());
		}
		
	}
	
	private void affectResidualRisk(IUIEnvironment currEnv, IAppObj controlAppObj, String testResult) throws Exception{
		
		IAppObjFacade riskFacade = currEnv.getAppObjFacade(ObjectType.RISK);
		IAppObjQuery riskQuery = riskFacade.createQuery();
		IAppObjIterator riskIterator = riskQuery.getResultIterator();
		
		//log.info("Resultado 1a Linha Controle: " + testResult);
		
		String controlID = controlAppObj.getAttribute(IControlAttributeType.ATTR_CONTROL_ID).getRawValue();
		//log.info("Parametro ControlID: " + controlID);
		
		try{
		
			while(riskIterator.hasNext()){
				
				IAppObj riskObj = riskIterator.next();
				IOVID riskOVID = riskObj.getVersionData().getHeadOVID();
				IAppObj riskUpdObj = riskFacade.load(riskOVID, true);
				riskFacade.allocateWriteLock(riskOVID);
				
				List<IAppObj> ctrlList = riskUpdObj.getAttribute(IRiskAttributeType.LIST_CONTROLS).getElements(this.getFullGrantUserContext());
				for(IAppObj ctrlObj : ctrlList){
					String ctrlID = ctrlObj.getAttribute(IControlAttributeType.ATTR_CONTROL_ID).getRawValue();
					//log.info("Parametro CtrlID: " + controlID);
					if(ctrlID.equals(controlID)){
						riskUpdObj.getAttribute(IRiskAttributeTypeCustom.ATTR_RA_CONTROL1LINE).setRawValue(testResult);
					}
				}
				//log.info(riskUpdObj);
				riskFacade.save(riskUpdObj, this.getDefaultTransaction(), true);
				riskFacade.releaseLock(riskOVID);
			}
			riskQuery.release();
		
		}catch(Exception e){
			throw e;
		}
		
	}*/

}
