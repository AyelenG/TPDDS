/* En keys guardo todos los componentes <span> */
var keys = document.querySelectorAll('#carga span');
var operators = ['+', '-', 'x', '/'];
var decimalAdded = false;
var parentesis = 0;
var tokens = [];

/* Carga de valores de Cuentas e Indicadores en el display */
$(".cuentas").change(function() {
	tokens.push("[" + $(".cuentas option:selected").text() + "]");
	$(".screen").text(tokens.join('')); this.blur();});
$(".cuentas").focusout(function() {this.selectedIndex=0;});
$(".indicadores").change(function() {
	tokens.push("<" + $(".indicadores option:selected").text() + ">");
	$(".screen").text(tokens.join('')); this.blur();});
$(".indicadores").focusout(function() {this.selectedIndex=0; });

/* Recorro todas las y monitoreo el evento onclick */
for(var i = 0; i < keys.length; i++) {
	keys[i].onclick = function(e) {
		/* Obtengo el valor del botón presionado */
		var btnVal = this.innerHTML;
		/* Obtengo el ultimo token */
		var ultimo = tokens[tokens.length-1];
		
		if(btnVal == 'Limpiar') {
			tokens = [];
			decimalAdded = false;
		}
		
		/* Botón < para borrar el último token */
		else if(btnVal == '&lt;') {
			tokens.pop();
			decimalAdded = false;
		}
 
		else if(btnVal == 'Cargar') {
			var equation = tokens.join('');
			
			/* Reemplazo las 'x' por '*'. Utilizo la expresión regular y la 'g' reemplaza todas las apariciones */
			equation = equation.replace(/x/g, '*');
			
			/* Reemplazo los '-' por '- ' para que no rompa el parser */
			equation = equation.replace(/-/g, '- ');
			
			/* Remuevo el ultimo caracter de la formula si es un operador o un . decimal */
			if(operators.indexOf(ultimo) > -1 || ultimo == '.')
				equation = equation.replace(/.$/, '');
			
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
		
		/* Chequeos adicionales */ 
		// 1. No se pueden agregar dos operadores seguidos.
		// 2. La formula no puede empezar con un operador, salvo el signo '-'
		// 3. No se puede agregar mas de un . decimal a un numero
		
		/* Operadores */
		else if(operators.indexOf(btnVal) > -1) {

			var formulaVacia = tokens.length == 0 || ultimo == '(';
			var ultimoEsOperador = operators.indexOf(ultimo) > -1;

			/* Solo agrego operador si la cadena no esta vacia y el ultimo elemento no es un operador */
			if(!formulaVacia && !ultimoEsOperador)
				tokens.push(btnVal);
			
			/* Permito el signo negativo si la cadena esta vacia */
			else if(formulaVacia && btnVal == '-')
				tokens.push(btnVal);
			
			/* Si el ultimo elemento es un operador lo reemplazo por el nuevo */
			if(!formulaVacia && ultimoEsOperador) {
				tokens.pop();
				tokens.push(btnVal);
			}
			
			decimalAdded = false;
		}
		
		/* Solo dejo ingresar un punto decimal por numero utilizando un flag */
		else if(btnVal == '.') {
			if(!decimalAdded) {
				tokens.push(btnVal);
				decimalAdded = true;
			}
		}
		
		/* No permito cerrar parentesis que no hayan sido abiertos */
		else if(btnVal == '(') {
			tokens.push(btnVal);
			parentesis++;
		}

		else if(btnVal == ')') {
			if (parentesis > 0) {
				tokens.push(btnVal);
				parentesis--;
			}
		}
		
		/* Se agrega cualquier otro token */
		else
			tokens.push(btnVal);
		
		/* Agrego los tokens como un String en el display */
		$(".screen").text(tokens.join(''));
		
		/* Previene los saltos de pagina */
		e.preventDefault();
	} 
}
