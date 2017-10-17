/* En keys guardo todos los componentes <span> */
var keys = document.querySelectorAll('#carga span');
var operators = ['+', '-', 'x', '/'];
var decimalAdded = false;

/* Carga de valores de Cuentas e Indicadores en el display */
$(".cuentas").change(function() {$(".screen").text($("div.screen").text() + "[" + $(".cuentas option:selected").text() + "]"); this.blur();});
$(".cuentas").focusout(function() {this.selectedIndex=0;});
$(".indicadores").change(function() {$(".screen").text($("div.screen").text() + "<" + $(".indicadores option:selected").text() + ">"); this.blur();});
$(".indicadores").focusout(function() {this.selectedIndex=0; });

/* Recorro todas las y monitoreo el evento onclick */
for(var i = 0; i < keys.length; i++) {
	keys[i].onclick = function(e) {
		// Get the input and button values
		var input = document.querySelector('.screen');
		var inputVal = input.innerHTML;
		var btnVal = this.innerHTML;
		
		// Now, just append the key values (btnValue) to the input string and finally use javascript's eval function to get the result
		if(btnVal == 'Limpiar') {
			input.innerHTML = '';
			decimalAdded = false;
		}
 
		else if(btnVal == 'Cargar') {
			var equation = inputVal;
			var lastChar = equation[equation.length - 1];
			
			// Replace all instances of x with *. This can be done easily using regex and the 'g' tag which will replace all instances of the matched character/substring
			equation = equation.replace(/x/g, '*');
			
			// Final thing left to do is checking the last character of the equation. If it's an operator or a decimal, remove it
			if(operators.indexOf(lastChar) > -1 || lastChar == '.')
				equation = equation.replace(/.$/, '');

			
//			if(equation)
//				input.innerHTML = eval(equation);
			if(equation) {
				var nombreIndicador = document.cargaForm.nombreIndicador.value.toUpperCase();
				if(nombreIndicador == '')
					alert("Debe completar el nombre del Indicador.");
				else if ($('.indicadores > option').get().some(o => o.value == nombreIndicador))
					alert("Ya existe un Indicador con ese nombre.");
				else  {
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", "formula");
		            hiddenField.setAttribute("value", equation);
		            document.cargaForm.appendChild(hiddenField);
		            document.cargaForm.submit();
				}
			}
			else
				alert("Debe completar la formula del Indicador.");				
			decimalAdded = false;
		}
		
		// Basic functionality of the calculator is complete. But there are some problems like 
		// 1. No two operators should be added consecutively.
		// 2. The equation shouldn't start from an operator except minus
		// 3. not more than 1 decimal should be there in a number
		
		// We'll fix these issues using some simple checks
		
		// indexOf works only in IE9+
		else if(operators.indexOf(btnVal) > -1) {
			// Operator is clicked
			// Get the last character from the equation
			var lastChar = inputVal[inputVal.length - 1];
			if(btnVal == '-') 
				btnVal += ' ';
			// Only add operator if input is not empty and there is no operator at the last
			if(inputVal != '' && operators.indexOf(lastChar) == -1) 
				input.innerHTML += btnVal;
			
			// Allow minus if the string is empty
			else if(inputVal == '' && btnVal == '- ') 
				input.innerHTML += btnVal;
			
			// Replace the last operator (if exists) with the newly pressed operator
			if(operators.indexOf(lastChar) > -1 && inputVal.length > 1) {
				// Here, '.' matches any character while $ denotes the end of string, so anything (will be an operator in this case) at the end of string will get replaced by new operator
				input.innerHTML = inputVal.replace(/.$/, btnVal);
			}
			
			decimalAdded =false;
		}
		
		// Now only the decimal problem is left. We can solve it easily using a flag 'decimalAdded' which we'll set once the decimal is added and prevent more decimals to be added once it's set. It will be reset when an operator, eval or clear key is pressed.
		else if(btnVal == '.') {
			if(!decimalAdded) {
				input.innerHTML += btnVal;
				decimalAdded = true;
			}
		}

		// if any other key is pressed, just append it
		else
			input.innerHTML += btnVal;
		// prevent page jumps
		e.preventDefault();
	} 
}
