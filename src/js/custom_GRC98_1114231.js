$(function(){
	console.log('Funcao JavaScript');
//	aam_objectcommand('customcachersk',null,'risk',null,'risk');

	var risk_id = $('#risk_id').val();
	var risk_name = $('#name').val();
	
	$('#tr_residualrisk_id').find("td.TEXTFIELD_WRITE").text(risk_id);
	$('#tr_residualrisk_name').find("td.TEXTFIELD_WRITE").text(risk_name);
	
	if($('#ra_control1line').val() === ''){
		$('#tr_residualra_control1line').find("td.TEXTFIELD_READ").text('Não Avaliado');
	}
	if($('#ra_control2line').val() === ''){
		$('#tr_residualra_control2line').find("td.TEXTFIELD_READ").text('Não Avaliado');
	}
	if($('#ra_control3line').val() === ''){
		$('#tr_residualra_control3line').find("td.TEXTFIELD_READ").text('Não Avaliado');
	}
	if($('#ra_controlfinal').val() === ''){
		$('#tr_residualra_controlfinal').find("td.TEXTFIELD_READ").text('Não Avaliado');
	}
	
	var ra_result = $('#ra_result').val();
	var ra_control1line = $('#ra_control1line').val();
	var ra_control2line = $('#ra_control2line').val();
	var ra_control3line = $('#ra_control3line').val();
	var ra_controlfinal = $('#ra_controlfinal').val();
	
	var ra_residual1line = classRiskRes(ra_result, ra_control1line);
	var ra_residual2line = classRiskRes(ra_result, ra_control2line);
	var ra_residual3line = classRiskRes(ra_result, ra_control3line);
	var ra_residualfinal = classRiskRes(ra_result, ra_controlfinal);
	
	if(ra_residual1line === undefined){
		ra_residual1line = 'Nao Avaliado';
	}
	if(ra_residual2line === undefined){
		ra_residual2line = 'Nao Avaliado';
	}
	if(ra_residual3line === undefined){
		ra_residual3line = 'Nao Avaliado';
	}
	if(ra_residualfinal === undefined){
		ra_residualfinal = 'Nao Avaliado';
	}
	
	$('#tr_residualra_residual1line').find("td.TEXTFIELD_READ").text(ra_residual1line);
	$('#tr_residualra_residual2line').find("td.TEXTFIELD_READ").text(ra_residual2line);
	$('#tr_residualra_residual3line').find("td.TEXTFIELD_READ").text(ra_residual3line);
	$('#tr_residualra_residualfinal').find("td.TEXTFIELD_READ").text(ra_residualfinal);
	

});

function classRiskRes(riskPotencial, control){
	if(riskPotencial === 'Muito Alto' && control === 'Muito Alto'){
		return 'Muito Alto';
	}
	if(riskPotencial === 'Muito Alto' && control === 'Alto'){
		return 'Muito Alto';
	}
	if(riskPotencial === 'Muito Alto' && control === 'Médio'){
		return 'Alto';
	}
	if(riskPotencial === 'Muito Alto' && control === 'Baixo'){
		return 'Médio';
	}
	if(riskPotencial === 'Alto' && control === 'Muito Alto'){
		return 'Alto';
	}
	if(riskPotencial === 'Alto' && control === 'Alto'){
		return 'Alto';
	}
	if(riskPotencial === 'Alto' && control === 'Médio'){
		return 'Médio';
	}
	if(riskPotencial === 'Alto' && control === 'Baixo'){
		return 'Médio';
	}
	if(riskPotencial === 'Médio' && control === 'Muito Alto'){
		return 'Médio';
	}
	if(riskPotencial === 'Médio' && control === 'Alto'){
		return 'Médio';
	}
	if(riskPotencial === 'Médio' && control === 'Médio'){
		return 'Médio';
	}
	if(riskPotencial === 'Médio' && control === 'Baixo'){
		return 'Baixo';
	}
}


/*
$(document).ready( function(){

	var ra_residual1line = $('#ra_residual1line').val();
	var ra_residual2line = $('#ra_residual2line').val();
	var ra_residual3line = $('#ra_residual3line').val();
	var ra_residualfinal = $('#ra_residualfinal').val();
	var ra_result = $('#ra_result').val();
	
	console.log('Passei no READY');
	
	if(ra_result === undefined || ra_result === ''){
		aam_objectcommand('customcachersk');
		return;	
	}
	if(ra_residual1line === undefined || ra_residual1line === '' || ra_residual1line === 'Não Avaliado'){
		aam_objectcommand('customcachersk');
		return;
	}
	if(ra_residual2line === undefined || ra_residual2line === '' || ra_residual2line === 'Não Avaliado'){
		aam_objectcommand('customcachersk');
		return;
	}
	if(ra_residual3line === undefined || ra_residual3line === '' || ra_residual3line === 'Não Avaliado'){
		aam_objectcommand('customcachersk');
		return;
	}
*/

/*	
	debugger;

	var risk_id = $('#risk_id').val();
	var risk_name = $('#name').val();
	
	$('#tr_residualrisk_id').find("td.TEXTFIELD_WRITE").text(risk_id);
	$('#tr_residualrisk_name').find("td.TEXTFIELD_WRITE").text(risk_name);

	aam_objectcommand('customcachersk');
	executed = true;
*/
//})

/*
$(document).ready( function(){
	aam_objectcommand('cache');
})

*/
//setTimeout(function(){ aam_objectcommand('cache'); }, 2000);