/* 
Change attribute name  - Issue manager
22/03/2017 - Humberto de Lima
Projeto : GPRCA 
*/

 
$(document).ready(function(){

	var selected_type = $("[name='action_type'] option:selected").text();    
     

	if(selected_type === "Plano de Ação"){

      	$('#tr_name').find("th.CONTENT_ATTR_LABEL_WRITE").text('Plano de Ação');
      }	

});
 
/* 
Change attribute name  - Issue manager
22/03/2017 - Humberto de Lima
Projeto : GPRCA 
*/

 
$(document).ready(function(){

	var selected_type = $("[name='action_type'] option:selected").text();    
     

	if(selected_type === "Plano de Ação"){

      	$('#tr_name').find("th.CONTENT_ATTR_LABEL_WRITE").text('Plano de Ação');
      	$('#tr_issue_date').find("th.CONTENT_ATTR_LABEL_READ").text('Data do plano de ação');
      	$('#tr_obj_id').find("th.CONTENT_ATTR_LABEL_READ").text('ID do plano de ação');
      	$('#tr_creator').find("th.CONTENT_ATTR_LABEL_READ").text('Criador do plano de ação');
      	$('#tr_creator_status').find("th.CONTENT_ATTR_LABEL_WRITE").text('Status do plano de ação');
      	$('#tr_issueRelevantObjects').find("th.CONTENT_ATTR_LABEL_WRITE").text('Objetos relevantes para o plano de ação');
      	$('#tr_category').find("th.CONTENT_ATTR_LABEL_WRITE").text('Categoria do plano de ação');
      	$('#tr_owners').find("th.CONTENT_ATTR_LABEL_WRITE").text('Dono do plano de ação');
      	$('#tr_origemteste').find("th.CONTENT_ATTR_LABEL_WRITE").text('Origem do plano de ação');
      	$('#tr_reviewers').find("th.CONTENT_ATTR_LABEL_WRITE").text('Revisor do plano de ação');
      	
      }	;

});
 
