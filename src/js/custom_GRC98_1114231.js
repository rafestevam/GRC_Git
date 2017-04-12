$(function(){
	console.log('Funcao JavaScript');
//	aam_objectcommand('customcachersk',null,'risk',null,'risk');

	var risk_id = $('#risk_id').val();
	var risk_name = $('#name').val();
	
	$('#tr_residualrisk_id').find("td.TEXTFIELD_WRITE").text(risk_id);
	$('#tr_residualrisk_name').find("td.TEXTFIELD_WRITE").text(risk_name);
	
	if($('#ra_result').val() === ''){
		$('#tr_residualra_result').find("td.TEXTFIELD_READ").text(unescape('N%E3o%20Avaliado'));
	}
	if($('#ra_control1line').val() === ''){
		$('#tr_residualra_control1line').find("td.TEXTFIELD_READ").text(unescape('N%E3o%20Avaliado'));
	}
	if($('#ra_control2line').val() === ''){
		$('#tr_residualra_control2line').find("td.TEXTFIELD_READ").text(unescape('N%E3o%20Avaliado'));
	}
	if($('#ra_control3line').val() === ''){
		$('#tr_residualra_control3line').find("td.TEXTFIELD_READ").text(unescape('N%E3o%20Avaliado'));
	}
	if($('#ra_controlfinal').val() === ''){
		$('#tr_residualra_controlfinal').find("td.TEXTFIELD_READ").text(unescape('N%E3o%20Avaliado'));
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
		ra_residual1line = 'N%E3o%20Avaliado';
	}
	if(ra_residual2line === undefined){
		ra_residual2line = 'N%E3o%20Avaliado';
	}
	if(ra_residual3line === undefined){
		ra_residual3line = 'N%E3o%20Avaliado';
	}
	if(ra_residualfinal === undefined){
		ra_residualfinal = 'N%E3o%20Avaliado';
	}
	
	$('#tr_residualra_residual1line').find("td.TEXTFIELD_READ").text(unescape(ra_residual1line));
	$('#tr_residualra_residual2line').find("td.TEXTFIELD_READ").text(unescape(ra_residual2line));
	$('#tr_residualra_residual3line').find("td.TEXTFIELD_READ").text(unescape(ra_residual3line));
	$('#tr_residualra_residualfinal').find("td.TEXTFIELD_READ").text(unescape(ra_residualfinal));
	

});

function classRiskRes(riskPotencial, control){
	var intControl       = escape(control);
	var intRiskPotencial = escape(riskPotencial);
	
	var cMuitoAlto = escape("Muito Alto");
	var cAlto      = escape("Alto");
	var cMedio     = "M%E9dio";
	var cBaixo     = escape("Baixo");
	
	if(intRiskPotencial === cMuitoAlto && intControl === cMuitoAlto){
		return 'Muito Alto';
	}
	if(intRiskPotencial === cMuitoAlto && intControl === cAlto){
		return 'Muito Alto';
	}
	if(intRiskPotencial === cMuitoAlto && intControl === cMedio){
		return 'Alto';
	}
	if(intRiskPotencial === cMuitoAlto && intControl === cBaixo){
		return cMedio;
	}
	if(intRiskPotencial === cAlto && intControl === cMuitoAlto){
		return 'Alto';
	}
	if(intRiskPotencial === cAlto && intControl === cAlto){
		return 'Alto';
	}
	if(intRiskPotencial === cAlto && intControl === cMedio){
		return cMedio;
	}
	if(intRiskPotencial === cAlto && intControl === cBaixo){
		return cMedio;
	}
	if(intRiskPotencial === cMedio && intControl === cMuitoAlto){
		return cMedio;
	}
	if(intRiskPotencial === cMedio && intControl === cAlto){
		return cMedio;
	}
	if(intRiskPotencial === cMedio && intControl === cMedio){
		return cMedio;
	}
	if(intRiskPotencial === cMedio && intControl === cBaixo){
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